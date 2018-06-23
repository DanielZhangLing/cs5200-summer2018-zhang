package edu.neu.cs5200.models;

public class Priviledge {
	private int id;
	private int priviledge;
	private int developerId;
	private int elementId;

	public Priviledge() {
		super();
	}

	public Priviledge(int id, int priviledge, int developerId, int elementId) {
		super();
		this.id = id;
		this.priviledge = priviledge;
		this.developerId = developerId;
		this.elementId = elementId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPriviledge() {
		return priviledge;
	}

	public void setPriviledge(int priviledge) {
		this.priviledge = priviledge;
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
