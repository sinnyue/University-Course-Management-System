package control;

import adt.ArrayList;
import adt.ListInterface;
import boundary.StudentRegistrationSystemUI;
import dao.CourseInitializer;
import dao.StudentProgrammeInitializer;
import entity.Course;
import entity.Programme;
import entity.Student;
import entity.StudentCourse;
import utility.MessageUI;

public class StudentRegistrationSystem {

    private MainMenu mainMenu;
    private ListInterface<Student> studentList = new ArrayList<>();
    private ListInterface<StudentCourse> stuCourseList = new ArrayList<>();
    private ListInterface<Course> courseList = new ArrayList<>();
    private ListInterface<Programme> programList = new ArrayList<>();
    private StudentRegistrationSystemUI systemUI = new StudentRegistrationSystemUI();
    private CourseInitializer initializer = new CourseInitializer();
    private StudentProgrammeInitializer SPdao = new StudentProgrammeInitializer();
    private CourseManagementSystem courseSystem;

    public StudentRegistrationSystem(ListInterface<Programme> programList) {
        this.studentList = SPdao.initializeRegistered();
        this.courseList = initializer.courseInitializer();
        this.programList = programList;
        this.courseSystem = new CourseManagementSystem();
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void runStudentMaintenance() {
        String choice = "0";
        do {
            choice = systemUI.getMenuChoice();
            switch (choice) {
                case "0":
                    MessageUI.displayExitMessage();
                    systemUI.listAllStudent(getAllStudent()); // Display all students
                    break;
                case "1":
                    addStudent();
                    break;
                case "2":
                    removeStudent();
                    break;
                case "3":
                    amendStudentDetails();
                    break;
                case "4":
                    searchForRegisteredCourses();
                    break;
                case "5":
                    addStudentToCourse();
                    break;
                case "6":
                    removeStudentFromCourse();
                    break;
                case "7":
                    calculateFeeForRegisteredCourses();
                    break;
                case "8":
                    filterStudentsForCourses();
                    break;
                case "9":
                    choice = systemUI.getReportChoice();
                    switch (choice) {
                        case "1":
                            generateTotalFeesReport();
                            break;
                        case "2":
                            generateStudentEnrollmentByCourseCategoryReport();
                            break;
                        default:
                            MessageUI.displayInvalidChoiceMessage();
                    }
                    break;
                case "10":
                    mainMenu.runMainMenu();
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != "0");
    }

    public ListInterface<Student> getStudentList() {
        return studentList;
    }

    //1. Add new students and select a programme
    public void addStudent() {
        systemUI.listAllProgramme(getAllProgram());
        String stuProgramme = systemUI.inputInterestProgram(); // input interest programme

        // Find the programme by its code
        Programme selectedProgramme = findProgramByCode(stuProgramme);
        if (selectedProgramme == null) {
            MessageUI.displayInvalidChoiceMessage();
            return;
        }

        String studentName = systemUI.inputStudentName(); // input student name
        if (!isAlphabets(studentName)) {
            MessageUI.displayInvalidChoiceMessage();
            return;
        }

        String stuContact = systemUI.inputStudentstuContact(); // input student contact number
        if (!isDigits(stuContact) || !validPhoneNumber(stuContact)) {
            MessageUI.displayInvalidChoiceMessage();
            return;
        }

        // Genarate student ID
        String studentID = generateStudentID(selectedProgramme); // Generate student ID based on selected programme
        Student newStudent = new Student(studentID, studentName, selectedProgramme, stuContact);
        studentList.add(newStudent);
        systemUI.displayStudentInfo(newStudent);
    }

    //generate student ID
    public String generateStudentID(Programme selectedProgramme) {
        // Initial counters for each known program code
        int countWMA = 0; // For BACC
        int countWMM = 0; // For BM
        int countWMB = 0; // For BBA
        int countWME = 0; // For BEE
        int countWMS = 0; // For BSE

        // Iterate through the student list
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            Student student = studentList.getEntry(i);
            String studentID = student.getStudentID(); // Get student ID
            // Extract program code from ID
            String programCode = studentID.substring(2, 5);

            // Increment counters based on program code
            if (programCode.contains("WMA")) {
                countWMA++;
            } else if (programCode.contains("WMM")) {
                countWMM++;
            } else if (programCode.contains("WMB")) {
                countWMB++;
            } else if (programCode.contains("WME")) {
                countWME++;
            } else if (programCode.contains("WMS")) {
                countWMS++;
            }
        }
        
        // Calculate total number of students for each program code
        int totalStudentsWMA = countWMA;
        int totalStudentsWMM = countWMM;
        int totalStudentsWMB = countWMB;
        int totalStudentsWME = countWME;
        int totalStudentsWMS = countWMS;
        
        String programCode = ""; // Initialize program code
        String sequenceNumber = "";
        int lastNumber;
        switch (selectedProgramme.getProgramCode()) { // Determine program code based on selected programme
            case "BACC":
                programCode = "WMA";
                // Get the last registered number for the specific program
                lastNumber = countWMA;
                lastNumber++; // Increment the sequence number by 1
                sequenceNumber = String.format("%03d", lastNumber); // Format the sequence number with leading zeros
                break;
            case "BM":
                programCode = "WMM";
                // Get the last registered number for the specific program
                lastNumber = countWMM;
                lastNumber++; // Increment the sequence number by 1
                sequenceNumber = String.format("%03d", lastNumber); // Format the sequence number with leading zeros
                break;
            case "BBA":
                programCode = "WMB";
                // Get the last registered number for the specific program
                lastNumber = countWMB;
                lastNumber++; // Increment the sequence number by 1
                sequenceNumber = String.format("%03d", lastNumber); // Format the sequence number with leading zeros
                break;
            case "BEE":
                programCode = "WME";
                // Get the last registered number for the specific program
                lastNumber = countWME;
                lastNumber++; // Increment the sequence number by 1
                sequenceNumber = String.format("%03d", lastNumber); // Format the sequence number with leading zeros
                break;
            case "BSE":
                programCode = "WMS";
                // Get the last registered number for the specific program
                lastNumber = countWMS;
                lastNumber++; // Increment the sequence number by 1
                sequenceNumber = String.format("%03d", lastNumber); // Format the sequence number with leading zeros
                break;
            default:
                break;
        }

        return "22" + programCode + sequenceNumber; // Construct the student ID
    }

    public Programme findProgramByCode(String code) {
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
            Programme programme = programList.getEntry(i);
            if (programme.getProgramCode().equals(code)) {
                return programme;
            }
        }
        return null; // Return null if no matching programme is found
    }

