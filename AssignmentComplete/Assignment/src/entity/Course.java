/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.*;


public class Course implements Comparable<Course> {
    private ListInterface<TutorialGroup> tutorialGroups;
    private Tutor tutor;
    private String courseCode;
    private String courseName;
    private int creditHours;
    private ListInterface<Programme> associatedPrograms;
    
    //Constructure
    public Course(String courseCode, String courseName, int creditHours) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.associatedPrograms = new ArrayList<>(); // Initialize associatedPrograms as an empty ArrayList
    }
    
    public Course(ListInterface<TutorialGroup> tutorialGroups, Tutor tutor, String courseCode, String courseName, int creditHours) {
        this.tutorialGroups = tutorialGroups;
        this.tutor = tutor;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }

    public Course(Tutor tutor, String courseCode, String courseName, int creditHours) {
        this.tutor = tutor;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
    }
    
    public ListInterface<Programme> getAssociatedPrograms() {
        return associatedPrograms;
    }
    
    //Getter
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public float getCreditHours() {
        return creditHours;
    }
    
    //Setter
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    
    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void assignTutor(Tutor tutorToAdd) {
        this.tutor = tutorToAdd;
    }

    public ListInterface<TutorialGroup> getTutorialGroups() {
        return tutorialGroups;
    }

    public void setTutorialGroups(ListInterface<TutorialGroup> tutorialGroups) {
        this.tutorialGroups = tutorialGroups;
    } 
    
      // Implementing Comparable
    @Override
    public int compareTo(Course other) {
        return this.courseName.compareTo(other.courseName);
    }
    
    
    
    @Override
    public String toString() {
        //return "Course Code" + courseCode + ", courseName=" + courseName + ", creditHours=" + creditHours + '}';
        return String.format("%-6s%-14s%-40s%-10s", "", courseCode, courseName, creditHours);
    }
}
