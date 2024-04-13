package degreesmart;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import degreesmart.Guest;

public class GuestTest {

    @Test
    public void testToString() {
        UUID uuid = UUID.randomUUID();
        Guest guest = new Guest(uuid);
        String expected = "            Role: GUEST\n" +
                          "            UUID: " + uuid + "\n";
        assertEquals(expected, guest.toString());
    }

    @Test
    public void testEqualsSameObject() {
        UUID uuid = UUID.randomUUID();
        Guest guest = new Guest(uuid);
        assertTrue(guest.equals(guest));
    }

    @Test
    public void testEqualsDifferentClass() {
        UUID uuid = UUID.randomUUID();
        Guest guest = new Guest(uuid);
        assertFalse(guest.equals("not a Guest object"));
    }

    @Test
    public void testEqualsEqualGuests() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.fromString(uuid1.toString());
        Guest guest1 = new Guest(uuid1);
        Guest guest2 = new Guest(uuid2);
        assertTrue(guest1.equals(guest2));
    }

    @Test
    public void testEqualsUnequalGuests() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        Guest guest1 = new Guest(uuid1);
        Guest guest2 = new Guest(uuid2);
        assertFalse(guest1.equals(guest2));
    }
}
