package com.hackathon.eventratio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Event {
	private int numMales;
	private int numFemales;
	private int numMutual;
	private int numInvited;
	private int numAttending;
	private int numMaybe;
	private int numDecline;
	private List<Integer> ages;
	private double averageAge;
	private List<Badge> badges;
	private String name;
	private String location;
	private Date date;
	private String eventID;
	
	public Event() {
		this.numMales = 0;
		this.numMutual = 0;
		this.numInvited = 0;
		this.numAttending = 0;
		this.numMaybe = 0;
		this.numDecline = 0;
		this.badges = null;
		this.ages = null;
		this.averageAge = 0.0;
		this.name = "";
		this.location = "";
		this.date = null;
		this.eventID = "";
	}
	
	public double getAverageAge() {
		return averageAge;
	}

	public void setAverageAge(double averageAge) {
		this.averageAge = averageAge;
	}

	public List<Integer> getAges() {
		return ages;
	}

	public void setAges(List<Integer> ages) {
		this.ages = ages;
	}

	public int getNumDecline() {
		return numDecline;
	}

	public void setNumDecline(int numDecline) {
		this.numDecline = numDecline;
	}

	public int getNumMaybe() {
		return numMaybe;
	}

	public void setNumMaybe(int numMaybe) {
		this.numMaybe = numMaybe;
	}

	public int getNumAttending() {
		return numAttending;
	}

	public void setNumAttending(int numAttending) {
		this.numAttending = numAttending;
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
	public int getnumInvited() {
		return numInvited;
	}
	public void setnumInvited(int numInvited) {
		this.numInvited = numInvited;
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
