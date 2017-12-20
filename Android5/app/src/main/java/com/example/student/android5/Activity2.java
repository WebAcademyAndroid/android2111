package com.example.student.android5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        String[] items = new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                R.id.textView,
                items);

        //adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);

        String[] texts = new String[]{"q", "w", "e", "r", "t"};
        numberPicker.setDisplayedValues(texts);
    }
}
