package com.example.student.android10;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Student>>,
        BlankFragment1.ActionListener {

    private ProgressDialog mDialog;
    private SaveTask mSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStudents(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSaveTask != null) {
            mSaveTask.cancel(true);
        }
    }

    private void getStudents(boolean restart) {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("Loading...");
        mDialog.setCancelable(false);
        mDialog.show();

        if (restart) {
            getSupportLoaderManager().restartLoader(0, null, this);
        } else {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    private void editStudent(Student student) {
        final BlankFragment2 fragment = BlankFragment2.newInstance(student);
        fragment.setActionListener(new BlankFragment2.ActionListener() {
            @Override
            public void save(Student student) {
                mSaveTask = new SaveTask();
                mSaveTask.execute(student);
                fragment.dismiss();
            }

            @Override
            public void cancel() {
                //getStudents(false);
                fragment.dismiss();
            }
        });

      /*  getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();*/
      fragment.setCancelable(false);
      fragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public Loader<ArrayList<Student>> onCreateLoader(int id, Bundle args) {
        return new StudentLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Student>> loader, ArrayList<Student> data) {
        BlankFragment1 fragment = BlankFragment1.newInstance(data);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commitAllowingStateLoss();

        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Student>> loader) {

    }

    public void OnClick(View v) {
        editStudent(new Student());
    }

    @Override
    public void click(Student student) {
        editStudent(student);
    }

    class SaveTask extends AsyncTask<Student, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage("Loading...");
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected Boolean doInBackground(Student... students) {
            DataBaseHelper helper = new DataBaseHelper(MainActivity.this);
            return helper.save(students[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (mDialog != null) {
                mDialog.dismiss();
            }

            getStudents(true);
        }
    }
}
