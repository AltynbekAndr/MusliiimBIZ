package com.example.zverek.myapplication.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OnBootReceiver extends BroadcastReceiver {

    final String LOG_TAG = "Logs: ";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, NamazTimeNotificationService.class);
        context.startService(serviceIntent);
        Toast.makeText(context,"Выполнилось",Toast.LENGTH_LONG).show();
    }
}