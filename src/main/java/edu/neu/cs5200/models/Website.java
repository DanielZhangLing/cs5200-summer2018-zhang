package edu.neu.cs5200.models;

import java.sql.Date;
import java.util.Collection;

public class Website {

	private int id;
	private String name;
	private String description;
	private Date updated;
	private Date created;
	private int visits;
	private int developerId;
	private Collection<Page> pages;

	public Website() {
		super();
	}

	public Website(int id, String name, String description, Date updated, Date created, int visits, int developerId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.updated = updated;
		this.created = created;
		this.visits = visits;
		this.developerId = developerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public Collection<Page> getPages() {
		return pages;
	}

	public void setPages(Collection<Page> pages) {
		this.pages = pages;
	}

	public void addPage(Page page) {
		this.pages.add(page);
	}

	public void removePage(Page page) {
		this.pages.remove(page);
	}

	public int getDeveloperId() {
		return developerId;
	}

	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
}
