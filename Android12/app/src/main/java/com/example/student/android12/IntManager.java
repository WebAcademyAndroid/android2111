package com.example.student.android12;

import android.content.Context;
import android.content.SharedPreferences;

public class IntManager {

    private static IntManager mInstance;
    private Context mContext;

    public static IntManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new IntManager(context);
        }

        return mInstance;
    }

    private IntManager(Context context){
        mContext = context;
    }

    public int nextInt(){
        SharedPreferences preferences = mContext.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int i = preferences.getInt("id", 0);

        if(i > 100000){
            i = 0;
        }

        i++;

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", i);
        editor.commit();

        return i;
    }
}
