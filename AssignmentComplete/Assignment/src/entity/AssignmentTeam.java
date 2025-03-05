package entity;


import java.io.Serializable;
import adt.*;

public class AssignmentTeam implements Serializable{
    private String teamName;
    private ListInterface<Student> students;    //Using an array instead of List
            
    public AssignmentTeam(String teamName) {
        this.teamName = teamName;
        this.students = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ListInterface<Student> getStudents() {
        return students;
    }

    public void setStudents(ListInterface<Student> students) {
        this.students = students;
    }
  

    @Override
    public String toString() {
        return "assignementTeam{" + "teamName=" + teamName + '}';
    }
 
}
