package com.example.student.android8;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MyIntentService extends IntentService {
    private static final String ACTION_INSERT = "com.example.student.android8.action.INSERT";
    private static final String ACTION_GET = "com.example.student.android8.action.GET";

    public static final String EXTRA_STUDENT = "com.example.student.android8.extra.STUDENT";
    public static final String EXTRA_ID = "com.example.student.android8.extra.ID";
    public static final String EXTRA_STUDENTS = "com.example.student.android8.extra.STUDENTS";
    public static final String EXTRA_PENDING_INTENT = "com.example.student.android8.extra.PENDING_INTENT";

    public static final int REQUEST_CODE_INSERT = 100;
    public static final int REQUEST_CODE_GET = 101;

    public MyIntentService() {
        super("MyIntentService");
    }

    public static void insert(Context context, Student student) {
        Intent intent = new Intent(context, MyIntentService.class);

        PendingIntent pendingIntent = ((AppCompatActivity) context).createPendingResult(REQUEST_CODE_INSERT, intent, 0);
        intent.putExtra(EXTRA_PENDING_INTENT, pendingIntent);

        intent.setAction(ACTION_INSERT);
        intent.putExtra(EXTRA_STUDENT, student);

        context.startService(intent);
    }

    public static void get(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        PendingIntent pendingIntent = ((AppCompatActivity) context).createPendingResult(REQUEST_CODE_GET, intent, 0);
        intent.putExtra(EXTRA_PENDING_INTENT, pendingIntent);

        intent.setAction(ACTION_GET);

        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (intent != null) {
            final String action = intent.getAction();

            PendingIntent pendingIntent = intent.getParcelableExtra(EXTRA_PENDING_INTENT);
            Intent result = new Intent();

            DataBaseHelper helper = new DataBaseHelper(this);

            if (ACTION_INSERT.equals(action)) {
                Student student = intent.getParcelableExtra(EXTRA_STUDENT);
                long id = helper.insert(student);

                result.putExtra(EXTRA_ID, id);
            } else if (ACTION_GET.equals(action)) {
                ArrayList<Student> students = helper.getStudents();

                result.putExtra(EXTRA_STUDENTS, students);
            }

            try {
                pendingIntent.send(this, Activity.RESULT_OK, result);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }
}
