/*
 * Group 1:
 * Yehonatan Mizrachi 
 * Aviel Derhy
 * Rotem Doar
 * Shay Kordana
 * 
 */

package src;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Menu {

	public static void main(String[] args) throws IOException{
		PhoneBookApp phoneBook = new PhoneBookApp();
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
					phoneBook.addContact(name, phoneNumber);
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null,"Invalid phone number!");
				}
				break;
			// remove contact
			case 2:
				if(!phoneBook.isEmpty()) {
					name = JOptionPane.showInputDialog("Enter full name\n");
					if(name == null) {break;}
					phoneBook.removeContact(name);
					break;
				}
				break;
			// print phone book
			case 3:
				if(!phoneBook.isEmpty()) {
					phoneBook.printPhoneBook();
					break;
				}
				break;
			// search contact by name				
			case 4:
				if(!phoneBook.isEmpty()) {
				name = JOptionPane.showInputDialog("Enter full name\n");
				if(name == null) {break;}
				phoneBook.searchContact(name);
				break;
				}
				break;
			// sort phone book by name				
			case 5:
				if(!phoneBook.isEmpty()) {
				phoneBook.sortByName();
				JOptionPane.showMessageDialog(null,"The phone Book has been sorted by increasing name.");
				break;
				}
				break;
			// sort phone book by phone number
			case 6:
				if(!phoneBook.isEmpty()) {
				phoneBook.sortNumeric();
				JOptionPane.showMessageDialog(null,"The phone Book has been sorted by decreasing phone Number.");
				break;
				}
				break;
			// remove duplicates
			case 7:
				if(!phoneBook.isEmpty()) {
				phoneBook.removeDuplicates();
				JOptionPane.showMessageDialog(null,"All duplications have been successfully removed.");
				break;
				}
				break;
			// reverse phone book
			case 8:
				if(!phoneBook.isEmpty()) {
				phoneBook.swapPhoneBook();
				JOptionPane.showMessageDialog(null,"The Phone Book has been successfully reversed.");
				break;
				}
				break;
			// export phone book to text file
			case 9:
				name = JOptionPane.showInputDialog("Enter name of text file:");
				if(name == null) {break;}
				phoneBook.toFile(name);
				JOptionPane.showMessageDialog(null,"The Phone Book has been successfully exported to the file " + name + ".");
				break;
			// import phone book from text file 
			case 10:
				name = JOptionPane.showInputDialog("Enter name of text file:");
				if(name == null) {break;}
				try {
					phoneBook.fromFile(name);
					JOptionPane.showMessageDialog(null,"The Phone Book has been successfully imported from the file " + name + ".");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}
				break;
			// exit
			case 11:
				//JOptionPane.showMessageDialog(null,"Bye :)");
				break;
			// wrong input
			default:
				JOptionPane.showMessageDialog(null,"Wrong input! Please try again.");
				break;
			}
		}
	}
}
