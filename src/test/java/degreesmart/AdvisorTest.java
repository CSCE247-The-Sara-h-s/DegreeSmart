package degreesmart;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdvisorTest {
	private Advisor a;
	private Student s1;
	private Student s2;

	@BeforeEach
	public void setTestUsers() {
		a = new Advisor(
			UUID.randomUUID(), "username0", "password", "email", "firstName", "lastName");
		s1 = new Student(
			UUID.randomUUID(), "username1", "password", "email", "firstName", "lastName");
		s2 = new Student(
			UUID.randomUUID(), "username2", "password", "email", "firstName", "lastName");
		s1.setUscId("A123456789");
		s2.setUscId("B987654321");
	}

	@AfterEach
	public void unsetTestUsers() {
		a = null;
		s1 = null;
		s2 = null;
	}

	@Test
	public void testRole_DefaultRole_ShouldEqualUnapprovedAdvisor() {
		assertEquals(Role.UNAPPROVED_ADVISOR, a.role);
		assertEquals(Role.UNAPPROVED_ADVISOR, a.getRole());
	}

	@Test
	public void testSetRole_Approved_ShouldEqualAdvisor() {
		a.setAdvisorRole();
		assertEquals(Role.ADVISOR, a.role);
		assertEquals(Role.ADVISOR, a.getRole());
	}

	@Test
	public void testSetRole_Unapproved_ShouldEqualUnapprovedAdvisor() {
		a.setAdvisorRole();
		a.unsetAdvisorRole();
		assertEquals(Role.UNAPPROVED_ADVISOR, a.role);
		assertEquals(Role.UNAPPROVED_ADVISOR, a.getRole());
	}

	@Test
	public void testToString_EmptyList_ShouldNotThrowException() {
		assertDoesNotThrow(() -> a.toString());
	}

	@Test
	public void testGetAssignedSize_EmptyList_ShouldEqualZero() {
		assertEquals(0, a.getAssignedStudents().size());
	}

	@Test
	public void testAddStudent_Null_ShouldEqualFalse() {
		assertEquals(false, a.addAssignedStudent(null));
	}

	@Test
	public void testAddStudent_ValidStudent_ShouldEqualTrue() {
		assertEquals(true, a.addAssignedStudent(s1));
	}

	@Test
	public void testAddStudentSize_ValidStudent_ShouldEqualOne() {
		a.addAssignedStudent(s1);
		assertEquals(1, a.getAssignedStudents().size());
	}

	@Test
	public void testAddStudnet_TwoStudents_ShouldEqualTrue() {
		assertEquals(true, a.addAssignedStudent(s1) && a.addAssignedStudent(s2));
	}

	@Test
	public void testAddStudentSize_TwoStudents_ShouldEqualTwo() {
		a.addAssignedStudent(s1);
		a.addAssignedStudent(s2);
		assertEquals(2, a.getAssignedStudents().size());
	}

	@Test
	public void testAddStudent_DuplicateStudents_ShouldEqualFalse() {
		a.addAssignedStudent(s1);
		assertEquals(false, a.addAssignedStudent(s1));
	}

	@Test
	public void testAddStudentSize_DuplicateStudents_ShouldEqualOne() {
		a.addAssignedStudent(s1);
		a.addAssignedStudent(s1);
		assertEquals(1, a.getAssignedStudents().size());
	}

	@Test
	public void testRemoveStudent_EmptyList_ShouldEqualFalse() {
		assertEquals(false, a.removeAssignedStudent(s1));
	}

	@Test
	public void testRemoveStudent_NullStudentEmptyList_ShouldEqualFalse() {
		assertEquals(false, a.removeAssignedStudent(null));
	}

	@Test
	public void testRemoveStudent_NotInList_ShouldEqualFalse() {
		a.addAssignedStudent(s1);
		assertEquals(false, a.removeAssignedStudent(s2));
	}

	@Test
	public void testRemoveStudent_Null_ShouldEqualFalse() {
		a.addAssignedStudent(s1);
		assertEquals(false, a.removeAssignedStudent(null));
	}

	@Test
	public void testRemoveStudent_InList_ShouldEqualTrue() {
		a.addAssignedStudent(s1);
		assertEquals(true, a.removeAssignedStudent(s1));
	}

	@Test
	public void testRemoveStudentSize_InList_ShouldEqualZero() {
		a.addAssignedStudent(s1);
		a.removeAssignedStudent(s1);
		assertEquals(0, a.getAssignedStudents().size());
	}
}
