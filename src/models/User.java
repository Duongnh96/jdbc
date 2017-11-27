package models;

public class User {

	private String email;

	/**
	 * Contructor
	 */
	public User() {
	}

	/**Contructor with parameter
	 * @param email
	 * @param firstname
	 * @param lastname
	 */
	public User(String email, String firstname, String lastname) {
		this.email = email;
		this.firstName = firstname;
		this.lastName = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private String firstName;
	private String lastName;

}
