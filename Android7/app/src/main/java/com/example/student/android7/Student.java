package com.example.student.android7;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("FirstName")
    public String FirstName;

    @SerializedName("LastName")
    public String LastName;

    @SerializedName("Age")
    public int Age;

    public Student(String firstName, String lastName, int age) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Age = age;
    }
}
