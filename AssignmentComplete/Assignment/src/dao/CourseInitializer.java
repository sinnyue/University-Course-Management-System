/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Course;
import entity.Faculty;
import entity.Programme;

/**
 *
 * @author shuej
 */
public class CourseInitializer {
    
    // Constructor to initialize the program and course lists with hard-coded values
    public CourseInitializer() {
//        programList = programInitializer();
        courseList = courseInitializer();
        facultyList = facultyInitializer();
    }
    
    // List to hold program entities
    private ListInterface<Programme> programList;
    
    // List to hold course entities
    private ListInterface<Course> courseList;
    
    private ListInterface<Faculty> facultyList;
    
    
//  //  Method to return a collection of with hard-coded entity values
//  public ListInterface<Programme> programInitializer() {
//    ListInterface<Programme> pList = new ArrayList<>();
//    pList.add(new Programme("BACC", "Bachelor of Accounting"));
//    pList.add(new Programme("BBA", "Bachelor of Business Administrator"));
//    pList.add(new Programme("BEE", "Bachelor of Electric Engineering"));
//    pList.add(new Programme("BM", "Bachelor of Marketing"));
//    pList.add(new Programme("BSE", "Bachelor of Software Engineering"));
//    return pList;
//  }
//  
  //  Method to return a collection of with hard-coded entity values
  public ListInterface<Course> courseInitializer() {
    ListInterface<Course> cList = new ArrayList<>();
    cList.add(new Course("ACC030", "Introduction to Accounting", 3));
    cList.add(new Course("ACC720", "Managerial Accounting", 3));
    cList.add(new Course("CS2042", "Research Methods", 2));
    cList.add(new Course("CS2063", "Data Structure and Algorithms", 3));
    cList.add(new Course("CS2083", "Formal Methods", 3));
    cList.add(new Course("CS3183", "Advanced Database Management", 3));
    cList.add(new Course("IT3343", "Agile Software Development", 3));
    cList.add(new Course("MS2633", "Advanced Discrete mathematics", 3));
    cList.add(new Course("ITT450", "Information and IT Security", 3));
    cList.add(new Course("E3232", "Entrepreneurship", 2));
    cList.add(new Course("PD684", "Principles of Finance", 3));
    return cList;
  }
  
  //  Method to return a collection of with hard-coded entity values
    public ListInterface<Faculty> facultyInitializer() {
    ListInterface<Faculty> fList = new ArrayList<>();
   // Initialize faculty members
        Faculty faculty1 = new Faculty("FAFB", "Faculty of Accountancy, Finance and Business");
        Faculty faculty2 = new Faculty("FOCS", "Faculty of Computing and Information Technology");
        Faculty faculty3 = new Faculty("FOET", "Faculty of Engineering and Technology");

        // Add faculty members to the list
        fList.add(faculty1);
        fList.add(faculty2);
        fList.add(faculty3);

        return fList;
  }
    
    // Getter method for program list
    public ListInterface<Programme> getProgramList() {
        return programList;
    }
  
    // Getter method for course list
    public ListInterface<Course> getCourseList() {
        return courseList;
    }
    
    public ListInterface<Faculty> getFacultyList() {
        return facultyList;
    }
  
    
}
