package src;

import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PhoneBookApp extends ContactsApp {

	private ContactsApp[] contactsApps;
	
	public PhoneBookApp(ContactsApp[] contactsApps) {
		this.contactsApps = contactsApps;
	}

	@Override
	public String getAppContent() {
		String allContent = "Contacts app content: \n";
		if(ContactsApp.contacts.isEmpty()) {
			allContent = allContent + "Phone book is empty";
		}
		else {
			allContent = allContent + this.toString();
		}
		return allContent;
	}

	private void addContact(String name, String phoneNumber) {
		// Check if the contact already exist
		Contact check = this.search(name);
		if(check != null) {
			ToolsFuncs.printError(check.getName()+" is already exists in phone book"); 
		}
		else {
			Contact newContact = new Contact(name, phoneNumber);
			ContactsApp.contacts.add(newContact);
		}
	}

	// get the contacts[i] 
	private Contact getContact(int i) {
		if(ContactsApp.contacts.size() > 0) {
			return ContactsApp.contacts.get(i);
		}
		else {
			return null;
		}
	}
	

	private void remove(String name) {

		// remove the first occurrence
		Contact removedContact = null;
		for (Contact contact : ContactsApp.contacts) { 
		    if (contact.getName().equals(name)) {
		    	ContactsApp.contacts.remove(contact);
		    	removedContact = contact;
		    	break;
		    }
		}

		if (removedContact == null) {
			ToolsFuncs.printError("The user " + name + " doesn't exist!");
			return;
		}
		
		// remove all its related information from the contacts applications
		for (ContactsApp app: this.contactsApps) {
			app.contactRemoved(removedContact);
		}

	}

	@Override
	public void contactRemoved(Contact contact) {
		// Do nothing
	}

	/* Search contact in phoneBook by name.
	 * If the contact is exist, it will print all its occurrences. */
	private void searchContact(String name)
	{
		Contact foundcontact = super.search(name);
		if (foundcontact == null)
			ToolsFuncs.printError("The contact " + name + " doesn't exist!");
		else {
			JOptionPane.showMessageDialog(null, foundcontact);
		}
	}
	
	private void sortByName() {
		Collections.sort(ContactsApp.contacts);
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "The total number of contacts: "+ContactsApp.contacts.size()+"\n";
		for (Contact contact : ContactsApp.contacts)
		{ 
		    str += contact.toString()+ "\n";
		}
		return str;
	}
	
	// method that check if the Phone Book is empty or not 
	private boolean isEmpty() {
		if(ContactsApp.contacts.isEmpty()) {
			ToolsFuncs.printError("Your phone book is empty!");
			return true;
		}
		return false;
	}
	
	// print phone book method
	private void printPhoneBook() {
		if(!this.isEmpty()) {
			ToolsFuncs.PrintScroll(this.toString());
		}
	}
	
	// swap phone book method
	private void swapPhoneBook() {
		int j = 0; 
		if(!isEmpty()) {
			// for loop that swap the i contact and the j contact in the arrayList
			// i is running from the end to the beginning
			// j is running from the beginning to the end
			for(int i = (ContactsApp.contacts.size() - 1) ; i >j; i--) {
				ToolsFuncs.swapContacts(ContactsApp.contacts,i,j); 
				j++;
			}
		}
	}
		
	
	// method sort numeric
	private void sortNumeric() {
		if(!isEmpty()) {
			QuicksortBS(ContactsApp.contacts,0,(ContactsApp.contacts.size()-1)); // calling method QuicksortBS
		}
	}

	// implement sort method that sort from big to small
	// the sort is based on Quicksort
	// Average running time - O(nlogn)
	private void QuicksortBS(ArrayList<Contact> contacts,int p,int r) {
		if(p<r) {
			int q = PartitionBS(contacts,p,r);
			QuicksortBS(contacts,p,q-1); 
			QuicksortBS(contacts,q+1,r);
		}
	}
	// implement PartitionBS function - this function is part of the QuicksortBS algorithm
	// The function is making the sort based on the pivot - r
	// and returning the - q 
	private int PartitionBS(ArrayList<Contact> contacts,int p,int r) {
		Long x = contacts.get(r).getNumericVal();
		int i = p-1;
		for(int j = p; j<=r-1;j++) {
			if(contacts.get(j).getNumericVal() > x) {
				i = i+1;
				ToolsFuncs.swapContacts(contacts,i,j);
			}
		}
		ToolsFuncs.swapContacts(contacts,i+1,r);
		return i+1;
	}
		
	//add all contacts to a file named by the user
	private void toFile(String fileName) throws IOException {
		//open a writer
		FileWriter myWriter = new FileWriter(fileName + ".txt");
		//run over all contacts in the phonebook and write them to file
		for (Contact contact : ContactsApp.contacts) {
		    myWriter.write(contact.getName() + ' ' + contact.getPhoneNumber() + '\n');
		}
	    myWriter.close();
	}

	//read all contacts from a file named by user
	private void fromFile(String fileName) throws IOException {
		//open the file the user told us
        String userDirectory = new File(fileName).getAbsolutePath();
		File file = new File(userDirectory +".txt");
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine()) {
			String str = scan.nextLine();
			String[] strArray = str.split(" ",2);
			addContact(strArray[0],strArray[1]);
        }

		scan.close();
	}
	
	@Override
	public void run() throws IOException {
		String name;
		String phoneNumber;
		int input = 0;
		
		while (input != 10)
		{
			ImageIcon contactsPic = ToolsFuncs.getMainPicture("Pic/CONTACTS.png",80,80);
			String questions = "Contacts\nPress:\n1- Add contact\n"
					+ "2- Delete contact\n3- Print phone book\n"
					+ "4- Search contact\n5- Sort by name\n6- Sort by phone number\n7- Reverse phonebook\n"
					+ "8- Export phone book to text file\n9- Import phone book from text file\n10- Exit";
			String s = (String) JOptionPane.showInputDialog(null, questions, "CONTACTS", JOptionPane.INFORMATION_MESSAGE, contactsPic, null,null );
			

			if(s != null) {
				try {
					input = Integer.parseInt(s);
				}
				// invalid input
				catch(Exception e){
					input = 12;
				}
			}
			// cancel
			else {input = 10;}

			switch(input)
			{
			// add contact
			case 1:
				name = JOptionPane.showInputDialog("Enter full name\n");
				if(name == null) {break;}
				phoneNumber = JOptionPane.showInputDialog("Enter Phone number\n");
				if(phoneNumber == null) {break;}
				
				String check = phoneNumber.replaceFirst("-", "");
				try {
					Long.parseLong(check);
					this.addContact(name, phoneNumber);
				}
				catch(Exception e){
					ToolsFuncs.printError("Invalid phone number!");
				}
				break;
			// remove contact
			case 2:
				if(!this.isEmpty()) {
					name = JOptionPane.showInputDialog("Enter full name\n");
					if(name == null) {break;}
					this.remove(name);
					break;
				}
				break;
			// print phone book
			case 3:
				if(!this.isEmpty()) {
					this.printPhoneBook();
					break;
				}
				break;
			// search contact by name				
			case 4:
				if(!this.isEmpty()) {
				name = JOptionPane.showInputDialog("Enter full name\n");
				if(name == null) {break;}
				this.searchContact(name);
				break;
				}
				break;
			// sort phone book by name				
			case 5:
				if(!this.isEmpty()) {
				this.sortByName();
				JOptionPane.showMessageDialog(null,"The phone Book has been sorted by increasing name.");
				break;
				}
				break;
			// sort phone book by phone number
			case 6:
				if(!this.isEmpty()) {
				this.sortNumeric();
				JOptionPane.showMessageDialog(null,"The phone Book has been sorted by decreasing phone Number.");
				break;
				}
				break;
			// reverse phone book
			case 7:
				if(!this.isEmpty()) {
				this.swapPhoneBook();
				JOptionPane.showMessageDialog(null,"The Phone Book has been successfully reversed.");
				break;
				}
				break;
			// export phone book to text file
			case 8:
				name = JOptionPane.showInputDialog("Enter name of text file:");
				if(name == null) {break;}
				this.toFile(name);
				JOptionPane.showMessageDialog(null,"The Phone Book has been successfully exported to the file " + name + ".");
				break;
			// import phone book from text file 
			case 9:
				name = JOptionPane.showInputDialog("Enter name of text file:");
				if(name == null) {break;}
				try {
					this.fromFile(name);
					JOptionPane.showMessageDialog(null,"The Phone Book has been successfully imported from the file " + name + ".");
				}
				catch (Exception e) {
					ToolsFuncs.printError(e.getMessage());
				}
				break;
			// exit
			case 10:
				break;
			// wrong input
			default:
				ToolsFuncs.printError("Wrong input! Please try again.");
				break;
			}
		}
	}
		
}
