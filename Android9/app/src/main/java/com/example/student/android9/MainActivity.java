package com.example.student.android9;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Student>> {

    private SaveTask mSaveTask;
    private SaveManyTask mSaveManyTask;
    private GetTask mGetTask;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.listView);

        getStudents(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSaveTask != null) {
            mSaveTask.cancel(true);
        }
        if (mSaveManyTask != null) {
            mSaveManyTask.cancel(true);
        }
        if (mGetTask != null) {
            mGetTask.cancel(true);
        }
    }

    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mSaveTask = new SaveTask();
                mSaveTask.execute(new Student("Ivan", "Ivanov", 22));
                break;
            case R.id.button2:
                mSaveManyTask = new SaveManyTask();
                mSaveManyTask.execute(new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22),
                        new Student("Ivan", "Ivanov", 22));
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
            case R.id.button9:
                break;
            case R.id.button10:
                break;
        }
    }

    private void getStudents(boolean restart) {
      /*  mGetTask = new GetTask();
        mGetTask.execute();*/

        if (restart) {
            getSupportLoaderManager().restartLoader(0, null, this);
        } else {
            getSupportLoaderManager().initLoader(0, null, this);
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
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Student>> loader) {

    }

    public class SaveTask extends AsyncTask<Student, Void, Long> {
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
        protected Long doInBackground(Student... students) {
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            DataBaseHelper helper = new DataBaseHelper(MainActivity.this);

            return helper.insert(students[0]);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);

            ((Button) findViewById(R.id.button)).setText(String.valueOf(aLong));

            if (mDialog != null) {
                mDialog.dismiss();
            }

            getStudents(true);
        }
    }

    public class SaveManyTask extends AsyncTask<Student, Integer, Boolean> {

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
        protected Boolean doInBackground(Student... students) {
            DataBaseHelper helper = new DataBaseHelper(MainActivity.this);
            boolean result = true;

            try {
                int count = 0;
                for (Student student : students) {
                    TimeUnit.SECONDS.sleep(2);

                    helper.insert(student);

                    count++;
                    publishProgress(count, students.length);
                }
            } catch (Exception e) {
                result = false;
            }

            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mDialog.setMessage(String.format("Save %d students from %d", values[0], values[1]));
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

    public class GetTask extends AsyncTask<Void, Void, ArrayList<Student>> {
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
        protected ArrayList<Student> doInBackground(Void... voids) {
            DataBaseHelper helper = new DataBaseHelper(MainActivity.this);
            return helper.getStudents();
        }

        @Override
        protected void onPostExecute(ArrayList<Student> students) {
            super.onPostExecute(students);

            if (mDialog != null) {
                mDialog.dismiss();
            }

            ArrayAdapter<Student> adapter = new ArrayAdapter<>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    students);

            mListView.setAdapter(adapter);
        }
    }
}
