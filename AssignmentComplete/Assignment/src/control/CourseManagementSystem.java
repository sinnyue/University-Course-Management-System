/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrayList;
import adt.ListInterface;
import boundary.CourseManagementSystemUI;
import boundary.MainMenuUI;
import boundary.TutorialGroupManagementUI;
import dao.CourseInitializer;
import dao.ProgramTutorialGroupStudentInitializer;
import entity.Course;
import entity.Faculty;
import entity.Programme;
import utility.MessageUI;
import utility.formatting;

/**
 *
 * @author shuej
 */
public class CourseManagementSystem {

    private MainMenu mainMenu;
    private ListInterface<Programme> programList = new ArrayList<>();
    private ListInterface<Course> courseList = new ArrayList<>();
    private ListInterface<Faculty> facultyList = new ArrayList<>();
    private CourseManagementSystemUI courseManageUI = new CourseManagementSystemUI();
    private CourseInitializer initializer = new CourseInitializer();
    private ProgramTutorialGroupStudentInitializer PTGdao = new ProgramTutorialGroupStudentInitializer();
    private TutorialGroupManagementUI groupUI = new TutorialGroupManagementUI();
    // Constructor to receive initialized lists

    public CourseManagementSystem() {
        this.programList = PTGdao.initializer();
        this.courseList = initializer.courseInitializer();
        this.facultyList = initializer.facultyInitializer();
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

    public ListInterface<Programme> getProgramList() {
        return programList;
    }

    public void runWelcomeCourseMenu() {
        String choice; //from main menu is course management
        do {
            choice = courseManageUI.getWelcomeCourseMenu();
            switch (choice) {
                case "1":
                    runManageProgramMenu();
                    courseManageUI.getManageProgramMenu();
                    break;
                case "2":
                    runManageCourseMenu();
                    courseManageUI.getManageCourseMenu();
                    break;
                case "3":
                    runReportCourseMenu();
                    courseManageUI.getReportCourseMenu();
                    break;
                case "4":
                    mainMenu.runMainMenu();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "4");
    }

    public void runManageProgramMenu() {
        String choice1;
        do {
            choice1 = courseManageUI.getManageProgramMenu();
            switch (choice1) {
                case "1":
                    // Prompt the user to choose between adding a new program or 
                    //adding a program to a course
                    String addChoice = courseManageUI.outputForProgramOption();
                    ;
                    switch (addChoice) {
                        case "1":
                            displayProgram();
                            String programCode = courseManageUI.inputProgramCode();
                            String programName = courseManageUI.inputProgramName();
                            addNewProgram(programCode, programName);
                            break;
                        case "2":
                            addProgramToCourse();
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                            break;
                    }
                    break;
                case "2":
                    // Remove programs from a course
                    removeProgramFromCourse();
                    break;
                case "3":
                    // Amend course details for a course
                    amendCourseDetails();
                    break;
                case "4":
                    // List all course for a programme
                    listCoursesForProgram();
                    break;
                case "5":
                    runWelcomeCourseMenu();// Back to Course Menu
                    courseManageUI.getWelcomeCourseMenu();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice1 != "5"); // Keep looping until the user chooses to go back
    }

    public void runManageCourseMenu() {
        String choice2;
        do {
            choice2 = courseManageUI.getManageCourseMenu();
            switch (choice2) {
                case "1":
                    // Prompt the user to choose between adding a new course or 
                    //adding an existing course to a program
                    String addChoice = courseManageUI.outputForCourseOption();
                    switch (addChoice) {
                        case "1":
                            addNewCourse();
                            break;
                        case "2":
                            addCourseToProgram();
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                            break;
                    }
                    break;
                case "2":
                    // Remove a course from a programme
                    removeCourseFromProgram();
                    break;
                case "3":
                    // Search courses offered for a programme
                    searchCoursesForProgram();
                    break;
                case "4":
                    // List course taken by different faculties
                    listCoursesTakenByFaculty();
                    break;
                case "5":
                    runWelcomeCourseMenu();// Back to Course Menu
                    courseManageUI.getWelcomeCourseMenu();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice2 != "5");
    }

    public void runReportCourseMenu() {
        String choice2;
        do {
            choice2 = courseManageUI.getReportCourseMenu();
            switch (choice2) {
                case "1":
                    generateCourseSummaryReport();
                    break;
                case "2":
                    generateFirstSummaryReport();
                    break;
                case "3":
                    generateSecondSummaryReport();
                    break;
                case "4":
                    runWelcomeCourseMenu();// Back to Course Menu
                    courseManageUI.getWelcomeCourseMenu();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice2 != "4");
    }

    public void addProgramToCourse() {
        // Display list of programs
        displayProgram();

        // Prompt the user to select a program
        int programChoice = courseManageUI.numberProgramChoice();

        // Validate user input
        if (programChoice < 1 || programChoice > programList.getNumberOfEntries()) {
            MessageUI.invalidProgramChoice();
            return;
        }

        // Get the selected program
        Programme selectedProgram = programList.getEntry(programChoice);

        // Display existing courses within the selected program
        ListInterface<Course> existingCourses = new ArrayList<>();
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            if (course.getAssociatedPrograms().contains(selectedProgram)) {
                existingCourses.add(course);
            }
        }

        if (!existingCourses.isEmpty()) {
            MessageUI.existingCourseFound();
            for (int i = 1; i <= existingCourses.getNumberOfEntries(); i++) {
                Course course = existingCourses.getEntry(i);
                System.out.println(i + ". " + course.getCourseCode() + " - "
                        + course.getCourseName());
            }
            System.out.println();
        } else {
            MessageUI.noExistingCourseFound();
        }

        // Display list of available courses to add
        displayCourse();

        // Prompt the user to select additional courses to add
        String[] courseChoices = courseManageUI.enterNumberOfCourseToAdd();

        // Validate and add selected courses to the program
        for (String choice : courseChoices) {
            int courseChoice = Integer.parseInt(choice);
            if (courseChoice >= 1 && courseChoice <= courseList.getNumberOfEntries()) {
                Course selectedCourse = courseList.getEntry(courseChoice);
                // Update associatedPrograms list for the selected course
                selectedCourse.getAssociatedPrograms().add(selectedProgram);
                System.out.println("Course " + selectedCourse.getCourseCode()
                        + " added to program " + selectedProgram.getProgramCode());
            } else {
                courseManageUI.invalidCourseChoice(courseChoice);
            }
        }
    }

    public void addNewProgram(String programCode, String programName) {

        // Create a new Programme object using the user input
        Programme newProgram = new Programme(programCode, programName);

        // Add the new program to the program list
        programList.add(newProgram);
        programList.bubbleSort();
        // Display updated list of programs
        displayProgram();

    }

    public void removeProgramFromCourse() {
        // Display list of courses
        displayCourse();

        // Prompt the user to select a course
        int courseChoice = courseManageUI.enterCourseChoiceToRemove();

        // Validate user input
        if (courseChoice < 1 || courseChoice > courseList.getNumberOfEntries()) {
            MessageUI.invalidCourseChoice();
            return;
        }

        // Get the selected course
        Course selectedCourse = courseList.getEntry(courseChoice);

        // Display programs associated with the selected course
        ListInterface<Programme> associatedPrograms = selectedCourse.getAssociatedPrograms();
        if (associatedPrograms.isEmpty()) {
            //System.out.println("No programs associated with this course.");
            MessageUI.noProgramAssociatedWithCourse();
            return;
        }
        //System.out.println("Programs associated with the selected course:");
        MessageUI.yesProgramAssociatedWithCourse();
        for (int i = 1; i <= associatedPrograms.getNumberOfEntries(); i++) {
            Programme program = associatedPrograms.getEntry(i);
            System.out.println(i + ". " + program.getProgramCode() + " - " + program.getProgramName());
        }

        // Prompt the user to select a program to remove
        int programChoice = courseManageUI.enterProgramChoiceToRemove();

        // Validate user input
        if (programChoice < 1 || programChoice > associatedPrograms.getNumberOfEntries()) {
            MessageUI.invalidProgramChoice();
            return;
        }

        // Get the selected program
        Programme selectedProgram = associatedPrograms.getEntry(programChoice);

        // Remove the program from the course
        boolean removed = selectedCourse.getAssociatedPrograms().remove(selectedProgram);

        // Inform the user
        if (removed) {
            System.out.println("Program " + selectedProgram.getProgramCode()
                    + " removed from course " + selectedCourse.getCourseCode());
        } else {
            System.out.println("Failed to remove program "
                    + selectedProgram.getProgramCode() + " from course "
                    + selectedCourse.getCourseCode());
        }
    }

    public void amendCourseDetails() {
        // Display list of courses
        displayCourse();

        // Prompt the user to select a course
        int courseChoice = courseManageUI.enterNumberCourseAmend();

        // Validate user input
        if (courseChoice < 1 || courseChoice > courseList.getNumberOfEntries()) {
            MessageUI.invalidCourseChoice();
            return;
        }

        // Get the selected course
        Course selectedCourse = courseList.getEntry(courseChoice);

        // Display current details of the selected course
        MessageUI.currentCourseDetail();
        System.out.println("Course Code: " + selectedCourse.getCourseCode());
        System.out.println("Course Name: " + selectedCourse.getCourseName());
        System.out.println("Credit Hours: " + selectedCourse.getCreditHours());

        // Prompt the user to enter new details
        String newCourseCode = courseManageUI.enterCourseCodeAmend();
        String newCourseName = courseManageUI.enterCourseNameAmend();
        int newCreditHours = courseManageUI.enterCourseCreditHoursAmend();

        // Update course details
        selectedCourse.setCourseCode(newCourseCode);
        selectedCourse.setCourseName(newCourseName);
        selectedCourse.setCreditHours(newCreditHours);

        // Inform the user
        MessageUI.successCourseAmend();

        courseList.bubbleSort();
    }

    public void listCoursesForProgram() {
        // Display list of programs
        displayProgram();

        // Prompt the user to select a program
        int programChoice = courseManageUI.enterNumberProgramToList();

        // Validate user input
        if (programChoice < 1 || programChoice > programList.getNumberOfEntries()) {
            MessageUI.invalidProgramChoice();
            return;
        }

        // Get the selected program
        Programme selectedProgram = programList.getEntry(programChoice);

        // Get the list of courses associated with the selected program
        ListInterface<Course> associatedCourses = new ArrayList<>();
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            if (course.getAssociatedPrograms().contains(selectedProgram)) {
                associatedCourses.add(course);
            }
        }
        // Display the list of courses
        StringBuilder courseListStr = new StringBuilder();
        courseListStr.append("Courses for Program: ")
                .append(selectedProgram.getProgramCode()).append("\n");
        courseListStr.append("No.\tCourseCode\tCourseName\t\t\t\tCreditHours\n");
        for (int i = 1; i <= associatedCourses.getNumberOfEntries(); i++) {
            Course course = associatedCourses.getEntry(i);
            courseListStr.append(i).append(".\t")
                    .append(course.getCourseCode()).append("\t\t")
                    .append(course.getCourseName())
                    .append("\t\t").append(course.getCreditHours()).append("\n");
        }
        courseManageUI.listAllCourse(courseListStr.toString());
    }

    public void displayProgram() {
//    System.out.println("\nList of Programme:");
//    System.out.println("No.\tProgramCode\tProgramName");
        MessageUI.listOfProgram();
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
            Programme program = programList.getEntry(i);
            System.out.println(i + ".\t" + program.getProgramCode() + "\t\t" + program.getProgramName());
        }
        System.out.println();
    }

    public void addCourseToProgram() {
        // Display list of courses
        displayCourse();

        // Prompt the user to select a course
        int courseChoice = courseManageUI.enterNumberCourseToAdd();

        // Validate user input
        if (courseChoice < 1 || courseChoice > courseList.getNumberOfEntries()) {
            MessageUI.invalidCourseChoice();
            return;
        }

        // Get the selected course
        Course selectedCourse = courseList.getEntry(courseChoice);

        // Display list of programs
        displayProgram();

        // Prompt the user to select a program
        int programChoice = courseManageUI.enterNumberProgramToAdd();

        //courseManegeUI.enterNumberProgramToAdd();
        // Validate user input
        if (programChoice < 1 || programChoice > programList.getNumberOfEntries()) {
            MessageUI.invalidProgramChoice();
            return;
        }

        // Get the selected program
        Programme selectedProgram = programList.getEntry(programChoice);

        // Check if the course is already associated with the selected program
        if (selectedCourse.getAssociatedPrograms().contains(selectedProgram)) {
            MessageUI.courseAssociatedAdd();
            return;
        }

        // Add the selected program to the associated programs list of the selected course
        selectedCourse.getAssociatedPrograms().add(selectedProgram);
        selectedCourse.getAssociatedPrograms().bubbleSort();
        // Inform the user
        System.out.println("Course " + selectedCourse.getCourseCode()
                + " added to program " + selectedProgram.getProgramCode());
    }

    public void addNewCourse() {
        // Display existing course
        displayCourse();

        // Prompt the user to enter details for a new course
        Course newCourse = courseManageUI.inputCourseDetails();

        // Add the new course to the existing list
        courseList.add(newCourse);
        courseList.bubbleSort();
        // Display updated list of course
        displayCourse();

        // Display list of programs
        displayProgram();

        // Prompt the user to select a program
        int programChoice = courseManageUI.enterNumberProgramToAdd();

        // Validate user input
        if (programChoice < 1 || programChoice > programList.getNumberOfEntries()) {
            //System.out.println("Invalid program choice.");
            MessageUI.invalidProgramChoice();
            return;
        }

        // Get the selected program
        Programme selectedProgram = programList.getEntry(programChoice);

        // Associate the new course with the selected program
        newCourse.getAssociatedPrograms().add(selectedProgram);

        // Inform the user
        System.out.println("Course " + newCourse.getCourseCode() + " added to program "
                + selectedProgram.getProgramCode());
    }

    public void removeCourseFromProgram() {
        // Display list of courses
        displayCourse();

        // Prompt the user to select a course
        int courseChoice = courseManageUI.enterNumberCourseToRemove();

        // Validate user input
        if (courseChoice < 1 || courseChoice > courseList.getNumberOfEntries()) {
            MessageUI.invalidProgramChoice();
            return;
        }

        // Get the selected course
        Course selectedCourse = courseList.getEntry(courseChoice);

        // Display programs associated with the selected course
        ListInterface<Programme> associatedPrograms = selectedCourse.getAssociatedPrograms();
        if (associatedPrograms.isEmpty()) {
            MessageUI.noProgramAssociatedWithCourse();
            return;
        }
        //System.out.println("Programs associated with the selected course:");
        MessageUI.yesProgramAssociatedWithCourse();
        for (int i = 1; i <= associatedPrograms.getNumberOfEntries(); i++) {
            Programme program = associatedPrograms.getEntry(i);
            System.out.println(i + ". " + program.getProgramCode() + " - " + program.getProgramName());
        }

        // Prompt the user to select a program to remove
        int programChoice = courseManageUI.enterNumberProgramToRemove();

        // Validate user input
        if (programChoice < 1 || programChoice > associatedPrograms.getNumberOfEntries()) {
            MessageUI.invalidProgramChoice();
            return;
        }

        // Get the selected program
        Programme selectedProgram = associatedPrograms.getEntry(programChoice);

        // Remove the course from the program
        boolean removed = selectedCourse.getAssociatedPrograms().remove(selectedProgram);

        // Inform the user
        if (removed) {
            System.out.println("Course " + selectedCourse.getCourseCode() + " removed from program "
                    + selectedProgram.getProgramCode());
        } else {
            System.out.println("Failed to remove course " + selectedCourse.getCourseCode()
                    + " from program " + selectedProgram.getProgramCode());
        }
    }

    public void searchCoursesForProgram() {
        // Display list of programs
        displayProgram();

        // Prompt the user to select a program
        int programChoice = courseManageUI.enterNumberProgramToSearch();

        // Validate user input
        if (programChoice < 1 || programChoice > programList.getNumberOfEntries()) {
            MessageUI.invalidProgramChoice();
            return;
        }

        // Get the selected program
        Programme selectedProgram = programList.getEntry(programChoice);

        // Initialize a StringBuilder to store the list of courses
        StringBuilder courseListStr = new StringBuilder();
        courseListStr.append("Courses offered for Program: ")
                .append(selectedProgram.getProgramCode()).append("\n");

        // Iterate through the list of courses and check association with the selected program
        boolean coursesFound = false;
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            if (course.getAssociatedPrograms().contains(selectedProgram)) {
                coursesFound = true;
                courseListStr.append(course.getCourseCode()).append(" - ")
                        .append(course.getCourseName()).append("\n");
            }
        }

        // If no courses found, inform the user
        if (!coursesFound) {
            courseListStr.append("No courses found for the selected program.");
        }

        // Display the list of courses or message
        System.out.println(courseListStr.toString());
    }

    public void displayCourse() {
        StringBuilder courseListStr = new StringBuilder();
        int columnWidth = 20; // Width of each column

        // Header
        courseListStr.append(String.format("%-5s%-20s%-37s%-10s\n", "No.", "CourseCode", "CourseName", "CreditHours"));

        // Display newly added courses
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            courseListStr.append(String.format("%-5d%-20s%-40s%-15s\n", i, course.getCourseCode(), course.getCourseName(), course.getCreditHours()));
        }

        // Add divider line
        courseListStr.append(formatting.getDivider('-', (columnWidth * 4) + 5));
        courseList.bubbleSort();
        // Output the list of courses
        courseManageUI.listAllCourse(courseListStr.toString());
    }

