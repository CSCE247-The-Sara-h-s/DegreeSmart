package degreesmart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TermTest {
    private Term term;
    private Term t;

    @BeforeEach
    public void setTestTerm() {
        term = new Term(Semester.FALL, 2023);
        t = new Term(Semester.SUMMER, 2024);
    }

    @AfterEach
    public void unsetTestTerm() {
        term = null;
        t = null;
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

    @Test
    public void testCreateTerm_ValidData_ShouldNotThrowException() {
        assertDoesNotThrow(() -> new Term(Semester.SPRING, 2024));
    }

    @Test
    public void testToString_DefaultData_ShouldNotThrowException() {
        assertDoesNotThrow(() -> t.toString());
    }

    @Test
    public void testCreateTerm_NullSemester_ShouldThrowException() {
        Exception e = assertThrows(IllegalArgumentException.class,
            () -> new Term(null, 2024));
        assertEquals("semester cannot be null", e.getMessage());
    }

    @Test
    public void testCreateTerm_ZeroYear_ShouldThrowException() {
        Exception e = assertThrows(IllegalArgumentException.class,
            () -> new Term(Semester.SPRING, 0));
        assertEquals("year cannot be less than one", e.getMessage());
    }

    @Test
    public void testCreateTerm_NegativeYear_ShouldThrowException() {
        Exception e = assertThrows(IllegalArgumentException.class,
            () -> new Term(Semester.SPRING, -1));
        assertEquals("year cannot be less than one", e.getMessage());
    }

    @Test
    public void testSetSemester_NullSemester_ShouldThrowException() {
        Exception e = assertThrows(IllegalArgumentException.class,
            () -> t.setSemester(null));
        assertEquals("semester cannot be null", e.getMessage());
    }

    @Test
    public void testSetYear_ZeroYear_ShouldThrowException() {
        Exception e = assertThrows(IllegalArgumentException.class,
            () -> t.setYear(0));
        assertEquals("year cannot be less than one", e.getMessage());
    }

    @Test
    public void testSetYear_NegativeYear_ShouldThrowException() {
        Exception e = assertThrows(IllegalArgumentException.class,
            () -> t.setYear(-1));
        assertEquals("year cannot be less than one", e.getMessage());
    }

    @Test
    public void testEquals_SameData_ShouldEqualTrue() {
        assertEquals(true, t.equals(new Term(t.getSemester(), t.getYear())));
    }

    @Test
    public void testEquals_Null_ShouldEqualFalse() {
        assertEquals(false, t.equals(null));
    }

    @Test
    public void testEquals_DifferentObject_ShouldEqualFalse() {
        assertEquals(false, t.equals(new Object()));   
    }

    @Test
    public void testEquals_DifferentSemeseter_ShouldEqualFalse() {
        Term t2 = new Term(Semester.FALL, t.getYear());
        assertEquals(false, t.equals(t2));

    }

    @Test
    public void testEquals_DifferentYear_ShouldEqualFalse() {
        Term t2 = new Term(t.getSemester(), 2000);
        assertEquals(false, t.equals(t2));
    }

    @Test
    public void testCompareTo_ParamHasSameData_ShouldEqualTrue() {
        Term t2 = new Term(t.getSemester(), t.getYear());
        assertEquals((Integer.valueOf(10)).compareTo(Integer.valueOf(10)), t.compareTo(t2));
    }

    @Test
    public void testCompareTo_ParamHasEarlierYear_ShouldEquaTrue() {
        Term t2 = new Term(t.getSemester(), 2000);
        assertEquals((Integer.valueOf(10)).compareTo(Integer.valueOf(2)), t.compareTo(t2));
    }

    @Test
    public void testCompareTo_ParamHasLaterYear_ShouldEqualTrue() {
        Term t2 = new Term(t.getSemester(), 3000);
        assertEquals((Integer.valueOf(10)).compareTo(Integer.valueOf(20)), t.compareTo(t2));
    }

    @Test
    public void testCompareTo_ParamHasEarlierSemester_ShouldEqualTrue() {
        Term t2 = new Term(Semester.SPRING, t.getYear());
        assertEquals((Integer.valueOf(10)).compareTo(Integer.valueOf(2)), t.compareTo(t2));
    }

    @Test
    public void testCompareTo_ParamHasLaterSemester_ShouldEqualTrue() {
        Term t2 = new Term(Semester.FALL, t.getYear());
        assertEquals((Integer.valueOf(10)).compareTo(Integer.valueOf(20)), t.compareTo(t2));
    }

    @Test
    public void testCompareTo_ParamHasEarlierYearEarlierSemester_ShouldEqualTrue() {
        Term t2 = new Term(Semester.SPRING, 2000);
        assertEquals((Integer.valueOf(10)).compareTo(Integer.valueOf(2)), t.compareTo(t2));
    }

    @Test
    public void testCompareTo_ParamHasEarlierYearLaterSemester_ShouldEqualTrue() {
        Term t2 = new Term(Semester.FALL, 2000);
        assertEquals((Integer.valueOf(10)).compareTo(Integer.valueOf(2)), t.compareTo(t2));
    }

    @Test
    public void testCompareTo_ParamHasLaterYearEarlierSemester_ShouldEqualTrue() {
        Term t2 = new Term(Semester.SPRING, 3000);
         assertEquals((Integer.valueOf(10)).compareTo(Integer.valueOf(20)), t.compareTo(t2));
    }

    @Test
    public void testCompareTo_ParamHasLaterYearLaterSemester_ShouldEqualTrue() {
        Term t2 = new Term(Semester.SPRING, 3000);
         assertEquals((Integer.valueOf(10)).compareTo(Integer.valueOf(20)), t.compareTo(t2));
    }
}
