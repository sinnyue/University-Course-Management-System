/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.ListInterface;
import control.TutorialGroupManagement;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import java.util.Scanner;
import utility.formatting;

/**
 *
 * @author shuej
 */
/**
 *
 * @author shuej
 */
public class TutorialGroupManagementUI {

    Scanner scanner = new Scanner(System.in);

    public String displayTutorialGroupMenu() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("\nWelcome to the Tutorial Group Management System");
        System.out.println("\n" + "-".repeat(50));
        System.out.println("1. Manage Tutorial Groups to Programme");
        System.out.println("2. Manage Students to Tutorial Groups");
        System.out.println("3. List All Student in a Tutorial and a Programme");
        System.out.println("4. Summary Reports");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;

    }

    public String manageProgramTutorialGroups() {

        System.out.println("\nManage Tutorial Groups to Programmes");
        System.out.println("1. Add a Tutorial Group to Programme");
        System.out.println("2. Remove a Tutorial Group from Programme");
        System.out.println("3. List all Tutorial Groups from a Programme");
        System.out.println("4. Return to TutorialGroup Menu");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;

    }

    public String inputProgramCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the program code: ");
        String code = scanner.nextLine().toUpperCase();
        return code;
    }

    public String inputProgramName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the program name: ");
        String name = scanner.nextLine();
        return name;
    }

    public String inputGroupNumber() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter group number: ");
        String groupNo = scanner.nextLine().toUpperCase();
        return groupNo;
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

    public Programme inputProgramDetails() {
        String code = inputProgramCode();
        String name = inputProgramName();
        // Additional fields as required
        return new Programme(code, name);  // Assuming Programme constructor has these parameters
    }

    public TutorialGroup inputTutorialGroupDetails() {
        String groupNo = inputGroupNumber();
        String year = inputYear();
        String semester = inputSemester();
        System.out.println();
        return new TutorialGroup(groupNo, year, semester);  // Assuming TutorialGroup constructor matches this signature
    }

    public static void printProgrammesInfo(ListInterface<Programme> programList) {

        programList.bubbleSort();
        int dividerLength = 95;
        String headerFormat = "%10s %40s %10s %6s %10s \n";

        StringBuilder outputStr = new StringBuilder();
        outputStr.append(String.format("\n\nProgram Information\n"));
        outputStr.append(formatting.getDivider('=', dividerLength));
        outputStr.append(String.format(headerFormat, "ProgramCode", "ProgramName", "GroupNo", "Year", "Semester"));
        outputStr.append(formatting.getDivider('=', dividerLength));

        for (int i = 0; i < programList.size(); i++) {
            Programme prog = programList.get(i); // Use the get method for indexed access
            ListInterface<TutorialGroup> groups = prog.getTutorialGroups();
            if (groups.isEmpty()) {  // Check if the list is empty
                outputStr.append(String.format(headerFormat,
                        prog.getProgramCode(),
                        prog.getProgramName(),
                        "N/A", "N/A", "N/A"));
            } else {
                for (int j = 0; j < groups.size(); j++) {
                    TutorialGroup group = groups.get(j);
                    outputStr.append(String.format(headerFormat,
                            prog.getProgramCode(),
                            prog.getProgramName(),
                            group.getGroupNo(),
                            group.getYear(),
                            group.getSemester()));
                }
            }
            outputStr.append(formatting.getDivider('-', dividerLength));
        }
        System.out.println(outputStr.toString());
    }

    public void listAllTutorialGroups(String programCode, ListInterface<Programme> programList) {
        Programme program = TutorialGroupManagement.findProgramByCode(programCode, programList);
        if (program == null) {
            System.out.println("Program not found with the code: " + programCode);
            return;
        }

        ListInterface<TutorialGroup> groups = program.getTutorialGroups();
        groups.bubbleSort();
        StringBuilder builder = new StringBuilder();

        builder.append(formatting.getDivider('=', 75));
        builder.append("Program Code: ").append(program.getProgramCode()).append("\n");
        builder.append("Program Name: ").append(program.getProgramName()).append("\n");
        builder.append(formatting.getDivider('=', 75));

        builder.append(String.format("%-4s %-20s %-10s %-10s\n", "No.", "Tutorial Groups", "Year", "Semester"));
        builder.append(formatting.getDivider('-', 75));

        if (groups.isEmpty()) {
            builder.append("No tutorial groups found for ").append(program.getProgramName()).append("\n");
        } else {
            for (int i = 0; i < groups.getNumberOfEntries(); i++) {
                TutorialGroup group = groups.get(i);
                if (group != null) {
                    builder.append(String.format("%-4d %-20s %-10s %-10s\n",
                            i + 1, group.getGroupNo(), group.getYear(), group.getSemester()));
                }
            }
        }
        builder.append(formatting.getDivider('-', 75));
        System.out.println(builder.toString());
    }

    public String manageStudentTutorialGroups() {

        System.out.println("\nManage Students to Tutorial Groups");
        System.out.println("1. Add Student to Tutorial Group");
        System.out.println("2. Remove Student from Tutorial Group");
        System.out.println("3. Change the Tutorial Group for a Student");
        System.out.println("4. Merge tutorial group");
        System.out.println("5. List All Students for All Tutorial Groups of Each Program ");
        System.out.println("6. Return to TutorialGroup Menu");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;

    }

    public String inputStudentID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the studentID: ");
        String studentID = scanner.nextLine().toUpperCase();
        return studentID;
    }

    public String inputStudentName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the student name: ");
        String studentName = scanner.nextLine();

        return studentName;
    }

    public String inputTryAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Invalid input. If you want to retry(Y/N):");
        return scanner.nextLine().toUpperCase();

    }

    public Student inputStudentDetails() {
        String studentID = inputStudentID();
        String studentName = inputStudentName();
        // Additional fields as required
        return new Student(studentID, studentName);  // Assuming Programme constructor has these parameters
    }

    public static void printTutorialGroupsInfo(ListInterface<Programme> programmes) {
        int dividerLength = 90;
        String programmeHeaderFormat = "Programme Code: %s\nProgramme Name: %s\n\n";
        String groupHeaderFormat = "%-10s %-20s %-10s\n";
        String studentHeaderFormat = "%-5s %-20s %-20s\n";
        StringBuilder outputStr = new StringBuilder();

        outputStr.append("Student List in Tutorial Groups\n");
        outputStr.append(formatting.getDivider('=', dividerLength));

        for (int i = 0; i < programmes.size(); i++) {
            Programme programme = programmes.get(i);
            outputStr.append(String.format(programmeHeaderFormat, programme.getProgramCode(), programme.getProgramName()));
            outputStr.append(formatting.getDivider('-', dividerLength));

            ListInterface<TutorialGroup> groups = programme.getTutorialGroups();
            outputStr.append(String.format(groupHeaderFormat, "GroupNo:", "Year:", "Semester:"));
            outputStr.append(formatting.getDivider('-', dividerLength));

            for (int j = 0; j < groups.size(); j++) {
                TutorialGroup group = groups.get(j);
                outputStr.append(String.format(groupHeaderFormat, group.getGroupNo(), group.getYear(), group.getSemester()));
                outputStr.append(formatting.getDivider('-', dividerLength));
                outputStr.append(String.format(studentHeaderFormat, "No.", "StudentID", "Student Name"));

                ListInterface<Student> students = group.getStudents();
                for (int k = 0; k < students.size(); k++) {
                    Student student = students.get(k);
                    outputStr.append(String.format(studentHeaderFormat, (k + 1), student.getStudentID(), student.getStudentName()));
                }
                outputStr.append(formatting.getDivider('-', dividerLength));
            }
            outputStr.append(formatting.getDivider('=', dividerLength));
        }

        System.out.println(outputStr.toString());
    }

    public String StudentList(TutorialGroup group) {
        StringBuilder formattedList = new StringBuilder();

        // Append header
        formattedList.append(formatting.getDivider('-', 90))
                .append("\n")
                .append(String.format("%-5s %-20s %-20s\n", "No.", "StudentID", "Student Name"))
                .append(formatting.getDivider('-', 100))
                .append("\n");

        // Append student details
        ListInterface<Student> students = group.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            formattedList.append(String.format("%-5d %-20s %-20s\n", i + 1, student.getStudentID(), student.getStudentName()));
        }

        // Append footer
        formattedList.append(formatting.getDivider('-', 90))
                .append("\n");

        return formattedList.toString();
    }

    public void printRemovalList(Student removedStudent, TutorialGroup tutorialGroup) {
        StringBuilder builder = new StringBuilder();
        int dividerLength = 80;
        String divider = formatting.getDivider('-', dividerLength);

        // Add header
        builder.append("Tutorial group for removed student ");
        builder.append(":\n");

        builder.append(divider);
        builder.append(String.format("%-10s%-10s%-10s\n", "GroupNo:", "Year:", "Semester:"));
        builder.append(divider);
        builder.append(String.format("%-10s%-10s%-10s\n", tutorialGroup.getGroupNo(), tutorialGroup.getYear(), tutorialGroup.getSemester()));
        builder.append(divider);
        builder.append(String.format("%-5s%-20s%-20s\n", "No.", "StudentID", "Student Name"));
        builder.append(divider);

        // Add existing students
        ListInterface<Student> students = tutorialGroup.getStudents();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            builder.append(String.format("%-5d%-20s%-20s\n", (i + 1), student.getStudentID(), student.getStudentName()));
        }
        builder.append(divider);

        // Print the result
        System.out.println(builder.toString());
    }

    public void displayTutorialGroups(ListInterface<TutorialGroup> tutorialGroups) {
        if (tutorialGroups != null && !tutorialGroups.isEmpty()) {
            // Print header
            System.out.println(formatting.getDivider('-', 80));
            System.out.printf("| %-20s | %-20s | %-20s |\n", "GroupNo", "Year", "Semester");
            System.out.println(formatting.getDivider('-', 80));

            // Print the tutorial groups for the student's program
            for (int i = 0; i < tutorialGroups.size(); i++) {
                TutorialGroup group = tutorialGroups.get(i);
                System.out.printf("| %-20s | %-20s | %-20s |\n", group.getGroupNo(), group.getYear(), group.getSemester());
            }

            // Print footer
            System.out.println(formatting.getDivider('-', 80));
        } else {
            System.out.println("No tutorial groups available.");
        }
    }

    public void printChangeGroupStudentLists(TutorialGroup group1, TutorialGroup group2) {
        StringBuilder builder = new StringBuilder();
        int dividerLength = 70;
        String divider = formatting.getDivider('-', dividerLength);

        builder.append(divider).append("\n");
        builder.append("Updated student list for Group ").append(group1.getGroupNo()).append(" ").append(group1.getYear()).append(" ").append(group1.getSemester()).append(":\n");
        builder.append(divider).append("\n");
        builder.append(String.format("%-10s%-30s%-20s\n", "No.", "StudentID", "StudentName"));
        builder.append(divider).append("\n");
        for (int i = 0; i < group1.getStudents().size(); i++) {
            Student student = group1.getStudents().get(i);
            builder.append(String.format("%-10d%-30s%-20s\n", (i + 1), student.getStudentID(), student.getStudentName()));
        }

        builder.append("\n").append(divider).append("\n");
        builder.append("Updated student list for Group ").append(group2.getGroupNo()).append(" ").append(group2.getYear()).append(" ").append(group2.getSemester()).append(":\n");
        builder.append(divider).append("\n");
        builder.append(String.format("%-10s%-30s%-20s\n", "No.", "StudentID", "StudentName"));
        builder.append(divider).append("\n");
        for (int i = 0; i < group2.getStudents().size(); i++) {
            Student student = group2.getStudents().get(i);
            builder.append(String.format("%-10d%-30s%-20s\n", (i + 1), student.getStudentID(), student.getStudentName()));
        }
        builder.append("\n").append(divider).append("\n");
        System.out.println(builder.toString());
    }

    public void printUpdatedStudentList(TutorialGroup group) {
        ListInterface<Student> students = group.getStudents();

        StringBuilder sb = new StringBuilder();
        sb.append("Merged Group Details:\n");
        sb.append("All group in year: ").append(group.getYear()).append(" semester: ").append(group.getSemester()).append("\n");
        sb.append(formatting.getDivider('-', 76));
        sb.append(String.format("%-12s %-15s %-20s %-30s\n", "No.", "GroupNo.", "StudentID", "StudentName"));
        sb.append(formatting.getDivider('-', 76));

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            sb.append(String.format("%-12d %-15s %-20s %-30s\n", i + 1, student.getGroupNo(), student.getStudentID(), student.getStudentName()));
        }
        sb.append(formatting.getDivider('-', 76));
        System.out.println(sb.toString());
    }

    public String listAllStudents() {
        // Implementation of listing all students in a tutorial group and a programme
        System.out.println("\nListing");
        System.out.println("1. List All Student in a Tutorial Group and Programme ");
        System.out.println("2. Return to Main Menu");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    public String formatStudentList(Programme program, TutorialGroup group, ListInterface<Student> students) {
        StringBuilder outputStr = new StringBuilder();
        outputStr.append(formatting.getDivider('-', 90));
        outputStr.append(String.format("Student List in %s - %s %s %s\n", program.getProgramName(), group.getGroupNo(), group.getYear(), group.getSemester()));
        outputStr.append(formatting.getDivider('=', 90));
        outputStr.append(String.format("%-5s %-20s %-40s\n", "No.", "Student ID", "Student Name"));
        outputStr.append(formatting.getDivider('=', 90));

        for (int k = 0; k < students.size(); k++) {
            Student student = students.get(k);
            outputStr.append(String.format("%-5d %-20s %-40s\n", k + 1, student.getStudentID(), student.getStudentName()));
        }

        outputStr.append(formatting.getDivider('-', 90));

        return outputStr.toString();
    }

    public void printStudentList(String outputStr) {
        System.out.println(outputStr);
    }

    public String generateReports() {

        System.out.println("\nSummary Reports");
        System.out.println("1. Tutorial Groups by Programme with Specific Year and Semester Report");
        System.out.println("2. Total Number of Tutorial Group and Students for Each Program Report");
        System.out.println("3. Return to Main Menu");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void displayInputInformation(String year, String semester) {
        System.out.println("\nYear: " + year);
        System.out.println("Semester: " + semester);
        System.out.println();
        System.out.println(formatting.getDivider('=', 80));
        System.out.println(String.format("%-5s %-15s %-35s %-20s", "No.", "ProgramCode", "ProgramName", "Tutorial Group"));
        System.out.println(formatting.getDivider('=', 80));
    }

    public void displayTutorialGroupInfo(Programme programme, TutorialGroup group, int count) {
        System.out.println(String.format("%-5d %-15s %-40s %-20s",
                count,
                programme.getProgramCode(),
                programme.getProgramName(),
                group.getGroupNo()));

    }

    public String generateSummary(ListInterface<Programme> programList) {
        StringBuilder summary = new StringBuilder();
        programList.bubbleSort();
        // Append header
        summary.append(formatting.getDivider('=', 105))
                .append(String.format("%-38s%-35s%-30s\n", "Programme Name", "Total Number Of Tutorial Groups", "Total Number of Students"))
                .append(formatting.getDivider('=', 105))
                .append("\n");

        // loop for iterating over programList
        for (int i = 0; i < programList.size(); i++) {
            Programme program = programList.get(i);
            ListInterface<TutorialGroup> groups = program.getTutorialGroups();
            int totalGroups = groups.size();
            int totalStudents = 0;

            // Calculate total students within the loop
            for (int j = 0; j < groups.size(); j++) {
                TutorialGroup group = groups.get(j);
                totalStudents += group.getStudents().size();
            }

            summary.append(String.format("%-38s%-35s%-30s\n", program.getProgramName(), totalGroups, totalStudents));
        }

        // Append footer
        summary.append(formatting.getDivider('-', 105))
                .append("\n");

        return summary.toString();
    }

    public void printTotalNumberOfGroupAndStudentSummary(String summary) {
        System.out.println(summary);
    }
}
