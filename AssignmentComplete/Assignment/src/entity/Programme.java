/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author shuej
 */
import adt.*;
import java.util.Objects;

/**
 *
 * @author shuej
 */
public class Programme implements Comparable<Programme>{
    private String programCode;
    private String programName;
    private ListInterface<Student> students;
    private ListInterface<TutorialGroup> tutorialGroups;
    private ListInterface<Course> coursesOffered;
    
    public Programme(){
    
    
    }

  
    public Programme(String programCode, String programName ) {
        this.programCode = programCode;
        this.programName = programName;
        this.tutorialGroups =  new ArrayList<>();
    }

  
    
    public Programme(String programCode, String programName, ListInterface<TutorialGroup> tutorialGroups) {
        this.programCode = programCode;
        this.programName = programName;
        this.tutorialGroups = tutorialGroups;
    }

    public Programme(String programCode, String programName, ListInterface<Student> students, ListInterface<TutorialGroup> tutorialGroups) {
        this.programCode = programCode;
        this.programName = programName;
        this.students = students;
        this.tutorialGroups = tutorialGroups;
    }

 
    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }


    public ListInterface<TutorialGroup> getTutorialGroups() {
        return tutorialGroups;
    }

    public void setTutorialGroups(ListInterface<TutorialGroup> tutorialGroups) {
        this.tutorialGroups = tutorialGroups;
    }

    public ListInterface<Student> getStudents() {
        return students;
    }

    public void setStudents(ListInterface<Student> students) {
        this.students = students;
    }

    public ListInterface<Course> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(ListInterface<Course> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

   
  
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  // Check for reference equality
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;  // Check for null and ensure the object is the same class
        }
        Programme that = (Programme) obj;  // Cast safely after checking the class
        return Objects.equals(this.programCode, that.programCode) &&  // Compare programCode
               Objects.equals(this.programName, that.programName) ; // Compare programName
              
    }


     // Implementing Comparable
    @Override
    public int compareTo(Programme other) {
        return this.programCode.compareTo(other.programCode);
    }
    

    @Override
    public String toString() {
        //return "Programme{" +"programCode='" + programCode + '\'' +", programName='" + programName + '\'' + '}';
        return String.format("%-6s%-14s%-40s", "", programCode, programName);
    }
    
}
