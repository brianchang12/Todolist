package model;

import java.util.ArrayList;
import java.util.Objects;


public class Exam {
    //Taken from Course

    ArrayList<ExamDateTime> datetimes;
    private String exam;

    public Exam(String exam) {
        this.exam = exam;
        datetimes = new ArrayList<>();

    }

    //MODIFIES: this, edt
    //EFFECTS: if edt has already been assigned, the edt removes its assigment
    //and is now assigned to a new exam (this). Adds edt to datetimes.
    //If it was never assigned to an exam, assign exam (this) adds edt to datetimes.

    public void addDateTime(ExamDateTime edt) {
        if (edt.isAssignedExam()) {
            (edt.getAssignedExam()).removeExamDateTime(edt);
            edt.assignExam(this);
            datetimes.add(edt);
        } else {
            edt.assignExam(this);
            datetimes.add(edt);
        }
    }
    //REQUIRES: one or more elements in datetimes
    //EFFECTS: returns strlist a list of date times which are strings

    public ArrayList<String> getListNameDate() {
        ArrayList<String> strlist = new ArrayList<>();
        for (ExamDateTime et : datetimes) {
            strlist.add(et.getDate());
        }
        return strlist;
    }

    public void removeExamDateTime(ExamDateTime edt) {
        datetimes.remove(edt);
    }


    public ArrayList<ExamDateTime> getDateTimes() {
        return datetimes;
    }

    public String getExamName() {
        return exam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exam exam1 = (Exam) o;
        return exam.equals(exam1.exam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exam);
    }
}

