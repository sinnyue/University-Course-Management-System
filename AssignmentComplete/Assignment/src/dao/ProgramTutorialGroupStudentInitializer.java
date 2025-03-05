/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.ArrayList;
import adt.ListInterface;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;

/**
 *
 * @author shuej
 */
public class ProgramTutorialGroupStudentInitializer {

    private ListInterface<Programme> programList;

    public ProgramTutorialGroupStudentInitializer() {

        this.programList = new ArrayList<>();
    }

    public ListInterface<Programme> initializer() {

        ListInterface<Student> studentList1 = new ArrayList<>();
        studentList1.add(new Student("22WMA001", "Ali"));
        studentList1.add(new Student("22WMA002", "Chen"));
        studentList1.add(new Student("22WMA003", "Goh"));
        studentList1.add(new Student("22WMA004", "Surekha"));
        studentList1.add(new Student("22WMA005", "Tan"));

        ListInterface<Student> studentList2 = new ArrayList<>();
        studentList2.add(new Student("22WMA006", "Abu"));
        studentList2.add(new Student("22WMA007", "Athirah"));
        studentList2.add(new Student("22WMA008", "Coco"));
        studentList2.add(new Student("22WMA009", "Ethen"));

        ListInterface<Student> studentList3 = new ArrayList<>();
        studentList3.add(new Student("21WMA001", "Azura"));
        studentList3.add(new Student("21WMA002", "Boh"));
        studentList3.add(new Student("21WMA003", "Celine"));
        studentList3.add(new Student("21WMA004", "Evon"));
        studentList3.add(new Student("21WMA005", "Jenifer"));

        ListInterface<Student> studentList4 = new ArrayList<>();
        studentList4.add(new Student("21WMA006", "Kelvin"));
        studentList4.add(new Student("21WMA007", "Lim"));
        studentList4.add(new Student("21WMA008", "Man Yu"));
        studentList4.add(new Student("21WMA009", "Ying Sha"));

        ListInterface<Student> studentList5 = new ArrayList<>();
        studentList5.add(new Student("20WMA001", "Bey"));
        studentList5.add(new Student("20WMA002", "Calvin"));
        studentList5.add(new Student("20WMA003", "Elvin"));
        studentList5.add(new Student("20WMA004", "Feng Wei"));
        studentList5.add(new Student("20WMA005", "Yi Di"));

        ListInterface<Student> studentList6 = new ArrayList<>();
        studentList6.add(new Student("22WMB001", "Bu Ting"));
        studentList6.add(new Student("22WMB002", "Chong"));
        studentList6.add(new Student("22WMB003", "Mei Fong"));
        studentList6.add(new Student("22WMB004", "Nana"));
        studentList6.add(new Student("22WMB005", "Wong"));

        ListInterface<Student> studentList7 = new ArrayList<>();
        studentList7.add(new Student("22WMB006", "Ah Ting"));
        studentList7.add(new Student("22WMB007", "Bee"));
        studentList7.add(new Student("22WMB008", "Ma Ling"));
        studentList7.add(new Student("22WMB009", "Wei Yi"));

        ListInterface<Student> studentList8 = new ArrayList<>();
        studentList8.add(new Student("21WMB001", "Bu Ting"));
        studentList8.add(new Student("21WMB002", "Chong"));
        studentList8.add(new Student("21WMB003", "Mei Fong"));
        studentList8.add(new Student("21WMB004", "Nana"));
        studentList8.add(new Student("21WMB005", "Wong"));

        ListInterface<Student> studentList9 = new ArrayList<>();
        studentList9.add(new Student("21WMB006", "Ah Xin"));
        studentList9.add(new Student("21WMB007", "Bong"));
        studentList9.add(new Student("21WMB008", "Chan"));
        studentList9.add(new Student("21WMB009", "Ke Song"));

        ListInterface<Student> studentList10 = new ArrayList<>();
        studentList10.add(new Student("20WMB001", "Ah Wen"));
        studentList10.add(new Student("20WMB002", "Ding Ding"));
        studentList10.add(new Student("20WMB003", "Wan Jian"));
        studentList10.add(new Student("20WMB004", "Zhe Hui"));

        ListInterface<Student> studentList11 = new ArrayList<>();
        studentList11.add(new Student("22WME001", "Chen Wen"));
        studentList11.add(new Student("22WME002", "Deng Ke"));
        studentList11.add(new Student("22WME003", "Feng Wei"));
        studentList11.add(new Student("22WME004", "Goh Joey"));
        studentList11.add(new Student("22WME005", "Zhi Hui"));

        ListInterface<Student> studentList12 = new ArrayList<>();
        studentList12.add(new Student("22WME006", "Ah Sin"));
        studentList12.add(new Student("22WME007", "Beh Wen"));
        studentList12.add(new Student("22WME008", "Wan Yi"));
        studentList12.add(new Student("22WME009", "Wan Xin"));

        ListInterface<Student> studentList13 = new ArrayList<>();
        studentList13.add(new Student("21WMR100", "Ke Xin"));
        studentList13.add(new Student("21WMR101", "Lim Lei"));
        studentList13.add(new Student("21WMR102", "Men Feng"));
        studentList13.add(new Student("21WMR103", "Neng Li"));
        studentList13.add(new Student("21WMR104", "Ong Lin"));

        ListInterface<Student> studentList14 = new ArrayList<>();
        studentList14.add(new Student("21WMR105", "Ah Lin"));
        studentList14.add(new Student("21WMR106", "Alvin"));
        studentList14.add(new Student("21WMR107", "Wen Yi"));
        studentList14.add(new Student("21WMR108", "Yi Qing"));

        ListInterface<Student> studentList15 = new ArrayList<>();
        studentList15.add(new Student("20WME001", "Ah Beng"));
        studentList15.add(new Student("20WME002", "Ding Wen"));
        studentList15.add(new Student("20WME003", "Wan Ru"));
        studentList15.add(new Student("20WME004", "Zhi Neng"));

        ListInterface<Student> studentList16 = new ArrayList<>();
        studentList16.add(new Student("22WMM001", "Chen Fong"));
        studentList16.add(new Student("22WMM002", "Di Leng"));
        studentList16.add(new Student("22WMM003", "Feng Fang"));
        studentList16.add(new Student("22WMM004", "Joy"));
        studentList16.add(new Student("22WMM005", "Srilelha"));

        ListInterface<Student> studentList17 = new ArrayList<>();
        studentList17.add(new Student("22WMM006", "Ah Pong"));
        studentList17.add(new Student("22WMM007", "Even"));
        studentList17.add(new Student("22WMM008", "Gowri"));
        studentList17.add(new Student("22WMM009", "Wan Sin"));

        ListInterface<Student> studentList18 = new ArrayList<>();
        studentList18.add(new Student("21WMM001", "Ke Xin"));
        studentList18.add(new Student("21WMM002", "Lim Lei"));
        studentList18.add(new Student("21WMM003", "Men Feng"));
        studentList18.add(new Student("21WMM004", "Neng Li"));
        studentList18.add(new Student("21WMM005", "Ong Lin"));

        ListInterface<Student> studentList19 = new ArrayList<>();
        studentList19.add(new Student("21WMM006", "Ah Lin"));
        studentList19.add(new Student("21WMM007", "Alvin"));
        studentList19.add(new Student("21WMM008", "Wen Yi"));
        studentList19.add(new Student("21WMM009", "Yi Qing"));

        ListInterface<Student> studentList20 = new ArrayList<>();
        studentList20.add(new Student("20WMM001", "Ah Beng"));
        studentList20.add(new Student("20WMM002", "Ding Wen"));
        studentList20.add(new Student("20WMM003", "Wan Ru"));
        studentList20.add(new Student("20WMM004", "Zhi Neng"));

        ListInterface<Student> studentList21 = new ArrayList<>();
        studentList21.add(new Student("22WMS001", "Chen Wen"));
        studentList21.add(new Student("22WMS002", "Dong Feng"));
        studentList21.add(new Student("22WMS003", "Fei Xxin"));
        studentList21.add(new Student("22WMS004", "Xiang Peng"));
        studentList21.add(new Student("22WMS005", "Zhen Li"));

        ListInterface<Student> studentList22 = new ArrayList<>();
        studentList22.add(new Student("22WMS006", "Ke Yi"));
        studentList22.add(new Student("22WMS007", "Long"));
        studentList22.add(new Student("22WMS008", "Men Zhi"));
        studentList22.add(new Student("22WMS009", "Nang Yu"));

        ListInterface<Student> studentList23 = new ArrayList<>();
        studentList23.add(new Student("21WMS001", "Dun Lin"));
        studentList23.add(new Student("21WMS002", "Poh Lin"));
        studentList23.add(new Student("21WMS003", "Qiu Wen"));
        studentList23.add(new Student("21WMS004", "Ivan"));
        studentList23.add(new Student("21WMS005", "Ong Hui"));

        ListInterface<Student> studentList24 = new ArrayList<>();
        studentList24.add(new Student("21WMS006", "Ah Leng"));
        studentList24.add(new Student("21WMS007", "Chu Mei"));
        studentList24.add(new Student("21WMS008", "Wen Wen"));
        studentList24.add(new Student("21WMS009", "Yi Wen"));

        ListInterface<Student> studentList25 = new ArrayList<>();
        studentList25.add(new Student("20WMS001", "Danesh"));
        studentList25.add(new Student("20WMS002", "Ewen"));
        studentList25.add(new Student("20WMS003", "Wan Ting"));
        studentList25.add(new Student("20WMS004", "Xin xin"));

        // first programme 
        ListInterface<TutorialGroup> oriGroupList1 = new ArrayList<>();
        oriGroupList1.add(new TutorialGroup("G1", "Y1", "S1", studentList1));
        oriGroupList1.add(new TutorialGroup("G2", "Y1", "S1", studentList2));
        oriGroupList1.add(new TutorialGroup("G1", "Y2", "S1", studentList3));
        oriGroupList1.add(new TutorialGroup("G2", "Y2", "S1", studentList4));
        oriGroupList1.add(new TutorialGroup("G1", "Y3", "S1", studentList5));

        // second programme 
        ListInterface<TutorialGroup> oriGroupList2 = new ArrayList<>();
        oriGroupList2.add(new TutorialGroup("G1", "Y1", "S1", studentList6));
        oriGroupList2.add(new TutorialGroup("G2", "Y1", "S1", studentList7));
        oriGroupList2.add(new TutorialGroup("G1", "Y2", "S1", studentList8));
        oriGroupList2.add(new TutorialGroup("G2", "Y2", "S1", studentList9));
        oriGroupList2.add(new TutorialGroup("G1", "Y3", "S1", studentList10));

        ListInterface<TutorialGroup> oriGroupList3 = new ArrayList<>();
        oriGroupList3.add(new TutorialGroup("G1", "Y1", "S1", studentList11));
        oriGroupList3.add(new TutorialGroup("G2", "Y1", "S1", studentList12));
        oriGroupList3.add(new TutorialGroup("G1", "Y2", "S1", studentList13));
        oriGroupList3.add(new TutorialGroup("G2", "Y2", "S1", studentList14));
        oriGroupList3.add(new TutorialGroup("G1", "Y3", "S1", studentList15));

        ListInterface<TutorialGroup> oriGroupList4 = new ArrayList<>();
        oriGroupList4.add(new TutorialGroup("G1", "Y1", "S1", studentList16));
        oriGroupList4.add(new TutorialGroup("G2", "Y1", "S1", studentList17));
        oriGroupList4.add(new TutorialGroup("G1", "Y2", "S1", studentList18));
        oriGroupList4.add(new TutorialGroup("G2", "Y2", "S1", studentList19));
        oriGroupList4.add(new TutorialGroup("G1", "Y3", "S1", studentList20));

        ListInterface<TutorialGroup> oriGroupList5 = new ArrayList<>();
        oriGroupList5.add(new TutorialGroup("G1", "Y1", "S1", studentList21));
        oriGroupList5.add(new TutorialGroup("G2", "Y1", "S1", studentList22));
        oriGroupList5.add(new TutorialGroup("G1", "Y2", "S1", studentList23));
        oriGroupList5.add(new TutorialGroup("G2", "Y2", "S1", studentList24));
        oriGroupList5.add(new TutorialGroup("G1", "Y3", "S1", studentList25));

        ListInterface<Programme> programList = new ArrayList<>();
        programList.add(new Programme("BACC", "Bachelor of Accounting", oriGroupList1));
        programList.add(new Programme("BBA", "Bachelor of Business Administrator", oriGroupList2));
        programList.add(new Programme("BEE", "Bachelor of Electronic Engineering", oriGroupList3));
        programList.add(new Programme("BM", "Bachelor of Marketing", oriGroupList4));
        programList.add(new Programme("BSE", "Bachelor of Software Engineering", oriGroupList5));

        return programList;

    }

    // Getter method for program list
    public ListInterface<Programme> getProgramList() {
        return programList;
    }

}
