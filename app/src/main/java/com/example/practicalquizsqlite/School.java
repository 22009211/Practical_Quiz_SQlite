package com.example.practicalquizsqlite;

public class School {
    private int id;
    private int numOfStudent;
    private String schoolName;

    public School(int id, int numOfStudent, String schoolName) {
        this.id = id;
        this.numOfStudent = numOfStudent;
        this.schoolName = schoolName;
    }

    public String toString(){
        return  schoolName+": "+numOfStudent;
    }
}