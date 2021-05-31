package diary;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import src.ToolsFuncs;

import javax.swing.JOptionPane;

import phoneBook.Contact;
import src.ContactsApp;

public class DiaryApp extends ContactsApp {
	private Vector<DiaryEvent> events = new Vector<DiaryEvent>();
	
	// method for managing all the app's features
	@Override
	public void run() throws IOException {
		
		int input=0;
		
		while (input != 7) {		
			input = ToolsFuncs.SetStartingMenu("This is the Diary application\nPlease press:\n1- Add Event\n2- Remove Event\n3- Print all event in a specifiec date\n"
					+ "4- Print meetings with contact\n5- Delete overlapping events\n6- Print all events \n7- Exit\n", 8, 7,"DIARY");
			try {
			switch(input)
			{
				case 1: // add an event to the diary
					addEvent();
					break;
				case 2: // remove an event from the diary
					removeEvent();
					break;
				case 3: // print all events in a specific date
					ToolsFuncs.PrintScroll(printEventsOnDate());
					break;
				case 4: // print all events with a specific contact
					ToolsFuncs.PrintScroll(printEventsByContact());
					break;
				case 5: // remove events that overlapping
					removeOverlapping();
					JOptionPane.showMessageDialog(null,"Done.");
					break;
				case 6: // print all events
					ToolsFuncs.PrintScroll(getAppContent());
					break;
				case 7: // exit
					break;
				default: // wrong input
					ToolsFuncs.printError("Wrong input! Please try again.");
					break;
			}
			}
			catch (Exception error)
			{
				/* if the exception isn't was because cancel ("0") and the exception
				 is by the programmer (all of our exceptions start by '#' */
				if (!error.getMessage().equals("0") && error.getMessage().charAt(0) == '#')
					ToolsFuncs.printError(error.getMessage());
				// if the exception was caused by the system (like out of bounds)
				else if (!error.getMessage().equals("0") && error.getMessage().charAt(0) != '#')
					ToolsFuncs.printError("# You did something wrong :(");
			}
		}
	}
	
	// return all events in string format
	@Override
	public String getAppContent()
	{
		String s = "Diary app content:\n";
		int num = 1;
		for (DiaryEvent d : events)
		{
			s += "Event " + num + ":\n" +  d.toString() + "\n\n";
			num++;
		}
		if (events.size() == 0)
			s += "The diary is empty!\n";
		return s;
	}
	
	// remove all events with contact
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
	
	// print all events with contact
	private String printEventsByContact() throws Exception
	{
		if (events.size() == 0) throw new Exception("# The diary is empty!");
		String contact_name = JOptionPane.showInputDialog("Enter name of contact:");
		if (contact_name == null) { // if cancel was pressed
			throw new Exception("0");
		}
		String s = "";
		
		for (DiaryEvent ev : events) // search in the diary
		{
			if (ev.getMissingDetail().equals(contact_name)) // if the event is with this contact
				s += "Event: \n" + ev.toString() + "\n\n";
		}
		if (s == "") { // if the contact wasn't found
			throw new Exception("# There is no meetings with this contact!");
		}
		else // return the string with the events
			return s;
	}
	
	// search if there is an event in a specific date
	private int searchEvent(Date d)
	{
		int length = events.size();
		for (int i = 0; i < length; i++)
			if (events.get(i).event_date.toString().equals(d.toString()))
				return i;
		return -1;
	}
	
	// print all events in a specific date
	private String printEventsOnDate() throws Exception
	{
		if (events.size() == 0) throw new Exception("# the diary is empty!");
		String meeting_date = JOptionPane.showInputDialog("Enter date (format: \"day-month-year\"):");
		if (meeting_date == null) throw new Exception("0"); // if cancel was pressed
		
		String[] day_month_year = meeting_date.split("-");
		String s = "";
		for (DiaryEvent ev : events) // search in the diary
		{
			Calendar c = Calendar.getInstance();
			c.setTime(ev.event_date);
			// if the event is in this date
			if (c.get(Calendar.YEAR) == Integer.parseInt(day_month_year[2]) && 
					c.get(Calendar.MONTH) + 1 == Integer.parseInt(day_month_year[1]) && 
					c.get(Calendar.DAY_OF_MONTH) == Integer.parseInt(day_month_year[0]))
			{
				s += "Event:\n" +  ev.toString() + "\n\n";
			}
		}
		if (s == "") { // if there is no events on that date
			throw new Exception("# You don't have a meeting on this date!");
		}
		else return s; // print the events
	}
	
