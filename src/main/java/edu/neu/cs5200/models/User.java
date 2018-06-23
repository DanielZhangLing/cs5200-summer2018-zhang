package edu.neu.cs5200.models;

public class User extends Person {

	private int userId;
	private Boolean userAgreement;

	public User() {
		super();
	}

	public User(Person person, int userId, Boolean userAgreement) {
		super();
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.username = person.getUsername();
		this.password = person.getPassword();
		this.email = person.getEmail();
		this.dob = person.getDob();
		this.userId = userId;
		this.userAgreement = userAgreement;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Boolean getUserAgreement() {
		return userAgreement;
	}

	public void setUserAgreement(Boolean userAgreement) {
		this.userAgreement = userAgreement;
	}
}
