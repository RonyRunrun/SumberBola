package com.sumberbola.service;

/**
 * Created by Rony R on 3/10/2017.
 */

public class Student {

    //Variables that are in our json
    private int StudentId;
    private String StudentName;
    private String StudentMarks;

    //Getters and setters
    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int bookId) {
        this.StudentId = StudentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String name) {
        this.StudentName = StudentName;
    }

    public String getStudentMarks() {
        return StudentMarks;
    }

    public void setStudentMarks(String price) {
        this.StudentMarks = StudentMarks;
    }

}