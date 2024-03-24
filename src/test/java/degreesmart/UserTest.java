package degreesmart;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
	private UUID uuid;

	@BeforeEach
	public void setupUuid() {
		uuid = UUID.randomUUID();
	}

	@Test
	public void TestCreateAdministrator_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(
			() -> new Administrator(uuid, "username", "password", "email", "firstName", "lastName"));
	}

	@Test
	public void TestCreateAdministrator_NullData_ShouldThrowException() {
		assertThrows(NullPointerException.class,
			() -> new Administrator(null, null, null, null, null, null));
	}

	@Test
	public void TestCreateAdministrator_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(null, "username", "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "UUID cannot be null");
	}

	@Test
	public void TestCreateAdministrator_NullUsername_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, null, "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "username cannot be null");
	}

	@Test
	public void TestCreateAdministrator_NullPassword_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, "username", null, "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "password cannot be null");
	}

	@Test
	public void TestCreateAdministrator_NullEmail_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, "username", "password", null, "firstName", "lastName"));
		assertEquals(e.getMessage(), "email cannot be null");
	}

	@Test
	public void TestCreateAdministrator_NullFirstName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, "username", "password", "email", null, "lastName"));
		assertEquals(e.getMessage(), "first name cannot be null");
	}

	@Test
	public void TestCreateAdministrator_NullLastName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, "username", "password", "email", "firstName", null));
		assertEquals(e.getMessage(), "last name cannot be null");
	}

	@Test
	public void TestCreateAdministrator_EmptyData_ShouldThrowException() {
		assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "", "", "", "", ""));
	}

	@Test
	public void TestCreateAdministrator_EmptyUsername_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "", "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "username cannot be empty");
	}

	@Test
	public void TestCreateAdministrator_EmptyPassword_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "username", "", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "password cannot be empty");
	}

	@Test
	public void TestCreateAdministrator_EmptyEmail_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "username", "password", "", "firstName", "lastName"));
		assertEquals(e.getMessage(), "email cannot be empty");
	}

	@Test
	public void TestCreateAdministrator_EmptyFirstName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "username", "password", "email", "", "lastName"));
		assertEquals(e.getMessage(), "first name cannot be empty");
	}

	@Test
	public void TestCreateAdministrator_EmptyLastName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "username", "password", "email", "firstName", ""));
		assertEquals(e.getMessage(), "last name cannot be empty");
	}

	@Test
	public void TestCreateAdvisor_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(
			() -> new Advisor(uuid, "username", "password", "email", "firstName", "lastName"));
	}

	@Test
	public void TestCreateAdvisor_NullData_ShouldThrowException() {
		assertThrows(NullPointerException.class,
			() -> new Advisor(null, null, null, null, null, null));
	}

	@Test
	public void TestCreateAdvisor_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(null, "username", "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "UUID cannot be null");
	}

	@Test
	public void TestCreateAdvisor_NullUsername_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, null, "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "username cannot be null");
	}

	@Test
	public void TestCreateAdvisor_NullPassword_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, "username", null, "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "password cannot be null");
	}

	@Test
	public void TestCreateAdvisor_NullEmail_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, "username", "password", null, "firstName", "lastName"));
		assertEquals(e.getMessage(), "email cannot be null");
	}

	@Test
	public void TestCreateAdvisor_NullFirstName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, "username", "password", "email", null, "lastName"));
		assertEquals(e.getMessage(), "first name cannot be null");
	}

	@Test
	public void TestCreateAdvisor_NullLastName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, "username", "password", "email", "firstName", null));
		assertEquals(e.getMessage(), "last name cannot be null");
	}

	@Test
	public void TestCreateAdvisor_EmptyData_ShouldThrowException() {
		assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "", "", "", "", ""));
	}

	@Test
	public void TestCreateAdvisor_EmptyUsername_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "", "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "username cannot be empty");
	}

	@Test
	public void TestCreateAdvisor_EmptyPassword_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "username", "", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "password cannot be empty");
	}

	@Test
	public void TestCreateAdvisor_EmptyEmail_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "username", "password", "", "firstName", "lastName"));
		assertEquals(e.getMessage(), "email cannot be empty");
	}

	@Test
	public void TestCreateAdvisor_EmptyFirstName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "username", "password", "email", "", "lastName"));
		assertEquals(e.getMessage(), "first name cannot be empty");
	}

	@Test
	public void TestCreateAdvisor_EmptyLastName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "username", "password", "email", "firstName", ""));
		assertEquals(e.getMessage(), "last name cannot be empty");
	}

	@Test
	public void TestCreateParent_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(
			() -> new Parent(uuid, "username", "password", "email", "firstName", "lastName"));
	}

	@Test
	public void TestCreateParent_NullData_ShouldThrowException() {
		assertThrows(NullPointerException.class,
			() -> new Parent(null, null, null, null, null, null));
	}

	@Test
	public void TestCreateParent_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(null, "username", "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "UUID cannot be null");
	}

	@Test
	public void TestCreateParent_NullUsername_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, null, "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "username cannot be null");
	}

	@Test
	public void TestCreateParent_NullPassword_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, "username", null, "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "password cannot be null");
	}

	@Test
	public void TestCreateParent_NullEmail_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, "username", "password", null, "firstName", "lastName"));
		assertEquals(e.getMessage(), "email cannot be null");
	}

	@Test
	public void TestCreateParent_NullFirstName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, "username", "password", "email", null, "lastName"));
		assertEquals(e.getMessage(), "first name cannot be null");
	}

	@Test
	public void TestCreateParent_NullLastName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, "username", "password", "email", "firstName", null));
		assertEquals(e.getMessage(), "last name cannot be null");
	}

	@Test
	public void TestCreateParent_EmptyData_ShouldThrowException() {
		assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "", "", "", "", ""));
	}

	@Test
	public void TestCreateParent_EmptyUsername_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "", "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "username cannot be empty");
	}

	@Test
	public void TestCreateParent_EmptyPassword_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "username", "", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "password cannot be empty");
	}

	@Test
	public void TestCreateParent_EmptyEmail_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "username", "password", "", "firstName", "lastName"));
		assertEquals(e.getMessage(), "email cannot be empty");
	}

	@Test
	public void TestCreateParent_EmptyFirstName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "username", "password", "email", "", "lastName"));
		assertEquals(e.getMessage(), "first name cannot be empty");
	}

	@Test
	public void TestCreateParent_EmptyLastName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "username", "password", "email", "firstName", ""));
		assertEquals(e.getMessage(), "last name cannot be empty");
	}

	@Test
	public void TestCreateStudent_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(
			() -> new Student(uuid, "username", "password", "email", "firstName", "lastName"));
	}

	@Test
	public void TestCreateStudent_NullData_ShouldThrowException() {
		assertThrows(NullPointerException.class,
			() -> new Student(null, null, null, null, null, null));
	}

	@Test
	public void TestCreateStudent_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(null, "username", "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "UUID cannot be null");
	}

	@Test
	public void TestCreateStudent_NullUsername_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, null, "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "username cannot be null");
	}

	@Test
	public void TestCreateStudent_NullPassword_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, "username", null, "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "password cannot be null");
	}

	@Test
	public void TestCreateStudent_NullEmail_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, "username", "password", null, "firstName", "lastName"));
		assertEquals(e.getMessage(), "email cannot be null");
	}

	@Test
	public void TestCreateStudent_NullFirstName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, "username", "password", "email", null, "lastName"));
		assertEquals(e.getMessage(), "first name cannot be null");
	}

	@Test
	public void TestCreateStudent_NullLastName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, "username", "password", "email", "firstName", null));
		assertEquals(e.getMessage(), "last name cannot be null");
	}

	@Test
	public void TestCreateStudent_EmptyData_ShouldThrowException() {
		assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "", "", "", "", ""));
	}

	@Test
	public void TestCreateStudent_EmptyUsername_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "", "password", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "username cannot be empty");
	}

	@Test
	public void TestCreateStudent_EmptyPassword_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "username", "", "email", "firstName", "lastName"));
		assertEquals(e.getMessage(), "password cannot be empty");
	}

	@Test
	public void TestCreateStudent_EmptyEmail_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "username", "password", "", "firstName", "lastName"));
		assertEquals(e.getMessage(), "email cannot be empty");
	}

	@Test
	public void TestCreateStudent_EmptyFirstName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "username", "password", "email", "", "lastName"));
		assertEquals(e.getMessage(), "first name cannot be empty");
	}

	@Test
	public void TestCreateStudent_EmptyLastName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "username", "password", "email", "firstName", ""));
		assertEquals(e.getMessage(), "last name cannot be empty");
	}

	@Test
	public void TestCreateGuest_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(() -> new Guest(uuid));
	}

	@Test
	public void TestCreateGuest_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Guest(null));
		assertEquals(e.getMessage(), "UUID cannot be null");
	}
}
