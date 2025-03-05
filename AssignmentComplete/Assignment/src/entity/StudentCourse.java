/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class StudentCourse {
    private Student student;
    private Course course;
    private String courseCategory;
    private String courseType;
    private double courseFee;
    
    public StudentCourse(Student student, Course course, String courseCategory, String courseType, float courseFee) {
        this.student = student;
        this.course = course;
        this.courseCategory = courseCategory;
        this.courseType = courseType;
        this.courseFee = courseFee;
    }

    public StudentCourse(Student student, Course course, String courseCategory, String courseType) {
        this.student = student;
        this.course = course;
        this.courseCategory = courseCategory;
        this.courseType = courseType;
    }

    
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getCourseType() {
        return courseType;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public double getCourseFee() {
        return courseFee;
    }
    

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }
    
    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    

    @Override
    public String toString() {
        return String.format("%-20s %-20s", courseCategory, courseType);
    }
    
}
