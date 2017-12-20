package com.example.student.android4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        final ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            students.add(new Student("Ivan" + i, "Ivanov" + i, i));
        }

        final StudentAdapter adapter = new StudentAdapter(this,
                R.layout.student,
                students);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        
        adapter.setStudentListener(new StudentAdapter.StudentListener() {
            @Override
            public void edit(Student student) {
                Toast.makeText(Activity3.this, student.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = students.get(position);
                Toast.makeText(Activity3.this, student.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                students.remove(position);
                adapter.notifyDataSetChanged();

                return true;
            }
        });
    }
}
