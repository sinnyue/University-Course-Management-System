package entity;


public class Tutor {

    private Tutor tutor;
    private TutorialGroup tutorialGroup;
    private Course course;
    private String tutorID;
    private String name;
    private String tutorRole;
    private int tutorExperience;

    public Tutor(Tutor tutor, Course course, String tutorID, String name, String tutorRole, int tutorExperience) {
        this.tutor = tutor;
        this.course = course;
        this.tutorID = tutorID;
        this.name = name;
        this.tutorRole = tutorRole;
        this.tutorExperience = tutorExperience;
    }

    public Tutor(String tutorID, String name, String tutorRole, int tutorExperience) {
        this.tutorID = tutorID;
        this.name = name;
        this.tutorRole = tutorRole;
        this.tutorExperience = tutorExperience;
    }

    public Tutor(Course course, String tutorID, String name, String tutorRole, int tutorExperience) {
        this.course = course;
        this.tutorID = tutorID;
        this.name = name;
        this.tutorRole = tutorRole;
        this.tutorExperience = tutorExperience;
    }

    public Tutor(TutorialGroup tutorialGroup, String tutorID, String name, String tutorRole, int tutorExperience) {
        this.tutorialGroup = tutorialGroup;
        this.tutorID = tutorID;
        this.name = name;
        this.tutorRole = tutorRole;
        this.tutorExperience = tutorExperience;
    }

    public Tutor(TutorialGroup tutorialGroup, Course course, String tutorID, String name, String tutorRole, int tutorExperience) {
        this.course = course;
        this.tutorialGroup = tutorialGroup;
        this.tutorID = tutorID;
        this.name = name;
        this.tutorRole = tutorRole;
        this.tutorExperience = tutorExperience;
    }

    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTutorRole() {
        return tutorRole;
    }

    public void setTutorRole(String tutorRole) {
        this.tutorRole = tutorRole;
    }

    public int getTutorExperience() {
        return tutorExperience;
    }

    public void setTutorExperience(int tutorExperience) {
        this.tutorExperience = tutorExperience;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public void setTutorialGroup(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public void assignCourse(Course courseToAdd) {
        this.course = courseToAdd;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-40s %-40s %-5s", tutorID, name, tutorRole, tutorExperience);
    }

}