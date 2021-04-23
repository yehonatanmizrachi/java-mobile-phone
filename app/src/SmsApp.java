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
		while (input != 6) {
			
			String s = JOptionPane.showInputDialog("This is the SMS application\nPlease press:\n1- Add message to contact\n2- Delete messages wtih contact\n"
					+ "3- Print all messages with specific contact\n4- Print all contacts that has your sentense in thier messages\n5- Print all messages\n"
					+ "6- Exit\n");
			input = Integer.parseInt(s);
			switch(input)
			{
				case 1:
					name = JOptionPane.showInputDialog("Enter contact`s name\n");
					if(name == null) {break;}
					message = JOptionPane.showInputDialog("Enter message to add\n");
					if(message == null) {break;}		
					if(this.addSMStoContact(this.search(name), message))
					{
						JOptionPane.showMessageDialog(null,"SMS has added successfully");
					}
					else {JOptionPane.showMessageDialog(null,"Sorry, couldn`t complete your request");}
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
				case 6:
					break;
				// wrong input
			}
		}
	}
		
	

	@Override
	public void contactRemoved(Contact contact) {
       // run over allSMS and delete if the contact asked to remove has sms`s in app.
       for (Map.Entry<Contact,String> entry : allSMS.entrySet()) {
		    if (entry.getKey().equals(contact)) {	
				allSMS.remove(contact);
		    }
       }
	}
	
	
	public Boolean addSMStoContact(Contact contact,String SMS) {
		if (contact == null) {
			return false;
		}
		if (!(allSMS.containsKey(contact))) {
			allSMS.put(contact,SMS);
			return true;
		}
		else {
			String oldValue = allSMS.get(contact);
			allSMS.put(contact,oldValue+"\n"+SMS);
			}
		return true;
		}
	
	
	public String printSMS(Contact contact) {
	    if (!allSMS.containsKey(contact)) {
	    	return "There are no message with this Contact";
	    }
	    else {
		    return allSMS.get(contact);
	    }
	}
	
	
	
	
}
