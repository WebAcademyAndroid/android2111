package com.example.student.android4;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {
    private ArrayList<Student> mStudents;
    private int mResource;
    private LayoutInflater mInflater;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Student> objects) {
        super(context, resource, objects);

        mStudents = objects;
        mResource = resource;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = mInflater.inflate(mResource, null);
        final Student student = mStudents.get(position);

        TextView textView = convertView.findViewById(R.id.textViewFirstName);
        textView.setText(student.FirstName);
        ((TextView) convertView.findViewById(R.id.textViewLastName)).setText(student.LastName);
        ((TextView) convertView.findViewById(R.id.textViewAge)).setText(String.valueOf(student.Age));

        convertView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.edit(student);
                }
            }
        });

        return convertView;
    }

    private StudentListener mListener;

    public void setStudentListener(StudentListener listener){
        mListener = listener;
    }

    public interface StudentListener{
        void edit(Student student);
    }
}
