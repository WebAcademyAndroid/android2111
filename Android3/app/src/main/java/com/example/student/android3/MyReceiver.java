package com.example.student.android3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle args = intent.getExtras();
        String text = args.getString("Date");

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
