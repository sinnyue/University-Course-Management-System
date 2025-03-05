/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.*;

/**
 *
 * @author shuej
 */


public class Faculty { //extends program, course
    private String facultyCode;
    private String facultyName;
    private ListInterface<Course> coursesTaken;

    public Faculty(String facultyCode, String facultyName) {
        this.facultyCode = facultyCode;
        this.facultyName = facultyName;
        this.coursesTaken = new ArrayList<>(); // Initialize the list of courses
    }
    
    // Method to get the list of courses taken by the faculty
    public ListInterface<Course> getCoursesTaken() {
        return coursesTaken;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public String toString() {
        return "faculty{" + "facultyCode=" + facultyCode + ", facultyName=" + facultyName + '}';
    }
    
    
}

