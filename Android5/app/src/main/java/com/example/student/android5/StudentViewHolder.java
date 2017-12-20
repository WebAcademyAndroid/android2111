package com.example.student.android5;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class StudentViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextViewFirstName, mTextViewLastName, mTextViewAge;

    public StudentViewHolder(View itemView) {
        super(itemView);

        mTextViewFirstName = itemView.findViewById(R.id.textViewFirstName);
        mTextViewLastName = itemView.findViewById(R.id.textViewLastName);
        mTextViewAge = itemView.findViewById(R.id.textViewAge);
    }

    public void setStudent(Student student) {
        mTextViewFirstName.setText(student.FirstName);
        mTextViewLastName.setText(student.LastName);
        mTextViewAge.setText(String.valueOf(student.Age));
    }
}
