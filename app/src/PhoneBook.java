package src;

import java.io.File;  // Import the File class

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

public class PhoneBook implements App{

	private ArrayList<Contact> contacts = new ArrayList<Contact>();

	public void addContact(String name, String phoneNumber) {
		Contact newContact = new Contact(name, phoneNumber);
		this.contacts.add(newContact);
	}
	
	// get the contacts[i] 
	public Contact getContact(int i) {
		if(contacts.size() > 0) {
			return this.contacts.get(i);
		}
		else {
			return null;
		}
	}

	public void removeContact(String name) {
		// remove the first occurrence
		for (Contact contact : this.contacts) { 
		    if (contact.getName().equals(name)) {		    	
		    	this.contacts.remove(contact);
		    	return;
		    }
		}
		JOptionPane.showMessageDialog(null, "The user " + name + " doesn't exist!");
	}
	
	/* Search contact in phoneBook by name.
	 * If the contact is exist, it will print all its occurrences. */
	public void searchContact(String name)
	{
		boolean isExist = false;
		String s = "";
		for (Contact contact : this.contacts) { 
		    if (contact.getName().equals(name)) {	
		    	s += contact.toString() + '\n';
		    	isExist = true;
		    }
		}
		if (!isExist)
			JOptionPane.showMessageDialog(null, "The contact " + name + " doesn't exist!");
		else
			JOptionPane.showMessageDialog(null, s);
	}
	
	/* Function that sorts the array in lexicographically by names.
	 * Uses merge-sort.
	 * Average running time - O(nlogn)*/
	public void sortByName()
	{
		if (contacts.size() > 1)
			mergeSort(0, contacts.size() - 1);
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
			temp1.add(new Contact(contacts.get(i)));
		for (int i = q + 1; i < r + 1; i++)
			temp2.add(new Contact(contacts.get(i)));
		
		int i = l;
		int i1 = 0;
		int i2 = 0;
		while (i1 < len1 && i2 < len2)
		{
			// Compare names by alphabetical order
			if (temp1.get(i1).getName().compareToIgnoreCase(temp2.get(i2).getName()) <= 0)
			{
				contacts.add(i, temp1.get(i1));
				i1++;
			}
			else
			{
				contacts.add(i, temp2.get(i2));
				i2++;
			}
			
			contacts.remove(i + 1);
			i++;
		}
		
		while (i1 < len1)
		{
			contacts.add(i, temp1.get(i1));
			contacts.remove(i + 1);
			i1++;
			i++;
		}
		while (i2 < len2)
		{
			contacts.add(i, temp2.get(i2));
			contacts.remove(i + 1);
			i2++;
			i++;
		}
	}
	
	/*
	 * Removes all the duplicates from the contacts list
	 * using HashSet for getting O(n) time complexity.
	 */
	public void removeDuplicates() {
		Set<Contact> newContacts = new HashSet<Contact>();

		for (Contact contact : this.contacts) {
				newContacts.add(contact);		
		}

		// update this.contacts to the new list
		this.contacts = new ArrayList<Contact>(newContacts);
	}

	@Override
	public String toString() {
		String str = "";
		str += "The total number of contacts: "+this.contacts.size()+"\n";
		for (Contact contact : this.contacts)
		{ 
		    str += contact.toString()+ "\n";
		}
		return str;
	}
	
	// method that check if the Phone Book is empty or not 
	public boolean isEmpty() {
		if(this.contacts.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error - Phone Book is empty");
			return true;
		}
		return false;
	}
	
	// print phone book method
	public void printPhoneBook() {
		if(!this.isEmpty()) {
			JOptionPane.showMessageDialog(null,this);
		}
	}
	
	// swap phone book method
		public void swapPhoneBook() {
			int j = 0; 
			if(!isEmpty()) {
				// for loop that swap the i contact and the j contact in the arrayList
				// i is running from the end to the beginning
				// j is running from the beginning to the end
				for(int i = (this.contacts.size() - 1) ; i >j; i--) {
					swapContacts(contacts,i,j); 
					j++;
				}
			}
		}
		
		// method that swap 2 contacts in the ArrayList
		public static void swapContacts(ArrayList<Contact> contacts,int i,int j) {
			Contact temp = new Contact(contacts.get(j)); // create temp contact object
			contacts.set(j,contacts.get(i)); // set the contacts ArrayList in the j place
			contacts.set(i,temp); // set the contacts ArrayList in the i place
		}
		
		// method sort numeric
		public void sortNumeric() {
			if(!isEmpty()) {
				QuicksortBS(this.contacts,0,(this.contacts.size()-1)); // calling method QuicksortBS
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
			int x = contacts.get(r).getNumericVal();
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
			for (Contact contact : this.contacts) {
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
		
		public void run() throws IOException {
			String name;
			String phoneNumber;
			int input = 0;
			
			while (input != 11)
			{
				String s = JOptionPane.showInputDialog("Phone Book system\nPress:\n1- Add contact\n2- Delete contact\n3- Print phone book\n"
						+ "4- Search contact\n5- Sort by name\n6- Sort by phone number\n7- Remove duplicates\n"
						+ "8- Reverse phonebook\n9- Export phone book to text file\n10- Import phone book from text file\n11- Exit");

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
				else {input = 11;}

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
						Integer.parseInt(check);
						this.addContact(name, phoneNumber);
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null,"Invalid phone number!");
					}
					break;
				// remove contact
				case 2:
					if(!this.isEmpty()) {
						name = JOptionPane.showInputDialog("Enter full name\n");
						if(name == null) {break;}
						this.removeContact(name);
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
				// remove duplicates
				case 7:
					if(!this.isEmpty()) {
					this.removeDuplicates();
					JOptionPane.showMessageDialog(null,"All duplications have been successfully removed.");
					break;
					}
					break;
				// reverse phone book
				case 8:
					if(!this.isEmpty()) {
					this.swapPhoneBook();
					JOptionPane.showMessageDialog(null,"The Phone Book has been successfully reversed.");
					break;
					}
					break;
				// export phone book to text file
				case 9:
					name = JOptionPane.showInputDialog("Enter name of text file:");
					if(name == null) {break;}
					this.toFile(name);
					JOptionPane.showMessageDialog(null,"The Phone Book has been successfully exported to the file " + name + ".");
					break;
				// import phone book from text file 
				case 10:
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
				case 11:
					JOptionPane.showMessageDialog(null,"Bye :)");
					break;
				// wrong input
				default:
					JOptionPane.showMessageDialog(null,"Wrong input! Please try again.");
					break;
				}
			}
		}
		
	}
