package edu.neu.cs5200.models;

import java.sql.Date;
import java.util.Collection;

public class Developer extends Person {
	private int developerId;
	private String developerKey;
	private Collection<Website> websites;

	public Developer() {
		super();
	}

	public Developer(Person person, int developerId, String developerKey) {
		super();
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.username = person.getUsername();
		this.password = person.getPassword();
		this.email = person.getEmail();
		this.dob = person.getDob();
		this.developerId = developerId;
		this.developerKey = developerKey;
	}
	
	public Developer(String firstName, String lastName, String username, String password, String email, Date dob, String developerKey) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.developerKey = developerKey;
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	public String getDeveloperKey() {
		return developerKey;
	}

	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}

	public Collection<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(Collection<Website> websites) {
		this.websites = websites;
	}
	
	public void addWebsite(Website website) {
		this.websites.add(website);
	}
	
	public void removeWebsite(Website website) {
		this.websites.remove(website);
	}

}
