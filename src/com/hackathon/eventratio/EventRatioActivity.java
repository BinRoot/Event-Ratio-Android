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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;

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
        
     
        WebView wv = (WebView)findViewById(R.id.web_pi);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        
        InputStream myHTMLIS = getResources().openRawResource(R.raw.pi);
        
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
        		
        wv.loadData(sb.toString(), "text/html", null);
    }
	
    
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
    }
    
    public String forTheLulz()
    {
    	String numMales = "";
        try {
            URL twitter = new URL(
                    "http://aqueous-cove-9179.herokuapp.com/sample.json");
            URLConnection tc = twitter.openConnection();
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