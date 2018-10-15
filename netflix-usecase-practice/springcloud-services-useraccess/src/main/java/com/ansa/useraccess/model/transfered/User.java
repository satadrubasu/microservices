package com.ansa.useraccess.model.transfered;

/**
 * @author Satadru Basu
 *  Note this is a transfer object from the User service.
 *  Its not an import hence the contract of this transfer model object needs to be in sync with 
 *   User - service model provider.
 */
public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	
	public User() {
		super();
	}

	public User(Long id,String firstName, String lastName, String emailId, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
	