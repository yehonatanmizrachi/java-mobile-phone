package src;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class DiaryApp extends ContactsApp {
	private Map<Date,DiaryEvent> events = new HashMap<Date,DiaryEvent>();
	
	@Override
	public void run() throws IOException {
		int input=0;
		
		while (input != 7) {		
			input = TestMobilePhone.SetStartingMenu("This is the Diary application\nPlease press:\n1- Add Event\n2- Remove Event\n3- Print all event in a specifiec date\n"
					+ "4- Print meetings with contact\n5- Delete overlapping events\n6- Print all events \n7- Exit\n", 8, 7);

			switch(input)
			{
				case 1:
					
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				default:
					JOptionPane.showMessageDialog(null,"Wrong input! Please try again.");
					break;
			}
		}
	}

	@Override
	public void contactRemoved(Contact contact) {
	
	}
	
	public void addEvent()
	{
		String date1;
		String time1;
		String contact_name;
		String description;
		int input = TestMobilePhone.SetStartingMenu("Enter:"
				+ "\n1- Add event with meeting\n2- Add event without meeting", 4, 3);
		
		contact_name = JOptionPane.showInputDialog("Enter name of contact:");
		date1 = JOptionPane.showInputDialog("Enter date (format: \"day-month-year\"):");
		time1 = JOptionPane.showInputDialog("Enter time (format: \"hour:minute:seconds\"):");
		
		switch(input)
		{
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			break;
		default:
			JOptionPane.showMessageDialog(null,"Wrong input!");
			break;
		}
		
		events.put(ev.event_date, ev);
	}
	
	public void removeEvent(DiaryEvent ev)
	{
		events.remove(ev.event_date);
	}
}
