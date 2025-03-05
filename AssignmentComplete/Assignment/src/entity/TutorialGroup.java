/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import adt.ListInterface;
import java.util.Objects;

/**
 *
 * @author shuej
 */
public class TutorialGroup implements Comparable<TutorialGroup> {

    private Tutor tutor;
    private Course course;
    private String groupNo;
    private String year;
    private String semester;
    private static int groupSize = 5; // Shared among all instances
    private ListInterface<Student> students;
    private ListInterface<AssignmentTeam> assignmentTeams;

    public TutorialGroup() {

    }

    public TutorialGroup(String groupNo, String year, String semester) {
        this.groupNo = groupNo;
        this.year = year;
        this.semester = semester;
        this.students = new ArrayList<>();
    }

    public TutorialGroup(String groupNo, String year, String semester, ListInterface<Student> students) {
        this.groupNo = groupNo;
        this.year = year;
        this.semester = semester;
        this.students = students;
        this.assignmentTeams = new ArrayList<>();
    }

    public TutorialGroup(ListInterface<Student> students, String groupNo) {
        this.groupNo = groupNo;
        this.students = new ArrayList<>();
        this.assignmentTeams = new ArrayList<>();
    }

    public TutorialGroup(Course course, String groupNo, String semester, String year) {
        this.course = course;
        this.groupNo = groupNo;
        this.semester = semester;
        this.year = year;
    }

    public TutorialGroup(Tutor tutor, Course course, String groupNo, String year, String semester, ListInterface<Student> students) {
        this.tutor = tutor;
        this.course = course;
        this.groupNo = groupNo;
        this.year = year;
        this.semester = semester;
        this.students = students;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public static int getGroupSize() {
        return groupSize;
    }

    public static void setGroupSize(int groupSize) {
        TutorialGroup.groupSize = groupSize;
    }

    public ListInterface<Student> getStudents() {
        return students;
    }

    public void setStudents(ListInterface<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "TutorialGroup{" + "groupNo=" + groupNo + ", year=" + year + ", semester=" + semester + ", students=" + students + '}';
    }

    public ListInterface<AssignmentTeam> getAssignmentTeams() {
        return assignmentTeams;
    }

    public void setAssignmentTeams(ListInterface<AssignmentTeam> assignmentTeams) {
        this.assignmentTeams = assignmentTeams;
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

    @Override
    public int compareTo(TutorialGroup other) {
        // First compare by year
        int yearComparison = this.year.compareTo(other.year);
        if (yearComparison != 0) {
            return yearComparison;
        }

        // If years are the same, compare by groupNo
        int groupNoComparison = this.groupNo.compareTo(other.groupNo);
        if (groupNoComparison != 0) {
            return groupNoComparison;
        }

        // If groupNos are the same, finally compare by semester
        return this.semester.compareTo(other.semester);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TutorialGroup that = (TutorialGroup) obj;
        return Objects.equals(groupNo, that.groupNo)
                && Objects.equals(year, that.year)
                && Objects.equals(semester, that.semester);
    }

}
