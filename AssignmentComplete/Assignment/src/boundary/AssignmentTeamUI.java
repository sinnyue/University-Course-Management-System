package boundary;

/**
 *
 * @author User
 */
import adt.*;
import entity.AssignmentTeam;
import control.AssignmentTeamManagement;
import entity.Student;
import entity.TutorialGroup;
import java.util.Scanner;

public class AssignmentTeamUI {

    private AssignmentTeamManagement teamDriver;
    private Scanner scanner;

    public AssignmentTeamUI(AssignmentTeamManagement teamDriver) {
        this.teamDriver = teamDriver;
        this.scanner = new Scanner(System.in);
    }

    public AssignmentTeamUI() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void displayTeamsForTutorialGroup(TutorialGroup tGroup) {

        if (tGroup.getAssignmentTeams().isEmpty()) {
            System.out.println("No teams found for tutorial group: " + tGroup.getGroupNo() + tGroup.getSemester()
                    + tGroup.getYear());
        } else {
            System.out.println("Listing all teams in tutorial group: " + tGroup);
            for (int i = 1; i <= tGroup.getAssignmentTeams().getNumberOfEntries(); i++) {
                AssignmentTeam team = tGroup.getAssignmentTeams().getEntry(i);

                System.out.println("Team Name: " + team.getTeamName() + " with Students: ");
                ListInterface<Student> students = team.getStudents();
                for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                    System.out.print("\n" + students.getEntry(j) + " ");
                }
                System.out.println();
            }
        }
    }

    public void displayOnlyTeamsForTutorialGroup(TutorialGroup tGroup) {
        if (tGroup.getAssignmentTeams().isEmpty()) {
            System.out.println("No teams found for tutorial group: " + tGroup.getGroupNo() + tGroup.getSemester()
                    + tGroup.getYear());
        } else {
            System.out.println("Listing all teams in tutorial group: " + tGroup);
            for (int i = 1; i <= tGroup.getAssignmentTeams().getNumberOfEntries(); i++) {
                AssignmentTeam team = tGroup.getAssignmentTeams().getEntry(i);
                System.out.println("Team Name: " + team.getTeamName());
            }
        }
    }

    public void displayStudentsForTutorialGroup(TutorialGroup tGroup) {
        if (tGroup.getStudents().isEmpty()) {
            System.out.println("No students found for tutorial group: " + tGroup.getGroupNo() + tGroup.getSemester() + tGroup.getYear());
        } else {
            System.out.println("Listing all students in tutorial group: ");
            ListInterface<Student> students = tGroup.getStudents();
            for (int i = 1; i <= students.getNumberOfEntries(); i++) {
                System.out.println(students.getEntry(i));
            }
        }
    }

    public void displayStudentsInTeam(TutorialGroup tGroup, String teamName) {
        ListInterface<AssignmentTeam> assignmentTeams = tGroup.getAssignmentTeams();

        if (assignmentTeams.isEmpty()) {
            System.out.println("No teams found for tutorial group: " + tGroup.getGroupNo() + tGroup.getSemester()
                    + tGroup.getYear());
        } else {
            AssignmentTeam selectedTeam = null;

            // Find the assignment team with the specified name
            for (int i = 1; i <= assignmentTeams.getNumberOfEntries(); i++) {
                AssignmentTeam team = assignmentTeams.getEntry(i);
                if (team.getTeamName().equals(teamName)) {
                    selectedTeam = team;
                    break;
                }
            }

            if (selectedTeam != null) {
                System.out.println("Team Name: " + selectedTeam.getTeamName());
                ListInterface<Student> students = selectedTeam.getStudents();

                if (students.isEmpty()) {
                    System.out.println("No students assigned to this team.");
                } else {
                    System.out.println("Students:");
                    for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                        Student student = students.getEntry(j);
                        System.out.println("  " + student.getStudentID() + ": " + student.getStudentName());
                    }
                }
            } else {
                System.out.println("Assignment team with name " + teamName + " not found.");
            }
        }
    }

    public void displayMenu() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("\n--- Assignment Team Management Menu ---");
        System.out.println("\n" + "-".repeat(50));

        System.out.println("1. Create Assignment Team");
        System.out.println("2. Remove Assignment Team");
        System.out.println("3. Update Assignment Team");
        System.out.println("4. List Students in Assignment Team");
        System.out.println("5. Add Students to Assignment Team");
        System.out.println("6. Remove Students from Assignment Team");
        System.out.println("7. Merge Assignment Teams");
        System.out.println("8. List Assignment Teams for Tutorial Group");
        System.out.println("9. Generate assignment teams in all tutorial group summary report");
        System.out.println("10. Generate all students enrolled in each tutorial group with teams summary report");
        System.out.println("0. Exit the subsystem");

    }

    public int getChoiceFromUser() {
        System.out.println("Enter your choice: ");
        int choice;
        choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public String getChoiceGFromUser() {
        System.out.println("Enter your choice: ");
        String choice;
        choice = scanner.nextLine();
        scanner.nextLine();
        return choice;
    }

    public String inputTutorialGroupId() {
        System.out.println("Enter the tutorial group ID: ");
        String tutorialGroupId = scanner.nextLine();
        return tutorialGroupId;
    }

    public String inputTeamName() {
        System.out.println("Enter the team name: ");
        String teamName = scanner.nextLine();
        return teamName;
    }

    public String inputStudentId() {
        System.out.println("Enter the student ID: ");
        String studentID = scanner.nextLine();
        return studentID;
    }

    public String inputStudentName() {
        System.out.println("Enter the student name: ");
        String studentName = scanner.nextLine();
        return studentName;
    }

    public String inputNewTeamName() {
        System.out.println("Enter the new team name: ");
        String newTeamName = scanner.nextLine();
        return newTeamName;
    }

    public void showCurrentTutorialGroup(TutorialGroup tGroup) {
        System.out.println("The tutorial group: " + tGroup.getGroupNo() + tGroup.getYear() + tGroup.getSemester());
    }

    public String inputTeam1Name() {
        System.out.println("Enter the name of the first team to merge: ");
        String teamName = scanner.nextLine();
        return teamName;
    }

    public String inputTeam2Name() {
        System.out.println("Enter the name of the second team to merge: ");
        String teamName = scanner.nextLine();
        return teamName;
    }

    //show all tutorial group having how many assignment teams and show the existing assignment teams
    public void generateSummaryReport1(ListInterface<TutorialGroup> tutorialGroups) {
        // Iterate over each tutorial group
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
            // Get the assignment teams for the current tutorial group
            ListInterface<AssignmentTeam> assignmentTeams = tutorialGroup.getAssignmentTeams();

            // Display tutorial group information
            System.out.println("Tutorial Group: " + tutorialGroup.getGroupNo() + tutorialGroup.getYear()
                    + tutorialGroup.getSemester());
            System.out.println("Number of Assignment Teams: " + assignmentTeams.getNumberOfEntries());

            // Display existing assignment teams
            System.out.println("Existing Assignment Teams:");
            if (assignmentTeams.isEmpty()) {
                System.out.println("None");
            } else {
                for (int j = 1; j <= assignmentTeams.getNumberOfEntries(); j++) {
                    AssignmentTeam team = assignmentTeams.getEntry(j);
                    System.out.println("- " + team.getTeamName());
                }
            }

            System.out.println(); // Add a blank line between tutorial groups
        }
    }

    // shows all students enrolled in each tutorial group along with their assigned assignment teams.
    public void generateSummaryReport2(ListInterface<TutorialGroup> tutorialGroups) {
        // Iterate over each tutorial group
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
            // Display tutorial group information
            System.out.println("Tutorial Group: " + tutorialGroup.getGroupNo());

            // Get the students in the tutorial group
            ListInterface<Student> students = tutorialGroup.getStudents();

            // Display the students and their assigned assignment teams
            System.out.println("Students:");
            if (students.isEmpty()) {
                System.out.println("None");
            } else {
                for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                    Student student = students.getEntry(j);
                    System.out.println("- Student ID: " + student.getStudentID() + ", Name: " + student.getStudentName());

                    // Get the assignment team for the student
                    AssignmentTeam assignedTeam = getAssignedTeamForStudent(tutorialGroup, student);
                    if (assignedTeam != null) {
                        System.out.println("  Assigned Team: " + assignedTeam.getTeamName());
                    } else {
                        System.out.println("  Assigned Team: None");
                    }
                }
            }

            System.out.println(); // Add a blank line between tutorial groups
        }
    }

// Helper method to get the assignment team assigned to a student in a tutorial group
    private AssignmentTeam getAssignedTeamForStudent(TutorialGroup tutorialGroup, Student student) {
        ListInterface<AssignmentTeam> assignmentTeams = tutorialGroup.getAssignmentTeams();
        for (int i = 1; i <= assignmentTeams.getNumberOfEntries(); i++) {
            AssignmentTeam team = assignmentTeams.getEntry(i);
            ListInterface<Student> studentsInTeam = team.getStudents();
            if (studentsInTeam.contains(student)) {
                return team;
            }
        }
        return null; // Student not assigned to any team
    }
}
