package com.example.student.android5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        final ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            students.add(new Student("Ivan " + i, "Ivanov " + i, i));
        }

       final RecyclerAdapter adapter = new RecyclerAdapter(this, R.layout.student, students);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new RecyclerAdapter.ClickListener() {
            @Override
            public void onClick(Student student) {
                Toast.makeText(Activity5.this, student.FirstName, Toast.LENGTH_SHORT).show();
            }
        });


        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();

                Student student = students.get(fromPos);
                students.remove(fromPos);
                students.add(toPos, student);

                adapter.notifyItemMoved(fromPos, toPos);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                students.remove(fromPos);
                adapter.notifyItemRemoved(fromPos);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
