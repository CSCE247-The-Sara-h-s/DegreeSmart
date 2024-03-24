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
	public void TestCreateAdministrator_NullEmailAddress_ShouldThrowException() {
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
	public void TestCreateAdministrator_EmptyEmailAddress_ShouldThrowException() {
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
	public void TestAdministratorSetUsername_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setUsername(null));
		assertEquals(e.getMessage(), "username cannot be null");
	}

	@Test
	public void TestAdministratorSetPassword_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setPassword(""));
		assertEquals(e.getMessage(), "password cannot be null");
	}

	@Test
	public void TestAdministratorSetEmailAddress_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setEmailAddress(null));
		assertEquals(e.getMessage(), "email cannot be null");
	}

	@Test
	public void TestAdministratorSetFirstName_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setFirstName(null));
		assertEquals(e.getMessage(), "first name cannot be null");
	}

	@Test
	public void TestAdministratorSetLastName_Null_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setLastName(null));
		assertEquals(e.getMessage(), "last name cannot be null");
	}

	@Test
	public void TestAdministratorSetUsername_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setUsername(""));
		assertEquals(e.getMessage(), "username cannot be empty");
	}

	@Test
	public void TestAdministratorSetPassword_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setPassword(""));
		assertEquals(e.getMessage(), "password cannot be empty");
	}

	@Test
	public void TestAdministratorSetEmailAddress_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setEmailAddress(""));
		assertEquals(e.getMessage(), "email cannot be empty");
	}

	@Test
	public void TestAdministratorSetFirstName_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setFirstName(""));
		assertEquals(e.getMessage(), "first name cannot be empty");
	}

	@Test
	public void TestAdministratorSetLastName_Empty_ShouldThrowException() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setLastName(""));
		assertEquals(e.getMessage(), "last name cannot be empty");
	}

	@Test
	public void TestAdministratorSetPreferredName_Null_ShouldSetEmptyString() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(null));
		assertEquals(u.getPreferredName(), "");
	}

	@Test
	public void TestAdministratorSetPreferredName_Empty_ShouldSetEmptyString() {
		Administrator u = new Administrator(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(""));
		assertEquals(u.getPreferredName(), "");
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
	public void TestCreateAdvisor_NullEmailAddress_ShouldThrowException() {
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
	public void TestCreateAdvisor_EmptyEmailAddress_ShouldThrowException() {
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
	public void TestAdvisorSetUsername_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setUsername(null));
		assertEquals(e.getMessage(), "username cannot be null");
	}

	@Test
	public void TestAdvisorSetPassword_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setPassword(""));
		assertEquals(e.getMessage(), "password cannot be null");
	}

	@Test
	public void TestAdvisorSetEmailAddress_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setEmailAddress(null));
		assertEquals(e.getMessage(), "email cannot be null");
	}

	@Test
	public void TestAdvisorSetFirstName_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setFirstName(null));
		assertEquals(e.getMessage(), "first name cannot be null");
	}

	@Test
	public void TestAdvisorSetLastName_Null_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setLastName(null));
		assertEquals(e.getMessage(), "last name cannot be null");
	}

	@Test
	public void TestAdvisorSetUsername_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setUsername(""));
		assertEquals(e.getMessage(), "username cannot be empty");
	}

	@Test
	public void TestAdvisorSetPassword_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setPassword(""));
		assertEquals(e.getMessage(), "password cannot be empty");
	}

	@Test
	public void TestAdvisorSetEmailAddress_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setEmailAddress(""));
		assertEquals(e.getMessage(), "email cannot be empty");
	}

	@Test
	public void TestAdvisorSetFirstName_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setFirstName(""));
		assertEquals(e.getMessage(), "first name cannot be empty");
	}

	@Test
	public void TestAdvisorSetLastName_Empty_ShouldThrowException() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setLastName(""));
		assertEquals(e.getMessage(), "last name cannot be empty");
	}

	@Test
	public void TestAdvisorSetPreferredName_Null_ShouldSetEmptyString() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(null));
		assertEquals(u.getPreferredName(), "");
	}

	@Test
	public void TestAdvisorSetPreferredName_Empty_ShouldSetEmptyString() {
		Advisor u = new Advisor(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(""));
		assertEquals(u.getPreferredName(), "");
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
	public void TestCreateParent_NullEmailAddress_ShouldThrowException() {
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
	public void TestCreateParent_EmptyEmailAddress_ShouldThrowException() {
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
	public void TestParentSetUsername_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setUsername(null));
		assertEquals(e.getMessage(), "username cannot be null");
	}

	@Test
	public void TestParentSetPassword_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setPassword(""));
		assertEquals(e.getMessage(), "password cannot be null");
	}

	@Test
	public void TestParentSetEmailAddress_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setEmailAddress(null));
		assertEquals(e.getMessage(), "email cannot be null");
	}

	@Test
	public void TestParentSetFirstName_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setFirstName(null));
		assertEquals(e.getMessage(), "first name cannot be null");
	}

	@Test
	public void TestParentSetLastName_Null_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setLastName(null));
		assertEquals(e.getMessage(), "last name cannot be null");
	}

	@Test
	public void TestParentSetUsername_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setUsername(""));
		assertEquals(e.getMessage(), "username cannot be empty");
	}

	@Test
	public void TestParentSetPassword_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setPassword(""));
		assertEquals(e.getMessage(), "password cannot be empty");
	}

	@Test
	public void TestParentSetEmailAddress_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setEmailAddress(""));
		assertEquals(e.getMessage(), "email cannot be empty");
	}

	@Test
	public void TestParentSetFirstName_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setFirstName(""));
		assertEquals(e.getMessage(), "first name cannot be empty");
	}

	@Test
	public void TestParentSetLastName_Empty_ShouldThrowException() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setLastName(""));
		assertEquals(e.getMessage(), "last name cannot be empty");
	}

	@Test
	public void TestParentSetPreferredName_Null_ShouldSetEmptyString() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(null));
		assertEquals(u.getPreferredName(), "");
	}

	@Test
	public void TestParentSetPreferredName_Empty_ShouldSetEmptyString() {
		Parent u = new Parent(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(""));
		assertEquals(u.getPreferredName(), "");
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
	public void TestCreateStudent_NullEmailAddress_ShouldThrowException() {
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
	public void TestCreateStudent_EmptyEmailAddress_ShouldThrowException() {
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
	public void TestStudentSetUsername_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setUsername(null));
		assertEquals(e.getMessage(), "username cannot be null");
	}

	@Test
	public void TestStudentSetPassword_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setPassword(""));
		assertEquals(e.getMessage(), "password cannot be null");
	}

	@Test
	public void TestStudentSetEmailAddress_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setEmailAddress(null));
		assertEquals(e.getMessage(), "email cannot be null");
	}

	@Test
	public void TestStudentSetFirstName_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setFirstName(null));
		assertEquals(e.getMessage(), "first name cannot be null");
	}

	@Test
	public void TestStudentSetLastName_Null_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(NullPointerException.class,
			() -> u.setLastName(null));
		assertEquals(e.getMessage(), "last name cannot be null");
	}

	@Test
	public void TestStudentSetUsername_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setUsername(""));
		assertEquals(e.getMessage(), "username cannot be empty");
	}

	@Test
	public void TestStudentSetPassword_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setPassword(""));
		assertEquals(e.getMessage(), "password cannot be empty");
	}

	@Test
	public void TestStudentSetEmailAddress_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setEmailAddress(""));
		assertEquals(e.getMessage(), "email cannot be empty");
	}

	@Test
	public void TestStudentSetFirstName_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setFirstName(""));
		assertEquals(e.getMessage(), "first name cannot be empty");
	}

	@Test
	public void TestStudentSetLastName_Empty_ShouldThrowException() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> u.setLastName(""));
		assertEquals(e.getMessage(), "last name cannot be empty");
	}

	@Test
	public void TestStudentSetPreferredName_Null_ShouldSetEmptyString() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(null));
		assertEquals(u.getPreferredName(), "");
	}

	@Test
	public void TestStudentSetPreferredName_Empty_ShouldSetEmptyString() {
		Student u = new Student(uuid, "username", "password", "email", "firstName", "lastName");
		assertDoesNotThrow(() -> u.setPreferredName(""));
		assertEquals(u.getPreferredName(), "");
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
