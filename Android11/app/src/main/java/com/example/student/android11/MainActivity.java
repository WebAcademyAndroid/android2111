package com.example.student.android11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RequiredEditText mEditText1, mEditText2, mEditText3;
    private StudentView mStudentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText1 = findViewById(R.id.requiredEditText);
        mEditText2 = findViewById(R.id.requiredEditText2);
        mEditText3 = findViewById(R.id.requiredEditText3);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText1.validate() & mEditText2.validate() & mEditText3.validate()) {
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mStudentView = findViewById(R.id.studentView);

        findViewById(R.id.buttonSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mStudentView.set(new Student("Ivan","Ivanov",22));
            }
        });
        findViewById(R.id.buttonGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mStudentView.validate()){
                   Student student = mStudentView.get();
                   Toast.makeText(MainActivity.this, student.toString(), Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}
