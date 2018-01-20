package com.example.student.android12;

public class Widget {

    public static final String TABLE_NAME = "Widgets";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ID_STUDENT = "_idStudent";
    public static final String COLUMN_ID_WIDGET = "_idWidget";

    public long id;
    public long idStudent;
    public long idWidget;

    public Widget() {
    }

    public Widget(long idStudent, long idWidget) {
        this.idStudent = idStudent;
        this.idWidget = idWidget;
    }
}
