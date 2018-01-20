package com.example.student.android12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle args = intent.getExtras();
        int id = args.getInt(AppWidget1.EXTRA_ID);

        Toast.makeText(context, String.valueOf(id), Toast.LENGTH_SHORT).show();
    }
}
