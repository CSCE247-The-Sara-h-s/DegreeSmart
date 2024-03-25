package degreesmart;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import degreesmart.Semester;
import degreesmart.Term;

public class TermTest {
    private Term term;

    @Before
    public void setUp() {
        term = new Term(Semester.FALL, 2023);
    }

    @After
    public void tearDown() {
        term = null;
    }

    @Test
    public void testGetSemester() {
        assertEquals(Semester.FALL, term.getSemester());
    }

    @Test
    public void testGetYear() {
        assertEquals(2023, term.getYear());
    }

    @Test
    public void testSetSemester() {
        term.setSemester(Semester.SPRING);
        assertEquals(Semester.SPRING, term.getSemester());
    }

    @Test
    public void testSetYear() {
        term.setYear(2025);
        assertEquals(2025, term.getYear());
    }

    @Test
    public void testEqualsSameTerm() {
        Term sameTerm = new Term(Semester.FALL, 2023);
        assertTrue(term.equals(sameTerm));
    }

    @Test
    public void testEqualsDifferentTerm() {
        Term differentTerm = new Term(Semester.SPRING, 2023);
        assertFalse(term.equals(differentTerm));
    }

    @Test
    public void testCompareToLessThan() {
        Term laterTerm = new Term(Semester.SPRING, 2024);
        assertTrue(term.compareTo(laterTerm) < 0);
    }

    @Test
    public void testCompareToGreaterThan() {
        Term earlierTerm = new Term(Semester.SPRING, 2022);
        assertTrue(term.compareTo(earlierTerm) > 0);
    }

    @Test
    public void testCompareToEqualTo() {
        Term sameTerm = new Term(Semester.FALL, 2023);
        assertEquals(0, term.compareTo(sameTerm));
    }

    @Test
    public void testToString() {
        assertEquals("FALL 2023", term.toString());
    }
}
