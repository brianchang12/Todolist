package test;


import model.Exam;
import model.ExamDateTime;
import model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class ExamTest {
    Exam e1;
    Exam e2;
    ExamDateTime edt1;
    ExamDateTime edt2;
    ExamDateTime edt3;
    ExamDateTime edt4;

    @BeforeEach
    public void setup() {
        e1 = new Exam("MATH 221");
        e2 = new Exam("CPSC 210");
        edt1 = new ExamDateTime("6 : 00 pm");
        edt2 = new ExamDateTime("4 : 00 pm");
        edt3 = new ExamDateTime("1 : 00 pm");
        edt4 = new ExamDateTime("8 : 00 pm");
        e1.addDateTime(edt1);
        e1.addDateTime(edt2);
        e2.addDateTime(edt3);
        e2.addDateTime(edt4);
    }

    @Test
    public void testAddDateTime() {
        e1.addDateTime(edt3);
        ArrayList<ExamDateTime> edummy = new ArrayList<>();
        edummy.add(edt1);
        edummy.add(edt2);
        edummy.add(edt3);
        assertEquals(edummy, e1.getDateTimes());
        edummy = new ArrayList<>();
        edummy.add(edt4);
        assertEquals(edummy, e2.getDateTimes());
        ExamDateTime edt5 = new ExamDateTime("3 : 00 am");
        e2.addDateTime(edt5);
        edummy.add(edt5);
        assertEquals(edummy, e2.getDateTimes());

    }

    @Test
    public void testRemoveDateTime() {
        e1.removeExamDateTime(edt2);
        ArrayList<ExamDateTime> edummy = new ArrayList<>();
        edummy.add(edt1);
        assertEquals(edummy, e1.getDateTimes());


    }
    @Test
    public void testGetExamName() {
        assertEquals("MATH 221", e1.getExamName());
    }

    @Test
    public void testListNameDate() {
        ArrayList<String> alst = new ArrayList<>();
        alst.add("6 : 00 pm");
        alst.add("4 : 00 pm");
        assertEquals(alst,e1.getListNameDate());
    }

    @Test
    public void testEquals() {
        assertFalse(e1.equals(null));
    }
}