    public void addCourseTakenByFaculty() {
        // Display list of faculties
        displayFaculty();

        // Prompt the user to select a faculty
        int facultyChoice = courseManageUI.enterNumberFacultyToAdd();

        // Validate user input
        if (facultyChoice < 1 || facultyChoice > facultyList.getNumberOfEntries()) {
            MessageUI.invalidFacultyChoice();
            return;
        }

        // Get the selected faculty
        Faculty selectedFaculty = facultyList.getEntry(facultyChoice);

        // Display list of courses
        displayCourse();

        // Prompt the user to select a course
        int courseChoice = courseManageUI.enterNumberCourseToAddAsTaken();

        // Validate user input
        if (courseChoice < 1 || courseChoice > courseList.getNumberOfEntries()) {
            MessageUI.invalidCourseChoice();
            return;
        }

        // Get the selected course
        Course selectedCourse = courseList.getEntry(courseChoice);

        // Add the selected course to the courses taken by the selected faculty
        selectedFaculty.getCoursesTaken().add(selectedCourse);

        // Inform the user
        System.out.println("Course " + selectedCourse.getCourseCode()
                + " added as taken by " + selectedFaculty.getFacultyName());

        // Ask if the user wants to add another course
        String addAnother = courseManageUI.questionAddAnother();

        if (addAnother.equals("yes")) {
            // Recursively call the method to add another course
            addCourseTakenByFaculty();
        } else if (!addAnother.equals("no")) {
            MessageUI.invalidChoiceNoMoreChoice();
        }
    }

