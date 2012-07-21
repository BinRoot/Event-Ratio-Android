package com.hackathon.eventratio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventRatioActivity extends Activity {
    /** Called when the activity is first created. */
    
	Facebook facebook = new Facebook("113271772144685");
	
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        facebook.authorize(this, new DialogListener() {
            
            public void onComplete(Bundle values) {}

            
            public void onFacebookError(FacebookError error) {}

            
            public void onError(DialogError e) {}

            
            public void onCancel() {}
        });
       
        // Create a crude view - this should really be set via the layout resources
        // but since its an example saves declaring them in the XML.
        LinearLayout rootLayout = new LinearLayout(getApplicationContext());
        TextView txt = new TextView(getApplicationContext());
        rootLayout.addView(txt);
        setContentView(rootLayout);
 
        // Set the text and call the connect function.
        txt.setText("Connecting...");
        txt.setText(connect("http://aqueous-cove-9179.herokuapp.com/sample.json"));
        
    }
	
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
    
    private String connect(String url){
    	 
        // Create the httpclient
        HttpClient httpclient = new DefaultHttpClient();
 
        // Prepare a request object
        HttpGet httpget = new HttpGet(url); 
 
        // Execute the request
        HttpResponse response;
 
        // return string
        String returnString = null;
 
        try {
 
            // Open the webpage.
            response = httpclient.execute(httpget);
 
            if(response.getStatusLine().getStatusCode() == 200){
                // Connection was established. Get the content. 
 
                HttpEntity entity = response.getEntity();
                // If the response does not enclose an entity, there is no need
                // to worry about connection release
 
                if (entity != null) {
                    // A Simple JSON Response Read
                    InputStream instream = entity.getContent();
 
                    // Load the requested page converted to a string into a JSONObject.
                    JSONObject myAwway = new JSONObject(convertStreamToString(instream));
 
                    // Get the query value'
                    String query = myAwway.getString("male");
 
                    // Make array of the suggestions
//                    JSONArray suggestions = myAwway.getJSONArray("badges/others");
// 
//                    
//                    returnString = "Found: " + suggestions.length() + " locations for " + query;
//                    for (int i = 0; i < suggestions.length(); i++) {
//                        returnString += "\n\t" + suggestions.getString(i);
//                    }
 
                    // Cose the stream.
                    instream.close();
                }
            }
            else {
                // code here for a response othet than 200.  A response 200 means the webpage was ok
                // Other codes include 404 - not found, 301 - redirect etc...
                // Display the response line.
                returnString = "Unable to load page - " + response.getStatusLine();
            }
        }
        catch (IOException  ex) {
            // thrown by line 80 - getContent();
            // Connection was not established
            returnString = "Connection failed; " + ex.getMessage();
        }
        catch (JSONException ex){
            // JSON errors
            returnString = "JSON failed; " + ex.getMessage();
        }
        return returnString;
    }
 
    private static String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the BufferedReader.readLine()
         * method. We iterate until the BufferedReader return null which means
         * there's no more data to read. Each line will appended to a StringBuilder
         * and returned as String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}