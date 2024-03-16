package degreesmart;

import java.util.UUID;

public class Guest extends User {
	public Guest(UUID uuid, String username) {
		super(uuid, "", "", "", "", "");
	}

	public boolean equals(Object object) {
		if (object == null || ! (object instanceof Guest)) {
			return false;
		}
		Guest guest = (Guest) object;

		return guest.getUuid().equals(getUuid());
	}
}
