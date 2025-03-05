package boundary;

import adt.ListInterface;
import entity.*;
import java.util.Scanner;

import java.util.Scanner;

public class TeachingAssignemntUI {

    Scanner scanner = new Scanner(System.in);

    public String getMenuChoice() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("\t  Teaching Assignment Menu");
        System.out.println("-".repeat(50) + "\n");

        System.out.println("1.  Assign tutor to courses");
        System.out.println("2.  Assign tutorial groups to a tutor");
        System.out.println("3.  Add tutors to a tutorial group for a course");
        System.out.println("4.  Search courses under a tutor");
        System.out.println("5.  Search tutors for a course (Tut, Prac, Lec)");
        System.out.println("6.  List tutors and tutorial groups for a course");
        System.out.println("7.  List courses for each tutor");
        System.out.println("8.  Filter tutors based on criteria");
        System.out.println("9.  Generate summary reports");
        System.out.println("0.  Back to Main Menu");

        System.out.print("\nEnter choice: ");
        String choice = scanner.nextLine();
        System.out.println();

        return choice;
    }

    public String generateTeachingAssignmentReportMenu() {
        System.out.println("\nTeaching Assignment Summary Report ");
        System.out.println("1. Course Assignment Summary Report");
        System.out.println("2. Tutorial Group Assignment Summary Report");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllTutors(String outputStr) {
        System.out.println("\nList of Tutors:\n" + outputStr);
    }

    public void listAllCourses(String outputStr) {
        System.out.println("\nList of Course:\n" + outputStr);
    }

    public void listAllTutorialGroups(String outputStr) {
        System.out.println("\nList of Tutorial Groups:\n" + outputStr);
    }

    public void printTutorDetails(Tutor tutor) {
        System.out.println("Tutor Details");
        System.out.println("Tutor ID:" + tutor.getTutorID());
        System.out.println("Tutor name: " + tutor.getName());
        System.out.println("Tutor role: " + tutor.getTutorRole());
    }

    public void printCourseDetails(Course course) {
        System.out.println("Course Details");
        System.out.println("Course Code: " + course.getCourseCode());
        System.out.println("Course Name: " + course.getCourseName());

    }

    public void printTutorialGroupDetails(TutorialGroup tutorialGroup) {
        System.out.println("Tutorial Group Details");
        System.out.println("Group ID: " + tutorialGroup.getGroupNo());

    }

    public String inputTutorID() {
        System.out.print("Enter Tutor ID: ");
        String ID = scanner.nextLine();
        return ID;
    }

    public String inputTutorName() {
        System.out.print("Enter tutor name: ");
        String name = scanner.nextLine();
        return name;
    }

    public String inputTutorRole() {
        System.out.print("Enter tutor role: ");
        String role = scanner.nextLine();
        return role;
    }

    private int inputTutorExperience() {
        System.out.print("Enter tutor experience: ");
        int experience = scanner.nextInt();
        return experience;
    }

    public String inputTutorialGroupID() {
        System.out.print("Enter tutorial group ID: ");
        String groupID = scanner.nextLine();
        return groupID;
    }

    public String inputYear() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter year: ");
        String year = scanner.nextLine().toUpperCase();
        return year;
    }

    public String inputSemester() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter semester: ");
        String semester = scanner.nextLine().toUpperCase();
        return semester;
    }

    public TutorialGroup inputTutorialGroupDetails() {
        String groupNo = inputTutorialGroupID();
        String year = inputYear();
        String semester = inputSemester();
        System.out.println();
        return new TutorialGroup(groupNo, year, semester);
    }

    public String inputCourseCode() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        return courseCode;
    }

    public Tutor inputTutorDetails() {
        String tutorID = inputTutorID();
        String tutorName = inputTutorName();
        String tutorRole = inputTutorRole();
        int tutorExperience = inputTutorExperience();
        return new Tutor(tutorID, tutorName, tutorRole, tutorExperience);
    }

    public int inputMinimumExperience() {
        System.out.print("Enter minimum experience (year): ");
        int experience = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        return experience;
    }

    public void displayTutorialGroup(TutorialGroup tGroup) {
        System.out.println("Tutorial Group Details:");
        System.out.println("Group Number: " + tGroup.getGroupNo());
        System.out.println("Year: " + tGroup.getYear());
        System.out.println("Semester: " + tGroup.getSemester());

    }

    public void displayTutorSearchMenu() {
        System.out.println("TUTOR SEARCH MENU");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Tutorial Group");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    public String getTutorSearchChoice() {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine(); // Consume newline character
        return choice;
    }

    public String inputSearchKeyword() {
        System.out.print("Enter search keyword: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
