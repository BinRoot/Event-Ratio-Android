package com.hackathon.eventratio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.util.Log;

public class GraphAPI {
	
	private static String DEBUG = "Graph";
	
	public static String getPiChatHTML(Activity a, int males, int females) {
		InputStream myHTMLIS = a.getResources().openRawResource(R.raw.pi);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(myHTMLIS));

        StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		String HTMLStr = sb.toString();
		
		String dataStr = HTMLStr.substring(HTMLStr.indexOf("([")+2, HTMLStr.indexOf("])"));
		
		Log.d(DEBUG, "data: "+dataStr);
		
		
		String newData = "['Task', 'Number of People'], ";
		
		newData = newData + "['Male', " + males+"], ";
		
		newData = newData + "['Female', " + females+"] ";
		
		return HTMLStr.replace(dataStr, newData);
		
	}
	
	public static String getBarHTML(Activity a, List<Integer> ageList) {
		InputStream myHTMLIS = a.getResources().openRawResource(R.raw.bar);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(myHTMLIS));

        StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		
		String HTMLStr = sb.toString();
		
		Log.d(DEBUG, "not dead 1");
		
		HashMap<Integer, Integer> ageMap = new HashMap<Integer, Integer>();
		
		Log.d(DEBUG, "not dead 1.5, " + ageList.size());
		
		for(int i=0; i<ageList.size(); i++) {
			Log.d(DEBUG, "not dead 2");
			
			Integer ageVal = ageMap.get(ageList.get(i));
			if(ageVal == null) {
				ageVal = 0;
			}
			ageMap.put(ageList.get(i), ageVal + 1);
		}
		
		
		Log.d(DEBUG, "not dead 3");
		
		String dataStr = HTMLStr.substring(HTMLStr.indexOf("([")+2, HTMLStr.indexOf("])"));
		
		String newData = "['Age', 'Number of People'], ";
		
		ArrayList<Integer> ageList2 = new ArrayList<Integer>(ageMap.keySet());
		Collections.sort(ageList2);
		
		Log.d(DEBUG, "not dead 4");
		
		for(int ageVal : ageList2) {
			newData = newData + "['" + ageVal + "', " + ageMap.get(ageVal)+"], ";
		}
		
		newData = newData.substring(0, newData.length()-2);
		

		return HTMLStr.replace(dataStr, newData);
		
	}
}
