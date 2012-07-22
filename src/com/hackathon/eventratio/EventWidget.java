package com.hackathon.eventratio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;
import android.content.Context;
import android.app.Application;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
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
        	RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        	
        	
 
        	// download an event from gmail and set it up (to come...)
        	// for now well just make a dummy one
        	if(events.isEmpty() == false) {
        		Event ev = events.get(0);
            	views.setTextViewText(R.id.event, ev.getName());
            	
            	DecimalFormat df = new DecimalFormat("##.#");
            	
            	String ratioStr = df.format((ev.getNumFemales()+0.0)/(ev.getNumMales()+0.0));
            	
            	views.setTextViewText(R.id.ratio, ratioStr);
            	
            	Intent intent = new Intent(context, EventRatioActivity.class);
                PendingIntent myPI = PendingIntent.getActivity(context, 0, intent, 0);
            	views.setOnClickPendingIntent(R.id.relWig, myPI);
        	}
        	
        	// update the widget
        	appWidgetManager.updateAppWidget(widgetId, views);
        	
 
        }
	}
}
