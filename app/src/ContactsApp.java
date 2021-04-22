package src;

import java.util.ArrayList;

public abstract class ContactsApp {
	
	public static ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	public abstract void contactRemoved(Contact contact);

	// TODO: implement
	//	public Contact[] searchContact() {
	//		
	//	}
}
