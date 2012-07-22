package com.hackathon.eventratio;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
 
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
 
public class EventWidget extends AppWidgetProvider {
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
 
        // loop through all app widgets the user has enabled
        for (int i=0;i < N; i++){
        	int widgetId = appWidgetIds[i];
 
        	// get our view so we can edit the time
        	RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget_layout);
 
        	DataService ds = DataService.getInstance();
 
        	// download an event from gmail and set it up (to come...)
        	// for now well just make a dummy one
        	views.setTextViewText(R.id.event, "Some sweet Event text!!!!");
 
        	// update the widget
        	appWidgetManager.updateAppWidget(widgetId, views);
 
        }
	}
}
