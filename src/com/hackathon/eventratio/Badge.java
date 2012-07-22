package com.hackathon.eventratio;

public class Badge {

	private String name;
	private String Description;
	
	public Badge() {
		name = "";
		Description = "";
	}

	public Badge(String name, String description) {
		super();
		this.name = name;
		Description = description;
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
