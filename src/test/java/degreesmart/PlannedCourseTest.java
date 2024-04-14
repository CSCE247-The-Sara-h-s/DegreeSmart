package degreesmart;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import degreesmart.model.*;

public class PlannedCourseTest {
	private Course c;
	private PlannedCourse p;

	@BeforeEach
	public void setTestObjects() {
		c = new Course(UUID.randomUUID(), Subject.CSCE, "247");
		p = new PlannedCourse(c, Semester.SPRING, 2024);
	}

	@AfterEach
	public void unsetTestObjects() {
		c = null;
		p = null;
	}

	@Test
	public void testCreatePlannedCourse_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(() -> new PlannedCourse(c, Semester.SPRING, 2024));
	}

	@Test
	public void testtoString_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(() -> p.toString());
	}

	@Test
	public void testCreatePlannedCourse_NullCourse_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new PlannedCourse(null, Semester.SPRING, 2024));
		assertEquals("course cannot be null", e.getMessage());
	}

	@Test
	public void testCreatePlannedCourse_NullSemester_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new PlannedCourse(c, null, 2024));
		assertEquals("semester cannot be null", e.getMessage());
	}

	@Test
	public void testCreatePlannedCourse_ZeroYear_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new PlannedCourse(c, Semester.SPRING, 0));
		assertEquals("year cannot be less than one", e.getMessage());
	}

	@Test
	public void testSetCourse_NullCoures_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> p.setCourse(null));
		assertEquals("course cannot be null", e.getMessage());
	}

	@Test
	public void testSetYear_ZeroYear_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> p.setYear(0));
		assertEquals("year cannot be less than one", e.getMessage());
	}

	@Test
	public void testSetSemester_NullSemester_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> p.setSemester(null));
		assertEquals("semester cannot be null", e.getMessage());
	}

	@Test
	public void testEquals_SameData_ShouldEqualTrue() {
		assertEquals(true, 
			p.equals(new PlannedCourse(p.getCourse(), p.getSemester(), p.getYear())));
	}

	@Test
	public void testEquals_Null_ShouldEqualFalse() {
		assertEquals(false, p.equals(null));
	}

	@Test
	public void testEquals_DifferentObject_ShouldEqualFalse() {
		assertEquals(false, p.equals(c));	
	}

	@Test
	public void testEquals_DifferentCourse_ShouldEqualFalse() {
		PlannedCourse p2 = new PlannedCourse(
			new Course(UUID.randomUUID(), Subject.MATH, "241"), p.getSemester(), p.getYear());
		assertEquals(false, p.equals(p2));
	}

	@Test
	public void testEquals_DifferentSemeseter_ShouldEqualFalse() {
		PlannedCourse p2 = new PlannedCourse(c, Semester.FALL, p.getYear());
		assertEquals(false, p.equals(p2));

	}

	@Test
	public void testEquals_DifferentYear_ShouldEqualFalse() {
		PlannedCourse p2 = new PlannedCourse(c, Semester.FALL, 2000);
		assertEquals(false, p.equals(p2));
	}
}
