package src;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ContactsApp implements App{
	
	public static ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	public abstract void contactRemoved(Contact contact);
	public abstract void run() throws IOException;

	// TODO: Rotem
	public String search(String name) {
		String s = "";
		for (Contact contact : ContactsApp.contacts) { 
		    if (contact.getName().equals(name)) {	
		    	s += contact.toString() + '\n';
		    }
		}
		return s;
		// If the return is -->  "" , it means that the contact doesn't exist.
	}

}
