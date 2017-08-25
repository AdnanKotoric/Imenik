package phone_adress_book;

public class ContactsProfile {

	String firstName;
	String lastName;
	String address;
	String email;
	String phoneNumber;

	ContactsProfile(String firstName, String lastName, String address, String email, String phoneNumber) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	String getFirstName() {
		return firstName;
	}

	void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	String getLastName() {
		return lastName;
	}

	void setLastName(String lastName) {
		this.lastName = lastName;
	}

	String getAddress() {
		return address;
	}

	void setAddress(String address) {
		this.address = address;
	}

	String getEmail() {
		return email;
	}

	void setEmail(String email) {
		this.email = email;
	}

	String getPhoneNumber() {
		return phoneNumber;
	}

	void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {

		return firstName + "," + lastName + "," + address + "," + email + "," + phoneNumber;
	}
}
