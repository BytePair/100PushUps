package com.bytepair.hundredpushups;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MyWidgetProvider extends AppWidgetProvider {

    private static final String LOG = MyWidgetProvider.class.getSimpleName();
    private static final String ACTION_CLICK = "ACTION_CLICK";

    private static Integer pushUpCounter = null;
    private static Date currentDate = new Date(118, 4, 1);

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        super.onUpdate(context, appWidgetManager, appWidgetIds);

        // set every View and every Intent before calling the updateAppWidget()
        RemoteViews viewsToUpdate = updateViews(context);
        viewsToUpdate = setIntents(viewsToUpdate, context, appWidgetIds);
        appWidgetManager.updateAppWidget(appWidgetIds, viewsToUpdate);
    }

    private RemoteViews updateViews(Context context) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        // increment push-up count and set text field
        // TODO: Only increment on clicks, not when adding to screen
        String updatedText = null;
        if (pushUpCounter == null) {
            pushUpCounter = 0;
        }
        else if (pushUpCounter < 100) {
            pushUpCounter++;
        }
        remoteViews.setTextViewText(R.id.pushupCountTextView, String.valueOf(pushUpCounter));

        // set date
        // TODO: Only set date when adding to screen and auto-updates
        updateDate();
        remoteViews.setTextViewText(R.id.dateTextView, currentDate.toString());

        return remoteViews;
    }

    private RemoteViews setIntents(RemoteViews remoteViews, Context context, int[] appWidgetIds) {

        Intent intent = new Intent(context, MyWidgetProvider.class);

        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

        return remoteViews;
    }

    /*
    private RemoteViews buildUpdate(Context context, int[] appWidgetIds) {

        RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        Intent intent = new Intent(context, MyWidgetProvider.class);

        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0 , intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // increment push-up count and set text field
        String updatedText = null;
        if (pushUpCounter == null) {
            pushUpCounter = 0;
        }
        else if (pushUpCounter < 100) {
            pushUpCounter++;
        }
        updateViews.setTextViewText(R.id.pushupCountTextView, String.valueOf(pushUpCounter));

        // set date
        updateDate();
        updateViews.setTextViewText(R.id.dateTextView, currentDate.toString());


        updateViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

        return(updateViews);
    }
    */

    private static void updateDate() {

        Calendar calOld = Calendar.getInstance();
        Calendar calNew = Calendar.getInstance();

        calOld.setTime(currentDate);
        calNew.setTime(new Date());

        // if year and date match, return early
        if ( (calOld.get(Calendar.YEAR) == calNew.get(Calendar.YEAR))
                && (calOld.get(Calendar.MONTH) == calNew.get(Calendar.MONTH))
                && (calOld.get(Calendar.DATE) == calNew.get(Calendar.DATE)) ) {
            return;
        }

        // otherwise, set new date
        currentDate = new Date();
    }



}
