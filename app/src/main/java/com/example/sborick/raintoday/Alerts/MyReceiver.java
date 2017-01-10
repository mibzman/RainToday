package com.example.sborick.raintoday.Alerts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //good morning!
        // an Intent broadcast.
        Intent msgIntent = new Intent(context, AlertService.class);
        msgIntent.putExtra("text", intent.getStringExtra("text"));
        context.startService(msgIntent);
    }

}
