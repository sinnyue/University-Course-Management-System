/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class Student implements Comparable<Student>{
    private String studentID;
    private String studentName;
    private String contactNo;
    private String groupNo;
    private Programme program;
    

    public Student(){
    
    }

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
        
    }

    public Student(String studentID, String studentName, Programme program,String contactNo ) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.contactNo = contactNo;
        this.program = program;
    }
    
    public Student(String studentID, String studentName, Programme program) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.program = program;
    }

   public Student(String studentID, String studentName, String groupNo) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.groupNo = groupNo;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public Programme getProgram() {
        return program;
    }

    public void setProgram(Programme program) {
        this.program = program;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

  @Override
    public String toString() {
        //return "Student{" + "studentID=" + studentID + ", studentName=" + studentName + ", program=" + program  + ", contactNo=" + contactNo+ '}';
        return String.format("%-6s%-14s%-15s%-10s%-11s", "", studentID, studentName,program,contactNo);
    }

      // Implement compareTo method
    @Override
    public int compareTo(Student other) {
        // Example comparison based on studentID
        return this.studentID.compareTo(other.studentID);
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentID != null ? studentID.equals(student.studentID) : student.studentID == null;
    }


   
    
    
}
