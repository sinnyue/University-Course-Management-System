/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

/**
 *
 * @author shuej
 */
public class MessageUI {

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice");
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system");
    }

    //successfull messages
    public static void displayRemovalSuccessMessage() {
        System.out.println("\nRemoved successfully");
    }

    public static void displayAmendmentSuccessMessage() {
        System.out.println("\nStudent amended successfully");
    }

    public static void displayAddToCourseSuccessMessage() {
        System.out.println("\nCourse added successfully");
    }

    public static void listOfProgram() {
        System.out.println("\nList of Programme:");
        System.out.println("No.\tProgramCode\tProgramName");
    }

    //error messages
    public static void displayCourseNotFoundError() {
        System.out.println("\nCourse have not exist");
    }

    public static void displayRemovalFailureMessage() {
        System.out.println("\nRemoved fail");
    }

    public static void displayAmendFailureMessage() {
        System.out.println("\nAdmend fail");
    }

    public static void displayStudentNotFoundError() {
        System.out.println("\nStudent have not exist");
    }

    public static void displayStudentOrCourseNotFoundError() {
        System.out.println("\nStudent or course have not exist");
    }

    public static void displayNoRecordsFound() {
        System.out.println("\nRecord have not exist");
    }
    
    public static void displayNoCourseFound(String str) {
        System.out.println("No courses registered for student: " + str);
    }
    

    public static void displayDuplicateError() {
        System.out.println("\nRecord have already exist");
    }

    public static void invalidProgramChoice() {
        System.out.println("\nInvalid program choice.");
    }

    public static void existingCourseFound() {
        System.out.println("Existing courses in the selected program:");
    }

    public static void noExistingCourseFound() {
        System.out.println("No existing courses found in the selected program.");
    }

    public static void invalidFacultyChoice() {
        System.out.println("Invalid faculty choice.");
    }

    public static void invalidCourseChoice() {
        System.out.println("\nInvalid course choice. ");
    }

    public static void noProgramAssociatedWithCourse() {
        System.out.println("No programs associated with this course.");
    }

    public static void yesProgramAssociatedWithCourse() {
        System.out.println("Programs associated with the selected course:");
    }

    public static void currentCourseDetail() {
        System.out.println("Current details of the selected course:");
    }

    public static void successCourseAmend() {
        System.out.println("Course details amended successfully.");
    }

    public static void courseAssociatedAdd() {
        System.out.println("The course is already associated with the selected program.");
    }

    public static void invalidChoiceNoMoreChoice() {
        System.out.print("Do you want to add another course? (yes/no): ");
    }

    public static void intialProgramList() {
        System.out.println("Initial Program List:");
    }

    public static void displayITutorialAddedtoProgrammmeSuccessfulMessage() {
        System.out.println("New tutorial group has been successfully added");
    }

    public static void displayFailedAddedGroupErrorMessage() {
        System.out.println("Failed to add tutorial group to the program.");
    }

    public static void displayTutorialRemovedFromProgramSuccessfulMessage() {
        System.out.println("New tutorial group has been successfully removed");
    }

    public static void displayTutorialGroupAlreadyExistsMessage() {
        System.out.println("Tutorial group already exist");
    }

    public static void displayGroupNotFoundErrorMessage() {
        System.out.println("Tutorial Group not found.");
    }

    public static void displayProgramNotFoundErrorMessage() {
        System.out.println("Program not found.");
    }

    public static void displayUpdatedList() {
        System.out.println("Updated Program List:");
    }

    public static void displayUpdatedGroupList() {
        System.out.println("\nUpdated Group List:");
    }

    public static void displayGroupIsFull() {
        System.out.println("Selected group is full. Please choose another group.");
    }

    public static void displaySelectedGroupNotFound() {
        System.out.println("Selected group is not found. Please try again.");
    }

    public static void displayStudentNotRegistered() {
        System.out.println("Student not registered.");
    }

    public static void displayCancelledMessages() {
        System.out.println("Operation canceled.");
    }

    public static void displayStudentRemovedFromGroupsSuccessfulMessage() {
        System.out.println("Student has been successfully removed");
    }

    public static void displayStudentNotFoundErrorMessage() {
        System.out.println("Student not found.");
    }

    public static void groupNoFoundforMerge() {
        System.out.println("No groups found to merge for the specified program, year, and semester.");
    }

    public static void groupNoFoundforList() {
        System.out.println("No tutorial groups found for the specified year and semester.");
    }

    public static void NoStudentFound() {
        System.out.println("No students found in this group!");
    }

    public static void displaySuccessChangedGroupMessage() {
        System.out.println("Students have successfully changed groups within the same programme, year, and semester.");
    }

    public static void displayFailChangedGroupMessage() {
        System.out.println("Changing not possible as students not found or in the same group, or not in the same year, semester, and programme");
    }

    public static void displayStudentAddedtoGroupSuccessfulMessage() {
        System.out.println("new student has been successfully added");
    }

    public static void displayFailedAddedStudentErrorMessage() {
        System.out.println("Failed to add sudent to the tutorial groups.");
    }

    public static void displayGroupNoNotFoundErrorMessage() {
        System.out.println(" GroupNo not found.");
    }

    public static void displayExitingMenu() {
        System.out.println("Exiting menu......");
    }

    public static void displayAssignmentTeamAddedToGroupSuccessfulMessage() {
        System.out.println("Assignment team added to tutorial group successfully.");
    }

    public static void displayReachedMaximumLimitOfAssignmentTeams() {
        System.out.println("The tutorial group has reached the limit of assignment teams.");
    }

    public static void displayAssignmentTeamUpdatedSuccessfulMessage() {
        System.out.println("Assignment team updated successfully.");
    }

    public static void displayAssignmentTeamNameAlreadyExistsMessage() {

        System.out.println("Assignment team name already exist");
    }

    public static void displayStudentAddedtoTeamSuccessfulMessage() {
        System.out.println("student has been added successfully added");
    }

    public static void displayStudentRemovedFromTeamSuccessfullyMessage() {
        System.out.println("student has been removed successfully");
    }

    public static void displayMergeTeamsSuccessfulMessage() {
        System.out.println("Assignment teams has been merged successfully");
    }

    public static void displayTeamsNotFoundErrorMessage() {
        System.out.println("Both or one of the teams are not found");
    }

    public static void displayErrorMessage() {
        System.out.println("Please input your choice with numeric.");
    }

    public static void displayAssignmentTeamNotFoundErrorMessage() {
        System.out.println("Assignment Team not found.");
    }

    public static void displayAssignmentTeamRemovedSuccessfullyMessage() {
        System.out.println("The assignment team has been successfully removed");
    }

    public static void displayAssignmentSuccessMessage() {
        System.out.println("\nAssigned successfully");
    }

    public static void displayAssignmentFailureMessage() {
        System.out.println("\nFailed to assign . Record not found.");
    }

    public static void displayTutorNotFoundError() {
        System.out.println("\nTutor not found. Please enter a valid Tutor ID");
    }

    public static void displayTutorialgroupNotFoundError() {
        System.out.println("Error: Tutorial group not found. Please enter a valid tutorial group ID.");
    }

    public static void displayAddNewTutorMessage() {
        System.out.println("Tutor not found. Please add a new tutor.");
    }

    public static void displayNewTutorAddedMessage() {
        System.out.println("Successfully add a new tutor.");
    }
    
    
}
