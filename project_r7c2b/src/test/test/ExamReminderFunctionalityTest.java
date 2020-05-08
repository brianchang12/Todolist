package test;

import model.Exam;
import model.ExamReminderFunctionality;
import model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExamReminderFunctionalityTest {
    Exam e1;
    Exam e2;
    Location l1;
    Location l2;
    Location l3;
    Location l4;
    Location l5;
    ArrayList<Exam> loe;
    ExamReminderFunctionality erf;
    Map<Exam, ArrayList<Location>> examLocations;

    @BeforeEach
    public void setup() {
        erf = new ExamReminderFunctionality();
        loe = new ArrayList<>();
        e1 = new Exam("MATH 221");
        l1 = new Location("WESB 100");
        l2 = new Location("WOOD 2");
        e2 = new Exam("CPSC 210");
        l3 = new Location("HENN 221");
        l4 = new Location("SRC A");
        l5 = new Location("SRC B");
        examLocations = new HashMap<>();
    }

    @Test
    public void testAddFirstExam() {
        erf.addFirstExam();
        loe.add(e1);
        assertEquals(loe ,erf.returnExams());

    }

    @Test
    public void testRemoveExam() {
        erf.addFirstExam();
        erf.addSecondExam();
        erf.removeExam(e1);
        loe.add(e2);
        assertEquals(erf.returnExams(),loe);

    }

    @Test
    public void testAddSecondExam() {
        erf.addSecondExam();
        loe.add(e2);
        assertEquals(loe ,erf.returnExams());

    }

    @Test
    public void testFormNameList() {
        erf.addFirstExam();
        erf.addSecondExam();
        ArrayList<String> dummylist = new ArrayList<>();
        dummylist.add("MATH 221");
        dummylist.add("CPSC 210");
        assertEquals(dummylist, erf.formNameList(erf.returnExams()));

    }

    @Test
    public void testAddExamMapAndLocationMap() {
        erf.addExamMap(e1);
        erf.addLocationMap(e1, l1);
        ArrayList<String> dummylist = new ArrayList<>();
        dummylist.add("WESB 100");
        assertEquals(dummylist, erf.examStatementLocations("MATH 221"));
    }

    @Test
    public void testGetLoc() {
        assertEquals("WESB 100", l1.getLocName());
    }

    @Test
    public void testExamByName() {
        erf.returnExams().add(e1);
        assertEquals(e1,erf.getExamByName("MATH 221"));
        assertEquals(null, erf.getExamByName("CPSC 210"));
    }


}
