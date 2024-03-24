package degreesmart;

import java.util.UUID;

public class Guest extends User {
	public Guest(UUID uuid) {
		super(uuid, "GUEST", "GUEST", "GUEST", "GUEST", "GUEST");
	}

	public String toString() {
		return ""
		+ "            Role: " + role + "\n"
		+ "            UUID: " + getUuid() + "\n";
	}

	public boolean equals(Object object) {
		if (object == null || ! (object instanceof Guest)) {
			return false;
		}
		Guest guest = (Guest) object;

		return guest.getUuid().equals(getUuid());
	}
}