    public void listCoursesTakenByFaculty() {
        addCourseTakenByFaculty();
        StringBuilder report = new StringBuilder();
        report.append("=== List Course taken by different Faculities ===\n\n");

        // Display list of faculties and their courses taken
        for (int i = 1; i <= facultyList.getNumberOfEntries(); i++) {
            Faculty faculty = facultyList.getEntry(i);
            report.append(faculty.getFacultyName()).append(" (")
                    .append(faculty.getFacultyCode()).append("):\n");

            // List courses taken by the faculty
            ListInterface<Course> coursesTaken = faculty.getCoursesTaken();
            if (coursesTaken.isEmpty()) {
                report.append("No courses taken.\n");
            } else {
                for (int j = 1; j <= coursesTaken.getNumberOfEntries(); j++) {
                    Course course = coursesTaken.getEntry(j);
                    report.append("- ").append(course.getCourseCode())
                            .append(" - ").append(course.getCourseName()).append("\n");
                }
            }
            report.append("\n");
        }
        // Print the report
        System.out.println(report.toString());
    }

// Add this method to display the list of faculties
    public void displayFaculty() {
        StringBuilder facultyListStr = new StringBuilder();
        facultyListStr.append("No.\tFacultyCode\tFacultyName\n");

        // Display the list of faculties
        for (int i = 1; i <= facultyList.getNumberOfEntries(); i++) {
            Faculty faculty = facultyList.getEntry(i);
            facultyListStr.append(i).append(".\t")
                    .append(faculty.getFacultyCode()).append("\t\t")
                    .append(faculty.getFacultyName()).append("\n");
        }
        courseManageUI.listAllFaculty(facultyListStr.toString());
    }

