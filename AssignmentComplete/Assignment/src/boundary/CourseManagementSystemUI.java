/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import entity.Course;
import entity.Faculty;
import entity.Programme;
import java.util.Scanner;

/**
 *
 * @author shuej
 */
public class CourseManagementSystemUI {

    Scanner scanner = new Scanner(System.in);

    public String getWelcomeCourseMenu() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("\nWelcome to Course Management\n");
        System.out.println("-".repeat(50) + "\n");

        System.out.println("1. Manage programme to course");
        System.out.println("2. Manage course from programme");
        System.out.println("3. Summary Report");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    public String getManageProgramMenu() {
        System.out.println("\nManage programme to course");
        System.out.println("1. Add a programme to course");
        System.out.println("2. Remove programs from a course");
        System.out.println("3. Amend course details for a programme");
        System.out.println("4. List all course for a programme");
        System.out.println("5. Back to Course Menu");
        System.out.print("Enter choice: ");
        String choice1 = scanner.nextLine();
        System.out.println();
        return choice1;
    }

    public String getManageCourseMenu() {
        System.out.println("\nManage course from programme");
        System.out.println("1. Add a new course to programme");
        System.out.println("2. Remove a course from a programme");
        System.out.println("3. Search course offered for a programme");
        System.out.println("4. List course taken by different faculities");
        System.out.println("5. Back to Course Menu");
        System.out.print("Enter choice: ");
        String choice2 = scanner.nextLine();
        System.out.println();
        return choice2;
    }

