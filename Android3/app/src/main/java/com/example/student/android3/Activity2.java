package com.example.student.android3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        final String text = getIntent().getStringExtra(MainActivity.EXTRA_PARAM_1);

        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_PARAM_2, text + " RESULT");

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
