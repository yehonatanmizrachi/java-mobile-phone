package src;

import java.util.ArrayList;

public abstract class contactsApp {
	
	public static ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	public abstract void remove(String name);
	
	// Search contact method
	public String search(String name) {
		String s = "";
		for (Contact contact : contactsApp.contacts) { 
		    if (contact.getName().equals(name)) {	
		    	s += contact.toString() + '\n';
		    }
		}
		return s;
		// If the return is -->  "" , it means that the contact doesn't exist.
	}

}
