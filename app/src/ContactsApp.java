package src;

import java.util.ArrayList;

public abstract class ContactsApp {
	
	public static ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	public abstract void contactRemoved(Contact contact);

	// TODO: Rotem
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
