package src;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ContactsApp implements App{

	public static ArrayList<Contact> contacts = new ArrayList<Contact>();

	public abstract void contactRemoved(Contact contact);
	public abstract void run() throws IOException;

	protected Contact search(String name) {
		for (Contact contact : ContactsApp.contacts) { 
		    if (contact.getName().equals(name)) {	
		    	return contact;
		    }
		}
		return null;
	}

}
