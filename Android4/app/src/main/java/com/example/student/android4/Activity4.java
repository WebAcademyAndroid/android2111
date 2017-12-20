package com.example.student.android4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        String[] items = new String[]{"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                items);

        final ListView listView = findViewById(R.id.listView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*int position = listView.getCheckedItemPosition();
                Toast.makeText(Activity4.this, String.valueOf(position), Toast.LENGTH_SHORT).show();*/
                SparseBooleanArray array = listView.getCheckedItemPositions();
                String result = "";
                for(int i=0;i<array.size();i++){
                    if(array.get(i)){
                        result += i;
                    }
                }
                Toast.makeText(Activity4.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
