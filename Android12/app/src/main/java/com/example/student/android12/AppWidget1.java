package com.example.student.android12;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Random;

public class AppWidget1 extends AppWidgetProvider {

    public static final String EXTRA_ID = "com.example.student.android12.extra.ID";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        CharSequence widgetText = Calendar.getInstance().getTime().toString();

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget1);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        Intent intent = new Intent(context, MyReceiver.class);
        intent.putExtra(EXTRA_ID, appWidgetId);

        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(context, IntManager.getInstance(context).nextInt(), intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
}

