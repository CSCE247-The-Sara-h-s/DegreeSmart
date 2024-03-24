package degreesmart;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
	private UUID uuid;

	@BeforeEach
	public void setTestUuid() {
		uuid = UUID.randomUUID();
	}

	@AfterEach
	public void unsetTestUuid() {
		uuid = null;
	}

	@Test
	public void testCreateAdministrator_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(
			() -> new Administrator(uuid, "username", "password", "email", "firstName", "lastName"));
	}

	@Test
	public void testCreateAdministrator_NullData_ShouldThrowException() {
		assertThrows(NullPointerException.class,
			() -> new Administrator(null, null, null, null, null, null));
	}

	@Test
	public void testCreateAdministrator_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(null, "username", "password", "email", "firstName", "lastName"));
		assertEquals("UUID cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_NullUsername_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, null, "password", "email", "firstName", "lastName"));
		assertEquals("username cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_NullPassword_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, "username", null, "email", "firstName", "lastName"));
		assertEquals("password cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_NullEmailAddress_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, "username", "password", null, "firstName", "lastName"));
		assertEquals("email cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_NullFirstName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, "username", "password", "email", null, "lastName"));
		assertEquals("first name cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_NullLastName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Administrator(uuid, "username", "password", "email", "firstName", null));
		assertEquals("last name cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_EmptyData_ShouldThrowException() {
		assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "", "", "", "", ""));
	}

	@Test
	public void testCreateAdministrator_EmptyUsername_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "", "password", "email", "firstName", "lastName"));
		assertEquals("username cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_EmptyPassword_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "username", "", "email", "firstName", "lastName"));
		assertEquals("password cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_EmptyEmailAddress_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "username", "password", "", "firstName", "lastName"));
		assertEquals("email cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_EmptyFirstName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "username", "password", "email", "", "lastName"));
		assertEquals("first name cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateAdministrator_EmptyLastName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Administrator(uuid, "username", "password", "email", "firstName", ""));
		assertEquals("last name cannot be empty", e.getMessage());
	}

	@Test
	public void testAdministratorSetUsername_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setUsername(null));
		assertEquals("username cannot be null", e.getMessage());
	}

	@Test
	public void testAdministratorSetPassword_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setPassword(""));
		assertEquals("password cannot be null", e.getMessage());
	}

	@Test
	public void testAdministratorSetEmailAddress_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setEmailAddress(null));
		assertEquals("email cannot be null", e.getMessage());
	}

	@Test
	public void testAdministratorSetFirstName_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setFirstName(null));
		assertEquals("first name cannot be null", e.getMessage());
	}

	@Test
	public void testAdministratorSetLastName_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setLastName(null));
		assertEquals("last name cannot be null", e.getMessage());
	}

	@Test
	public void testAdministratorSetUsername_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setUsername(""));
		assertEquals("username cannot be empty", e.getMessage());
	}

	@Test
	public void testAdministratorSetPassword_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setPassword(""));
		assertEquals("password cannot be empty", e.getMessage());
	}

	@Test
	public void testAdministratorSetEmailAddress_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setEmailAddress(""));
		assertEquals("email cannot be empty", e.getMessage());
	}

	@Test
	public void testAdministratorSetFirstName_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setFirstName(""));
		assertEquals("first name cannot be empty", e.getMessage());
	}

	@Test
	public void testAdministratorSetLastName_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setLastName(""));
		assertEquals("last name cannot be empty", e.getMessage());
	}

	@Test
	public void testAdministratorSetPreferredName_Null_ShouldSetEmptyString() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(null));
		assertEquals("", u.getPreferredName());
	}

	@Test
	public void testAdministratorSetPreferredName_Empty_ShouldSetEmptyString() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(""));
		assertEquals("", u.getPreferredName());
	}

	@Test
	public void testCreateAdvisor_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(
			() -> new Advisor(uuid, "username", "password", "email", "firstName", "lastName"));
	}

	@Test
	public void testCreateAdvisor_NullData_ShouldThrowException() {
		assertThrows(NullPointerException.class,
			() -> new Advisor(null, null, null, null, null, null));
	}

	@Test
	public void testCreateAdvisor_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(null, "username", "password", "email", "firstName", "lastName"));
		assertEquals("UUID cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_NullUsername_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, null, "password", "email", "firstName", "lastName"));
		assertEquals("username cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_NullPassword_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, "username", null, "email", "firstName", "lastName"));
		assertEquals("password cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_NullEmailAddress_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, "username", "password", null, "firstName", "lastName"));
		assertEquals("email cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_NullFirstName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, "username", "password", "email", null, "lastName"));
		assertEquals("first name cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_NullLastName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Advisor(uuid, "username", "password", "email", "firstName", null));
		assertEquals("last name cannot be null", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_EmptyData_ShouldThrowException() {
		assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "", "", "", "", ""));
	}

	@Test
	public void testCreateAdvisor_EmptyUsername_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "", "password", "email", "firstName", "lastName"));
		assertEquals("username cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_EmptyPassword_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "username", "", "email", "firstName", "lastName"));
		assertEquals("password cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_EmptyEmailAddress_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "username", "password", "", "firstName", "lastName"));
		assertEquals("email cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_EmptyFirstName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "username", "password", "email", "", "lastName"));
		assertEquals("first name cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateAdvisor_EmptyLastName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Advisor(uuid, "username", "password", "email", "firstName", ""));
		assertEquals("last name cannot be empty", e.getMessage());
	}

	@Test
	public void testAdvisorSetUsername_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setUsername(null));
		assertEquals("username cannot be null", e.getMessage());
	}

	@Test
	public void testAdvisorSetPassword_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setPassword(""));
		assertEquals("password cannot be null", e.getMessage());
	}

	@Test
	public void testAdvisorSetEmailAddress_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setEmailAddress(null));
		assertEquals("email cannot be null", e.getMessage());
	}

	@Test
	public void testAdvisorSetFirstName_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setFirstName(null));
		assertEquals("first name cannot be null", e.getMessage());
	}

	@Test
	public void testAdvisorSetLastName_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setLastName(null));
		assertEquals("last name cannot be null", e.getMessage());
	}

	@Test
	public void testAdvisorSetUsername_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setUsername(""));
		assertEquals("username cannot be empty", e.getMessage());
	}

	@Test
	public void testAdvisorSetPassword_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setPassword(""));
		assertEquals("password cannot be empty", e.getMessage());
	}

	@Test
	public void testAdvisorSetEmailAddress_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setEmailAddress(""));
		assertEquals("email cannot be empty", e.getMessage());
	}

	@Test
	public void testAdvisorSetFirstName_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setFirstName(""));
		assertEquals("first name cannot be empty", e.getMessage());
	}

	@Test
	public void testAdvisorSetLastName_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setLastName(""));
		assertEquals("last name cannot be empty", e.getMessage());
	}

	@Test
	public void testAdvisorSetPreferredName_Null_ShouldSetEmptyString() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(null));
		assertEquals("", u.getPreferredName());
	}

	@Test
	public void testAdvisorSetPreferredName_Empty_ShouldSetEmptyString() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(""));
		assertEquals("", u.getPreferredName());
	}

	@Test
	public void testCreateParent_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(
			() -> new Parent(uuid, "username", "password", "email", "firstName", "lastName"));
	}

	@Test
	public void testCreateParent_NullData_ShouldThrowException() {
		assertThrows(NullPointerException.class,
			() -> new Parent(null, null, null, null, null, null));
	}

	@Test
	public void testCreateParent_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(null, "username", "password", "email", "firstName", "lastName"));
		assertEquals("UUID cannot be null", e.getMessage());
	}

	@Test
	public void testCreateParent_NullUsername_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, null, "password", "email", "firstName", "lastName"));
		assertEquals("username cannot be null", e.getMessage());
	}

	@Test
	public void testCreateParent_NullPassword_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, "username", null, "email", "firstName", "lastName"));
		assertEquals("password cannot be null", e.getMessage());
	}

	@Test
	public void testCreateParent_NullEmailAddress_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, "username", "password", null, "firstName", "lastName"));
		assertEquals("email cannot be null", e.getMessage());
	}

	@Test
	public void testCreateParent_NullFirstName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, "username", "password", "email", null, "lastName"));
		assertEquals("first name cannot be null", e.getMessage());
	}

	@Test
	public void testCreateParent_NullLastName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Parent(uuid, "username", "password", "email", "firstName", null));
		assertEquals("last name cannot be null", e.getMessage());
	}

	@Test
	public void testCreateParent_EmptyData_ShouldThrowException() {
		assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "", "", "", "", ""));
	}

	@Test
	public void testCreateParent_EmptyUsername_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "", "password", "email", "firstName", "lastName"));
		assertEquals("username cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateParent_EmptyPassword_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "username", "", "email", "firstName", "lastName"));
		assertEquals("password cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateParent_EmptyEmailAddress_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "username", "password", "", "firstName", "lastName"));
		assertEquals("email cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateParent_EmptyFirstName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "username", "password", "email", "", "lastName"));
		assertEquals("first name cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateParent_EmptyLastName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Parent(uuid, "username", "password", "email", "firstName", ""));
		assertEquals("last name cannot be empty", e.getMessage());
	}

	@Test
	public void testParentSetUsername_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setUsername(null));
		assertEquals("username cannot be null", e.getMessage());
	}

	@Test
	public void testParentSetPassword_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setPassword(""));
		assertEquals("password cannot be null", e.getMessage());
	}

	@Test
	public void testParentSetEmailAddress_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setEmailAddress(null));
		assertEquals("email cannot be null", e.getMessage());
	}

	@Test
	public void testParentSetFirstName_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setFirstName(null));
		assertEquals("first name cannot be null", e.getMessage());
	}

	@Test
	public void testParentSetLastName_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setLastName(null));
		assertEquals("last name cannot be null", e.getMessage());
	}

	@Test
	public void testParentSetUsername_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setUsername(""));
		assertEquals("username cannot be empty", e.getMessage());
	}

	@Test
	public void testParentSetPassword_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setPassword(""));
		assertEquals("password cannot be empty", e.getMessage());
	}

	@Test
	public void testParentSetEmailAddress_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setEmailAddress(""));
		assertEquals("email cannot be empty", e.getMessage());
	}

	@Test
	public void testParentSetFirstName_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setFirstName(""));
		assertEquals("first name cannot be empty", e.getMessage());
	}

	@Test
	public void testParentSetLastName_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setLastName(""));
		assertEquals("last name cannot be empty", e.getMessage());
	}

	@Test
	public void testParentSetPreferredName_Null_ShouldSetEmptyString() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(null));
		assertEquals("", u.getPreferredName());
	}

	@Test
	public void testParentSetPreferredName_Empty_ShouldSetEmptyString() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(""));
		assertEquals("", u.getPreferredName());
	}

	@Test
	public void testCreateStudent_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(
			() -> new Student(uuid, "username", "password", "email", "firstName", "lastName"));
	}

	@Test
	public void testCreateStudent_NullData_ShouldThrowException() {
		assertThrows(NullPointerException.class,
			() -> new Student(null, null, null, null, null, null));
	}

	@Test
	public void testCreateStudent_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(null, "username", "password", "email", "firstName", "lastName"));
		assertEquals("UUID cannot be null", e.getMessage());
	}

	@Test
	public void testCreateStudent_NullUsername_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, null, "password", "email", "firstName", "lastName"));
		assertEquals("username cannot be null", e.getMessage());
	}

	@Test
	public void testCreateStudent_NullPassword_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, "username", null, "email", "firstName", "lastName"));
		assertEquals("password cannot be null", e.getMessage());
	}

	@Test
	public void testCreateStudent_NullEmailAddress_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, "username", "password", null, "firstName", "lastName"));
		assertEquals("email cannot be null", e.getMessage());
	}

	@Test
	public void testCreateStudent_NullFirstName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, "username", "password", "email", null, "lastName"));
		assertEquals("first name cannot be null", e.getMessage());
	}

	@Test
	public void testCreateStudent_NullLastName_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Student(uuid, "username", "password", "email", "firstName", null));
		assertEquals("last name cannot be null", e.getMessage());
	}

	@Test
	public void testCreateStudent_EmptyData_ShouldThrowException() {
		assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "", "", "", "", ""));
	}

	@Test
	public void testCreateStudent_EmptyUsername_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "", "password", "email", "firstName", "lastName"));
		assertEquals("username cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateStudent_EmptyPassword_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "username", "", "email", "firstName", "lastName"));
		assertEquals("password cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateStudent_EmptyEmailAddress_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "username", "password", "", "firstName", "lastName"));
		assertEquals("email cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateStudent_EmptyFirstName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "username", "password", "email", "", "lastName"));
		assertEquals("first name cannot be empty", e.getMessage());
	}

	@Test
	public void testCreateStudent_EmptyLastName_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new Student(uuid, "username", "password", "email", "firstName", ""));
		assertEquals("last name cannot be empty", e.getMessage());
	}

	@Test
	public void testStudentSetUsername_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setUsername(null));
		assertEquals("username cannot be null", e.getMessage());
	}

	@Test
	public void testStudentSetPassword_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setPassword(""));
		assertEquals("password cannot be null", e.getMessage());
	}

	@Test
	public void testStudentSetEmailAddress_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setEmailAddress(null));
		assertEquals("email cannot be null", e.getMessage());
	}

	@Test
	public void testStudentSetFirstName_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setFirstName(null));
		assertEquals("first name cannot be null", e.getMessage());
	}

	@Test
	public void testStudentSetLastName_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setLastName(null));
		assertEquals("last name cannot be null", e.getMessage());
	}

	@Test
	public void testStudentSetUsername_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setUsername(""));
		assertEquals("username cannot be empty", e.getMessage());
	}

	@Test
	public void testStudentSetPassword_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setPassword(""));
		assertEquals("password cannot be empty", e.getMessage());
	}

	@Test
	public void testStudentSetEmailAddress_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setEmailAddress(""));
		assertEquals("email cannot be empty", e.getMessage());
	}

	@Test
	public void testStudentSetFirstName_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setFirstName(""));
		assertEquals("first name cannot be empty", e.getMessage());
	}

	@Test
	public void testStudentSetLastName_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setLastName(""));
		assertEquals("last name cannot be empty", e.getMessage());
	}

	@Test
	public void testStudentSetPreferredName_Null_ShouldSetEmptyString() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(null));
		assertEquals("", u.getPreferredName());
	}

	@Test
	public void testStudentSetPreferredName_Empty_ShouldSetEmptyString() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(""));
		assertEquals("", u.getPreferredName());
	}

	@Test
	public void testCreateGuest_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(() -> new Guest(uuid));
	}

	@Test
	public void testCreateGuest_NullUuid_ShouldThrowException() {
		Exception e = assertThrows(NullPointerException.class,
			() -> new Guest(null));
		assertEquals("UUID cannot be null", e.getMessage());
	}
}