    // Method to check if an int contains only digits
        public static boolean isDigits(String str) {
        // Check if each character in the string is a digit
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // Method to check if a string contains only alphabetic characters
    public static boolean isAlphabets(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    // Method to check if an integer represents a valid phone number
    public static boolean validPhoneNumber(String phoneNumber) {
        // Check if the integer is non-negative and consists of exactly 10 digits
        if (phoneNumber != null && phoneNumber.matches("\\d{10}")) {
            return true; // Return true if the integer represents a valid phone number
        } else {
            return false; // Otherwise, return false
        }
    }

    public String getAllStudent() {
        String outputStr = "";
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            outputStr += studentList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    public String getAllProgram() {
        String outputStr = "";
        for (int i = 1; i <= programList.getNumberOfEntries(); i++) {
            outputStr += programList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    //2. Remove a Student
    public void removeStudent() {
        systemUI.listAllStudent(getAllStudent()); // Display all students
        String studentIDToRemove = systemUI.inputStudentID(); // Assuming inputStudentIDToRemove() returns a String
        Student studentToRemove = findStudentByID(studentIDToRemove);
        if (studentToRemove != null) {
            studentList.remove(studentToRemove);
            MessageUI.displayRemovalSuccessMessage();
        } else {
            MessageUI.displayStudentNotFoundError();
        }
        systemUI.listAllStudent(getAllStudent());
    }

    private Student findStudentByID(String studentID) {
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            Student currentStudent = studentList.getEntry(i);
            if (currentStudent.getStudentID().equals(studentID)) {
                return currentStudent;
            }
        }
        return null; // Student not found
    }
    
    //3. Amend Student details
    void amendStudentDetails() {
        systemUI.listAllStudent(getAllStudent()); // Display all students
        String studentIDToAmend = systemUI.inputStudentID(); // Assuming inputStudentIDToAmend() returns a String
        Student studentToAmend = findStudentByID(studentIDToAmend);
        if (studentToAmend != null) {
            String newName = systemUI.inputStudentName(); // Assuming inputNewName() returns a String
            studentToAmend.setStudentName(newName);
            String newDetail = systemUI.inputStudentstuContact(); // Assuming inputNewName() returns a String
            studentToAmend.setContactNo(newDetail);
            MessageUI.displayAmendmentSuccessMessage();
        } else {
            MessageUI.displayStudentNotFoundError();
        }
        systemUI.listAllStudent(getAllStudent()); // Display all students
    }

    // Utility method to find the position of a student by ID
    private int findStudentPositionByID(String studentID) {
        for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
            if (studentList.getEntry(i).getStudentID().equals(studentID)) {
                return i;
            }
        }
        return -1; // Return -1 if the student is not found
    }

    //4. Search Student for registered courses
    public void searchForRegisteredCourses() {
        systemUI.listAllStudent(getAllStudent()); // Display all students
        String studentIDToSearch = systemUI.inputStudentID(); // Get student ID to search
        Student studentToSearch = findStudentByID(studentIDToSearch); // Find student by ID
        if (studentToSearch != null) {
            boolean coursesFound = false;
            systemUI.displayRegisteredCourse();
            for (int i = 1; i <= stuCourseList.getNumberOfEntries(); i++) {
                StudentCourse studentCourse = stuCourseList.getEntry(i);
                if (studentCourse.getStudent().equals(studentToSearch)) {
                    Course course = studentCourse.getCourse();
                    coursesFound = true;
                    systemUI.displayRegisteredCourse(course.getCourseCode(), course.getCourseName(), 
                            studentCourse.getCourseCategory(), studentCourse.getCourseType());
                }
            }
            if (!coursesFound) {
                MessageUI.displayNoCourseFound(studentToSearch.getStudentName());
            }
        } else {
            MessageUI.displayStudentNotFoundError();
        }
    }

    //5. Add students to a few courses(main,elective,resit,repeat)
    public void addStudentToCourse() {
        systemUI.listAllStudent(getAllStudent()); // Display all students
        String studentIDToAdd = systemUI.inputStudentID(); // Get student ID to add
        Student studentToAdd = findStudentByID(studentIDToAdd); // Find student by ID
        if (studentToAdd == null) {
            MessageUI.displayStudentNotFoundError();
            return;
        }

        systemUI.listAllCourse(getAllCourse()); // Display all course
        String courseCodeToAdd = systemUI.inputCourseCode(); // Get course code
        Course courseToAdd = findCourseByCode(courseCodeToAdd); // Find course by code
        if (courseToAdd == null) {
            MessageUI.displayCourseNotFoundError();
            return;
        }

        String courseCategory = systemUI.inputCourseCategory(); // Input course category (main, elective, resit, repeat)
        if (courseCategory.equals("resit")) { // resit situation
            if (!isValidCourseCategory(courseCategory)) {
                MessageUI.displayCourseNotFoundError();
                return;
            }

            // Check if the student is already enrolled in a course with the same category and type
            if (isStudentEnrolled(studentToAdd, courseToAdd, courseCategory, null)) {
                MessageUI.displayDuplicateError();
                return;
            }

            StudentCourse newStudentCourse = new StudentCourse(studentToAdd, courseToAdd, courseCategory, null);
            newStudentCourse.getStudent().getStudentID();
            newStudentCourse.getCourse().getCourseCode();
            stuCourseList.add(newStudentCourse);
        } else { // other situation
            if (!isValidCourseCategory(courseCategory)) {
                MessageUI.displayCourseNotFoundError();
                return;
            }
            String courseType = systemUI.inputCourseType(); // Input course type (tutorial, practical, lecture)
            if (!isValidCourseType(courseType)) {
                MessageUI.displayCourseNotFoundError();
                return;
            }

            // Check if the student is already enrolled in a course with the same category and type
            if (isStudentEnrolled(studentToAdd, courseToAdd, courseCategory, courseType)) {
                MessageUI.displayDuplicateError();
                return;
            }

            StudentCourse newStudentCourse = new StudentCourse(studentToAdd, courseToAdd, courseCategory, courseType);
            newStudentCourse.getStudent().getStudentID();
            newStudentCourse.getCourse().getCourseCode();
            stuCourseList.add(newStudentCourse);
        }

        MessageUI.displayAddToCourseSuccessMessage(); // Assuming this method exists to display a generic success message
    }

    // Method to check if a student is already enrolled in a course with the same category and type
    private boolean isStudentEnrolled(Student student, Course course, String courseCategory, String courseType) {
        for (int i = 1; i <= stuCourseList.getNumberOfEntries(); i++) {
            StudentCourse studentCourse = stuCourseList.getEntry(i);
            if (studentCourse.getStudent().equals(student)
                    && studentCourse.getCourse().equals(course)
                    && studentCourse.getCourseCategory().equals(courseCategory)
                    && studentCourse.getCourseType().equals(courseType)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidCourseCategory(String category) {
        return category.equals("main") || category.equals("elective") || category.equals("resit") || category.equals("repeat");
    }

    private boolean isValidCourseType(String type) {
        return type.equals("tutorial") || type.equals("practical") || type.equals("lecture");
    }

    private Course findCourseByCode(String courseCode) {
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course currentCourse = courseList.getEntry(i);
            if (currentCourse.getCourseCode().equals(courseCode)) {
                return currentCourse;
            }
        }
        return null; // Course not found
    }

    public String getAllCourse() {
        String outputStr = "";
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            outputStr += courseList.getEntry(i) + "\n";
        }
        return outputStr;
    }
    
    
    //6. Remove a Student from a Course (main,elective)registration
    public void removeStudentFromCourse() {
        systemUI.listAllStudent(getAllStudent()); // Display all students
        String studentIDToRemove = systemUI.inputStudentID(); // Get student ID for removal
        Student studentToRemove = findStudentByID(studentIDToRemove); // Find student by ID
        if (studentToRemove == null) {
            MessageUI.displayStudentNotFoundError();
            return;
        }

        // Show the registered courses
        boolean coursesFound = false;
        systemUI.displayRegisteredCourse();
        for (int i = 1; i <= stuCourseList.getNumberOfEntries(); i++) {
            StudentCourse studentCourse = stuCourseList.getEntry(i);
            if (studentCourse.getStudent().equals(studentToRemove)) {
                Course course = studentCourse.getCourse();
                coursesFound = true;
                systemUI.displayRegisteredCourse(course.getCourseCode(), course.getCourseName(),
                        studentCourse.getCourseCategory(), studentCourse.getCourseType());
            }
        }
        if (!coursesFound) {
            MessageUI.displayNoCourseFound(studentToRemove.getStudentName());
            return;
        }
        
        
        String courseCodeToRemove = systemUI.inputCourseCode(); // Get course code for removal
        String courseTypeToRemove = systemUI.inputCourseType(); // Get course type for removal
        String courseCategoryToRemove = systemUI.inputCourseCategory(); // Get course category for removal
        Course courseToRemove = findCourseByCode(courseCodeToRemove); // Find course by code
        if (courseToRemove == null) {
            MessageUI.displayCourseNotFoundError();
            return;
        }

        // Attempt to remove student from course
        boolean removed = removeStudentFromCourse(studentIDToRemove, courseCodeToRemove, courseTypeToRemove, courseCategoryToRemove);
        // If removal successful, display success message
        if (removed) {
            MessageUI.displayRemovalSuccessMessage();
        } else {
            // If removal unsuccessful, display failure message
            MessageUI.displayRemovalFailureMessage();
        }
    }

    // Method to remove a student from a course considering course type and category
    public boolean removeStudentFromCourse(String studentID, String courseCode, String courseType, String courseCategory) {
        // Find the student-course association to remove
        for (int i = 1; i <= stuCourseList.getNumberOfEntries(); i++) {
            StudentCourse studentCourse = stuCourseList.getEntry(i);
            if (studentCourse.getStudent().getStudentID().equals(studentID)
                    && studentCourse.getCourse().getCourseCode().equals(courseCode)
                    && studentCourse.getCourseType().equals(courseType)
                    && studentCourse.getCourseCategory().equals(courseCategory)) {
                return stuCourseList.remove(studentCourse);
            }
        }
        return false; // Return false if no such association is found
    }
    

    //7. Calculate fee paid for registered courses
    public void calculateFeeForRegisteredCourses() {
        systemUI.listAllStudent(getAllStudent()); // Display all students
        String studentIDToCalculate = systemUI.inputStudentID(); // Get student ID for fee calculation
        Student studentToCalculate = findStudentByID(studentIDToCalculate); // Find student by ID
        if (studentToCalculate == null) {
            MessageUI.displayStudentNotFoundError();
            return;
        }
        
        
        // Show the registered courses
        systemUI.displayRegisteredCourse();
        boolean coursesFound = false;
        for (int i = 1; i <= stuCourseList.getNumberOfEntries(); i++) {
            StudentCourse studentCourse = stuCourseList.getEntry(i);
            if (studentCourse.getStudent().equals(studentToCalculate)) {
                Course course = studentCourse.getCourse();
                coursesFound = true;
                systemUI.displayRegisteredCourse(course.getCourseCode(), course.getCourseName(),
                        studentCourse.getCourseCategory(), studentCourse.getCourseType());
            }
        }
        if (!coursesFound) {
            MessageUI.displayNoCourseFound(studentToCalculate.getStudentName());
            return;
        }

        double totalFee = 0.0; // Initialize total fee

        // Iterate through the student's registered courses
        for (int i = 1; i <= stuCourseList.getNumberOfEntries(); i++) {
            StudentCourse studentCourse = stuCourseList.getEntry(i);

            // Check if the current course is registered by the student
            if (studentCourse.getStudent().equals(studentToCalculate)) {
                String courseCategory = studentCourse.getCourseCategory();
                if (courseCategory.equals("resit")) {
                    // Calculate fee based on course type
                    double courseFee = 100.0;
                    totalFee += courseFee; // Add course fee to total fee
                } else {
                    String courseType = studentCourse.getCourseType();

                    // Calculate fee based on course type
                    double courseFee = 0.0;
                    if (courseType.equals("tutorial") || courseType.equals("practical")) {
                        courseFee = 200.0;
                    } else if (courseType.equals("lecture")) {
                        courseFee = 300.0;
                    }
                    totalFee += courseFee; // Add course fee to total fee
                }
            }
        }
        
        //Display the fees description
        systemUI.displayFeedDescription();
        // Display the total fee paid by the student
        systemUI.displayTotalFeeMessage(totalFee);
    }

    //8. Filters students for courses based on criteria
    public void filterStudentsForCourses() {
        systemUI.listAllCourse(getAllCourse()); // Display all course
        // Get course code from user input
        String courseCode = systemUI.inputCourseCode(); // Assuming inputCourseCode() returns a String
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            MessageUI.displayCourseNotFoundError();
            return;
        }

        // Get course type from user input
        String courseType = systemUI.inputCourseType(); // Assuming inputCourseType() returns a String
        if (!isValidCourseType(courseType)) {
            MessageUI.displayInvalidChoiceMessage();
            return;
        }

        // Filter students based on criteria
        boolean studentsFound = false;
        for (int i = 1; i <= stuCourseList.getNumberOfEntries(); i++) {
            StudentCourse studentCourse = stuCourseList.getEntry(i);
            if (studentCourse.getCourse().equals(course)
                    && studentCourse.getCourseType().equals(courseType)) {
                Student student = studentCourse.getStudent();
                System.out.println(student.getStudentName() + " - " + student.getStudentID());
                studentsFound = true;
            }
        }

        // Display message if no students are found
        if (!studentsFound) {
            MessageUI.displayStudentNotFoundError();
        }
    }

    //Summary report (Show the total fees spent by course)
    public void generateTotalFeesReport() {
        systemUI.reportTitle2();

        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            Course course = courseList.getEntry(i);
            int totalFees = calculateTotalFeesForCourse(course);
            systemUI.displayFees(course.getCourseCode(), course.getCourseName(), totalFees);
        }
    }

    private int calculateTotalFeesForCourse(Course course) {
        int totalFees = 0;
        for (int i = 1; i <= stuCourseList.getNumberOfEntries(); i++) {
            StudentCourse studentCourse = stuCourseList.getEntry(i);
            if (studentCourse.getCourse().equals(course)) {
                String courseCategory = studentCourse.getCourseCategory();
                if (courseCategory.equals("resit")) {
                    // Add course fee to total fees
                    int fee = 100;
                    totalFees += fee;
                } else {
                    // Add course fee to total fees
                    totalFees += getCourseFee(studentCourse.getCourseType());
                }
            }
        }
        return totalFees;
    }

    private int getCourseFee(String courseType) {
        // Assign fee based on course type
        if (courseType.equals("tutorial") || courseType.equals("practical")) {
            return 200; // Tutorial and practical fees
        } else if (courseType.equals("lecture")) {
            return 300; // Lecture fee
        } else {
            return 0; // Default fee
        }
    }

    //Summary report (Generate Student Enrollment by Course Category Report)
    public void generateStudentEnrollmentByCourseCategoryReport() {
        systemUI.reportTitle1();

        // Initialize counters for each course category
        int mainCount = 0;
        int electiveCount = 0;
        int resitCount = 0;
        int repeatCount = 0;

        // Iterate through the student-course associations
        for (int i = 1; i <= stuCourseList.getNumberOfEntries(); i++) {
            StudentCourse studentCourse = stuCourseList.getEntry(i);
            String courseCategory = studentCourse.getCourseCategory();

            // Increment the counter based on the course category
            switch (courseCategory) {
                case "main":
                    mainCount++;
                    break;
                case "elective":
                    electiveCount++;
                    break;
                case "resit":
                    resitCount++;
                    break;
                case "repeat":
                    repeatCount++;
                    break;
            }
        }
        systemUI.displayReport1(mainCount, electiveCount, resitCount, repeatCount);
    }

}
