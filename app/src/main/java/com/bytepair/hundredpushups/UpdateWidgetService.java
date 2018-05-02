package com.bytepair.hundredpushups;

import java.util.Random;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {

    private static final String LOG = UpdateWidgetService.class.getSimpleName();


    private static final int HUNDRED = 100;
    private int current_count = HUNDRED;



    @Override
    public void onStart(Intent intent, int startId) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());

        int[] allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

//      ComponentName thisWidget = new ComponentName(getApplicationContext(),
//              MyWidgetProvider.class);
//      int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);


        for (int widgetId : allWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(this
                    .getApplicationContext().getPackageName(),
                    R.layout.widget_layout);

            // Set the text
            remoteViews.setTextViewText(R.id.pushupCountTextView, String.valueOf(current_count));

            /*
            // Register an onClickListener
            Intent clickIntent = new Intent(this.getApplicationContext(), MyWidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                    allWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    getApplicationContext(), 0, clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.pushupCountTextView, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
            */

        }
        stopSelf();

        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}

