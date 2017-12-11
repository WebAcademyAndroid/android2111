package com.example.student.android2;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentP implements Parcelable {
    public String FirstName;
    public String LastName;
    public int Age;

    public StudentP() {
    }

    public StudentP(String firstName, String lastName, int age) {
        FirstName = firstName;
        LastName = lastName;
        Age = age;
    }

    @Override
    public String toString() {
        return String.format("%s %s, age: %d", FirstName, LastName, Age);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.FirstName);
        dest.writeString(this.LastName);
        dest.writeInt(this.Age);
    }

    protected StudentP(Parcel in) {
        this.FirstName = in.readString();
        this.LastName = in.readString();
        this.Age = in.readInt();
    }

    public static final Creator<StudentP> CREATOR = new Creator<StudentP>() {
        @Override
        public StudentP createFromParcel(Parcel source) {
            return new StudentP(source);
        }

        @Override
        public StudentP[] newArray(int size) {
            return new StudentP[size];
        }
    };
}
