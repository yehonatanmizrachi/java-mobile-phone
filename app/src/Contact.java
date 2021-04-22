package src;


public class Contact {
	
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
	public int getNumericVal() {
		String s = this.phoneNumber.replace("-", "");
		return Integer.parseInt(s);
	}

	@Override
	public String toString() {
		return "{ Name: " + name + ", "+"Phone Number: " + phoneNumber + " }";
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
		result = prime * result + this.getNumericVal();
		result = prime2 * result + this.name.hashCode();

		return result;
	}
}
