package com.example.student.android5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Random;

public class Activity4 extends AppCompatActivity {

    private static final String KEY_GROUP_NAME = "GroupName";
    private static final String KEY_STUDENT_NAME = "StudentName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

       /* ArrayList<HashMap<String, String>> groups = new ArrayList<>();
        ArrayList<ArrayList<HashMap<String, String>>> allChildren = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            HashMap<String, String> group = new HashMap<>();
            group.put(KEY_GROUP_NAME, "Group " + i);
            groups.add(group);

            ArrayList<HashMap<String, String>> children = new ArrayList<>();
            allChildren.add(children);

            for (int j = 0; j < r.nextInt(10); j++) {
                HashMap<String, String> child = new HashMap<>();
                child.put(KEY_STUDENT_NAME, "Student " + j);
                children.add(child);
            }
        }

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groups,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{KEY_GROUP_NAME},
                new int[]{android.R.id.text1},
                allChildren,
                android.R.layout.simple_list_item_1,
                new String[]{KEY_STUDENT_NAME},
                new int[]{android.R.id.text1}
        );
*/
        ArrayList<Group> groups = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            Group group = new Group();
            group.Name = "Group " + i;
            groups.add(group);

            group.Students = new Student[r.nextInt(10)];
            for (int j = 0; j < group.Students.length; j++) {
                group.Students[j] = new Student("Ivan " + j, "Ivanov " + j, j);
            }
        }

        StudentExpandableAdapter adapter = new StudentExpandableAdapter(
                this, R.layout.group, R.layout.student, groups);

        final ExpandableListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);


        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(listView.isGroupExpanded(groupPosition)){
                    listView.collapseGroup(groupPosition);
                }else {
                    listView.expandGroup(groupPosition);
                }
                return true;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }
}
