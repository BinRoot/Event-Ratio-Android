package com.hackathon.eventratio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;
import android.content.Context;
import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Gallery;
import android.widget.RemoteViews;
 
public class EventWidget extends AppWidgetProvider {
	
	public String token;
	String DEBUG = "EventRatio";
	List<Event> events;
	
	public void onEnabled(Context context){

	}
	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
 
		SharedPreferences sharedPreferences = context.getSharedPreferences("MY_SHARED_PREF",0);
		Log.d("Widget","" + sharedPreferences.contains("fb_token"));
		token = sharedPreferences.getString("fb_token", "");
		
		DataService ds = DataService.getInstance();
		Log.d("Widget", "Alive 1" + token);
		 events = ds.getAllEvents(token);
		 Log.d("Widget", "Alive 2" + events);
        // loop through all app widgets the user has enabled
        for (int i=0;i < N; i++){
        	int widgetId = appWidgetIds[i];
 
        	// get our view so we can edit the time
        	RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget_layout);
        	
        	
 
        	// download an event from gmail and set it up (to come...)
        	// for now well just make a dummy one
        	views.setTextViewText(R.id.event, "test - " + token + "*");
 
        	// update the widget
        	appWidgetManager.updateAppWidget(widgetId, views);
 
        }
	}
}
