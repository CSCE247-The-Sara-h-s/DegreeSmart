package degreesmart;

import java.util.UUID;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import degreesmart.model.*;

public class AdvisingNoteTest {
	private Advisor a;
	private AdvisingNote n;

	@BeforeEach
	public void setTestObjects() {
		a = new Advisor(
			UUID.randomUUID(), "username", "password", "email", "firstName", "lastName");
		n = new AdvisingNote(a, "note", "17 March 2024 02:37:22 EDT");
	}

	@AfterEach
	public void unsetTestObjects() {
		a = null;
		n = null;
	}

	@Test
	public void testCreateNote_ValidData_ShouldNotThrowException() {
		assertDoesNotThrow(() -> new AdvisingNote(a, "note", "17 March 2024 02:37:22 EDT"));
	}

	@Test
	public void testCreateNote_ImplicitTime_ShouldNotBeNull() {
		assertNotNull((new AdvisingNote(a, "note")).getTime());
	}

	@Test
	public void testToString_DefaultData_ShouldNotThrowException() {
		assertDoesNotThrow(() -> n.toString());
	}

	@Test
	public void testGetTimeString_DefaultData_ShouldNotThrowException() {
		assertDoesNotThrow(() -> n.getTimeString());
	}

	@Test
	public void testCreateNote_NullAuthor_ShouldNotThrowException() {
		assertDoesNotThrow(() -> new AdvisingNote(null, "note", "17 March 2024 02:37:22 EDT"));
	}

	@Test
	public void testCreateNote_InvalidDate_ShouldThrowException() {
		assertThrows(IllegalArgumentException.class,
			() -> new AdvisingNote(a, "note", "x17 March 2024 02:37:22 EDT"));
	}

	@Test
	public void testCreateNote_NullDate_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new AdvisingNote(a, "note", null));
		assertEquals("time cannot be null", e.getMessage());
	}

	@Test
	public void testCreateNote_NullNote_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new AdvisingNote(a, null, "17 March 2024 02:37:22 EDT"));
		assertEquals("note cannot be null", e.getMessage());
	}

	@Test
	public void testCreateNote_EmptyNote_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> new AdvisingNote(a, "", "17 March 2024 02:37:22 EDT"));
		assertEquals("note cannot be empty", e.getMessage());
	}

	@Test
	public void testSetAuthor_Null_ShouldNotThrowException() {
		assertDoesNotThrow(() -> n.setAuthor(null));
	}

	@Test
	public void testSetNote_Null_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> n.setNote(null));
		assertEquals("note cannot be null", e.getMessage());
	}

	@Test
	public void testSetNote_Empty_ShouldThrowException() {
		Exception e = assertThrows(IllegalArgumentException.class,
			() -> n.setNote(""));
		assertEquals("note cannot be empty", e.getMessage());
	}

	@Test
	public void testEquals_NullObject_ShouldEqualFalse() {
		assertEquals(false, n.equals(null));
	}

	@Test
	public void testEquals_NotEqual_ShouldEqualFalse() {
		AdvisingNote n2 = new AdvisingNote(a, "different note");
		assertEquals(false, n.equals(n2));
	}

	@Test
	public void testEquals_Equal_ShouldEqualTrue() {
		AdvisingNote n2 = new AdvisingNote(n.getAuthor(), n.getNote(), n.getTimeString());
		assertEquals(true, n.equals(n2));	
	}

	@Test
	public void testEquals_BothNullAuthors_ShouldEqualTrue() {
		AdvisingNote n2 = new AdvisingNote(null, n.getNote(), n.getTimeString());
		n.setAuthor(null);
		assertEquals(true, n.equals(n2));
	}

	@Test
	public void testEquals_FirstNullAuthor_ShouldEqualFalse() {
		AdvisingNote n2 = new AdvisingNote(n.getAuthor(), n.getNote(), n.getTimeString());
		n.setAuthor(null);
		assertEquals(false, n.equals(n2));
	}

	@Test
	public void testEquals_SecondNullAuthor_ShouldEqualFalse() {
		AdvisingNote n2 = new AdvisingNote(null, n.getNote(), n.getTimeString());
		assertEquals(false, n.equals(n2));
	}
}
