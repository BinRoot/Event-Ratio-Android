package com.hackathon.eventratio;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
		
		try {
			JSONObject json = new JSONObject(getData(url));
			JSONArray events = json.getJSONArray("events");
			
			for (int i = 0; i < events.length(); i++) {  
				String eventID = events.getString(i); 
			} 
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Event> events = new ArrayList<Event>();
		
		
		
		
		
		return null;
	}
}
