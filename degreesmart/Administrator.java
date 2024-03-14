package degreesmart;

import java.util.UUID;

public class Administrator extends User {
  public Administrator(UUID uuid, String username, String password, String email, String firstName, 
      String lastName) {
    super(uuid, username, password, email, firstName, lastName);
  }

  public String toString() {
    return ""
      + "            Role: Administrator\n"
      + super.toString();
  }
}
