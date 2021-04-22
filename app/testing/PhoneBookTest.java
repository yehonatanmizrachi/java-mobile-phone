//package testing;
//
//import src.PhoneBookApp;
//import src.ContactsApp;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.io.IOException;
//
//import org.junit.jupiter.api.Test;
//
//
//
//class PhoneBookAppTest {
//
//	@Test
//	void testAddContact() {
//		String expectedOutput = 
//				"The total number of contacts: 4\n"
//						+ "{ Name: Aviel, Phone Number: 058-1234567 }\n"
//						+ "{ Name: Shay, Phone Number: 058-7654321 }\n"
//						+ "{ Name: Rotem, Phone Number: 052-1111111 }\n"
//						+ "{ Name: Yehonatan, Phone Number: 052-1231231 }\n";
//
//		PhoneBookApp PhoneBookApp = new PhoneBookApp();
//		ContactsApp.contacts.clear();
//		PhoneBookApp.addContact("Aviel", "058-1234567");
//		PhoneBookApp.addContact("Shay", "058-7654321");
//		PhoneBookApp.addContact("Rotem", "052-1111111");
//		PhoneBookApp.addContact("Yehonatan", "052-1231231");
//
//		assertEquals(expectedOutput, PhoneBookApp.toString()); 
//	}
//
//	@Test
//	void testRemoveContact() {
//		String expectedOutput = 
//				"The total number of contacts: 2\n"
//						+ "{ Name: Aviel, Phone Number: 058-1234567 }\n"
//						+ "{ Name: Shay, Phone Number: 058-7654321 }\n";
//
//		PhoneBookApp PhoneBookApp = new PhoneBookApp();
//		ContactsApp.contacts.clear();
//
//		PhoneBookApp.addContact("Aviel", "058-1234567");
//		PhoneBookApp.addContact("Shay", "058-7654321");
//		PhoneBookApp.addContact("Rotem", "052-1111111");
//		PhoneBookApp.addContact("Yehonatan", "052-1231231");
//
//		PhoneBookApp.remove("Yehonatan");
//		PhoneBookApp.remove("Rotem");
//
//		assertEquals(expectedOutput, PhoneBookApp.toString()); 
//	}
//
//	@Test
//	void removeDuplicates() {
//		String expectedOutput = 
//			"The total number of contacts: 3\n"
//					+ "{ Name: Aviel, Phone Number: 058-7654321 }\n"
//					+ "{ Name: Yehonatan, Phone Number: 052-1231231 }\n"
//					+ "{ Name: Aviel, Phone Number: 052-1111111 }\n";
//
//		PhoneBookApp PhoneBookApp = new PhoneBookApp();
//		ContactsApp.contacts.clear();
//
//		PhoneBookApp.addContact("Aviel", "052-1111111");
//		PhoneBookApp.addContact("Aviel", "058-7654321");
//		PhoneBookApp.addContact("Aviel", "052-1111111");
//		PhoneBookApp.addContact("Yehonatan", "052-1231231");
//
//		PhoneBookApp.removeDuplicates();
//
//		assertEquals(expectedOutput, PhoneBookApp.toString());
//	}
//
//	@Test
//	void testSwap() {
//		String expectedOutput = 
//				"The total number of contacts: 5\n"
//					+ "{ Name: Shalom, Phone Number: 5 }\n"
//					+ "{ Name: Moshe, Phone Number: 4 }\n"
//					+ "{ Name: Yosi, Phone Number: 3 }\n"
//					+ "{ Name: Haim, Phone Number: 2 }\n"
//					+ "{ Name: Rotem, Phone Number: 1 }\n";
//
//		PhoneBookApp PhoneBookApp = new PhoneBookApp();
//		ContactsApp.contacts.clear();
//
//		PhoneBookApp.addContact("Rotem", "1");
//		PhoneBookApp.addContact("Haim", "2");
//		PhoneBookApp.addContact("Yosi", "3");
//		PhoneBookApp.addContact("Moshe", "4");
//		PhoneBookApp.addContact("Shalom", "5");
//
//		PhoneBookApp.swapPhoneBookApp();
//
//		assertEquals(expectedOutput, PhoneBookApp.toString()); 	
//	}
//
//	@Test
//	void testGetNumeric(){
//		int expectedOutput = 546890530;
//		
//		PhoneBookApp PhoneBookApp = new PhoneBookApp();
//		ContactsApp.contacts.clear();
//
//		PhoneBookApp.addContact("Rotem","054-6890530");
//		assertEquals(expectedOutput, PhoneBookApp.getContact(0).getNumericVal());
//	}
//
//	@Test
//	void testSortNumeric(){
//		String expectedOutput = 
//				"The total number of contacts: 5\n"
//						+ "{ Name: Shalom, Phone Number: 054-5555555 }\n"
//						+ "{ Name: Moshe, Phone Number: 054-4444444 }\n"
//						+ "{ Name: Rotem, Phone Number: 054-1111111 }\n"
//						+ "{ Name: Yosi, Phone Number: 052-3333333 }\n"
//						+ "{ Name: Haim, Phone Number: 050-2222222 }\n";
//
//
//		PhoneBookApp PhoneBookApp = new PhoneBookApp();
//		ContactsApp.contacts.clear();
//
//		PhoneBookApp.addContact("Yosi", "052-3333333");
//		PhoneBookApp.addContact("Haim", "050-2222222");
//		PhoneBookApp.addContact("Rotem", "054-1111111");
//		PhoneBookApp.addContact("Moshe", "054-4444444");
//		PhoneBookApp.addContact("Shalom", "054-5555555");
//
//		PhoneBookApp.sortNumeric();
//
//		assertEquals(expectedOutput, PhoneBookApp.toString());
//	}
//	
//	@Test
//	void testSortLexicographic(){
//		String expectedOutput = 
//				"The total number of contacts: 5\n"
//						+ "{ Name: Haim, Phone Number: 050-2222222 }\n"
//						+ "{ Name: Moshe, Phone Number: 054-4444444 }\n"
//						+ "{ Name: Rotem, Phone Number: 054-1111111 }\n"
//						+ "{ Name: Shalom, Phone Number: 054-5555555 }\n"
//						+ "{ Name: Yosi, Phone Number: 052-3333333 }\n";
//		
//		PhoneBookApp PhoneBookApp = new PhoneBookApp();
//		ContactsApp.contacts.clear();
//
//		PhoneBookApp.addContact("Yosi", "052-3333333");
//		PhoneBookApp.addContact("Haim", "050-2222222");
//		PhoneBookApp.addContact("Rotem", "054-1111111");
//		PhoneBookApp.addContact("Moshe", "054-4444444");
//		PhoneBookApp.addContact("Shalom", "054-5555555");
//		
//		PhoneBookApp.sortByName();
//		
//		assertEquals(expectedOutput,PhoneBookApp.toString());
//	}
//
//	@Test
//	void testToAndFromFile() throws IOException{
//		String expectedOutput =
//				"The total number of contacts: 4\n"
//						+ "{ Name: Aviel, Phone Number: 058-1234567 }\n"
//						+ "{ Name: Shay, Phone Number: 058-7654321 }\n"
//						+ "{ Name: Rotem, Phone Number: 052-1111111 }\n"
//						+ "{ Name: Yehonatan, Phone Number: 052-1231231 }\n";
//
//		PhoneBookApp PhoneBookApp = new PhoneBookApp();
//		ContactsApp.contacts.clear();
//
//		PhoneBookApp.addContact("Aviel", "058-1234567");
//		PhoneBookApp.addContact("Shay", "058-7654321");
//		PhoneBookApp.addContact("Rotem", "052-1111111");
//		PhoneBookApp.addContact("Yehonatan", "052-1231231");
//
//		PhoneBookApp.toFile("test");
//
//		PhoneBookApp.remove("Aviel");
//		PhoneBookApp.remove("Shay");
//		PhoneBookApp.remove("Rotem");
//		PhoneBookApp.remove("Yehonatan");
//
//		PhoneBookApp.fromFile("test");
//
//		assertEquals(expectedOutput, PhoneBookApp.toString());
//	}
//}
