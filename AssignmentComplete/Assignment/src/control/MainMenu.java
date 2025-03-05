/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ListInterface;
import boundary.CourseManagementSystemUI;
import boundary.MainMenuUI;
import boundary.StudentRegistrationSystemUI;
import boundary.TutorialGroupManagementUI;
import dao.CourseInitializer;
import dao.ProgramTutorialGroupStudentInitializer;
import dao.StudentProgrammeInitializer;
import entity.Course;
import entity.Faculty;
import entity.Programme;
import entity.Student;
import utility.MessageUI;

/**
 *
 * @author shuej
 */
public class MainMenu {

    private MainMenuUI mainMenuUI = new MainMenuUI();
    private StudentRegistrationSystemUI systemUI = new StudentRegistrationSystemUI();
    private CourseManagementSystemUI courseManageUI = new CourseManagementSystemUI();
    private TutorialGroupManagementUI groupUI = new TutorialGroupManagementUI();
    private ProgramTutorialGroupStudentInitializer PTGdao = new ProgramTutorialGroupStudentInitializer();
    private StudentProgrammeInitializer SPdao = new StudentProgrammeInitializer();
    private CourseManagementSystem courseSystem;
    private TutorialGroupManagement tutorialGroupManagement;
    private StudentRegistrationSystem studentRegistrationSystem;
    private TeachingAssignmentSystem teachingAssignmentSystem;
    private AssignmentTeamManagement assignmentTeamManagement;

    public MainMenu(StudentRegistrationSystem studentRegistrationSystem, CourseManagementSystem courseManagementSystem, TutorialGroupManagement tutorialGroupManagement, TeachingAssignmentSystem teachingAssignmentSystem, AssignmentTeamManagement assignmentTeamManagement) {
        this.courseSystem = courseManagementSystem;
        this.tutorialGroupManagement = tutorialGroupManagement;
        this.studentRegistrationSystem = studentRegistrationSystem;
        this.teachingAssignmentSystem = teachingAssignmentSystem;
        this.assignmentTeamManagement = assignmentTeamManagement;
    }

    public void runMainMenu() {
        String choice;
        do {
            choice = mainMenuUI.getMenuChoice(); // Use mainMenuUI to get menu choice
            switch (choice) {
                case "1":
                    studentRegistrationSystem.runStudentMaintenance();
                    break;
                case "2":
                    courseSystem.runWelcomeCourseMenu();
                    courseSystem.runManageProgramMenu();
                    courseSystem.runManageCourseMenu();
                    courseSystem.runReportCourseMenu();
                    break;

                case "3":
                    tutorialGroupManagement.runTutorialGroup();
                    tutorialGroupManagement.manageProgramTutorialGroups();
                    tutorialGroupManagement.manageStudentTutorialGroups();
                    tutorialGroupManagement.listAllStudents();
                    tutorialGroupManagement.generateReports();
                    break;

                case "4":
                    teachingAssignmentSystem.runProductMaintenance(tutorialGroupManagement);
                    break;
                case "5":
                    assignmentTeamManagement.startMenu(tutorialGroupManagement);
                    break;
                case "6":
                    MessageUI.displayExitMessage();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "6"); // Exit loop when choice is 6
    }

    public static void main(String[] args) {

        CourseManagementSystem courseSystem = new CourseManagementSystem();
        ListInterface<Programme> programList = courseSystem.getProgramList();
        ListInterface<Course> courseList = courseSystem.getCourseList();

        StudentRegistrationSystem studentRegistrationSystem = new StudentRegistrationSystem(courseSystem.getProgramList());
        ListInterface<Student> studentList = studentRegistrationSystem.getStudentList();
        TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement(studentRegistrationSystem.getStudentList(), courseSystem.getProgramList());
        TeachingAssignmentSystem teachingAssignmentSystem = new TeachingAssignmentSystem(courseSystem.getCourseList());
        AssignmentTeamManagement assignmentTeamManagement = new AssignmentTeamManagement();
//        AssignmentTeamSystem assignmentTeamSystem = new AssignmentTeamSystem();

        MainMenu mainMenu = new MainMenu(studentRegistrationSystem, courseSystem, tutorialGroupManagement, teachingAssignmentSystem, assignmentTeamManagement);
        // Set the MainMenu object in StudentRegistrationSystem
        studentRegistrationSystem.setMainMenu(mainMenu);
        courseSystem.setMainMenu(mainMenu);
        tutorialGroupManagement.setMainMenu(mainMenu);
        teachingAssignmentSystem.setMainMenu(mainMenu);
        assignmentTeamManagement.setMainMenu(mainMenu);
        mainMenu.runMainMenu();

    }

}
