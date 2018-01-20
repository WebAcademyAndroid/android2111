package com.example.student.android12;

import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    private DataBaseHelper mHelper;
    private Student mStudent;

    private StudentView mStudentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mStudentView = findViewById(R.id.studentView);

        mHelper = new DataBaseHelper(this);
        long id = getIntent().getExtras().getLong(AppWidget2.EXTRA_ID_STUDENT, 0);
        mStudent = mHelper.getStudents(id);

        mStudentView.set(mStudent);

        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStudentView.validate()) {
                    mStudent = mStudentView.get();

                    mHelper.save(mStudent);

                    ArrayList<Widget> widgets = mHelper.getStudentWidgets(mStudent.id);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(Activity2.this);

                    for (Widget w : widgets) {
                        AppWidget2.updateAppWidget(Activity2.this, appWidgetManager, (int) w.idWidget);
                    }
                    finish();
                }
            }
        });
    }
}
