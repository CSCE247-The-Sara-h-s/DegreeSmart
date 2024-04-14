package degreesmart;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import degreesmart.model.*;

public class AdministratorTest {
	private Administrator a;

	@BeforeEach
	public void setTestAdministrator() {
		a = new Administrator(
			UUID.randomUUID(), "username", "password", "email", "firstName", "lastName");
	}

	@AfterEach
	public void unsetTestAdministrator() {
		a = null;
	}

	@Test
	public void testRole_DefaultRole_ShouldEqualAdministrator() {
		assertEquals(Role.ADMINISTRATOR, a.role);
		assertEquals(Role.ADMINISTRATOR, a.getRole());
	}
}
