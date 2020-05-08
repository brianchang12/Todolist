package model;

import java.util.ArrayList;

public class ExamDateTime {
    //Taken from Course
    String date;
    Exam exm;

    public ExamDateTime(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Exam getAssignedExam() {
        return exm;
    }


    public void assignExam(Exam exm) {
        this.exm = exm;
    }


    //EFFECTS: Determines if a ExamDateTime has been assigned an exm,
    //returns true if exm has been assigned, false if not
    public Boolean isAssignedExam() {
        if (exm != null) {
            return true;
        } else {
            return false;
        }
    }
}

