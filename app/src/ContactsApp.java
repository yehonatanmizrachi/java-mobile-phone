package src;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ContactsApp implements App{
	
	public static ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	public abstract void contactRemoved(Contact contact);
	public abstract void run() throws IOException;

	@SuppressWarnings("null")
	public ArrayList<Contact> search(String name) {
		ArrayList<Contact> foundcontact = null;
		for (Contact contact : ContactsApp.contacts) { 
		    if (contact.getName().equals(name)) {	
		    	//s += contact.toString() + '\n';
		    	foundcontact.add(contact);
		    }
		}
		return foundcontact;
		// If the return is -->  "" , it means that the contact doesn't exist.
	}

}
