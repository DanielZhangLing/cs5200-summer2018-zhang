package edu.neu.cs5200.models;

public class Role {
	private int id;
	private int role;
	private int developerId;
	private int elementId;

	public Role() {
		super();
	}

	public Role(int id, int role, int developerId, int elementId) {
		super();
		this.id = id;
		this.role = role;
		this.developerId = developerId;
		this.elementId = elementId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

}
