package com.example.student.android12;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyButton extends RelativeLayout {

    private TextView mText;
    private ImageView mImage;

    public MyButton(Context context) {
        super(context);
        init(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_button, this);

        mText = view.findViewById(R.id.textView);
        mImage = view.findViewById(R.id.imageView);

        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyButton, 0, 0);

            String text = array.getString(R.styleable.MyButton_text);
            if(text != null){
                mText.setText(text);
            }

            int src = array.getResourceId(R.styleable.MyButton_src, 0);
            if(src != 0){
                mImage.setImageResource(src);
            }

            array.recycle();
        }
    }
}
