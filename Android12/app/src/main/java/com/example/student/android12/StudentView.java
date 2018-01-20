package com.example.student.android12;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

public class StudentView extends RelativeLayout {
    private RequiredEditText mEditTextFirstName, mEditTextLastName, mEditTextAge;

    private Student mStudent;

    public StudentView(Context context) {
        super(context);
        init(context, null);
    }

    public StudentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.student, this);

        mEditTextFirstName = view.findViewById(R.id.editTextFirstName);
        mEditTextLastName = view.findViewById(R.id.editTextLastName);
        mEditTextAge = view.findViewById(R.id.editTextAge);
    }

    public void set(Student student) {
        clear();

        mStudent = student;

        if (mStudent != null) {
            mEditTextFirstName.setText(mStudent.FirstName);
            mEditTextLastName.setText(mStudent.LastName);
            mEditTextAge.setText(String.valueOf(mStudent.Age));
        }
    }

    public Student get() {
        if (mStudent != null) {
            mStudent.FirstName = mEditTextFirstName.getText().toString();
            mStudent.LastName = mEditTextLastName.getText().toString();
            mStudent.Age = Long.parseLong(mEditTextAge.getText().toString());
        }

        return mStudent;
    }

    public boolean validate() {
        return mEditTextFirstName.validate() & mEditTextLastName.validate() & mEditTextAge.validate();
    }

    public void clear(){
        mStudent = null;

        mEditTextFirstName.setText(null);
        mEditTextLastName.setText(null);
        mEditTextAge.setText(null);

        mEditTextFirstName.setError(null);
        mEditTextLastName.setError(null);
        mEditTextAge.setError(null);
    }
}
