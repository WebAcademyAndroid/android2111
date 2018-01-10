package com.example.student.android9_1;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Student>> {

    private ListView mListView;
    private SaveTask mSaveTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);

        getSupportLoaderManager().initLoader(0, null, this);

        findViewById(R.id.buttonAdd).setOnClickListener(saveListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSaveTask != null) {
            mSaveTask.cancel(true);
        }
    }

    @Override
    public Loader<ArrayList<Student>> onCreateLoader(int id, Bundle args) {
        return new StudentLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Student>> loader, ArrayList<Student> students) {
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                students);

        mListView.setAdapter(adapter);

        mListView.setSelection(students.size()-1);
        mListView.smoothScrollToPosition(students.size()-1);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Student>> loader) {

    }

    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mSaveTask = new SaveTask();
            mSaveTask.execute(new Student("Petro", "Petrov", 33));
        }
    };

    public class SaveTask extends AsyncTask<Student, Void, Void> {
        private ProgressDialog mDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage("Wait...");
            mDialog.setCancelable(false);
            mDialog.show();
        }

        @Override
        protected Void doInBackground(Student... students) {
            Student student = students[0];

            try {
                ContentValues values = new ContentValues();
                values.put(Student.COLUMN_FIRST_NAME, student.FirstName);
                values.put(Student.COLUMN_LAST_NAME, student.LastName);
                values.put(Student.COLUMN_AGE, student.Age);

                Uri uri = Uri.parse("content://com.example.student.android9/students");
                getContentResolver().insert(uri, values);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (mDialog != null) {
                mDialog.dismiss();
            }

            getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
        }
    }
}
