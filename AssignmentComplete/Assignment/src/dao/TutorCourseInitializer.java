package dao;

import adt.*;
import entity.*;

public class TutorCourseInitializer {

    private ListInterface<Course> initializeCourse;
    private ListInterface<Tutor> initializeTutor;
    private ListInterface<TutorialGroup> initializeTutorialGroup;

    

    public ListInterface<Tutor> initializeTutor() {
        ListInterface<Tutor> tutorList = new ArrayList<>();
        tutorList.add(new Tutor("T1", "John Doe", "Lec", 5));
        tutorList.add(new Tutor("T2", "Jane Smith", "Tut", 6));
        tutorList.add(new Tutor("T3", "David Lee", "Prac", 3));
        tutorList.add(new Tutor("T4", "Emily Johnson", "Lec", 8));
        tutorList.add(new Tutor("T5", "Michael Brown", "Tut", 4));
        tutorList.add(new Tutor("T6", "Sarah Williams", "Prac", 7));
        tutorList.add(new Tutor("T7", "Chris Taylor", "Lec", 9));
        tutorList.add(new Tutor("T8", "Jessica Evans", "Tut", 5));
        tutorList.add(new Tutor("T9", "Andrew Wilson", "Prac", 6));
        tutorList.add(new Tutor("T10", "Olivia Martinez", "Lec", 7));
        return tutorList;
    }

   
}
