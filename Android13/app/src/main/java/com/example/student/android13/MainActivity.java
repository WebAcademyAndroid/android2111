package com.example.student.android13;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EditText mEditTextName, mEditTextDescription;

    private DatabaseReference mDataBase;

    private FirebaseRecyclerAdapter<Room, RoomViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.listView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEditTextName = findViewById(R.id.editTextRoomName);
        mEditTextDescription = findViewById(R.id.editTextRoomDescription);

        mDataBase = FirebaseDatabase.getInstance().getReference();

        findViewById(R.id.buttonSave).setOnClickListener(saveListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAdapter = new FirebaseRecyclerAdapter<Room, RoomViewHolder>(
                Room.class,
                android.R.layout.simple_expandable_list_item_2,
                RoomViewHolder.class,
                mDataBase.child("rooms")) {
            @Override
            protected void populateViewHolder(RoomViewHolder viewHolder, Room model, int position) {
                viewHolder.mTextViewDescription.setText(model.getDescription());

                final String key = mAdapter.getRef(position).getKey();
                viewHolder.mTextViewName.setText(key);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Activity2.class);
                        intent.putExtra("id", key);
                        startActivity(intent);
                    }
                });
            }
        };

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mAdapter.cleanup();
    }


    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = mEditTextName.getText().toString();
            Room room = new Room(mEditTextDescription.getText().toString());

            mDataBase.child("rooms/" + name).setValue(room);

            mEditTextName.setText(null);
            mEditTextDescription.setText(null);
        }
    };
}
