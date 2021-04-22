package testing;

<<<<<<< HEAD
import src.PhoneBookApp;
=======
import src.PhoneBook;
import src.contactsApp;
>>>>>>> 3d261402c86c21b04ed072e3f97565113fdefda6

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;



class PhoneBookTest {

	@Test
	void testAddContact() {
		String expectedOutput = 
				"The total number of contacts: 4\n"
						+ "{ Name: Aviel, Phone Number: 058-1234567 }\n"
						+ "{ Name: Shay, Phone Number: 058-7654321 }\n"
						+ "{ Name: Rotem, Phone Number: 052-1111111 }\n"
						+ "{ Name: Yehonatan, Phone Number: 052-1231231 }\n";

<<<<<<< HEAD
		PhoneBookApp phoneBook = new PhoneBookApp();

=======
		PhoneBook phoneBook = new PhoneBook();
		contactsApp.contacts.clear();
>>>>>>> 3d261402c86c21b04ed072e3f97565113fdefda6
		phoneBook.addContact("Aviel", "058-1234567");
		phoneBook.addContact("Shay", "058-7654321");
		phoneBook.addContact("Rotem", "052-1111111");
		phoneBook.addContact("Yehonatan", "052-1231231");

		assertEquals(expectedOutput, phoneBook.toString()); 
	}

	@Test
	void testRemoveContact() {
		String expectedOutput = 
				"The total number of contacts: 2\n"
						+ "{ Name: Aviel, Phone Number: 058-1234567 }\n"
						+ "{ Name: Shay, Phone Number: 058-7654321 }\n";

<<<<<<< HEAD
		PhoneBookApp phoneBook = new PhoneBookApp();
=======
		PhoneBook phoneBook = new PhoneBook();
		contactsApp.contacts.clear();
>>>>>>> 3d261402c86c21b04ed072e3f97565113fdefda6

		phoneBook.addContact("Aviel", "058-1234567");
		phoneBook.addContact("Shay", "058-7654321");
		phoneBook.addContact("Rotem", "052-1111111");
		phoneBook.addContact("Yehonatan", "052-1231231");

		phoneBook.remove("Yehonatan");
		phoneBook.remove("Rotem");

		assertEquals(expectedOutput, phoneBook.toString()); 
	}

	@Test
	void removeDuplicates() {
		String expectedOutput = 
			"The total number of contacts: 3\n"
					+ "{ Name: Aviel, Phone Number: 058-7654321 }\n"
					+ "{ Name: Yehonatan, Phone Number: 052-1231231 }\n"
					+ "{ Name: Aviel, Phone Number: 052-1111111 }\n";

<<<<<<< HEAD
		PhoneBookApp phoneBook = new PhoneBookApp();
=======
		PhoneBook phoneBook = new PhoneBook();
		contactsApp.contacts.clear();
>>>>>>> 3d261402c86c21b04ed072e3f97565113fdefda6

		phoneBook.addContact("Aviel", "052-1111111");
		phoneBook.addContact("Aviel", "058-7654321");
		phoneBook.addContact("Aviel", "052-1111111");
		phoneBook.addContact("Yehonatan", "052-1231231");

		phoneBook.removeDuplicates();

		assertEquals(expectedOutput, phoneBook.toString());
	}

	@Test
	void testSwap() {
		String expectedOutput = 
				"The total number of contacts: 5\n"
					+ "{ Name: Shalom, Phone Number: 5 }\n"
					+ "{ Name: Moshe, Phone Number: 4 }\n"
					+ "{ Name: Yosi, Phone Number: 3 }\n"
					+ "{ Name: Haim, Phone Number: 2 }\n"
					+ "{ Name: Rotem, Phone Number: 1 }\n";

<<<<<<< HEAD
		PhoneBookApp phoneBook = new PhoneBookApp();
=======
		PhoneBook phoneBook = new PhoneBook();
		contactsApp.contacts.clear();
>>>>>>> 3d261402c86c21b04ed072e3f97565113fdefda6

		phoneBook.addContact("Rotem", "1");
		phoneBook.addContact("Haim", "2");
		phoneBook.addContact("Yosi", "3");
		phoneBook.addContact("Moshe", "4");
		phoneBook.addContact("Shalom", "5");

		phoneBook.swapPhoneBook();

		assertEquals(expectedOutput, phoneBook.toString()); 	
	}

