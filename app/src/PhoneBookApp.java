package src;

import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class PhoneBookApp extends ContactsApp {

	private ContactsApp[] contactsApps;
	
	public PhoneBookApp(ContactsApp[] contactsApps) {
		this.contactsApps = contactsApps;
	}

	@Override
	public String getAppContent() {
		String allContent = "Contacts: \n";
		if(ContactsApp.contacts.isEmpty()) {
			allContent = allContent + "Phone book is Empty"+ "\n";
		}
		else {
			allContent = allContent + this.toString();
		}
		return allContent;
	}

	public void addContact(String name, String phoneNumber) {
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
	public Contact getContact(int i) {
		if(ContactsApp.contacts.size() > 0) {
			return ContactsApp.contacts.get(i);
		}
		else {
			return null;
		}
	}
	

	public void remove(String name) {

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
			JOptionPane.showMessageDialog(null, "The user " + name + " doesn't exist!");
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
	public void searchContact(String name)
	{
		Contact foundcontact = super.search(name);
		if (foundcontact == null)
			ToolsFuncs.printError("The contact " + name + " doesn't exist!");
		else {
			JOptionPane.showMessageDialog(null, foundcontact);
		}
	}
	
	
	/* Function that sorts the array in lexicographically by names.
	 * Uses merge-sort.
	 * Average running time - O(nlogn)*/
	public void sortByName()
	{
		if (ContactsApp.contacts.size() > 1)
			mergeSort(0, ContactsApp.contacts.size() - 1);
	}
	
	// Split the array to two parts from the middle, sort every part and merge them at the end.
	private void mergeSort(int l, int r)
	{
		if (l < r)
		{
			int q = (l + r) / 2;
			mergeSort(l, q);
			mergeSort(q + 1, r);
			merge(l, q, r);
		}
	}
	
	// Get two sorted arrays and combined them into one array.
	// Using merge algorithm
	private void merge(int l, int q, int r)
	{
		ArrayList<Contact> temp1 = new ArrayList<Contact>();
		ArrayList<Contact> temp2 = new ArrayList<Contact>();
		
		int len1 = q - l + 1;
		int len2 = r - (q + 1) + 1;
		
		for (int i = l; i < q + 1; i++)
			temp1.add(new Contact(ContactsApp.contacts.get(i)));
		for (int i = q + 1; i < r + 1; i++)
			temp2.add(new Contact(ContactsApp.contacts.get(i)));
		
		int i = l;
		int i1 = 0;
		int i2 = 0;
		while (i1 < len1 && i2 < len2)
		{
			// Compare names by alphabetical order
			if (temp1.get(i1).getName().compareToIgnoreCase(temp2.get(i2).getName()) <= 0)
			{
				ContactsApp.contacts.add(i, temp1.get(i1));
				i1++;
			}
			else
			{
				ContactsApp.contacts.add(i, temp2.get(i2));
				i2++;
			}
			
			ContactsApp.contacts.remove(i + 1);
			i++;
		}
		
		while (i1 < len1)
		{
			ContactsApp.contacts.add(i, temp1.get(i1));
			ContactsApp.contacts.remove(i + 1);
			i1++;
			i++;
		}
		while (i2 < len2)
		{
			ContactsApp.contacts.add(i, temp2.get(i2));
			ContactsApp.contacts.remove(i + 1);
			i2++;
			i++;
		}
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
	public boolean isEmpty() {
		if(ContactsApp.contacts.isEmpty()) {
			ToolsFuncs.printError("Your phone book is empty!");
			return true;
		}
		return false;
	}
	
	// print phone book method
	public void printPhoneBook() {
		if(!this.isEmpty()) {
			App[] forPrint = new App[1];
			forPrint[0] = this;
			ToolsFuncs.PrintAll(forPrint);
		}
	}
	
	// swap phone book method
	public void swapPhoneBook() {
		int j = 0; 
		if(!isEmpty()) {
			// for loop that swap the i contact and the j contact in the arrayList
			// i is running from the end to the beginning
			// j is running from the beginning to the end
			for(int i = (ContactsApp.contacts.size() - 1) ; i >j; i--) {
				swapContacts(ContactsApp.contacts,i,j); 
				j++;
			}
		}
	}
		
	// method that swap 2 contacts in the ArrayList
	public static void swapContacts(ArrayList<Contact> contacts,int i,int j) {
		Contact temp = new Contact(contacts.get(j)); // create temp contact object
		ContactsApp.contacts.set(j,contacts.get(i)); // set the contacts ArrayList in the j place
		ContactsApp.contacts.set(i,temp); // set the contacts ArrayList in the i place
	}
	
	// method sort numeric
	public void sortNumeric() {
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
				swapContacts(contacts,i,j);
			}
		}
		swapContacts(contacts,i+1,r);
		return i+1;
	}
		
	//add all contacts to a file named by the user
	public void toFile(String fileName) throws IOException {
		//open a writer
		FileWriter myWriter = new FileWriter(fileName + ".txt");
		//run over all contacts in the phonebook and write them to file
		for (Contact contact : ContactsApp.contacts) {
		    myWriter.write(contact.getName() + ' ' + contact.getPhoneNumber() + '\n');
		}
	    myWriter.close();
	}

	//read all contacts from a file named by user
	public void fromFile(String fileName) throws IOException {
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
			String s = JOptionPane.showInputDialog("              Contacts\nPress:\n1- Add contact\n"
					+ "2- Delete contact\n3- Print phone book\n"
					+ "4- Search contact\n5- Sort by name\n6- Sort by phone number\n7- Reverse phonebook\n"
					+ "8- Export phone book to text file\n9- Import phone book from text file\n10- Exit");

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
					JOptionPane.showMessageDialog(null,e);
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
