package phone_adress_book;

public class UsersProfile {

	private String name;
	private int password;
	private String email;
	

	public UsersProfile(String name, int password, String email) {
	
		this.name = name;
		this.password = password;
		this.email = email;
	}

	String getName() {
		return name;
	}

	int getPassword() {
		return password;
	}

	String getEmail() {
		return email;
	}




	
	
	
}
