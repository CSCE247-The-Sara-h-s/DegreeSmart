package degreesmart;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParentTest {
	private Parent p;
	private Student s1;
	private Student s2;

	@BeforeEach
	public void setTestUsers() {
		p = new Parent(
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
		p = null;
		s1 = null;
		s2 = null;
	}

	@Test
	public void testRole_DefaultRole_ShouldEqualParent() {
		assertEquals(Role.PARENT, p.role);
		assertEquals(Role.PARENT, p.getRole());
	}

	@Test
	public void testToString_EmptyLists_ShouldNotThrowException() {
		assertDoesNotThrow(() -> p.toString());
	}

	@Test
	public void testGetChildrenSize_EmptyList_ShouldEqualZero() {
		assertEquals(0, p.getChildren().size());
	}

	@Test
	public void testAddChild_Null_ShouldEqualFalse() {
		assertEquals(false, p.addChild(null));
	}

	@Test
	public void testAddChild_ValidStudent_ShouldEqualTrue() {
		assertEquals(true, p.addChild(s1));
	}

	@Test
	public void testAddChildSize_ValidStudent_ShouldEqualOne() {
		p.addChild(s1);
		assertEquals(1, p.getChildren().size());
	}

	@Test
	public void testAddChild_TwoStudents_ShouldEqualTrue() {
		assertEquals(true, p.addChild(s1) && p.addChild(s2));
	}

	@Test
	public void testAddChildSize_TwoStudents_ShouldEqualTwo() {
		p.addChild(s1);
		p.addChild(s2);
		assertEquals(2, p.getChildren().size());
	}

	@Test
	public void testAddChild_DuplicateStudents_ShouldEqualFalse() {
		p.addChild(s1);
		assertEquals(false, p.addChild(s1));
	}

	@Test
	public void testAddChildSize_DuplicateStudents_ShouldEqualOne() {
		p.addChild(s1);
		p.addChild(s1);
		assertEquals(1, p.getChildren().size());
	}

	@Test
	public void testRemoveChild_EmptyList_ShouldEqualFalse() {
		assertEquals(false, p.removeChild(s1));
	}

	@Test
	public void testRemoveChild_NullStudentEmptyList_ShouldEqualFalse() {
		assertEquals(false, p.removeChild(null));
	}

	@Test
	public void testRemoveChild_NotInList_ShouldEqualFalse() {
		p.addChild(s1);
		assertEquals(false, p.removeChild(s1));
	}

	@Test
	public void testRemoveChild_Null_ShouldEqualFalse() {
		p.addChild(s1);
		assertEquals(false, p.removeChild(null));
	}

	@Test
	public void testRemoveChild_InList_ShouldEqualTrue() {
		p.addChild(s1);
		assertEquals(true, p.removeChild(s1));
	}

	@Test
	public void testRemoveChildSize_InList_ShouldEqualZero() {
		p.addChild(s1);
		p.removeChild(s1);
		assertEquals(0, p.getChildren().size());
	}

	@Test
	public void testGetRequestsSize_EmptyList_ShouldEqualZero() {
		assertEquals(0, p.getPendingAccessRequests().size());
	}

	@Test
	public void testAddRequest_Null_ShouldEqualFalse() {
		assertEquals(false, p.addPendingAccessRequest(null));
	}

	@Test
	public void testAddRequest_ValidStudent_ShouldEqualTrue() {
		assertEquals(true, p.addPendingAccessRequest(s1));
	}

	@Test
	public void testAddRequestSize_ValidStudent_ShouldEqualOne() {
		p.addPendingAccessRequest(s1);
		assertEquals(1, p.getPendingAccessRequests().size());
	}

	@Test
	public void testAddRequest_TwoStudents_ShouldEqualTrue() {
		assertEquals(true, p.addPendingAccessRequest(s1) && p.addPendingAccessRequest(s2));
	}

	@Test
	public void testAddRequestSize_TwoStudents_ShouldEqualTwo() {
		p.addPendingAccessRequest(s1);
		p.addPendingAccessRequest(s2);
		assertEquals(2, p.getPendingAccessRequests().size());
	}

	@Test
	public void testAddRequest_DuplicateStudents_ShouldEqualFalse() {
		p.addPendingAccessRequest(s1);
		assertEquals(false, p.addPendingAccessRequest(s1));
	}

	@Test
	public void testAddRequestSize_DuplicateStudents_ShouldEqualOne() {
		p.addPendingAccessRequest(s1);
		p.addPendingAccessRequest(s1);
		assertEquals(1, p.getPendingAccessRequests().size());
	}

	@Test
	public void testRemoveRequest_EmptyList_ShouldEqualFalse() {
		assertEquals(false, p.removePendingAccessRequest(s1));
	}

	@Test
	public void testRemoveRequest_NullStudentEmptyList_ShouldEqualFalse() {
		assertEquals(false, p.removePendingAccessRequest(null));
	}

	@Test
	public void testRemoveRequest_NotInList_ShouldEqualFalse() {
		p.addPendingAccessRequest(s1);
		assertEquals(false, p.removePendingAccessRequest(s1));
	}

	@Test
	public void testRemoveRequest_Null_ShouldEqualFalse() {
		p.addPendingAccessRequest(s1);
		assertEquals(false, p.removePendingAccessRequest(null));
	}

	@Test
	public void testRemoveRequest_InList_ShouldEqualTrue() {
		p.addPendingAccessRequest(s1);
		assertEquals(true, p.removePendingAccessRequest(s1));
	}

	@Test
	public void testRemoveRequestSize_InList_ShouldEqualZero() {
		p.addPendingAccessRequest(s1);
		p.removePendingAccessRequest(s1);
		assertEquals(0, p.getPendingAccessRequests().size());
	}
}
