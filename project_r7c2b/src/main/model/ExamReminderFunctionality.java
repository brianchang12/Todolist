package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExamReminderFunctionality {
    //Taken from the Course

    Map<Exam, ArrayList<Location>> examLocations;
    Exam e1;
    Exam e2;
    Location l1;
    Location l2;
    Location l3;
    Location l4;
    Location l5;
    ArrayList<Exam> loe;
    ExamDateTime edt1;
    ExamDateTime edt2;
    ExamDateTime edt3;
    ExamDateTime edt4;


    public ExamReminderFunctionality() {
        loe = new ArrayList<>();
        e1 = new Exam("MATH 221");
        l1 = new Location("WESB 100");
        l2 = new Location("WOOD 2");
        e2 = new Exam("CPSC 210");
        l3 = new Location("HENN 221");
        l4 = new Location("SRC A");
        l5 = new Location("SRC B");
        edt1 = new ExamDateTime("6 : 00 pm");
        edt2 = new ExamDateTime("4 : 00 pm");
        edt3 = new ExamDateTime("1 : 00 pm");
        edt4 = new ExamDateTime("8 : 00 pm");
        examLocations = new HashMap<>();


    }

    //MODIFIES: this, e1
    //EFFECTS: Stores exam (e1) and locations (l1) into hashmap (examLocations) also adds edt1
    //on to e1
    // and adds an exam (e1) to list of exams (loe).
    public void addFirstExam() {
        loe.add(e1);
        e1.addDateTime(edt1);
        addExamMap(e1);
        addLocationMap(e1, l1);
        addLocationMap(e1, l2);
    }

    //MODIFIES: this, e2
    //EFFECTS: Stores exam (e2) and locations (l3,l4,l5) into hashmap (examLocations)
    // and adds an exam (e2) to list of exams (loe) and etd2 adds on to e2.
    public void addSecondExam() {
        loe.add(e2);
        e2.addDateTime(edt2);
        addExamMap(e2);
        addLocationMap(e2, l3);
        addLocationMap(e2, l4);
        addLocationMap(e2, l5);
    }

    //MODIFIES: this
    //EFFECTS: Removes exam from list of exams (loe) and removes an exam from hashmap
    //(examLocations)
    public void removeExam(Exam e) {
        loe.remove(e);
        examLocations.remove(e);
    }

    //EFFECTS: setups the hashmap (ExamLocations) puts exam into key entry

    public void addExamMap(Exam exam) {
        examLocations.put(exam, new ArrayList<>());
    }

    //EFFECTS: Stores name into hashmap (examLocations), stores name in value entry
    // in the key value of exam.
    public void addLocationMap(Exam exam, Location name) {
        ArrayList<Location> names = examLocations.get(exam);
        names.add(name);
    }
    //REQUIRES: list of exam (loe) must have one or more exams
    //EFFECTS: Extract the name of exams from list of exams (loe),
    //returns the listexamnames which is a list of strings of names

    public ArrayList<String> formNameList(ArrayList<Exam> loe) {
        ArrayList<String> listexamnames = new ArrayList<>();
        for (Exam exam : loe) {
            listexamnames.add(exam.getExamName());
        }
        return listexamnames;
    }

    public ArrayList<Exam> returnExams() {
        return loe;
    }


    //REQUIRES: the parameter (examnames) when formed into an exam is to be included in hashmap
    //examLocations.
    //EFFECTS: Extract a list of location pertaining to examnames,
    // return the list of location names from the parameter examnames, return (enames).
    public ArrayList<String> examStatementLocations(String examnames) {
        ArrayList<String> enames = new ArrayList<>();
        ArrayList<Location> loc = examLocations.get(new Exam(examnames));
        for (Location l : loc) {
            enames.add(l.getLocName());
        }
        return enames;
    }


    //REQUIRES: the parameter str to have the same name of an
    // element in loe.
    //EFFECTS: returns the exam in loe that has the same name as str

    public Exam getExamByName(String str) {
        for (Exam e : loe) {
            if (e.getExamName().equals(str)) {
                return e;
            }
        }
        return null;
    }

}
