package com.hackathon.eventratio;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DataService {
	
	public static String DEBUG = "Shutup";
	
	private static DataService ds;
	
	public DataService(){
		//NOPE
	}
	
	public static DataService getInstance() {
		if (ds == null) {
			ds = new DataService();
		}
		return ds;
	} 
	
	private static String getData(String urlStr)
	{
		
	    InputStream is = null;
	    try {
	    	URL connectURL = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)connectURL.openConnection();

	        is = conn.getInputStream(); 
	        // scoop up the reply from the server
	        int ch; 
	        StringBuffer sb = new StringBuffer(); 
	        while( ( ch = is.read() ) != -1 ) { 
	            sb.append( (char)ch ); 
	        } 
	        return sb.toString(); 
	    }
	    catch(Exception e) {
	       Log.e(DEBUG, "biffed it getting HTTPResponse");
	    }
	    finally 
	    {
	        try {
	        if (is != null)
	            is.close();
	        } catch (Exception e) {}
	    }

	    return "";
	}
	
	public static List<Event> getAllEvents(String token){
		String url = 
			"http://aqueous-cove-9179.herokuapp.com/allevents?access_token=" + token;
		
		List<Event> eventList = new ArrayList<Event>();
		
		try {
			JSONObject json = new JSONObject(getData(url));
			JSONArray events = json.getJSONArray("events");
			
			for (int i = 0; i < events.length(); i++) {  
				String eventID = events.getString(i); 
				eventList.add(getEvent(eventID, token));
			} 
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return eventList;
	}

	public static Event getEvent(String eventID, String token) {
		Event event = new Event();
		
		String url = "http://aqueous-cove-9179.herokuapp.com/event/" + eventID + "?access_token=" + token;
		
		try {
			JSONObject json = new JSONObject(getData(url));
			event.setNumMales(Integer.parseInt(json.getString("male")));
			event.setNumFemales(Integer.parseInt(json.getString("female")));
			event.setnumInvited(Integer.parseInt(json.getString("invited")));
			event.setNumAttending(Integer.parseInt(json.getString("attending")));
			event.setNumMaybe(Integer.parseInt(json.getString("maybe")));
			event.setNumDecline(Integer.parseInt(json.getString("decline")));
			event.setNumMutual(Integer.parseInt(json.getString("mutuals")));
			event.setName(json.getString("name"));
			event.setLocation(json.getString("location"));
			event.setAverageAge(Double.parseDouble(json.getString("averageAge")));
			//So ghetto I know
			event.setDate(new SimpleDateFormat(json.getString("time").split("T")[0]));
			
			List<Integer> ageList = new ArrayList<Integer>();
			
			JSONArray ages = json.getJSONArray("ages");
			
			for(int i = 0; i < ages.length(); i++){	
				ageList.add(Integer.parseInt(ages.getString(i)));
			}
			
			event.setAges(ageList);
			
			List<Badge> badgeList = new ArrayList<Badge>();
			
			JSONArray badges = json.getJSONArray("badges");
			
			for(int i = 0; i < badges.length(); i++){
				badgeList.add(new Badge(((JSONObject) (badges.get(i))).getString("name"), ((JSONObject) (badges.get(i))).getString("description")));
			}
			
			event.setBadges(badgeList);
			
		} catch (JSONException e) {
			Log.d(DEBUG, "shit");
		}
		
		return event;
	}
}
