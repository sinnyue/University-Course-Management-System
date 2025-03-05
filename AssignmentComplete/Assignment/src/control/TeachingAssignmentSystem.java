package control;

import boundary.TutorialGroupManagementUI;

import dao.TutorCourseInitializer;
import boundary.TeachingAssignemntUI;
import entity.*;
import utility.MessageUI;
import adt.*;

public class TeachingAssignmentSystem {

    private MainMenu mainMenu;
    private ListInterface<Tutor> tutorList = new ArrayList<>();
    private ListInterface<Course> courseList = new ArrayList<>();
    private ListInterface<TutorialGroup> tutorialGroup;
//    private ListInterface<TutorialGroup> tutorialGroupList;
    private TeachingAssignemntUI systemUI = new TeachingAssignemntUI();
    private TutorCourseInitializer Cdao = new TutorCourseInitializer();
    private CourseManagementSystem courseSystem;
    private ListInterface<Student> students;

    public TeachingAssignmentSystem(ListInterface<Course> courseList) {
        this.courseList = courseList;
        this.tutorList = Cdao.initializeTutor();
        this.tutorialGroup = new ArrayList<>();
        this.courseSystem = new CourseManagementSystem();

    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void runProductMaintenance(TutorialGroupManagement TGM) {
        int number;

        for (int i = 1; i <= TGM.getProgramList().getNumberOfEntries(); i++) {
            Programme programme = TGM.getProgramList().getEntry(i);
            for (int j = 1; j <= programme.getTutorialGroups().getNumberOfEntries(); j++) {
                tutorialGroup.add(programme.getTutorialGroups().getEntry(j));
            }
        }
        students = TGM.getStudentList();
        TutorialGroupManagementUI tgmUI = new TutorialGroupManagementUI();

        String choice = "0";
        do {
            choice = systemUI.getMenuChoice();
            switch (choice) {
                case "0":
                    MessageUI.displayExitMessage();
                    break;
                case "1":
                    assignTutorToCourse();
                    break;
                case "2":
                    assignTutorialGroupToTutor();
                    break;
                case "3":
                    addTutorToTutorialGroupForCourse();
                    break;
                case "4":
                    searchCoursesUnderTutor();
                    break;
                case "5":
                    searchTutorsForCourse();
                    break;
                case "6":
                    listTutorsAndTutorialGroupsForCourse();
                    break;
                case "7":
                    listCoursesForTutors();
                    break;
                case "8":
                    filterTutorsForCriteria();

                    break;
                case "9":
                    generateTutorsAssignmentReport();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "0");
    }

    public void assignTutorToCourse() {
        boolean assignmentSuccessful = false;

        do {
            // Display available tutors
            System.out.println("List of Tutors:");
            System.out.println("-".repeat(86));
            System.out.printf("|%-10s | %-30s | %-15s | %-20s|\n", "Tutor ID", "Name", "Role", "Teaching Experience");
            System.out.println("-".repeat(86));
            for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                Tutor tutor = tutorList.getEntry(i);
                System.out.printf("|%-10s | %-30s | %-15s | %-20s|\n", tutor.getTutorID(), tutor.getName(), tutor.getTutorRole(), tutor.getTutorExperience());
            }
            System.out.println("-".repeat(86));

            // Get user input for tutor ID
            String tutorIDToAdd = systemUI.inputTutorID();
            Tutor tutorToAdd = findTutorById(tutorIDToAdd);
            if (tutorToAdd == null) {
                MessageUI.displayTutorNotFoundError();
                continue;
            }

            // Display available courses
            System.out.println("List of Courses:");
            System.out.println("-".repeat(50));
            System.out.printf("|%-15s | %-30s |\n", "Course Code", "Course Name");
            System.out.println("-".repeat(50));
            for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                Course course = courseList.getEntry(i);
                System.out.printf("|%-15s | %-30s |\n", course.getCourseCode(), course.getCourseName());
            }
            System.out.println("-".repeat(50));

            // Get user input for course code
            String courseCodeToAdd = systemUI.inputCourseCode();
            Course courseToAdd = findCourseByCode(courseCodeToAdd);
            if (courseToAdd == null) {
                MessageUI.displayCourseNotFoundError();
                continue;
            }

            // Update the tutor's assigned course
            tutorToAdd.setCourse(courseToAdd);

            // Update the course's assigned tutor
            courseToAdd.setTutor(tutorToAdd);

            MessageUI.displayAssignmentSuccessMessage();
            assignmentSuccessful = true;

        } while (!assignmentSuccessful);
    }

