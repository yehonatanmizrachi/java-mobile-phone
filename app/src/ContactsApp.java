package src;

import java.io.IOException;
import java.util.ArrayList;

// abstract class
public abstract class ContactsApp implements App{

	// Array that includes all the contacts.
	public static ArrayList<Contact> contacts = new ArrayList<Contact>();

	// method that removed all the content related to specific contact.
	// Implemented by the inheritance classes.
	public abstract void contactRemoved(Contact contact);
	public abstract void run() throws IOException;

	// search contact from the ArrayList
	protected Contact search(String name) {
		for (Contact contact : ContactsApp.contacts) { 
		    if (contact.getName().equals(name)) {	
		    	return contact;
		    }
		}
		return null;
	}

}