	@Test
	void testGetNumeric(){
		int expectedOutput = 546890530;
<<<<<<< HEAD
		PhoneBookApp phoneBook = new PhoneBookApp();
=======
		
		PhoneBook phoneBook = new PhoneBook();
		contactsApp.contacts.clear();

>>>>>>> 3d261402c86c21b04ed072e3f97565113fdefda6
		phoneBook.addContact("Rotem","054-6890530");
		assertEquals(expectedOutput, phoneBook.getContact(0).getNumericVal());
	}

	@Test
	void testSortNumeric(){
		String expectedOutput = 
				"The total number of contacts: 5\n"
						+ "{ Name: Shalom, Phone Number: 054-5555555 }\n"
						+ "{ Name: Moshe, Phone Number: 054-4444444 }\n"
						+ "{ Name: Rotem, Phone Number: 054-1111111 }\n"
						+ "{ Name: Yosi, Phone Number: 052-3333333 }\n"
						+ "{ Name: Haim, Phone Number: 050-2222222 }\n";


<<<<<<< HEAD
		PhoneBookApp phoneBook = new PhoneBookApp();
=======
		PhoneBook phoneBook = new PhoneBook();
		contactsApp.contacts.clear();
>>>>>>> 3d261402c86c21b04ed072e3f97565113fdefda6

		phoneBook.addContact("Yosi", "052-3333333");
		phoneBook.addContact("Haim", "050-2222222");
		phoneBook.addContact("Rotem", "054-1111111");
		phoneBook.addContact("Moshe", "054-4444444");
		phoneBook.addContact("Shalom", "054-5555555");

		phoneBook.sortNumeric();

		assertEquals(expectedOutput, phoneBook.toString());
	}
	
	@Test
	void testSortLexicographic(){
		String expectedOutput = 
				"The total number of contacts: 5\n"
						+ "{ Name: Haim, Phone Number: 050-2222222 }\n"
						+ "{ Name: Moshe, Phone Number: 054-4444444 }\n"
						+ "{ Name: Rotem, Phone Number: 054-1111111 }\n"
						+ "{ Name: Shalom, Phone Number: 054-5555555 }\n"
						+ "{ Name: Yosi, Phone Number: 052-3333333 }\n";
		
		
<<<<<<< HEAD
		PhoneBookApp phoneBook = new PhoneBookApp();
		
=======
		PhoneBook phoneBook = new PhoneBook();
		contactsApp.contacts.clear();

>>>>>>> 3d261402c86c21b04ed072e3f97565113fdefda6
		phoneBook.addContact("Yosi", "052-3333333");
		phoneBook.addContact("Haim", "050-2222222");
		phoneBook.addContact("Rotem", "054-1111111");
		phoneBook.addContact("Moshe", "054-4444444");
		phoneBook.addContact("Shalom", "054-5555555");
		
		phoneBook.sortByName();
		
		assertEquals(expectedOutput,phoneBook.toString());
	}

	@Test
	void testToAndFromFile() throws IOException{
		String expectedOutput =
				"The total number of contacts: 4\n"
						+ "{ Name: Aviel, Phone Number: 058-1234567 }\n"
						+ "{ Name: Shay, Phone Number: 058-7654321 }\n"
						+ "{ Name: Rotem, Phone Number: 052-1111111 }\n"
						+ "{ Name: Yehonatan, Phone Number: 052-1231231 }\n";

<<<<<<< HEAD
		PhoneBookApp phoneBook = new PhoneBookApp();
=======
		PhoneBook phoneBook = new PhoneBook();
		contactsApp.contacts.clear();
>>>>>>> 3d261402c86c21b04ed072e3f97565113fdefda6

		phoneBook.addContact("Aviel", "058-1234567");
		phoneBook.addContact("Shay", "058-7654321");
		phoneBook.addContact("Rotem", "052-1111111");
		phoneBook.addContact("Yehonatan", "052-1231231");

		phoneBook.toFile("test");

		phoneBook.remove("Aviel");
		phoneBook.remove("Shay");
		phoneBook.remove("Rotem");
		phoneBook.remove("Yehonatan");

		phoneBook.fromFile("test");

		assertEquals(expectedOutput, phoneBook.toString());
	}
}
