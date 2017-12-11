package com.example.student.android2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        int i = intent.getIntExtra(MainActivity.EXTRA_INT, 0);

        TextView textView = findViewById(R.id.textView2);
        textView.setText(MainActivity.TEXT);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Intent intent = new Intent(Activity2.this, MainActivity.class);
                startActivity(intent);*/

                finish();
            }
        });
    }
}