    public String getReportCourseMenu() {
        System.out.println("\nSummary Reports");
        System.out.println("1. Total of Courses Taken by Different Programs");
        System.out.println("2. Credit Hours for Programme");
        System.out.println("3. Credit Hours for Course");
        System.out.println("4. Back to Course Menu");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    public String outputForProgramOption() {
        // Prompt the user to choose between adding a new program or adding a program to a course
        System.out.println("Choose an option:");
        System.out.println("1. Add a new programme");
        System.out.println("2. Add existing programme to course");
        System.out.print("Enter choice: "); // Prompt for input 
        String addChoice = scanner.nextLine(); // Consume newline character
        return addChoice;
    }

    public String outputForCourseOption() {
        // Prompt the user to choose between adding a new course or adding n existing course to a program
        System.out.println("Choose an option:");
        System.out.println("1. Add a new course to a program");
        System.out.println("2. Add an existing course to a program");
        System.out.print("Enter choice: "); // Prompt for input  
        String addChoice = scanner.nextLine(); // Consume newline character
        return addChoice;
    }

    //Add Program method Output String
    public int numberProgramChoice() {
        System.out.println("Choose the number of the program to add courses: ");
        int programChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return programChoice;
    }

    public String[] enterNumberOfCourseToAdd() {
        System.out.print("Enter the numbers of the courses to add (separated by spaces): ");
        String[] courseChoices = scanner.nextLine().trim().split("\\s+");
        return courseChoices;
    }

    public void invalidCourseChoice(int courseChoice) {
        System.out.println("Invalid course choice: " + courseChoice);
    }

    //Remove Program output String
    public int enterCourseChoiceToRemove() {
        System.out.print("Enter the number of the course to remove : ");
        int courseChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return courseChoice;
    }

    public int enterProgramChoiceToRemove() {
        System.out.print("\nEnter the number of the program to remove: ");
        int programChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return programChoice;
    }

    //Amend Course Details
    public int enterNumberCourseAmend() {
        System.out.print("Enter the number of the course to amend details: ");
        int courseChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return courseChoice;
    }

    public String enterCourseCodeAmend() {
        System.out.print("\nEnter new course code: ");
        String newCourseCode = scanner.nextLine().trim();
        return newCourseCode;
    }

    public String enterCourseNameAmend() {
        System.out.print("Enter new course name: ");
        String newCourseName = scanner.nextLine().trim();
        return newCourseName;
    }

    public int enterCourseCreditHoursAmend() {
        System.out.print("Enter new Credit Hours: ");
        int newCreditHours = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return newCreditHours;
    }

    //List all Course
    public int enterNumberProgramToList() {
        System.out.print("Enter the number of the program to list courses: ");
        int programChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return programChoice;
    }

    //Add Program method Output String
    public int enterNumberCourseToAdd() {
        System.out.print("Enter the number of the course to add to a program: ");
        int courseChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return courseChoice;
    }

    public int enterNumberProgramToAdd() {
        System.out.print("Enter the number of the program to add the course to: ");
        int programChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return programChoice;
    }

    //Remove course from program
    public int enterNumberCourseToRemove() {
        System.out.print("Enter the number of the course to remove from program: ");
        int courseChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return courseChoice;
    }

    public int enterNumberProgramToRemove() {
        System.out.print("\nEnter the number of the program to remove the course from: ");
        int programChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return programChoice;
    }

    //Search
    public int enterNumberProgramToSearch() {
        System.out.print("Enter the number of the program to search courses: ");
        int programChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return programChoice;
    }

    //Course Take By Faculty
    public int enterNumberFacultyToAdd() {
        System.out.print("Enter the number of the faculty to add a course taken: ");
        int facultyChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return facultyChoice;
    }

    public int enterNumberCourseToAddAsTaken() {
        System.out.print("Enter the number of the course to add as taken: ");
        int courseChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return courseChoice;
    }

    public String questionAddAnother() {
        System.out.print("Do you want to add another course? (yes/no): ");
        String addAnother = scanner.nextLine().trim().toLowerCase();
        return addAnother;
    }

    //List Output Program and Course String
    public void listAllProgram(String outputStr) {
        System.out.println("\nList of Programme:\n" + outputStr);
    }

    public void listAllCourse(String outputStr) {
        System.out.println("\nList of Course:\n" + outputStr);
    }

    public void listAllFaculty(String outputStr) {
        System.out.println("\nList of Faculty:\n" + outputStr);
    }

    public void printProgramDetails(Programme programme) {
        System.out.println("Programme Details");
        System.out.println("Programme Code:" + programme.getProgramCode());
        System.out.println("Programme Name: " + programme.getProgramName());
    }

    public void printCourseDetails(Course course) {
        System.out.println("Course Details");
        System.out.println("Course Code:" + course.getCourseCode());
        System.out.println("Course Name: " + course.getCourseName());
        System.out.println("Credit Hours: " + course.getCreditHours());
    }

    public void printFacultyDetails(Faculty faculty) {
        System.out.println("Faculty Details");
        System.out.println("Faculty Code:" + faculty.getFacultyCode());
        System.out.println("Faculty Name: " + faculty.getFacultyName());
    }

    //Input Program and Course String
    public String inputProgramCode() {
        System.out.print("Enter a new programme code: ");
        String programCode = scanner.nextLine();
        return programCode;
    }

    public String inputProgramName() {
        System.out.print("Enter a new programme name: ");
        String programName = scanner.nextLine();
        return programName;
    }

    public String inputDuration() {
        System.out.print("Enter a new duration: ");
        String duration = scanner.nextLine();
        return duration;
    }

    public Programme inputProgramDetails() {
        String programCode = inputProgramCode();
        String programName = inputProgramName();
        System.out.println();
        return new Programme(programCode, programName);
    }

    public String inputCourseCode() {
        System.out.print("Enter a new course code: ");
        String courseCode = scanner.nextLine();
        return courseCode;
    }

    public String inputCourseName() {
        System.out.print("Enter a new course name: ");
        String courseName = scanner.nextLine();
        return courseName;
    }

    public int inputCreditHours() {
        System.out.print("Enter a new credit hours: ");
        int creditHours = scanner.nextInt();
        scanner.nextLine();
        return creditHours;
    }

    public Course inputCourseDetails() {
        String courseCode = inputCourseCode();
        String courseName = inputCourseName();
        int creditHours = inputCreditHours();
        System.out.println();
        return new Course(courseCode, courseName, creditHours);
    }

}
