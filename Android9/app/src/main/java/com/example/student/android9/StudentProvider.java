package com.example.student.android9;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

public class StudentProvider extends ContentProvider {

    private DataBaseHelper mDBHelper;
    private SQLiteDatabase db;

    static final String AUTHORITY = "com.example.student.android9";
    static final String STUDENTS_PATH = "students";

    public static final Uri STUDENT_URI = Uri.parse("content://" + AUTHORITY + "/" + STUDENTS_PATH);

    static final String STUDENT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + STUDENTS_PATH;
    static final String STUDENT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + STUDENTS_PATH;


    static final int URI_STUDENTS = 1;
    static final int URI_STUDENTS_ID = 2;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, STUDENTS_PATH, URI_STUDENTS);
        uriMatcher.addURI(AUTHORITY, STUDENTS_PATH + "/#", URI_STUDENTS_ID);
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new DataBaseHelper(getContext());

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = mDBHelper.getWritableDatabase();

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(Student.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case URI_STUDENTS_ID:
                queryBuilder.appendWhere(Student.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
        }

        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = mDBHelper.getWritableDatabase();
        long id = 0;

        switch (uriMatcher.match(uri)) {
            case URI_STUDENTS:
                id = db.insert(Student.TABLE_NAME, null, values);
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(STUDENT_URI, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = mDBHelper.getWritableDatabase();
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case URI_STUDENTS:
                count = db.delete(Student.TABLE_NAME, selection, selectionArgs);
                break;
            case URI_STUDENTS_ID:
                String id = uri.getLastPathSegment();
                if (selection == null || selection.isEmpty()) {
                    count = db.delete(Student.TABLE_NAME, Student.COLUMN_ID + "=" + id, null);
                } else {
                    count = db.delete(Student.TABLE_NAME, Student.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db = mDBHelper.getWritableDatabase();
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case URI_STUDENTS:
                count = db.update(Student.TABLE_NAME, values, selection, selectionArgs);
                break;
            case URI_STUDENTS_ID:
                String id = uri.getLastPathSegment();
                if (selection == null || selection.isEmpty()) {
                    count = db.update(Student.TABLE_NAME, values, Student.COLUMN_ID + "=" + id, null);
                } else {
                    count = db.update(Student.TABLE_NAME, values, Student.COLUMN_ID + "=" + id + " and " + selection, selectionArgs);
                }
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_STUDENTS:
                return STUDENT_CONTENT_TYPE;
            case URI_STUDENTS_ID:
                return STUDENT_CONTENT_ITEM_TYPE;
        }

        return null;
    }
}
