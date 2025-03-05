/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.ArrayList;
import adt.ListInterface;
import boundary.TutorialGroupManagementUI;
import dao.ProgramTutorialGroupStudentInitializer;
import dao.StudentProgrammeInitializer;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import utility.MessageUI;
import utility.formatting;

/**
 *
 * @author shuej
 */
public class TutorialGroupManagement {

    private MainMenu mainMenu;
    private ListInterface<Programme> programList = new ArrayList<>();
    private ListInterface<TutorialGroup> groupList = new ArrayList<>();
    private ListInterface<Student> studentList = new ArrayList<>();
    private TutorialGroupManagementUI groupUI = new TutorialGroupManagementUI();
    private ProgramTutorialGroupStudentInitializer PTGdao = new ProgramTutorialGroupStudentInitializer();
    private StudentProgrammeInitializer SPdao = new StudentProgrammeInitializer();
    private CourseManagementSystem courseSystem;

    public TutorialGroupManagement(ListInterface<Student> studentList, ListInterface<Programme> programList) {

        this.studentList = studentList;
        this.programList = programList;
        this.groupUI = new TutorialGroupManagementUI();
        this.courseSystem = new CourseManagementSystem();

    }

    TutorialGroupManagement() {
        this.programList = PTGdao.initializer();
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public ListInterface<Programme> getProgramList() {
        return programList;
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setProgramList(ListInterface<Programme> programList) {
        this.programList = programList;
    }

    public void setStudentList(ListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public ListInterface<TutorialGroup> getGroupList() {
        return groupList;
    }

    public void setGroupList(ListInterface<TutorialGroup> groupList) {
        this.groupList = groupList;
    }

    public TutorialGroupManagementUI getGroupUI() {
        return groupUI;
    }

    public void setGroupUI(TutorialGroupManagementUI groupUI) {
        this.groupUI = groupUI;
    }

    public ProgramTutorialGroupStudentInitializer getPTGdao() {
        return PTGdao;
    }

    public void setPTGdao(ProgramTutorialGroupStudentInitializer PTGdao) {
        this.PTGdao = PTGdao;
    }

    public StudentProgrammeInitializer getSPdao() {
        return SPdao;
    }

    public void setSPdao(StudentProgrammeInitializer SPdao) {
        this.SPdao = SPdao;
    }

    public void runTutorialGroup() {
        String choice = "0";
        do {
            choice = groupUI.displayTutorialGroupMenu();
            switch (choice) {
                case "1":
                    manageProgramTutorialGroups();
                    break;
                case "2":
                    manageStudentTutorialGroups();
                    break;
                case "3":
                    listAllStudents();
                    break;
                case "4":
                    generateReports();
                    break;
                case "5":
                    mainMenu.runMainMenu();
                    return;
                default:
                    MessageUI.displayInvalidChoiceMessage();

            }
        } while (choice != "5");
    }

    public void manageProgramTutorialGroups() {
        String choice = "0";

        do {
            choice = groupUI.manageProgramTutorialGroups();

            switch (choice) {
                case "1":
                    addTutorialGroupToProgram();
                    break;
                case "2":
                    removeTutorialGroupFromProgram();
                    break;
                case "3":
                    String programCode = groupUI.inputProgramCode();
                    groupUI.listAllTutorialGroups(programCode, programList);
                    break;
                case "4":
                    return;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "4");
    }

    public void addTutorialGroupToProgram() {
        // Step 1: Display available programs

        groupUI.printProgrammesInfo(programList);

        // Step 2: Input program details and find existing program
        Programme queryProgram = groupUI.inputProgramDetails();
        Programme existingProgram = findProgram(queryProgram);

        // Step 3: Proceed if the program exists, otherwise show error
        if (existingProgram != null) {
            // Step 4: Input new tutorial group details
            TutorialGroup newGroup = groupUI.inputTutorialGroupDetails();

            // Step 5: Validate if the tutorial group already exists
            if (!tutorialGroupExists(existingProgram, newGroup)) {
                // Step 6: Add the tutorial group to the existing program if it does not exist
                existingProgram.getTutorialGroups().add(newGroup);
                existingProgram.getTutorialGroups().bubbleSort(); //  sort tutorial groups
                MessageUI.displayITutorialAddedtoProgrammmeSuccessfulMessage();

                programList.bubbleSort();
                MessageUI.displayUpdatedList();
                // Step 7: Print updated programs and tutorial groups
                groupUI.printProgrammesInfo(programList);
            } else {
                MessageUI.displayTutorialGroupAlreadyExistsMessage();
            }
        } else {
            MessageUI.displayProgramNotFoundErrorMessage();
        }

    }

    private boolean tutorialGroupExists(Programme program, TutorialGroup newGroup) {
        return program.getTutorialGroups().contains(newGroup);
    }

    // Helper method to find an existing program
    private Programme findProgram(Programme queryProgram) {
        for (int i = 0; i < programList.size(); i++) {
            Programme existingProgram = programList.get(i);
            if (existingProgram.getProgramCode().equals(queryProgram.getProgramCode())) {
                return existingProgram;
            }
        }
        return null; // Return null if the program is not found
    }

    public void removeTutorialGroupFromProgram() {

        MessageUI.intialProgramList();
        groupUI.printProgrammesInfo(programList);
        // Input program details

        String programCode = groupUI.inputProgramCode();
        // Find the program
        Programme programInput = findProgramByCode(programCode, programList);

        if (programInput != null) {

            TutorialGroup tutorialRemoval = groupUI.inputTutorialGroupDetails();

            // Check if the group exists using contains
            if (programInput.getTutorialGroups().contains(tutorialRemoval)) {
                boolean removalSuccess = removeTutorialGroup(programInput, tutorialRemoval);
                if (removalSuccess) {
                    if (programInput.getTutorialGroups().isEmpty()) {
                        programList.remove(programInput);
                        System.out.println("Removed program as it has no more tutorial groups: " + programInput);
                    }
                    MessageUI.displayTutorialRemovedFromProgramSuccessfulMessage();
                } else {
                    MessageUI.displayGroupNotFoundErrorMessage();
                }
            } else {
                MessageUI.displayGroupNotFoundErrorMessage();
            }
        } else {
            MessageUI.displayProgramNotFoundErrorMessage();
        }

        MessageUI.displayUpdatedList();
        groupUI.printProgrammesInfo(programList);
    }

    private boolean removeTutorialGroup(Programme program, TutorialGroup tutorial) {
        ListInterface<TutorialGroup> groups = program.getTutorialGroups();
        boolean removed = groups.remove(tutorial);

        if (removed) {
            System.out.println("Removed: " + tutorial);
            program.getTutorialGroups().bubbleSort();
        }

        return removed;
    }

    public static Programme findProgramByCode(String programCode, ListInterface<Programme> programList) {
        for (int i = 0; i < programList.size(); i++) {
            Programme program = programList.get(i);
            if (program.getProgramCode().equalsIgnoreCase(programCode)) {
                return program;
            }
        }
        return null;
    }

    public void manageStudentTutorialGroups() {
        String choice = "0";

        do {
            choice = groupUI.manageStudentTutorialGroups();

            switch (choice) {
                case "1":
                    addStudentToTutorialGroup();
                    break;
                case "2":
                    // Remove Student from Tutorial Group
                    removeStudentFromGroups();
                    break;
                case "3":
                    changeStudentGroup();
                    break;
                case "4":

                    String programCode = groupUI.inputProgramCode();
                    String year = groupUI.inputYear();
                    String semester = groupUI.inputSemester();

                    // Assuming mergeGroups is correctly implemented to take program code as well
                    TutorialGroup mergedGroup = mergeGroups(programCode, year, semester);
                    if (mergedGroup != null) {
                        groupUI.printUpdatedStudentList(mergedGroup);  // Assuming this method is implemented to display the group's students
                    } else {
                        MessageUI.groupNoFoundforMerge();
                    }
                    break;

                case "5":
                    groupUI.printTutorialGroupsInfo(programList);
                case "6":
                    return;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "5");
    }

    public Student findStudentById(String studentID) {
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null; // Student not found
    }

    public void addStudentToTutorialGroup() {
        String studentID = groupUI.inputStudentID();
        Student studentToAdd = findStudentById(studentID);

        if (studentToAdd != null) {

            String studentProgramName = studentToAdd.getProgram().getProgramName();
            System.out.println("Available tutorial groups for " + studentProgramName + ":");

            Programme matchingProgram = findProgramWithTutorialGroups(studentProgramName);

            if (matchingProgram != null) {
                ListInterface<TutorialGroup> tutorialGroups = matchingProgram.getTutorialGroups();

                if (tutorialGroups != null && !tutorialGroups.isEmpty()) {
                    groupUI.displayTutorialGroups(tutorialGroups);

                    boolean groupFound = false;
                    while (!groupFound) {
                        String selectGroupNo = groupUI.inputGroupNumber();
                        String selectYear = groupUI.inputYear();
                        String selectSemester = groupUI.inputSemester();

                        TutorialGroup selectedGroup = findTutorialGroup(tutorialGroups, selectGroupNo, selectYear, selectSemester);

                        if (selectedGroup != null) {
                            if (selectedGroup.getStudents().size() < TutorialGroup.getGroupSize()) {
                                // Check if student already exists in the selected group
                                if (!selectedGroup.getStudents().contains(studentToAdd)) {
                                    selectedGroup.getStudents().add(studentToAdd);
                                    System.out.println("Student " + studentToAdd.getStudentName() + " added to group " + selectedGroup.getGroupNo() + selectedGroup.getYear() + selectedGroup.getSemester());
                                    updateProgramList(programList, matchingProgram, selectedGroup);

                                    // Print the formatted list of students in the tutorial group
                                    System.out.println("\nStudent List in Tutorial Groups");
                                    System.out.println("Progamme Name: " + matchingProgram.getProgramName());
                                    System.out.println(groupUI.StudentList(selectedGroup)); // Format and display the student list
                                } else {
                                    System.out.println("Student " + studentToAdd.getStudentName() + " already belongs to group " + selectedGroup.getGroupNo() + selectedGroup.getYear() + selectedGroup.getSemester());
                                }

                                groupFound = true;
                            } else {
                                MessageUI.displayGroupIsFull();
                                String tryAgain = groupUI.inputTryAgain();
                                if (!tryAgain.equals("Y")) {
                                    MessageUI.displayCancelledMessages();
                                    return;
                                }
                            }
                        } else {
                            MessageUI.displaySelectedGroupNotFound();
                            String tryAgain = groupUI.inputTryAgain();
                            if (!tryAgain.equals("Y")) {
                                MessageUI.displayCancelledMessages();
                                return;
                            }
                        }
                    }
                } else {
                    System.out.println("No tutorial groups available for " + studentProgramName);
                }
            } else {
                System.out.println("Program with tutorial groups not found for program name: " + studentProgramName);
            }
        } else {
            MessageUI.displayStudentNotRegistered();
        }
    }

    private TutorialGroup findTutorialGroup(ListInterface<TutorialGroup> tutorialGroups, String groupNo, String year, String semester) {
        for (int i = 0; i < tutorialGroups.size(); i++) {
            TutorialGroup group = tutorialGroups.get(i);
            if (group.getGroupNo().equalsIgnoreCase(groupNo) && group.getYear().equalsIgnoreCase(year) && group.getSemester().equalsIgnoreCase(semester)) {
                return group;
            }
        }
        return null; // If no matching group is found
    }

    private void updateProgramList(ListInterface<Programme> programList, Programme programToUpdate, TutorialGroup groupToUpdate) {
        // Iterate through the program list to find the program to update
        for (int i = 0; i < programList.size(); i++) {
            Programme program = programList.get(i);

            // Check if the current program matches the program to update
            if (program.getProgramCode().equals(programToUpdate.getProgramCode())) {
                // Found the matching program, now update its tutorial groups
                ListInterface<TutorialGroup> tutorialGroups = programToUpdate.getTutorialGroups();

                // Iterate through the tutorial groups of the program to find the matching group
                for (int j = 0; j < tutorialGroups.size(); j++) {
                    TutorialGroup currentGroup = tutorialGroups.get(j);

                    // Check if the group matches the one being updated
                    if (currentGroup.getGroupNo().equals(groupToUpdate.getGroupNo())
                            && currentGroup.getYear().equals(groupToUpdate.getYear())
                            && currentGroup.getSemester().equals(groupToUpdate.getSemester())) {
                        // Found the matching group, update its student list
                        currentGroup.setStudents(groupToUpdate.getStudents());
                        break; // No need to continue searching
                    }
                }
            }
        }
    }

    private Programme findProgramWithTutorialGroups(String studentProgramName) {
        for (int i = 0; i < programList.size(); i++) {
            Programme program = programList.get(i);
            if (program.getProgramName().equals(studentProgramName)) {
                ListInterface<TutorialGroup> tutorialGroups = program.getTutorialGroups();
                if (tutorialGroups != null && !tutorialGroups.isEmpty()) {
                    return program;
                }
            }
        }
        return null;
    }

    public void removeStudentFromGroups() {

        // Prompt the user to input the program code
        String programCode = groupUI.inputProgramCode();

        // Find the program corresponding to the input code
        Programme program = findProgramByCode(programCode);

        if (program != null) {
            // User inputs group details and a TutorialGroup object is created
            TutorialGroup inputGroupDetails = groupUI.inputTutorialGroupDetails();

            // Retrieve group details from the inputGroupDetails object
            String groupNo = inputGroupDetails.getGroupNo();
            String semester = inputGroupDetails.getSemester();
            String year = inputGroupDetails.getYear();

            // Retrieve the list of tutorial groups for the program
            ListInterface<TutorialGroup> tutorialGroups = program.getTutorialGroups();

            TutorialGroup foundGroup = null;
            for (int i = 0; i < tutorialGroups.size(); i++) {
                TutorialGroup group = tutorialGroups.get(i);
                if (group.getGroupNo().equals(groupNo) && group.getSemester().equals(semester) && group.getYear().equals(year)) {
                    foundGroup = group;
                    break;
                }
            }

            if (foundGroup != null) {
                // Print the list of students in the found group
                System.out.println("Students in Group " + groupNo + year + semester + " for " + program.getProgramName() + ":");
                ListInterface<Student> students = foundGroup.getStudents();
                for (int i = 0; i < students.size(); i++) {
                    Student student = students.get(i);
                    System.out.println("Student ID: " + student.getStudentID() + ", Student Name: " + student.getStudentName());
                }
                // Let the user input the details of the student to be removed
                Student studentToRemove = groupUI.inputStudentDetails();  // Assuming there's a method to input student details

                if (foundGroup.getStudents().contains(studentToRemove)) {
                    boolean removalSuccess = removeStudent(foundGroup, studentToRemove);
                    if (removalSuccess) {
                        if (foundGroup.getStudents().isEmpty()) {
                            groupList.remove(foundGroup);
                            System.out.println("Removed group as it has no more students: " + foundGroup);
                        }
                        MessageUI.displayStudentRemovedFromGroupsSuccessfulMessage();
                        MessageUI.displayUpdatedGroupList();
                        groupUI.printRemovalList(studentToRemove, foundGroup);
                    } else {
                        MessageUI.displayStudentNotFoundErrorMessage();
                    }
                } else {
                    MessageUI.displayStudentNotFoundErrorMessage();
                }
            } else {
                MessageUI.displayGroupNotFoundErrorMessage();
            }
        } else {
            MessageUI.displayProgramNotFoundErrorMessage();
        }
    }

    private Programme findProgramByCode(String programCode) {
        for (int i = 0; i < programList.size(); i++) {
            Programme program = programList.get(i);
            if (program.getProgramCode().equals(programCode)) {
                return program;
            }
        }
        return null; // Return null if no matching program is found
    }

    private boolean removeStudent(TutorialGroup group, Student student) {
        ListInterface<Student> students = group.getStudents();
        boolean removed = students.remove(student);

        if (removed) {
            System.out.println("Removed: " + student.getStudentID() + " " + student.getStudentName());
            students.bubbleSort();
        }

        return removed;
    }

    public void changeStudentGroup() {
        Student inputStudentDetails1 = groupUI.inputStudentDetails();
        Student inputStudentDetails2 = groupUI.inputStudentDetails();

        TutorialGroup group1 = null, group2 = null;
        Student student1 = null, student2 = null;
        String programCode1 = "", programCode2 = "";

        // Locate both students and their groups using traditional for loops
        for (int i = 0; i < programList.size(); i++) {
            Programme programme = programList.get(i);
            ListInterface<TutorialGroup> groups = programme.getTutorialGroups();
            for (int j = 0; j < groups.size(); j++) {
                TutorialGroup group = groups.get(j);
                ListInterface<Student> students = group.getStudents();
                for (int k = 0; k < students.size(); k++) {
                    Student student = students.get(k);
                    if (student.getStudentID().equals(inputStudentDetails1.getStudentID())
                            && student.getStudentName().equalsIgnoreCase(inputStudentDetails1.getStudentName())) {
                        student1 = student;
                        group1 = group;
                    } else if (student.getStudentID().equals(inputStudentDetails2.getStudentID())
                            && student.getStudentName().equalsIgnoreCase(inputStudentDetails2.getStudentName())) {
                        student2 = student;
                        group2 = group;
                    }
                    if (student1 != null && student2 != null) {
                        break;  // Both students found, break inner loop
                    }
                }
                if (student1 != null && student2 != null) {
                    break;  // Both students found, break middle loop
                }
            }
            if (student1 != null && student2 != null) {
                break;  // Both students found, break outer loop
            }
        }

        // Check if students are in different groups but the same year, semester, and program
        if (student1 != null && student2 != null && group1 != group2
                && group1.getYear().equals(group2.getYear())
                && group1.getSemester().equals(group2.getSemester())
                && programCode1.equals(programCode2)) {
            // Perform the swap
            group1.getStudents().remove(student1);
            group2.getStudents().remove(student2);
            group1.getStudents().add(student2);
            group2.getStudents().add(student1);

            ListInterface<Student> studentList1 = group1.getStudents();
            ListInterface<Student> studentList2 = group2.getStudents();
            // Sort the student list for group1
            studentList1.bubbleSort();

            // Sort the student list for group2
            studentList2.bubbleSort();

            MessageUI.displaySuccessChangedGroupMessage();
            groupUI.printChangeGroupStudentLists(group1, group2);

        } else {
            MessageUI.displayFailChangedGroupMessage();
        }

    }

    private TutorialGroup mergeGroups(String programCode, String year, String semester) {
        // Create a new group for merged students
        TutorialGroup newGroup = new TutorialGroup("MergedGroup", year, semester);
        boolean found = false;

        // Find the specified program first
        Programme targetProgram = null;
        for (int i = 0; i < programList.size(); i++) {
            if (programList.get(i).getProgramCode().equalsIgnoreCase(programCode)) {
                targetProgram = programList.get(i);
                break;
            }
        }

        if (targetProgram != null) {
            ListInterface<TutorialGroup> groups = targetProgram.getTutorialGroups();
            for (int i = 0; i < groups.size(); i++) {
                TutorialGroup group = groups.get(i);
                if (group.getYear().equals(year) && group.getSemester().equals(semester)) {
                    ListInterface<Student> students = group.getStudents();
                    for (int j = 0; j < students.size(); j++) {
                        Student student = students.get(j);
                        student.setGroupNo(group.getGroupNo());
                        newGroup.getStudents().add(student);
                    }
                    found = true; // Indicates that at least one group was merged
                }
            }
        }

        if (found) {
            return newGroup;
        } else {
            return null;
        }
    }

    public void listAllStudents() {
        String choice = "0";

        do {
            choice = groupUI.listAllStudents();

            switch (choice) {
                case "1":
                    listAllStudentsProgrammeGroup();
                    break;

                case "2":
                    return;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "5");
    }

    private void listAllStudentsProgrammeGroup() {
        String programCode = groupUI.inputProgramCode();
        TutorialGroup groupDetail = groupUI.inputTutorialGroupDetails();

        Programme targetProgram = findProgramByCode(programCode);
        if (targetProgram == null) {
            MessageUI.displayProgramNotFoundErrorMessage();
            return;
        }

        TutorialGroup targetGroup = findTutorialGroup(targetProgram, groupDetail);
        if (targetGroup == null) {
            MessageUI.displayGroupNotFoundErrorMessage();
            return;
        }

        ListInterface<Student> students = targetGroup.getStudents();
        if (students.isEmpty()) {
            MessageUI.NoStudentFound();
            return;
        }

        String outputStr = groupUI.formatStudentList(targetProgram, targetGroup, students);
        groupUI.printStudentList(outputStr);
    }

    private TutorialGroup findTutorialGroup(Programme program, TutorialGroup groupDetail) {
        String groupNo = groupDetail.getGroupNo();
        String year = groupDetail.getYear();
        String semester = groupDetail.getSemester();

        ListInterface<TutorialGroup> groups = program.getTutorialGroups();
        for (int j = 0; j < groups.size(); j++) {
            TutorialGroup group = groups.get(j);
            if (group.getGroupNo().equalsIgnoreCase(groupNo) && group.getYear().equals(year) && group.getSemester().equals(semester)) {
                return group;
            }
        }
        return null;
    }

    public void generateReports() {
        String choice = "0";

        do {
            choice = groupUI.generateReports();

            switch (choice) {
                case "1":

                    listTutorialGroupsByYearAndSemester(programList);

                    break;
                case "2":
                    String summary = listTotalNumberOfGroupAndStudentforProgramme(programList);
                    groupUI.printTotalNumberOfGroupAndStudentSummary(summary);
                    break;

                case "3":
                    return;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "3");

    }

    public void listTutorialGroupsByYearAndSemester(ListInterface<Programme> programList) {
        // Step 1: Input year and semester
        String year = groupUI.inputYear();
        String semester = groupUI.inputSemester();

        // Display input information
        groupUI.displayInputInformation(year, semester);

        // List tutorial groups
        listMatchingTutorialGroups(programList, year, semester);
    }

    private void listMatchingTutorialGroups(ListInterface<Programme> programList, String year, String semester) {
        int count = 0;

        for (int i = 0; i < programList.size(); i++) {
            Programme programme = programList.get(i);
            ListInterface<TutorialGroup> groups = programme.getTutorialGroups();

            for (int j = 0; j < groups.size(); j++) {
                TutorialGroup group = groups.get(j);
                if (group.getYear().equals(year) && group.getSemester().equals(semester)) {
                    groupUI.displayTutorialGroupInfo(programme, group, ++count);
                }
            }
        }

        if (count == 0) {
            MessageUI.groupNoFoundforList();
        }

        System.out.println(formatting.getDivider('-', 80));
    }

    public String listTotalNumberOfGroupAndStudentforProgramme(ListInterface<Programme> programList) {
        return groupUI.generateSummary(programList);
    }

}
