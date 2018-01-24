package com.example.student.android13;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Activity2 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EditText mEditTextMessage;
    private TextView mTextViewDescription;

    private DatabaseReference mDataBase;
    private FirebaseRecyclerAdapter<Message, MessageViewHolder> mAdapter;
    private ValueEventListener mListener;
    private String mIdRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        mRecyclerView = findViewById(R.id.listView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mEditTextMessage = findViewById(R.id.editTextMessage);
        mTextViewDescription = findViewById(R.id.textViewDescription);

        mDataBase = FirebaseDatabase.getInstance().getReference();

        mIdRoom = getIntent().getStringExtra("id");

        findViewById(R.id.buttonSave).setOnClickListener(saveListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                android.R.layout.simple_expandable_list_item_2,
                MessageViewHolder.class,
                mDataBase.child("messages/" + mIdRoom).orderByChild("time")) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, Message model, int position) {
                viewHolder.mTextViewName.setText(model.getMessage());

                String time = model.getTime() > 0 ? DateUtils.getRelativeTimeSpanString(
                        model.getTime(),
                        System.currentTimeMillis(),
                        DateUtils.MINUTE_IN_MILLIS).toString() : "";
                viewHolder.mTextViewDescription.setText(time);
            }
        };

        mRecyclerView.setAdapter(mAdapter);

        mListener = mDataBase.child("rooms/" + mIdRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Room room = dataSnapshot.getValue(Room.class);
                mTextViewDescription.setText(room.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        mAdapter.cleanup();

        mListener = null;
    }

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            HashMap<String, Object> map = new HashMap<>();

            map.put("message", mEditTextMessage.getText().toString());
            map.put("time", ServerValue.TIMESTAMP);

            mDataBase.child("messages/" + mIdRoom).push().setValue(map);

            mEditTextMessage.setText(null);
        }
    };
}
