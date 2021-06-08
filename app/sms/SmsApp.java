package sms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import phoneBook.Contact;
import src.App;
import src.ContactsApp;
import src.ToolsFuncs;;
public class SmsApp extends ContactsApp {

	//Map of all SMS with their contacts
	private Map<Contact,String> allSMS = new HashMap<Contact,String>();

	
	@Override
	public void run() throws IOException {
		int input=0;
		String name;
		String message;
		String sentence;
		while (input != 6) {
			
			//menu
			input = ToolsFuncs.SetStartingMenu("This is the SMS application\nPlease press:\n1- Add message to contact\n2- Delete messages with contact\n"
					+ "3- Print all messages with specific contact\n4- Print all contacts that has your sentense in thier messages\n5- Print all messages\n"
					+ "6- Exit\n",7,6,"SMS");
			try {
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
						JOptionPane.showMessageDialog(null,this.removeContact(this.search(name)));
						break;
					case 3:
						name = JOptionPane.showInputDialog("Enter contact`s name\n");
						if(name == null) {break;}
						ToolsFuncs.PrintScroll(this.printSMS(this.search(name)));
						break;
					case 4:
						sentence = JOptionPane.showInputDialog("Enter sentence to search\n");
						if(sentence == null) {break;}
						ToolsFuncs.PrintScroll(this.printNames(sentence));
						break;
					case 5:
						ToolsFuncs.PrintScroll(PrintAll());
						break;
					case 6:
						break;
					default:
						ToolsFuncs.printError("Wrong input! Please try again.");
						break;
				}
			}
			catch (Exception e) {
				ToolsFuncs.printError(e.getMessage());
			}
		}
		App.phone.returnToPhone();

	}

	
	//remove contact from map
	@Override
	public void contactRemoved(Contact contact) {
		allSMS.remove(contact);
	}
	
	private String removeContact(Contact contact) throws Exception{
		if (contact == null) {
			throw new Exception("The contact doesn't exist");
		}

		allSMS.remove(contact);
		return "The contact" + contact.getName() + "removed successfully";
	}
	
	private String addSMStoContact(Contact contact,String SMS) throws Exception{
		if (contact == null) {
			throw new Exception("Sorry, there is not contact with this name");
		}
		// ignore empty SMS
		if (SMS.equals(""))
			return "Sorry, SMS must have something in it"; 
		// if contact is not exist already add his SMS to map
		if (!(allSMS.containsKey(contact))) {
			allSMS.put(contact,SMS);
			return "SMS has added successfully";
		}
		// contact already exist, so add new SMS to the old SMS 
		else {
			String oldValue = allSMS.get(contact);
			allSMS.put(contact,oldValue+"\n"+SMS);
			}
		return "You guys talking a lot.. SMS has added";
		}
	
	
	private String printSMS(Contact contact) throws Exception{
		//if contact is not in map -> no SMS with him
	    if (!allSMS.containsKey(contact)) {
	    	throw new Exception("There are no message with this contact");
	    }
	    else {
		    return allSMS.get(contact);
	    }
	}
	
	private String printNames(String text) throws Exception{
		String names = "";
		//iterate over map
	    for (Map.Entry<Contact,String> entry : allSMS.entrySet()) {
	    	//if value(SMS) contains the text we add its key(contact) to the string of names
	    	if(entry.getValue().contains(text))
	    		names += entry.getKey().getName() + "\n";
	    }
	    if (names == "") {
	    	throw new Exception("There are no contacts with your text");
	    }
		return "This contacts has your text in thier sms:\n" + names;
	}

	
	private String PrintAll() throws Exception {
		String allMessages = "";
		String name = "";
		//if map is empty = no SMS
		if (allSMS.isEmpty()) {
			throw new Exception("The SMS app is empty");
		}
		//iterate over map and and print all keys+values (contact+SMS)
	    for (Map.Entry<Contact,String> entry : allSMS.entrySet()) {
	    	name = entry.getKey().getName();
	        allMessages += name+ ":\n" + entry.getValue() + "\n";
	    }
		return allMessages;
	}
	
	@Override
	public String getAppContent()
	{
		String s = "SMS app content:\n";
		try {
			s = s + PrintAll() + "\n";
		}
		catch (Exception e) {
			s = s + e.getMessage() + "\n";
		}
		return s;
	}
	
	
}
