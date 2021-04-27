package src;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DiaryApp extends ContactsApp {
	private Vector<DiaryEvent> events = new Vector<DiaryEvent>();
	
	@Override
	public void run() throws IOException {
		
		int input=0;
		
		while (input != 7) {		
			input = TestMobilePhone.SetStartingMenu("This is the Diary application\nPlease press:\n1- Add Event\n2- Remove Event\n3- Print all event in a specifiec date\n"
					+ "4- Print meetings with contact\n5- Delete overlapping events\n6- Print all events \n7- Exit\n", 8, 7);
			try {
			switch(input)
			{
				case 1:
					addEvent();
					break;
				case 2:
					removeEvent();
					break;
				case 3:
					printEventsOnDate();
					break;
				case 4:
					printEventsByContact();
					break;
				case 5:
					removeOverlapping();
					break;
				case 6:
					printAllEvents();
					break;
				case 7:
					break;
				default:
					JOptionPane.showMessageDialog(null,"Wrong input! Please try again.");
					break;
			}
			}
			catch (Exception error)
			{
				JOptionPane.showMessageDialog(null,"You did something wrong :(\nplease try again.");
			}
		}
	}
	
	private void removeOverlapping()
	{
		int length = events.size();
		Calendar cal1, cal2;
		for (int i = 0; i < length - 1; i++)
		{
			cal1 = Calendar.getInstance();
			cal2 = Calendar.getInstance();
			cal1.setTime(events.get(i).event_date);
			cal2.setTime(events.get(i + 1).event_date);
			
			if ((events.get(i).compareTo(events.get(i + 1)) == 0) || 
					(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
					 cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH) && ((events.get(i + 1).time_sec - events.get(i).time_sec)) < events.get(i).event_duration * 60))
			{
				events.remove(i + 1);
				length--;
				i--;
			}
		}
		JOptionPane.showMessageDialog(null,"Done.");
	}
	
	public void printEventsByContact()
	{
		String contact_name = JOptionPane.showInputDialog("Enter name of contact:");
		String s = "";
		
		for (DiaryEvent ev : events)
		{
			if (ev.getMissingDetail().equals(contact_name))
				s += "Event: \n" + ev.toString() + "\n\n";
		}
		if (s == "")
			JOptionPane.showMessageDialog(null,"There is no meetings with this contact!");
		else
			JOptionPane.showMessageDialog(null,s);
	}
	
	public void printAllEvents()
	{
		String s = "";
		int num = 1;
		for (DiaryEvent d : events)
		{
			s += "Event " + num + ":\n" +  d.toString() + "\n\n";
			num++;
		}
		if (events.size() == 0)
			s += "The diary is empty!";
		JOptionPane.showMessageDialog(null,s);
	}

	@Override
	public void contactRemoved(Contact contact) {
		int length = events.size();
		for (int i = 0; i < length; i++)
		{
			if (events.get(i).getMissingDetail().equals(contact.getName()))
			{
				events.remove(i);
				i--;
				length--;
			}
		}
	}
	
	private void addEvent()
	{
		String date1;
		String time1;
		String duration;
		String contact_name;
		String description;
		
		date1 = JOptionPane.showInputDialog("Enter date (format: \"day-month-year\"):");
		time1 = JOptionPane.showInputDialog("Enter time (format: \"hour:minute:seconds\"):");
		duration = JOptionPane.showInputDialog("Enter duration of event:");
		
		if (searchEvent(DiaryEvent.formatDate(date1, time1)) != -1)
		{
			JOptionPane.showMessageDialog(null,"You already have an event on that time!");
			return;
		}
		
		if (Integer.parseInt(duration) <= 0 || Integer.parseInt(duration) > 60)
		{
			JOptionPane.showMessageDialog(null,"The duration of the meeting must be in range [1,60]!");
			return;
		}
		
		int input = TestMobilePhone.SetStartingMenu("Enter:"
				+ "\n1- Add event with meeting\n2- Add event without meeting", 4, 3);
		
		DiaryEvent d = null;
		switch(input)
		{
		case 1:
			contact_name = JOptionPane.showInputDialog("Enter name of contact:");
			Contact c = this.search(contact_name);
			if (c == null)
				JOptionPane.showMessageDialog(null,"No such contact!");
			else
				d = new EventWithMeeting(date1, time1, Integer.parseInt(duration), c);
			break;
		case 2:
			description = JOptionPane.showInputDialog("Enter description for the event:");
			d = new EventWithoutMeeting(date1, time1, Integer.parseInt(duration), description);
			break;
		case 3:
			break;
		default:
			JOptionPane.showMessageDialog(null,"Wrong input!");
			return;
		}
		
		int i = 0;
		int length = events.size();
		while (i < length && events.get(i).compareTo(d) == -1)
			i++;
		
		if (length >= 1 && i != length && events.get(i).compareTo(d) == 0)
			JOptionPane.showMessageDialog(null,"The event is already exist!");
		else
			events.insertElementAt(d, i);
	}
	
	private void removeEvent()
	{
		String date1 = JOptionPane.showInputDialog("Enter date (format: \"day-month-year\"):");
		String time1 = JOptionPane.showInputDialog("Enter time (format: \"hour:minute:seconds\"):");
		int index = searchEvent(DiaryEvent.formatDate(date1, time1));
		if (index != -1)
			events.remove(index);
		else
			JOptionPane.showMessageDialog(null,"The event doesn't exist!");
	}
	
	public int searchEvent(Date d)
	{
		int length = events.size();
		for (int i = 0; i < length; i++)
			if (events.get(i).event_date.toString().equals(d.toString()))
				return i;
		return -1;
	}
	
	public void printEventsOnDate()
	{
		String meeting_date = JOptionPane.showInputDialog("Enter date (format: \"day-month-year\"):");
		
		String[] day_month_year = meeting_date.split("-");
		String s = "";
		for (DiaryEvent ev : events)
		{
			Calendar c = Calendar.getInstance();
			c.setTime(ev.event_date);
			if (c.get(Calendar.YEAR) == Integer.parseInt(day_month_year[2]) && 
					c.get(Calendar.MONTH) + 1 == Integer.parseInt(day_month_year[1]) && 
					c.get(Calendar.DAY_OF_MONTH) == Integer.parseInt(day_month_year[0]))
			{
				s += "Event:\n" +  ev.toString() + "\n\n";
			}
		}
		if (s == "") JOptionPane.showMessageDialog(null,"You don't have a meeting on this date!");
		else JOptionPane.showMessageDialog(null,s);
	}
}