package com.example.student.android2;


import java.io.Serializable;

public class Student implements Serializable{
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
        return String.format("%s %s, age: %d", FirstName, LastName, Age);
    }
}
