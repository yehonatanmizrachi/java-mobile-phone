package phoneBook;


public class Contact implements Comparable<Contact>{
	
	private String name, phoneNumber;
	
	// constructor
	public Contact(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	// copy constructor
	public Contact(Contact c) {
		this.name = c.name;
		this.phoneNumber=c.phoneNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	

	// transfer the phone number to numeric integer (removing the beginning '0' and the '-')
	public Long getNumericVal() {
		String s = this.phoneNumber.replace("-", "");
		return Long.parseLong(s);
	}

	@Override
	public String toString() {
		return "Name: " + name + ", "+"Phone Number: " + phoneNumber ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Contact))
			return false;
		
		Contact temp = (Contact) obj;
		return name.equals(temp.name)&&phoneNumber.equals(temp.phoneNumber);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		final int prime2 = 19;

		int result = 1;
		result = (int) (prime * result + this.getNumericVal());
		result = prime2 * result + this.name.hashCode();

		return result;
	}
	
	@Override
	public int compareTo(Contact cont) {
		
		if(this.getName().compareToIgnoreCase(cont.getName()) < 0) {
			return -1;
		}
		if(this.getName().compareToIgnoreCase(cont.getName()) == 0) {
			return 0;
		}
		return 1;
		
	}
}