    public void assignTutorialGroupToTutor() {
        boolean assignmentSuccessful = false;

        do {
            // Display available tutors
            System.out.println("List of Tutors:");
            System.out.println("-".repeat(86));
            System.out.printf("|%-10s | %-30s | %-15s | %-20s|\n", "Tutor ID", "Name", "Role", "Teaching Experience");
            System.out.println("-".repeat(86));
            for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                Tutor tutor = tutorList.getEntry(i);
                System.out.printf("|%-10s | %-30s | %-15s | %-20s|\n", tutor.getTutorID(), tutor.getName(), tutor.getTutorRole(), tutor.getTutorExperience());
            }
            System.out.println("-".repeat(86));

            // Get user input for tutor ID
            String tutorID = systemUI.inputTutorID();

            Tutor tutorToAdd = findTutorById(tutorID);
            if (tutorToAdd == null) {
                MessageUI.displayTutorNotFoundError();
                continue;
            }

            // Display available tutorial groups
            TutorialGroupManagementUI tgmUI = new TutorialGroupManagementUI();
            tgmUI.displayTutorialGroups(tutorialGroup);

            // Get user input for tutorial group details
            TutorialGroup tutorialGroup = systemUI.inputTutorialGroupDetails();
            systemUI.displayTutorialGroup(tutorialGroup);

            // Find the tutorial group by its ID
            TutorialGroup tutorialGroupToAdd = findTutorialGroupById(tutorialGroup.getGroupNo());
            if (tutorialGroupToAdd == null) {
                MessageUI.displayTutorialgroupNotFoundError();
                continue;
            }

            // Assign the tutorial group to the tutor
            tutorToAdd.setTutorialGroup(tutorialGroupToAdd);

            MessageUI.displayAssignmentSuccessMessage();
            assignmentSuccessful = true;

        } while (!assignmentSuccessful);
    }

    public void addTutorToTutorialGroupForCourse() {
        boolean assignmentSuccessful = false;

        do {
            // Display available tutors
            System.out.println("List of Tutors:");
            System.out.println("-".repeat(86));
            System.out.printf("|%-10s | %-30s | %-15s | %-20s|\n", "Tutor ID", "Name", "Role", "Teaching Experience");
            System.out.println("-".repeat(86));
            for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                Tutor tutor = tutorList.getEntry(i);
                System.out.printf("|%-10s | %-30s | %-15s | %-20s|\n", tutor.getTutorID(), tutor.getName(), tutor.getTutorRole(), tutor.getTutorExperience());
            }
            System.out.println("-".repeat(86));

            // Get user input for tutor ID
            String tutorID = systemUI.inputTutorID();
            Tutor tutorToAdd = findTutorById(tutorID);
            if (tutorToAdd == null) {
                MessageUI.displayAddNewTutorMessage();
                tutorToAdd = systemUI.inputTutorDetails();
                addTutor(tutorToAdd);

                // Display the newly added tutor
                System.out.println("New tutor added:");
                System.out.println("-" + tutorToAdd);
                MessageUI.displayNewTutorAddedMessage();
            } else {
                // Display available tutorial groups
                TutorialGroupManagementUI tgmUI = new TutorialGroupManagementUI();
                tgmUI.displayTutorialGroups(tutorialGroup);

                // Get user input for tutorial group details
                TutorialGroup tutorialGroup = systemUI.inputTutorialGroupDetails();
                systemUI.displayTutorialGroup(tutorialGroup);

                // Find the tutorial group by its ID
                TutorialGroup tutorialGroupToAdd = findTutorialGroupById(tutorialGroup.getGroupNo());
                if (tutorialGroupToAdd == null) {
                    MessageUI.displayTutorialgroupNotFoundError();
                    return;
                }

                // Display available courses
                System.out.println("List of Courses:");
                System.out.println("-".repeat(50));
                System.out.printf("|%-15s | %-30s |\n", "Course Code", "Course Name");
                System.out.println("-".repeat(50));
                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                    Course course = courseList.getEntry(i);
                    System.out.printf("|%-15s | %-30s |\n", course.getCourseCode(), course.getCourseName());
                }
                System.out.println("-".repeat(50));

                // Get user input for course code
                String courseCode = systemUI.inputCourseCode();
                Course courseToAdd = findCourseByCode(courseCode);
                if (courseToAdd == null) {
                    MessageUI.displayCourseNotFoundError();
                    return;
                }

                // Assign the tutorial group and course to the tutor
                tutorToAdd.setTutorialGroup(tutorialGroupToAdd);
                tutorToAdd.setCourse(courseToAdd);
                MessageUI.displayAssignmentSuccessMessage();
            }

            assignmentSuccessful = true;

        } while (!assignmentSuccessful);
    }

    public void searchCoursesUnderTutor() {
        boolean validInput = false;

        do {
            String tutorIDToSearch = systemUI.inputTutorID();
            Tutor tutorToSearch = findTutorById(tutorIDToSearch);

            if (tutorToSearch != null) {
                boolean coursesFound = false;

                System.out.println("Tutor ID: " + tutorToSearch.getTutorID());
                System.out.println("Tutor Name: " + tutorToSearch.getName());

                if (tutorToSearch.getCourse() != null) {
                    System.out.println("Courses Assigned:");
                    System.out.println("Course Code: " + tutorToSearch.getCourse().getCourseCode());
                    System.out.println("Course Name: " + tutorToSearch.getCourse().getCourseName());
                    coursesFound = true;
                } else {
                    System.out.println("No courses assigned to this tutor.");
                }

                if (!coursesFound) {
                    System.out.println("No courses found for tutor with ID: " + tutorIDToSearch);
                }

                validInput = true;
            } else {
                MessageUI.displayTutorNotFoundError();
            }
        } while (!validInput);
    }

    public void searchTutorsForCourse() {
        systemUI.listAllCourses(getAllCourses());
        String courseCode = systemUI.inputCourseCode();

        Course course = findCourseByCode(courseCode);
        if (course == null) {
            MessageUI.displayCourseNotFoundError();
            return;
        }

        String tutorRole = systemUI.inputTutorRole();

        System.out.println("Tutors for " + course.getCourseName() + " :");

        if (tutorList != null) {
            boolean foundTutors = false;

            for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                Tutor tutor = tutorList.getEntry(i);
                if (tutor.getCourse() != null && tutor.getCourse().getCourseCode().equalsIgnoreCase(courseCode)
                        && tutor.getTutorRole().equalsIgnoreCase(tutorRole)) {
                    System.out.println("-" + tutor.getName());
                    foundTutors = true;
                }
            }

            if (!foundTutors) {
                System.out.println("No tutors found for " + tutorRole + " in this course.");
            }
        } else {
            System.out.println("Tutor list is not initialized.");
        }
    }

    public void listTutorsAndTutorialGroupsForCourse() {
        // Prompt user for course code
        systemUI.listAllCourses(getAllCourses());
        String courseCode = systemUI.inputCourseCode();

        // Find course object
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            MessageUI.displayCourseNotFoundError();
            return;
        }

        // Display header
        System.out.println("Tutors and Tutorial Groups for Course: " + course.getCourseName());

        // Check if tutorList and tutorialGroupList are initialized
        if (tutorList != null && tutorialGroup != null) {
            // Flag to track if any tutor or tutorial group is found for the course
            boolean found = false;

            // Print table header
            System.out.println("-".repeat(91)); // Separator line
            System.out.printf("|%-25s|%-15s| %-20s|%-15s|%-10s|\n", "Tutor", "Role", "Tutorial Group", "Year", "Semester");
            System.out.println("-".repeat(91)); // Separator line

            // Iterate through the tutors assigned to the course
            for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
                Tutor tutor = tutorList.getEntry(i);
                if (tutor.getCourse() != null && tutor.getCourse().getCourseCode().equalsIgnoreCase(courseCode)) {
                    TutorialGroup tutorialGroup = tutor.getTutorialGroup();
                    if (tutorialGroup != null) {
                        // Print tutor and tutorial group details
                        System.out.printf("|%-25s|%-15s| %-20s|%-15s|%-10s|\n", tutor.getName(), tutor.getTutorRole(),
                                tutorialGroup.getGroupNo(), tutorialGroup.getYear(), tutorialGroup.getSemester());
                        System.out.println("-".repeat(91)); // Separator line
                        found = true;
                    }
                }
            }

            // Display message if no tutors or tutorial groups found
            if (!found) {
                System.out.println("No tutors or tutorial groups found for this course.");
            }
        } else {
            System.out.println("Tutor list or tutorial group list is not initialized.");
        }
    }

    public void listCoursesForTutors() {
        // Display header
        System.out.println("List Courses For Tutors");
        System.out.println("-".repeat(113)); // Separator line
        System.out.printf("| %-10s | %-30s | %-10s | %-50s |\n", "Tutor Id", "Name", "Role", "Course Handle");
        System.out.println("-".repeat(113)); // Separator line

        // Iterate through each tutor
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            Tutor tutor = tutorList.getEntry(i);
            String courseId = (tutor.getCourse() != null) ? tutor.getCourse().getCourseCode() : "N/A";
            String courseName = (tutor.getCourse() != null) ? tutor.getCourse().getCourseName() : "N/A";

            // Print tutor details in a tidy format
            System.out.printf("| %-10s | %-30s | %-10s | %-50s |\n", tutor.getTutorID(), tutor.getName(), tutor.getTutorRole(), courseId + " - " + courseName);
        }

        // Footer
        System.out.println("-".repeat(113)); // Separator line
    }

    public void filterTutorsForCriteria() {
        String choice;
        String groupNo = null;
        String year = null;
        String semester = null;

        do {
            // Display search menu
            systemUI.displayTutorSearchMenu();
            choice = systemUI.getTutorSearchChoice();

            switch (choice) {
                case "0":
                    return; // Exit search menu
                case "1":
                    searchTutorsByName();
                    break;
                case "2":
                    // Prompt user to input tutorial group details

                    groupNo = systemUI.inputTutorialGroupID();

                    year = systemUI.inputYear();

                    semester = systemUI.inputSemester();

                    // Pass initialized variables as arguments to the method
                    searchTutorsByTutorialGroup(groupNo, year, semester);
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "0");
    }

    private void searchTutorsByName() {
        // Get search keyword from user
        String keyword = systemUI.inputSearchKeyword();

        // Display header
        System.out.println("Search Results:");
        System.out.println("-".repeat(90));
        System.out.printf("| %-10s | %-30s | %-40s |\n", "Tutor ID", "Name", "Courses Handled");
        System.out.println("-".repeat(90));

        // Iterate through tutors
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            Tutor tutor = tutorList.getEntry(i);
            if (tutor.getName().toLowerCase().contains(keyword.toLowerCase())) {
                // Print tutor details
                System.out.printf("| %-10s | %-30s | %-40s |\n", tutor.getTutorID(), tutor.getName(), getAssignedCourses(tutor));
            }
        }

        // Display footer
        System.out.println("-".repeat(90));
    }

    private void searchTutorsByTutorialGroup(String groupNo, String year, String semester) {

        // Display header
        System.out.println("Search Results:");
        System.out.println("-".repeat(90));
        System.out.printf("| %-10s | %-30s | %-40s |\n", "Tutor ID", "Name", "Courses Handled");
        System.out.println("-".repeat(90));

        // Iterate through tutors
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            Tutor tutor = tutorList.getEntry(i);
            TutorialGroup tutorGroup = tutor.getTutorialGroup();
            if (tutorGroup != null && tutorGroup.getGroupNo().equalsIgnoreCase(groupNo)
                    && tutorGroup.getYear().equalsIgnoreCase(year)
                    && tutorGroup.getSemester().equalsIgnoreCase(semester)) {
                // Print tutor details
                System.out.printf("| %-10s | %-30s | %-40s |\n", tutor.getTutorID(), tutor.getName(), getAssignedCourses(tutor));
            }
        }

        // Display footer
        System.out.println("-".repeat(90));
    }

    private String getAssignedCourses(Tutor tutor) {
        StringBuilder assignedCourses = new StringBuilder();
        if (tutor.getCourse() != null) {
            assignedCourses.append(tutor.getCourse().getCourseCode()).append(" - ").append(tutor.getCourse().getCourseName());
        }
        return assignedCourses.toString();
    }

    public void generateTutorsAssignmentReport() {
        String choice = "0";

        do {
            // Display sub-menu options
            choice = systemUI.generateTeachingAssignmentReportMenu();

            switch (choice) {
                case "0":
                    return; // Exit to main menu
                case "1":
                    generateCourseAssignmentReport();
                    break;
                case "2":
                    generateTutorialGroupAssignmentReport();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "3");
    }

    public void generateCourseAssignmentReport() {
        // Display header
        System.out.println("COURSE ASSIGNMENT SUMMARY REPORT");

        // Format the current date and time
        long currentTimeMillis = System.currentTimeMillis();
        String formattedTime = String.format("%tF %tT", currentTimeMillis, currentTimeMillis);

        System.out.println("Generated at: " + formattedTime);
        System.out.println();

        System.out.printf("%-10s %-40s %-13s %s\n", "CODE", "COURSE NAME", "CREDIT HOUR", "TUTOR ASSIGN");
        System.out.println("-".repeat(80));

        // Variables to track highest and lowest assignments
        int maxTutors = 0;
        int minTutors = Integer.MAX_VALUE;
        Course maxAssignedCourse = null;
        Course minAssignedCourse = null;

        // Iterate over each course
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);

            // Count the number of assigned tutors for the current course
            int assignedTutors = 0;

            // Check if there is a direct assignment of tutor to the course
            if (course.getTutor() != null) {
                assignedTutors++;
            }

            // Check if there are tutors assigned via tutorial groups
            for (int j = 1; j <= tutorList.getNumberOfEntries(); j++) {
                Tutor tutor = tutorList.getEntry(j);
                if (tutor.getCourse() != null && tutor.getCourse().getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                    assignedTutors++;
                }
            }

            // Print course details
            String tutorAssign = (assignedTutors == 0) ? "N/A" : String.valueOf(assignedTutors);
            System.out.printf("%-10s %-40s %-13s %s\n", course.getCourseCode(), course.getCourseName(),
                    course.getCreditHours(), tutorAssign);

            // Update max and min assignments
            if (assignedTutors > maxTutors) {
                maxTutors = assignedTutors;
                maxAssignedCourse = course;
            }
            if (assignedTutors < minTutors && assignedTutors > 0) {
                minTutors = assignedTutors;
                minAssignedCourse = course;
            }
        }

        // Print highest and lowest assignments
        System.out.println();
        if (maxAssignedCourse != null) {
            System.out.println("HIGHEST COURSE ASSIGNED:");
            System.out.printf("-> [%d TUTOR] <%s> %s\n", maxTutors, maxAssignedCourse.getCourseCode(), maxAssignedCourse.getCourseName());
        }
        if (minAssignedCourse != null) {
            System.out.println("LOWEST COURSES ASSIGNED:");
            System.out.printf("-> [%d TUTOR] <%s> %s\n", minTutors, minAssignedCourse.getCourseCode(), minAssignedCourse.getCourseName());
            System.out.println("[NOTE: 0 TUTOR ASSIGNED IS NOT COUNTED]");
        }
    }

    public void generateTutorialGroupAssignmentReport() {
        // Print header
        System.out.println("TUTORIAL GROUP ASSIGNMENT SUMMARY REPORT");

        // Format the current date and time
        String formattedTime = String.format("%tF %tT", System.currentTimeMillis(), System.currentTimeMillis());

        // Print the generated time
        System.out.println("Generated at: " + formattedTime);
        System.out.println();

        // Print table header
        System.out.printf("%-10s %-15s %s\n", "TUTOR ID", "TUTOR NAME", "GROUP COUNT");
        System.out.println("-".repeat(37));

        // Print tutor and group counts
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            Tutor tutor = tutorList.getEntry(i);
            int groupCount = countAssignedGroups(tutor);
            System.out.printf("%-10s %-15s %d\n", tutor.getTutorID(), tutor.getName(), groupCount);
        }

        // Find tutor with maximum and minimum group count
        Tutor maxTutor = null;
        Tutor minTutor = null;
        int maxGroupCount = Integer.MIN_VALUE;
        int minGroupCount = Integer.MAX_VALUE;

        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            Tutor tutor = tutorList.getEntry(i);
            int groupCount = countAssignedGroups(tutor);
            if (groupCount > maxGroupCount) {
                maxGroupCount = groupCount;
                maxTutor = tutor;
            }
            if (groupCount < minGroupCount) {
                minGroupCount = groupCount;
                minTutor = tutor;
            }
        }

        // Print maximum and minimum group counts
        System.out.println("\nMAXIMUM TUTORIAL GROUP HANDLED:");
        System.out.printf("-> Tutor ID: %s, Tutor Name: %s, Group Count: %d\n", maxTutor.getTutorID(), maxTutor.getName(), maxGroupCount);

        System.out.println("\nMINIMUM TUTORIAL GROUP HANDLED:");
        System.out.printf("-> Tutor ID: %s, Tutor Name: %s, Group Count: %d\n", minTutor.getTutorID(), minTutor.getName(), minGroupCount);
    }

    private int countAssignedGroups(Tutor tutor) {
        int count = 0;
        for (int i = 1; i <= tutorialGroup.getNumberOfEntries(); i++) {
            TutorialGroup group = tutorialGroup.getEntry(i);
            if (tutor.getTutorialGroup() != null && tutor.getTutorialGroup().equals(group)) {
                count++;
            }
        }
        return count;
    }

    private Tutor findTutorById(String tutorID) {
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            Tutor currentTutor = tutorList.getEntry(i);
            if (currentTutor.getTutorID().equals(tutorID)) {
                return currentTutor;
            }
        }
        return null;
    }

    private TutorialGroup findTutorialGroupById(String tutorialGroupID) {
        for (int i = 1; i <= tutorialGroup.getNumberOfEntries(); i++) {
            TutorialGroup currentTutorialGroup = tutorialGroup.getEntry(i);
            if (currentTutorialGroup.getGroupNo().equals(tutorialGroupID)) {
                return currentTutorialGroup;
            }
        }
        return null;
    }

    private Course findCourseByCode(String courseCode) {
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course currentCourse = courseList.getEntry(i);
            if (currentCourse.getCourseCode().equals(courseCode)) {
                return currentCourse;
            }
        }
        return null;
    }

    public String getAllTutors() {
        String outputStr = "";
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
            outputStr += tutorList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    public String getAllCourses() {
        String outputStr = "";
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            outputStr += courseList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    public void displayTutorialGroup(TutorialGroup tGroup) {
        System.out.println("Tutorial Group Details:");
        System.out.println("Group Number: " + tGroup.getGroupNo());
        System.out.println("Year: " + tGroup.getYear());
        System.out.println("Semester: " + tGroup.getSemester());
    }

    private void addTutor(Tutor tutorToAdd) {
        tutorList.add(tutorToAdd);
    }

}
