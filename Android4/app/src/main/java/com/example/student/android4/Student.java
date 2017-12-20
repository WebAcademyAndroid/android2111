package com.example.student.android4;

import android.widget.ListView;

public class Student {
    public String FirstName;
    public String LastName;
    public int Age;

    public Student() {
    }

    public Student(String firstName, String lastName, int age) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
    }

    @Override
    public String toString() {
        return String.format("%s %s, \n%d", FirstName, LastName, Age);
    }
}
