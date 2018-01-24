package com.example.student.android13;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextViewName, mTextViewDescription;

    public MessageViewHolder(View itemView) {
        super(itemView);

        mTextViewName = itemView.findViewById(android.R.id.text1);
        mTextViewDescription = itemView.findViewById(android.R.id.text2);
    }
}
