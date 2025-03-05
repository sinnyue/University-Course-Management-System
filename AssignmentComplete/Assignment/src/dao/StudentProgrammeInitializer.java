/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Programme;
import entity.Student;

/**
 *
 * @author shuej
 */
public class StudentProgrammeInitializer {
     private ListInterface<Student> studentList;
        
        
    public static ListInterface<Student> initializeRegistered() { 
        
        Programme program1 = new Programme("BACC","Bachelor of Accounting");
        Programme program2 = new Programme("BM","Bachelor of Marketing");
        Programme program3 = new Programme("BBA","Bachelor of Business Administrator");
        Programme program4 = new Programme("BEE","Bachelor of Electronic Engineering");
        Programme program5 = new Programme("BSE","Bachelor of Software Engineering");
        
        
        ListInterface<Student> oriStudentWithRegistredProgramme =new ArrayList<>();
        oriStudentWithRegistredProgramme.add(new Student("22WMA110","Li Wen ",program1));
        oriStudentWithRegistredProgramme.add(new Student("22WMA111","Wan Yi ",program1));
        oriStudentWithRegistredProgramme.add(new Student("22WMM110","Kai Wen",program2));
        oriStudentWithRegistredProgramme.add(new Student("22WMB110","Wan Wen",program3));
        oriStudentWithRegistredProgramme.add(new Student("22WME110","Celine",program4));
        oriStudentWithRegistredProgramme.add(new Student("22WMS110","Kai Xin",program5));
        
        return oriStudentWithRegistredProgramme;
    }
    
    public ListInterface<Student> getStudentList() {
        return studentList;
    }
  
}

