package com.example.student.android8;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (action.equals("InsertStudent")) {
            Student student = intent.getParcelableExtra("Student");
            long id = insertStudent(student);


            Intent result = new Intent();
            result.putExtra("id", id);

            PendingIntent pendingIntent = intent.getParcelableExtra("PendingIntent");
            try {
                pendingIntent.send(this, Activity.RESULT_OK, result);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public long insertStudent(Student student) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataBaseHelper helper = new DataBaseHelper(this);
        return helper.insert(student);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}
