package com.example.student.android8;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyService mService;

    private ArrayList<Student> mStudents;
    private ArrayAdapter<Student> mAdapter;
    private ProgressDialog mDialog;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);

        //getStudents();
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, MyService.class);

                PendingIntent pendingIntent = createPendingResult(1, intent, 0);
                intent.putExtra("PendingIntent", pendingIntent);

                intent.setAction("InsertStudent");
                intent.putExtra("Student", new Student("Ivan", "Ivanov", 22));

                startService(intent);
                break;
            case R.id.button2:
                if (mService == null) {
                    ServiceConnection connection = new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                            mService = ((MyService.MyBinder) iBinder).getService();
                            insertStudent();
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName componentName) {
                            mService = null;
                        }
                    };

                    Intent intent2 = new Intent(this, MyService.class);
                    bindService(intent2, connection, BIND_AUTO_CREATE);
                } else {
                    insertStudent();
                }
                break;
            case R.id.button3:
                mDialog = new ProgressDialog(this);
                mDialog.setMessage("Wait...");
                mDialog.setCancelable(false);
                mDialog.show();

                MyIntentService.insert(this, new Student("Petr", "Petrov", 33));
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
            case R.id.button9:
                break;
            case R.id.button10:
                break;
        }
    }

    private void insertStudent() {
        long id = mService.insertStudent(new Student("Ivan", "Ivanov", 22));
        Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
    }

    private void getStudents() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        MyIntentService.get(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                long id = data.getLongExtra("id", 0);
                Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == MyIntentService.REQUEST_CODE_GET) {
            if (resultCode == RESULT_OK) {
                mStudents = data.getParcelableArrayListExtra(MyIntentService.EXTRA_STUDENTS);

                if(mAdapter == null) {
                    mAdapter = new ArrayAdapter<>(
                            this,
                            android.R.layout.simple_list_item_1,
                            android.R.id.text1,
                            mStudents);

                    mListView.setAdapter(mAdapter);
                }else{
                    mAdapter.notifyDataSetChanged();
                }

                if (mDialog != null) {
                    mDialog.dismiss();
                }
            }
        }else if (requestCode == MyIntentService.REQUEST_CODE_INSERT) {
            long id = data.getLongExtra(MyIntentService.EXTRA_ID, 0);
            Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();

            if (mDialog != null) {
                mDialog.dismiss();
            }

            getStudents();
        }
    }
}
