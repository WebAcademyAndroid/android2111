package com.example.student.android3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_PARAM_1 = "com.example.student.android3.extra.PARAM_1";
    public static final String EXTRA_PARAM_2 = "com.example.student.android3.extra.PARAM_2";
    private static final int REQUEST_CODE_ACTIVITY_2 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);
        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action1:
                Toast.makeText(this, "Action 1 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action2:
                Toast.makeText(this, "Action 2 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action3:
                Toast.makeText(this, "Action 3 click", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action1:
                Toast.makeText(this, "Action 1 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action2:
                Toast.makeText(this, "Action 2 click", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action3:
                Toast.makeText(this, "Action 3 click", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra(EXTRA_PARAM_1, "some text");

                startActivityForResult(intent, REQUEST_CODE_ACTIVITY_2);
                break;
            case R.id.button2:
                Toast.makeText(this, "LONG", Toast.LENGTH_LONG).show();
                break;
            case R.id.button3: {
                Toast toast = Toast.makeText(this, "SHORT", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 100, 0);
                toast.show();
            }
            break;
            case R.id.button4: {
                Toast toast = Toast.makeText(this, "SHORT", Toast.LENGTH_SHORT);

                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.mipmap.ic_launcher);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                LinearLayout linearLayout = (LinearLayout) toast.getView();
                linearLayout.addView(imageView, 0);

                toast.show();
            }
            break;
            case R.id.button5: {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Intent intent1 = new Intent(this, MyReceiver.class);
                intent1.putExtra("Date", new Date().toString());

                PendingIntent pendingIntent =
                        PendingIntent.getBroadcast(this, new Random().nextInt(), intent1, 0);

                Notification notification = new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setTicker("Ticker")
                        .setContentTitle("Title")
                        .setContentText("Text")
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .build();

                notificationManager.notify(1, notification);
            }
            break;
            case R.id.button6: {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Notification notification = new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setTicker("Ticker2")
                        .setContentTitle("Title2")
                        .setContentText("Text2")
                        .setWhen(System.currentTimeMillis())
                        .build();

                notificationManager.notify(2, notification);
            }
            break;
            case R.id.button7:
                new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Toast.makeText(
                                        MainActivity.this,
                                        hourOfDay + ":" + minute,
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        },
                        40, 90, true).show();
                break;
            case R.id.button8:
                PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.textView));
                popupMenu.inflate(R.menu.main);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action1:
                                Toast.makeText(MainActivity.this, "Action 1 click", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.action2:
                                Toast.makeText(MainActivity.this, "Action 2 click", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.action3:
                                Toast.makeText(MainActivity.this, "Action 3 click", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                break;
            case R.id.button9:
                break;
            case R.id.button10:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ACTIVITY_2) {
            if (resultCode == RESULT_OK) {
                String text = data.getStringExtra(EXTRA_PARAM_2);
                //((Button)findViewById(R.id.button)).setText(text);

                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            }
        }
    }
}
