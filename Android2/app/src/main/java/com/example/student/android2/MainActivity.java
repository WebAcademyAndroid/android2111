package com.example.student.android2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    public static final String EXTRA_TEXT = "com.example.student.android2.extra.TEXT";
    public static final String EXTRA_INT = "com.example.student.android2.extra.INT";
    public static final String EXTRA_STUDENT = "com.example.student.android2.extra.STUDENT";
    public static final String EXTRA_STUDENT_2 = "com.example.student.android2.extra.STUDENT_2";

    public static String TEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView);

       /* findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTextView.setText("AAAAAAAAAAA");
            }
        });*/
    }

    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, Activity2.class);
                intent.putExtra(EXTRA_TEXT, "Hello activity");
                intent.putExtra(EXTRA_INT, 555);

                TEXT = "ABC";

                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent3 = new Intent(this, Activity3.class);

                Student student = new Student("Ivan", "Ivanov", 22);
                intent3.putExtra(EXTRA_STUDENT, student);

                StudentP studentP = new StudentP("Petr", "Petrov", 33);
                intent3.putExtra(EXTRA_STUDENT_2, studentP);

                startActivity(intent3);
                break;
        }
    }
}