	// add event to the diary
	private void addEvent() throws Exception
	{
		String date1;
		String time1;
		String duration;
		String contact_name;
		String description;
		// labels for printing on the screen for each input
		String[] labels = {"Enter date (format: \"day-month-year\"):", "Enter time (format: \"hour:minute:seconds\"):",
				"Enter duration of event:"};
		String[] inputs = ToolsFuncs.multipleInputsWindow(true, labels, "Enter Date's content");
		if (inputs == null) throw new Exception("0"); // there was an invalid input
		
		String[] valids = {"date", "time", "duration"};
		checkInputValidation(inputs, valids); // check input validation
		date1 = inputs[0];
		time1 = inputs[1];
		duration = inputs[2];
		
		// if the event is already in the diary
		if (searchEvent(DiaryEvent.formatDate(date1, time1)) != -1)
			throw new Exception("# You already have an event on that time!");
		
		int input = ToolsFuncs.SetStartingMenu("Enter:"
				+ "\n1- Add event with meeting\n2- Add event without meeting", 4, 3,"MEETING");
		if (input == 3) throw new Exception("0"); // if cancel was pressed
		
		DiaryEvent d = null;
		switch(input)
		{
		case 1: // meeting with contact
			contact_name = JOptionPane.showInputDialog("Enter name of contact:");
			if (contact_name == null) throw new Exception("0");
			Contact c = this.search(contact_name);
			if (c == null) // the contact doesn't exists
				throw new Exception("# No such contact!");
			else // make a meeting
				d = new EventWithMeeting(date1, time1, Integer.parseInt(duration), c);
			break;
		case 2: // meeting without contact
			description = JOptionPane.showInputDialog("Enter description for the event:");
			if (description == null) throw new Exception("0"); // if cancel was pressed
			d = new EventWithoutMeeting(date1, time1, Integer.parseInt(duration), description);
			break;
		case 3: // exit
			break;
		default: // wrong input
			ToolsFuncs.printError("Wrong input!");
			return;
		}
		
		// put the event in the diary by order
		int i = 0;
		int length = events.size();
		while (i < length && events.get(i).compareTo(d) == -1)
			i++;
		
		if (length >= 1 && i != length && events.get(i).compareTo(d) == 0) // if the event is already exists
			throw new Exception("# The event is already exist!");
		else
			events.insertElementAt(d, i); // insert new event to the diary in the right place
	}
	
	// remove events that overlapping
	private void removeOverlapping()
	{
		int length = events.size();
		Calendar cal1, cal2;
		for (int i = 0; i < length - 1; i++) // move over all the events
		{
			cal1 = Calendar.getInstance();
			cal2 = Calendar.getInstance();
			cal1.setTime(events.get(i).event_date); // get date of event1
			cal2.setTime(events.get(i + 1).event_date); // get date of event2
			
			// if event1 and event2 has the same date
			if ((events.get(i).compareTo(events.get(i + 1)) == 0) || 
					(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
					 cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH) && ((events.get(i + 1).time_sec - events.get(i).time_sec)) < events.get(i).event_duration * 60))
			{
				events.remove(i + 1);
				length--;
				i--;
			}
		}
	}
	
	// remove an event
	private void removeEvent() throws Exception
	{
		if (events.size() == 0) throw new Exception("# The diary is empty!");
		// print window with multiple inputs
		String[] labels = {"Enter date (format: \"day-month-year\"):", "Enter time (format: \"hour:minute:seconds\"):"};
		String[] inputs = ToolsFuncs.multipleInputsWindow(false, labels, "Enter Date's content");
		if (inputs == null) throw new Exception("0"); // if there was an invalid input
		
		String date1 = inputs[0];
		String time1 = inputs[1];
		int index = searchEvent(DiaryEvent.formatDate(date1, time1));
		if (index != -1)
			events.remove(index);
		else // if the event doesn't exist
			throw new Exception("# The event doesn't exist!");
	}
	
	// gets inputs and the formats of the inputs and checks there validation
	private void checkInputValidation(String[] inputs, String[] formats) throws Exception
	{
		int length = inputs.length;
		for (int i = 0; i < length; i++) // move on all inputs
		{
			if (formats[i].equals("date")) // if we check date format
			{
				String[] day_month_year = inputs[i].split("-");
				if (day_month_year.length != 3) throw new Exception("# Not enough arguments for date!");
				if (Integer.parseInt(day_month_year[0]) < 1 || Integer.parseInt(day_month_year[0]) > 31)
					throw new Exception("# days must be in range of [1,31]!");
				if (Integer.parseInt(day_month_year[1]) < 1 || Integer.parseInt(day_month_year[1]) > 12)
					throw new Exception("# months must be in range of [1,12]!");
				if (Integer.parseInt(day_month_year[2]) < 1)
					throw new Exception("# years must be greater then 0!");
			}
			else if (formats[i].equals("time")) // if we check time format
			{
				String[] hour_minute_second = inputs[i].split(":");
				if (hour_minute_second.length != 3) throw new Exception("# Not enough arguments for time!");
				if (Integer.parseInt(hour_minute_second[0]) < 0 || Integer.parseInt(hour_minute_second[0]) > 23)
					throw new Exception("# hours must be in range of [0,23]!");
				if (Integer.parseInt(hour_minute_second[1]) < 0 || Integer.parseInt(hour_minute_second[1]) > 59)
					throw new Exception("# minutes must be in range of [0,59]!");
				if (Integer.parseInt(hour_minute_second[2]) < 0 || Integer.parseInt(hour_minute_second[2]) > 59)
					throw new Exception("# seconds must be in range of [0,59]!");
			}
			else if (formats[i].equals("duration")) // if we check duration format
			{
				// check if the duration is between [1,60]
				if (Integer.parseInt(inputs[i]) <= 0 || Integer.parseInt(inputs[i]) > 60)
					throw new Exception("# The duration of the meeting must be in range [1,60]!");
			}
		}
	}
}