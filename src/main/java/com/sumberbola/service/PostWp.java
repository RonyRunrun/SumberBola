package com.sumberbola.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rony R on 3/7/2017.
 */

public class PostWp {
    @SerializedName("StudentId")
    @Expose
    private String studentId;
    @SerializedName("StudentName")
    @Expose
    private String studentName;
    @SerializedName("StudentMarks")
    @Expose
    private String studentMarks;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(String studentMarks) {
        this.studentMarks = studentMarks;
    }

}