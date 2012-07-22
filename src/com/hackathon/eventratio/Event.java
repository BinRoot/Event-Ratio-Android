package com.hackathon.eventratio;

import java.util.Date;
import java.util.List;

public class Event {
	private int numMales;
	private int numFemales;
	private int numMutual;
	private int numTotal;
	private List<Badge> badges;
	private String name;
	private String location;
	private Date date;
	private String eventID;
	
	public Event(int numMales, int numFemales, int numMutual, int numTotal,
			List<Badge> badges, String name, String location, Date date,
			String eventID) {
		super();
		this.numMales = numMales;
		this.numFemales = numFemales;
		this.numMutual = numMutual;
		this.numTotal = numTotal;
		this.badges = badges;
		this.name = name;
		this.location = location;
		this.date = date;
		this.eventID = eventID;
	}
	
	public Event() {
		this.numMales = 0;
		this.numFemales = 0;
		this.numMutual = 0;
		this.numTotal = 0;
		this.badges = null;
		this.name = "";
		this.location = "";
		this.date = null;
		this.eventID = "";
	}
	
	public int getNumMales() {
		return numMales;
	}
	public void setNumMales(int numMales) {
		this.numMales = numMales;
	}
	public int getNumFemales() {
		return numFemales;
	}
	public void setNumFemales(int numFemales) {
		this.numFemales = numFemales;
	}
	public int getNumMutual() {
		return numMutual;
	}
	public void setNumMutual(int numMutual) {
		this.numMutual = numMutual;
	}
	public int getNumTotal() {
		return numTotal;
	}
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}
	public List<Badge> getBadges() {
		return badges;
	}
	public void setBadges(List<Badge> badges) {
		this.badges = badges;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	
	
}
