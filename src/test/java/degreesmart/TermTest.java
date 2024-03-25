package degreesmart;

import static org.junit.Assert.*;

import org.junit.Test;

import degreesmart.Semester;
import degreesmart.Term;

public class TermTest {

    @Test
    public void testGetSemester() {
        Term term = new Term(Semester.FALL, 2023);
        assertEquals(Semester.FALL, term.getSemester());
    }

    @Test
    public void testGetYear() {
        Term term = new Term(Semester.SPRING, 2022);
        assertEquals(2022, term.getYear());
    }

    @Test
    public void testSetSemester() {
        Term term = new Term(Semester.SUMMER, 2024);
        term.setSemester(Semester.FALL);
        assertEquals(Semester.FALL, term.getSemester());
    }


    @Test
    public void testSetYearInvalid() {
        Term term = new Term(Semester.SPRING, 2025);
        term.setYear(-1);
        assertEquals(0, term.getYear());
    }

    @Test
    public void testEqualsSameObject() {
        Term term = new Term(Semester.SUMMER, 2022);
        assertTrue(term.equals(term));
    }

    @Test
    public void testEqualsDifferentClass() {
        Term term = new Term(Semester.FALL, 2023);
        assertFalse(term.equals("not a Term object"));
    }

    @Test
    public void testEqualsEqualTerms() {
        Term term1 = new Term(Semester.SPRING, 2022);
        Term term2 = new Term(Semester.SPRING, 2022);
        assertTrue(term1.equals(term2));
    }

    @Test
    public void testEqualsUnequalTerms() {
        Term term1 = new Term(Semester.FALL, 2023);
        Term term2 = new Term(Semester.SPRING, 2023);
        assertFalse(term1.equals(term2));
    }

    @Test
    public void testCompareToEquals() {
        Term term1 = new Term(Semester.FALL, 2024);
        Term term2 = new Term(Semester.FALL, 2024);
        assertEquals(0, term1.compareTo(term2));
    }

    @Test
    public void testToString() {
        Term term = new Term(Semester.SUMMER, 2022);
        assertEquals("SUMMER 2022", term.toString());
    }
}