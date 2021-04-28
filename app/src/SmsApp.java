package src;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class SmsApp extends ContactsApp {

	
	Map<Contact,String> allSMS = new HashMap<Contact,String>();
	
	
	@Override
	public void run() throws IOException {
		int input=0;
		String name;
		String message;
		String sentence;
		while (input != 6) {
			
			String s = JOptionPane.showInputDialog("This is the SMS application\nPlease press:\n1- Add message to contact\n2- Delete messages with contact\n"
					+ "3- Print all messages with specific contact\n4- Print all contacts that has your sentense in thier messages\n5- Print all messages\n"
					+ "6- Exit\n");
		
			if(s != null) {
				try {
					input = Integer.parseInt(s);
				}
				// invalid input
				catch(Exception e){
					input = 7;
				}
			}
			else {input = 6;}
			
			switch(input)
			{
				case 1:
					name = JOptionPane.showInputDialog("Enter contact`s name\n");
					if(name == null) {break;}
					message = JOptionPane.showInputDialog("Enter message to add\n");
					if(message == null) {break;}		
					JOptionPane.showMessageDialog(null,this.addSMStoContact(this.search(name), message));
					break;
				case 2:
					name = JOptionPane.showInputDialog("Enter contact`s name\n");
					if(name == null) {break;}
					this.contactRemoved(this.search(name));
					break;
				case 3:
					name = JOptionPane.showInputDialog("Enter contact`s name\n");
					if(name == null) {break;}
					JOptionPane.showMessageDialog(null,this.printSMS(this.search(name)));
					break;
				case 4:
					sentence = JOptionPane.showInputDialog("Enter sentence to search\n");
					if(sentence == null) {break;}
					JOptionPane.showMessageDialog(null,this.printNames(sentence));
					break;
				case 5:
					JOptionPane.showMessageDialog(null,this.printAllSMS());
					break;
				case 6:
					break;
				default:
					JOptionPane.showMessageDialog(null,"Wrong input! Please try again.");
					break;
			}
		}
	}
		
	

	@Override
	public void contactRemoved(Contact contact) {
			allSMS.remove(contact);
	}
	
	
	public String addSMStoContact(Contact contact,String SMS) {
		if (contact == null) {
			return "Sorry, there is not contact with this name";
		}
		if (SMS.equals(""))
			return "Sorry, SMS must have something in it"; 
		if (!(allSMS.containsKey(contact))) {
			allSMS.put(contact,SMS);
			return "SMS has added successfully";
		}
		else {
			String oldValue = allSMS.get(contact);
			allSMS.put(contact,oldValue+"\n"+SMS);
			}
		return "You guys talking a lot I see.. SMS has added";
		}
	
	
	public String printSMS(Contact contact) {
	    if (!allSMS.containsKey(contact)) {
	    	return "There are no message with this Contact";
	    }
	    else {
		    return allSMS.get(contact);
	    }
	}
	
	public String printNames(String text) {
		String names = "";
	    for (Map.Entry<Contact,String> entry : allSMS.entrySet()) {
	    	if(entry.getValue().contains(text))
	    		names += entry.getKey().getName() + "\n";
	    }
	    if (names == "")
	    	return "There are no contacts with your text";
		return "This contacts has your text in thier sms:\n" + names;
	}


	public String printAllSMS() {
		String allMessages = "";
		String name = "";
		if (allSMS.isEmpty())
			return "There are no messages";
	    for (Map.Entry<Contact,String> entry : allSMS.entrySet()) {
	    	name = entry.getKey().getName();
	        allMessages += name+ ":\n" + entry.getValue() + "\n";
	    }
		return allMessages;
	}
	
	
	
}
