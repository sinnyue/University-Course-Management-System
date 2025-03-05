/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.ListInterface;
import entity.Course;
import entity.Programme;
import entity.Student;
import entity.StudentCourse;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author shuej
 */
public class StudentRegistrationSystemUI {

    private static final Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    public String getMenuChoice() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("\n Welcome to Student Registration Management System");
        System.out.println("\n" + "-".repeat(50));

        System.out.println("1. Add new student");
        System.out.println("2. Remove existed students");
        System.out.println("3. Amend student details");
        System.out.println("4. Search student for registered courses");
        System.out.println("5. Add students to a few courses(main,elective,resit,repeat)");
        System.out.println("6. Remove a student from a course (main,elective) registration");
        System.out.println("7. Calculate fee paid for registered courses");
        System.out.println("8. Filters students for courses based on criteria");
        System.out.println("9. Report Menu");
        System.out.println("10. Back to Main Menu");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    public String getReportChoice() {
        System.out.println("\nREPORT MENU");
        System.out.println("1. Summary report to show the total fees spent by course");
        System.out.println("2. Generate Student Enrollment by Course Category Report");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    //get student name
    public String inputStudentName() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        return name;
    }

    //get student details
    public String inputStudentstuContact() {
        System.out.print("Enter contact number: ");
        String contactNo = scanner.nextLine();
        return contactNo;
    }

    //get interest programme 
    public String inputInterestProgram() {
        System.out.print("Enter programme code: ");
        String programmeCode = scanner.nextLine();
        return programmeCode;
    }

    //get index choice 
    public int inputIndex() {
        System.out.print("Enter index number: ");
        int indexNo = scanner.nextInt();
        return indexNo;
    }
    
    //display registred course title
    public void displayRegisteredCourse() {
        System.out.printf("%-15s %-40s %-15s %-15s\n", "Course Code", "Course Name", "Course Category" , "Course Type");
        System.out.println("================================================================================================================");
    }

    //display registred course
    public void displayRegisteredCourse(String str1, String str2, String str3, String str4) {
        System.out.printf("%-15s %-40s %-15s %-15s\n", str1, str2, str3 , str4);
    }
    
    //display registred course
    public void displayFeedDescription() {
        System.out.println("Lecture - 300 | practical - 200 | tutorial - 200");
    }
    
    //display student information
    public void displayStudentInfo(Student student) {
        // Display the information of the student
        System.out.println("Newly registered student:");
        System.out.println("ID: " + student.getStudentID());
        System.out.println("Name: " + student.getStudentName());
        System.out.println("Programme: " + student.getProgram().getProgramName());
        System.out.println("Contact: " + student.getContactNo());
        // Add more information if needed
    }

    // Method to select a program
    public Programme selectProgramme(ListInterface<Programme> programme) {

        // Prompt user to select a program
        // Implement logic to handle user input and return the selected program
        int programIndex = inputIndex(); // Assuming inputProgramIndex() returns the selected program index
        if (programIndex >= 0 && programIndex < programme.size()) {
            return programme.get(programIndex);
        } else {
            // Handle invalid input
            return null;
        }
    }

    public void displayFees(String str1, String str2, int str3) {
        System.out.printf("%-15s %-35s $%-20d\n", str1, str2, str3);
    }

    //list all the student
    public void listAllStudent(String outputStr) {
        System.out.println("\nList of Student:");
        System.out.printf("%-6s%-14s%-21s%-14s%-40s%-14s\n", "", "Student ID", "Student Name","Program Code", "Program Name","Contact Nummber");
        System.out.println("================================================================================================================\n" + outputStr);
    }

    //list all the course 
    public void listAllCourse(String outputStr) {
        System.out.println("\nList of Course:");
        System.out.printf("%-6s%-14s%-40s%-10s\n", "", "courseCode", "courseName", "creditHours");
        System.out.println("=========================================================================\n" + outputStr);
    }
    //list all the course 

    public void listAllCourse() {
        System.out.println("\nList of Course:");
        System.out.printf("%-6s%-14s%-40s%-10s\n", "", "courseCode", "courseName", "creditHours");
        System.out.println("=========================================================================\n");
    }

    //list all the programme 
    public void listAllProgramme(String outputStr) {
        System.out.println("\nList of Program:");
        System.out.printf("%-6s%-14s%-40s\n", "", "Program code", "Program Name");
        System.out.println("======================================================\n" + outputStr);
    }

    //summary report title 1
    public void reportTitle1() {
        System.out.println("Student Enrollment by Course Category Report: Breakdown of Student Enrollment by Course Category\n");
        System.out.printf("%-20s %-20s\n", "Course Category", "Total Students Enrolled");
        System.out.println("------------------------------------------------------------------------");
    }

    //summary report title 2
    public void reportTitle2() {
        System.out.println("Course Report: Total Fees Spent by Course\n");
        System.out.printf("%-15s %-30s %-20s\n", "Course Code", "Course Name", "Total Fees Spent");
        System.out.println("-----------------------------------------------------------");
    }

    //summary report title 2
    public void displayReport1(int str1, int str2, int str3, int str4) {
        // Display the breakdown of student enrollment by course category
        System.out.printf("%-20s %-20d\n", "Main", str1);
        System.out.printf("%-20s %-20d\n", "Elective", str2);
        System.out.printf("%-20s %-20d\n", "Resit", str3);
        System.out.printf("%-20s %-20d\n", "Repeat", str4);
    }

    //input the student ID
    public String inputStudentID() {
        System.out.print("Enter the ID of the student: ");
        return scanner.nextLine();
    }

    //input the course code
    public String inputCourseCode() {
        System.out.print("Enter the code of the course: ");
        String courseCode = scanner.nextLine();
        return courseCode;
    }

    //input the course category
    public String inputCourseCategory() {
        System.out.print("Enter course category (main, elective, resit, repeat): ");
        String category = scanner.nextLine().trim().toLowerCase(); // Normalize input to lowercase
        return category;
    }

    //input the course type
    public String inputCourseType() {
        System.out.print("Enter course type (tutorial, practical, lecture): ");
        String type = scanner.nextLine().trim().toLowerCase(); // Normalize input to lowercase
        return type;
    }

    public void displayFilterCriteria(String courseType, String courseCategory) {
        System.out.println("Filtering results for Course Type: " + courseType + " and Course Category: " + courseCategory);
    }

    public void displayTotalFeeMessage(double totalFee) {
        System.out.println("\nTotal fee paid for registered courses: $" + totalFee);
    }

    public void displayStudentCourseDetails(StudentCourse sc) {
        Student student = sc.getStudent();
        Course course = sc.getCourse();
        System.out.println("Student ID: " + student.getStudentID() + ", Name: " + student.getStudentName()
                + ", Course Code: " + course.getCourseCode() + ", Course Name: " + course.getCourseName()
                + ", Course Type: " + sc.getCourseType() + ", Course Category: " + sc.getCourseCategory());
    }
}
