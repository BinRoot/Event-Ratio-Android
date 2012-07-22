package com.hackathon.eventratio;

public class Badge {

	private String name;
	private String Description;
	private String id;
	
	public Badge() {
		name = "";
		Description = "";
	}

	public Badge(String name, String description, String id) {
		super();
		this.name = name;
		Description = description;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}	
}
