package com.example.student.android5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        String[] items = new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                R.id.textView,
                items);


        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }
}
