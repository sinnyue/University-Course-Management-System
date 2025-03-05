package control;

import adt.*;
import boundary.AssignmentTeamUI;
import boundary.TutorialGroupManagementUI;
import boundary.MainMenuUI;

import entity.*;
import utility.MessageUI;

public class AssignmentTeamManagement {

    private ListInterface<AssignmentTeam> teams;
    private AssignmentTeamUI teamUI;
    private ListInterface<TutorialGroup> tutorialGroup;
    private ListInterface<Student> students;

    private MainMenu mainMenu;

    public AssignmentTeamManagement() {
        teams = new ArrayList<>();
        this.teamUI = new AssignmentTeamUI(this);
        this.tutorialGroup = new ArrayList<>();
        students = new ArrayList();

    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public boolean createTeam(AssignmentTeam team) {
        return teams.add(team);
    }

    public boolean removeTeam(String teamNo) {
        for (int i = 1; i <= teams.getNumberOfEntries(); i++) {
            AssignmentTeam team = teams.getEntry(i);
            if (team.getTeamName().equals(teamNo)) {
                teams.remove(i);
                return true;
            }
        }
        return false;
    }

    public void startMenu(TutorialGroupManagement TGM) {
        int number;

        for (int i = 1; i <= TGM.getProgramList().getNumberOfEntries(); i++) {
            Programme programme = TGM.getProgramList().getEntry(i);
            for (int j = 1; j <= programme.getTutorialGroups().getNumberOfEntries(); j++) {
                tutorialGroup.add(programme.getTutorialGroups().getEntry(j));
            }
        }
        students = TGM.getStudentList();
        TutorialGroupManagementUI tgmUI = new TutorialGroupManagementUI();
        int choice;
        do {
            teamUI.displayMenu();
            choice = teamUI.getChoiceFromUser();
            switch (choice) {
                case 1:
                    tgmUI.displayTutorialGroups(tutorialGroup);
                    number = teamUI.getChoiceFromUser();
                    handleCreateTeam(tutorialGroup.getEntry(number));
                    break;
                case 2:
                    tgmUI.displayTutorialGroups(tutorialGroup);
                    number = teamUI.getChoiceFromUser();
                    handleRemoveTeam(tutorialGroup.getEntry(number));
                    break;
                case 3:
                    tgmUI.displayTutorialGroups(tutorialGroup);
                    number = teamUI.getChoiceFromUser();
                    handleUpdateTeam(tutorialGroup.getEntry(number));
                    break;
                case 4:
                    tgmUI.displayTutorialGroups(tutorialGroup);
                    int group = teamUI.getChoiceFromUser();
                    teamUI.displayOnlyTeamsForTutorialGroup(tutorialGroup.getEntry(group));
                    String selectedTeamName = teamUI.inputTeamName();
                    teamUI.displayStudentsInTeam(tutorialGroup.getEntry(group), selectedTeamName);
                    break;
                case 5:
                    tgmUI.displayTutorialGroups(tutorialGroup);
                    number = teamUI.getChoiceFromUser();
                    handleAddStudentToTeam(tutorialGroup.getEntry(number));
                    break;
                case 6:
                    tgmUI.displayTutorialGroups(tutorialGroup);
                    number = teamUI.getChoiceFromUser();
                    handleRemoveStudentFromTeam(tutorialGroup.getEntry(number));
                    break;
                case 7:
                    tgmUI.displayTutorialGroups(tutorialGroup);
                    number = teamUI.getChoiceFromUser();
                    handleMergeTeams(tutorialGroup.getEntry(number));
                    break;
                case 8:
                    tgmUI.displayTutorialGroups(tutorialGroup);
                    group = teamUI.getChoiceFromUser();
                    teamUI.displayTeamsForTutorialGroup(tutorialGroup.getEntry(group));
                    break;
                case 9:
                    teamUI.generateSummaryReport1(tutorialGroup);

                    break;
                case 10:
                    teamUI.generateSummaryReport2(tutorialGroup);
                    break;
                case 0:
                    MessageUI.displayExitingMenu();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    // Methods for handling assignment team operations
    private void handleCreateTeam(TutorialGroup tGroup) {
        // 1. Retrieve the list of existing assignment teams
        ListInterface<AssignmentTeam> assignmentTeams = tGroup.getAssignmentTeams();

        // 2. Check if the number of existing assignment teams exceeds three
        if (assignmentTeams.getNumberOfEntries() >= 3) {
            teamUI.displayTeamsForTutorialGroup(tGroup);
            MessageUI.displayReachedMaximumLimitOfAssignmentTeams();
            return; // Return to the menu
        }

        // 3. Display the existing assignment teams
        teamUI.displayTeamsForTutorialGroup(tGroup);

        // 4. Prompt the user to input the details for the new assignment team
        String teamName = teamUI.inputTeamName();

        // 5. Check if the new assignment team name is the same as any existing assignment team
        for (int i = 1; i <= assignmentTeams.getNumberOfEntries(); i++) {
            AssignmentTeam team = assignmentTeams.getEntry(i);
            if (team.getTeamName().equals(teamName)) {
                MessageUI.displayAssignmentTeamNameAlreadyExistsMessage();
                return; // Return to the menu
            }
        }

        // 6. Add the new assignment team to the TutorialGroup
        AssignmentTeam newTeam = new AssignmentTeam(teamName);
        tGroup.getAssignmentTeams().add(newTeam);
        MessageUI.displayAssignmentTeamAddedToGroupSuccessfulMessage();
    }

    private void handleAddStudentToTeam(TutorialGroup tGroup) {
        // Retrieve the list of existing assignment teams
        ListInterface<AssignmentTeam> assignmentTeams = tGroup.getAssignmentTeams();

        // Retrieve the list of students in the tutorial group
        ListInterface<Student> students = tGroup.getStudents();

        // Display the list of students in the tutorial group
        teamUI.displayStudentsForTutorialGroup(tGroup);

        // Prompt the user to select a student
        String selectedStudentId = teamUI.inputStudentId();

        //Find student with id
        Student studentToAdd = findStudentById(selectedStudentId, students);

        if (studentToAdd != null) {
            // Display the list of assignment teams
            teamUI.displayTeamsForTutorialGroup(tGroup);

            // Prompt the user to enter the team name
            String selectedTeamName = teamUI.inputTeamName();

            boolean teamFound = false;
            for (int i = 1; i <= assignmentTeams.getNumberOfEntries(); i++) {
                AssignmentTeam team = assignmentTeams.getEntry(i);
                if (team.getTeamName().equals(selectedTeamName)) {
                    team.getStudents().add(studentToAdd);
                    teamFound = true;
                    break; // No need to continue looping once the team is found
                }
            }

            if (teamFound) {
                MessageUI.displayStudentAddedtoTeamSuccessfulMessage();
            } else {
                MessageUI.displayAssignmentTeamNotFoundErrorMessage();
            }
        } else {
            MessageUI.displayStudentNotFoundErrorMessage();
        }
    }

    private Student findStudentById(String studentId, ListInterface<Student> students) {
        for (int i = 1; i <= students.getNumberOfEntries(); i++) {
            Student student = students.getEntry(i);
            if (student.getStudentID().equals(studentId)) {
                return student; // Student found
            }
        }
        return null; // Student not found
    }

    private void handleRemoveTeam(TutorialGroup tGroup) {
        // Display all assignment teams under tutorial group
        teamUI.displayTeamsForTutorialGroup(tGroup);

        // Get the team name to remove
        String teamNameToRemove = teamUI.inputTeamName();

        // Retrieve the list of assignment teams from the tutorial group
        ListInterface<AssignmentTeam> assignmentTeams = tGroup.getAssignmentTeams();

        boolean teamFound = false;
        // Iterate through the assignment teams to find the team with the specified name
        for (int i = 1; i <= assignmentTeams.getNumberOfEntries(); i++) {
            AssignmentTeam team = assignmentTeams.getEntry(i);
            if (team.getTeamName().equals(teamNameToRemove)) {
                // Remove the team if found
                assignmentTeams.remove(i);
                teamFound = true;
                MessageUI.displayAssignmentTeamRemovedSuccessfullyMessage();
                break; // Exit loop after removing the team
            }
        }

        if (!teamFound) {
            // If the team with the specified name is not found
            MessageUI.displayAssignmentTeamNotFoundErrorMessage();
        }
    }

    private void handleUpdateTeam(TutorialGroup tGroup) {
        // Display all assignment teams under tutorial group
        teamUI.displayTeamsForTutorialGroup(tGroup);

        // Get the team name to update
        String teamNameToUpdate = teamUI.inputTeamName();
        String newTeamName = teamUI.inputNewTeamName();

        // Retrieve the list of assignment teams from the tutorial group
        ListInterface<AssignmentTeam> assignmentTeams = tGroup.getAssignmentTeams();

        boolean teamFound = false;
        // Iterate through the assignment teams to find the team with the specified name
        for (int i = 1; i <= assignmentTeams.getNumberOfEntries(); i++) {
            AssignmentTeam team = assignmentTeams.getEntry(i);
            if (team.getTeamName().equals(teamNameToUpdate)) {
                // Update the team name if found
                team.setTeamName(newTeamName);
                teamFound = true;
                MessageUI.displayAssignmentTeamUpdatedSuccessfulMessage();
                break; // Exit loop after updating the team name
            }
        }

        if (!teamFound) {
            // If the team with the specified name is not found
            MessageUI.displayAssignmentTeamNotFoundErrorMessage();
        }
    }

    private void handleRemoveStudentFromTeam(TutorialGroup tGroup) {
        // Display all assignment teams under the tutorial group
        teamUI.displayTeamsForTutorialGroup(tGroup);

        // Get the team name from which to remove the student
        String teamNameToRemoveFrom = teamUI.inputTeamName();

        // Retrieve the list of assignment teams from the tutorial group
        ListInterface<AssignmentTeam> assignmentTeams = tGroup.getAssignmentTeams();

        // Flag to track if the team was found
        boolean teamFound = false;

        // Iterate through the assignment teams to find the team with the specified name
        for (int i = 1; i <= assignmentTeams.getNumberOfEntries(); i++) {
            AssignmentTeam team = assignmentTeams.getEntry(i);
            if (team.getTeamName().equals(teamNameToRemoveFrom)) {
                // Display the students in the team
                teamUI.displayStudentsInTeam(tGroup, teamNameToRemoveFrom);

                // Get the student ID to remove from the team
                String studentIdToRemove = teamUI.inputStudentId();

                // Retrieve the list of students in the team
                ListInterface<Student> students = team.getStudents();

                // Flag to track if the student was found and removed
                boolean studentRemoved = false;

                for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                    Student student = students.getEntry(j);
                    if (student.getStudentID().equals(studentIdToRemove)) {
                        // Remove student if found
                        students.remove(j);
                        studentRemoved = true;
                        MessageUI.displayStudentRemovedFromTeamSuccessfullyMessage();
                        break; // Exit the loop after removing the student
                    }
                }

                // If the student was not found, display an error message
                if (!studentRemoved) {
                    MessageUI.displayStudentNotFoundErrorMessage();
                }

                // Update the team's list of students
                team.setStudents(students);

                teamFound = true;
                break; // Exit the loop after finding the team
            }
        }

        // If the team was not found, display an error message
        if (!teamFound) {
            MessageUI.displayAssignmentTeamNotFoundErrorMessage();
        }
    }

    private void handleMergeTeams(TutorialGroup tGroup) {
        // Display all assignment teams under tutorial group
        teamUI.displayTeamsForTutorialGroup(tGroup);

        // Get the names of the teams to merge
        String teamName1 = teamUI.inputTeam1Name();
        String teamName2 = teamUI.inputTeam2Name();

        // Retrieve the list of assignment teams from the tutorial group
        ListInterface<AssignmentTeam> assignmentTeams = tGroup.getAssignmentTeams();

        // Find the teams with the specified names
        AssignmentTeam team1 = findTeamByName(assignmentTeams, teamName1);
        AssignmentTeam team2 = findTeamByName(assignmentTeams, teamName2);

        // Check if both teams are found
        if (team1 != null && team2 != null) {
            // Merge the teams
            mergeTeams(team1, team2);

            // Remove the second team from the list of assignment teams
            assignmentTeams.remove(team2);

            // Display a success message
            MessageUI.displayMergeTeamsSuccessfulMessage();
        } else {
            // Display an error message if one or both teams are not found
            MessageUI.displayTeamsNotFoundErrorMessage();
        }
    }

// Helper method to find a team by its name
    private AssignmentTeam findTeamByName(ListInterface<AssignmentTeam> teams, String name) {
        for (int i = 1; i <= teams.getNumberOfEntries(); i++) {
            AssignmentTeam team = teams.getEntry(i);
            if (team.getTeamName().equals(name)) {
                return team;
            }
        }
        return null; // Return null if the team is not found
    }

// Helper method to merge two teams
    private void mergeTeams(AssignmentTeam team1, AssignmentTeam team2) {
        // Merge the student lists of the two teams
        ListInterface<Student> students1 = team1.getStudents();
        ListInterface<Student> students2 = team2.getStudents();
        for (int i = 1; i <= students2.getNumberOfEntries(); i++) {
            Student student = students2.getEntry(i);
            students1.add(student);
        }
    }

//
//    public static void main(String[] args) {
//       // Create instances of AssignmentTeamManagement and AssignmentTeamUI
//        AssignmentTeamManagement teamControl = new AssignmentTeamManagement();
//        AssignmentTeamUI teamUI = new AssignmentTeamUI(teamControl);
//        TutorialGroupManagement TGM = new TutorialGroupManagement();
//        teamControl.startMenu(TGM);
//        // Display the menu and handle user interactions
//        
//    }
}
