package com.hackathon.eventratio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
}
