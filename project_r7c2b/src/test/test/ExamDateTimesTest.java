package test;

import model.Exam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



import model.ExamDateTime;

public class ExamDateTimesTest {
    ExamDateTime edt1;
    ExamDateTime edt2;
    Exam e1;

    @BeforeEach
    void setup() {
        edt1 = new ExamDateTime("6 : 00 pm");
        edt2 = new ExamDateTime("4 : 00 pm");
        e1 = new Exam("MATH 221");
    }

    @Test
    void testAssignExam() {
        edt1.assignExam(e1);
        assertEquals(e1, edt1.getAssignedExam());
    }

    @Test
    void testGetDate() {
        assertEquals("6 : 00 pm", edt1.getDate());
    }
    @Test
    void testIsAssignedExam() {
        edt1.assignExam(e1);
        assertTrue(edt1.isAssignedExam());
        assertFalse(edt2.isAssignedExam());
    }
}
