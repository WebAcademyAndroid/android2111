package com.example.student.android12;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class RequiredEditText extends android.support.v7.widget.AppCompatEditText {

    private boolean mRequired;

    public RequiredEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RequiredEditText, 0, 0);
        mRequired = array.getBoolean(R.styleable.RequiredEditText_required, false);
        array.recycle();
    }

    public boolean isRequired() {
        return mRequired;
    }

    public void setRequired(boolean required) {
        this.mRequired = required;
    }

    public boolean validate(){
        if(mRequired && getText().length() == 0){
            setError("Required field");
            return  false;
        }
        return true;
    }
}
