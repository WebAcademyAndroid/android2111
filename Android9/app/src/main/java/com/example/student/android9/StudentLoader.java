package com.example.student.android9;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentLoader extends AsyncTaskLoader<ArrayList<Student>> {
    private DataBaseHelper mHelper;

    public StudentLoader(Context context) {
        super(context);
        mHelper = new DataBaseHelper(context);
    }

    @Override
    public ArrayList<Student> loadInBackground() {
        Log.d("LOADER", "loading");

        return mHelper.getStudents();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
