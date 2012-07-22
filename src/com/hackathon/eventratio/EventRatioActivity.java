package com.hackathon.eventratio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

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
import android.os.Handler;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;

public class EventRatioActivity extends Activity {
    /** Called when the activity is first created. */
    
	String DEBUG = "EventRatio";
	Facebook facebook = new Facebook("453762924657294");

	List<Event> eventList;
	int currentEventIndex = 0;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String [] permisisons = {"user_birthday","friends_birthday","user_events",
        		"friends_events","user_relationships","friends_relationships",
        		"user_relationship_details","friends_relationship_details"};
        
        facebook.authorize(this, permisisons, new DialogListener() {
            
            public void onComplete(Bundle values) {
            	
            	Log.d(DEBUG, "token: "+facebook.getAccessToken());
                eventList = DataService.getAllEvents(facebook.getAccessToken());
                Log.d(DEBUG, "eventlist: " + eventList);
                
                if(eventList.isEmpty() == false) {
                	Event currentEvent = eventList.get(currentEventIndex);
                    Log.d(DEBUG, "event: " + currentEvent);
                    setupPiChart(currentEvent.getNumMales(), currentEvent.getNumFemales());
                    setupGaugeChart(currentEvent.getAges());
                }
                
            }

            public void onFacebookError(FacebookError error) {
            	Log.d(DEBUG, "FacebookError: "+error.getMessage());
            }

            public void onError(DialogError e) {
            	Log.d(DEBUG, "onErr: " + e.getMessage());
            }

            public void onCancel() {}
        });
      

       
        
    }
    
    private void setupPiChart(int males, int females) {
    	WebView wvPi = (WebView)findViewById(R.id.web_pi);
        WebSettings webSettings = wvPi.getSettings();
        webSettings.setJavaScriptEnabled(true);

		String output = GraphAPI.getPiChatHTML(this, males, females);
		//Log.d(DEBUG, "output: "+output);
		
		wvPi.setHorizontalScrollBarEnabled(false);
		wvPi.loadData(output, "text/html", null);
    }
    
    private void setupGaugeChart(List<Integer> ageList) {
    	Log.d(DEBUG, "ageList: "+ageList);
    	
    	WebView wvGuage = (WebView)findViewById(R.id.web_guage);
        WebSettings webSettings = wvGuage.getSettings();
        webSettings.setJavaScriptEnabled(true);
        
        InputStream myHTMLIS = getResources().openRawResource(R.raw.bar);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(myHTMLIS));

        StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		String output = GraphAPI.getBarHTML(this, ageList);
        
		Log.d(DEBUG, "bar: "+output);
		
		wvGuage.loadData(output, "text/html", null);
    }
	
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
    
    public String forTheLulz()
    {
    	String numMales = "";
        try {
            URL eventhandlerBackend = new URL(
                    "http://aqueous-cove-9179.herokuapp.com/sample.json");
            URLConnection tc = eventhandlerBackend.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    tc.getInputStream()));
 
            String jsonResult = "";
            String line;
            while ((line = in.readLine()) != null) {
                jsonResult += line;
            } 
            
            JSONObject json = new JSONObject(jsonResult);
            
             numMales = json.getString("male");
            
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return numMales;
    }
}