package com.example.student.android2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra(MainActivity.EXTRA_STUDENT);

        StudentP studentP = intent.getParcelableExtra(MainActivity.EXTRA_STUDENT_2);

        TextView textView = findViewById(R.id.textView3);
        textView.setText(R.string.app_name);
    }
}