    public void generateFirstSummaryReport() {
        StringBuilder report = new StringBuilder();
        report.append("\t=== Total Credit Hours for each Programme Summary Report ===\n\n");

        // Iterate through the list of programs
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
            Programme program = programList.getEntry(i);
            int totalCreditHours = calculateTotalCreditHours(program);
            report.append(String.format("%-10s  %-40s  Total Credit Hours: %d\n",
                    program.getProgramCode(), program.getProgramName(), totalCreditHours));
        }
        // Print the report
        System.out.println(report.toString());
    }

    private int calculateTotalCreditHours(Programme program) {
        int totalCreditHours = 0;
        // Iterate through the list of courses associated with the program
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            if (course.getAssociatedPrograms().contains(program)) {
                totalCreditHours += course.getCreditHours();
            }
        }
        return totalCreditHours;
    }

    public void generateSecondSummaryReport() {
        StringBuilder report = new StringBuilder();
        report.append("\t=== Total Credit Hours for each Course Summary Report ===\n\n");

        // Display list of courses
        report.append("Courses:\n");
        int totalCreditHours = 0; // Initialize total credit hours counter
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            report.append(String.format("%-10s", course.getCourseCode())).append("\t") // Fixed width for course code
                    .append(String.format("%-35s", course.getCourseName())).append("\t") // Fixed width for course name
                    .append("Credit Hours: ").append(String.format("%-4.1f", course.getCreditHours())).append("\n");  // Fixed width for credit hours
            totalCreditHours += course.getCreditHours(); // Add course credit hours to total
        }
        report.append("\n");

        // Display total credit hours
        report.append("Total Credit Hours: ").append(totalCreditHours).append("\n\n");

        System.out.println(report.toString());
    }

    public void generateCourseSummaryReport() {
        StringBuilder report = new StringBuilder();
        report.append("\t=== Total of Courses Taken by Different Programs ===\n\n");
        // Iterate through the list of programs
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
            Programme program = programList.getEntry(i);
            report.append("Program: ").append(program.getProgramCode()).append(" - ")
                    .append(program.getProgramName()).append("\n");

            // Get the list of courses associated with the program
            ListInterface<Course> associatedCourses = new ArrayList<>();
            for (int j = 1; j <= courseList.getNumberOfEntries(); j++) {
                Course course = courseList.getEntry(j);
                if (course.getAssociatedPrograms().contains(program)) {
                    associatedCourses.add(course);
                }
            }
            // Append the list of courses
            if (!associatedCourses.isEmpty()) {
                report.append("Courses:\n");
                for (int k = 1; k <= associatedCourses.getNumberOfEntries(); k++) {
                    Course course = associatedCourses.getEntry(k);
                    report.append("\t\t ").append(course.getCourseCode())
                            .append("  ").append(course.getCourseName()).append("\n");
                }
            } else {
                report.append("No courses offered in this program yet.\n");
            }
            report.append("\n");
        }
        // Print the report
        System.out.println(report.toString());
    }

}
