package com.example.student.android5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {

    private int mResourceId;
    private ArrayList<Student> mStudents;
    private LayoutInflater mInflater;

    private int mPosition = -1;

    public RecyclerAdapter(Context context, int resourceId, ArrayList<Student> students) {
        mResourceId = resourceId;
        mStudents = students;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StudentViewHolder(mInflater.inflate(mResourceId, parent, false));
    }

    @Override
    public void onBindViewHolder(final StudentViewHolder holder, final int position) {
        final Student student = mStudents.get(position);
        holder.setStudent(student);

        final RadioButton radioButton = holder.itemView.findViewById(R.id.radioButton);
        if(mPosition == position){
            radioButton.setChecked(true);
        }else{
            radioButton.setChecked(false);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(mListener != null){
                    mListener.onClick(student);
                }*/

                int oldPosition = mPosition;
                mPosition = position;

                notifyItemChanged(oldPosition);
                notifyItemChanged(mPosition);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }


    private ClickListener mListener;

    public void setOnClickListener(ClickListener listener) {
        mListener = listener;
    }

    public interface ClickListener {
        void onClick(Student student);
    }
}
