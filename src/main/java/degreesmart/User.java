package degreesmart;

import java.util.Objects;
import java.util.UUID;

public abstract class User {
  private UUID uuid;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String preferredName;
  private String emailAddress;
  protected Role role;

  public User(UUID uuid, String username, String password, String emailAddress,
      String firstName, String lastName) {
    setUuid(uuid);
    setUsername(username);
    setPassword(password);
    setEmailAddress(emailAddress);
    setFirstName(firstName);
    setLastName(lastName);

    role = Role.GUEST;
  }

  private void setUuid(UUID uuid) throws IllegalArgumentException {
    if (uuid == null) {
      throw new IllegalArgumentException("UUID cannot be null");
    }

    this.uuid = uuid;
  }

  public UUID getUuid() {
    return uuid;
  }

  public Role getRole() {
    return role;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) throws IllegalArgumentException {
    if (username == null) {
      throw new IllegalArgumentException("username cannot be null");
    }

    if (username.equals("")) {
      throw new IllegalArgumentException("username cannot be empty");
    }

    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) throws IllegalArgumentException {
    if (password == null) {
      throw new IllegalArgumentException("password cannot be null");
    }

    if (password.equals("")) {
      throw new IllegalArgumentException("password cannot be empty");
    }

    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) throws IllegalArgumentException {
    if (firstName == null) {
      throw new IllegalArgumentException("first name cannot be null");
    }

    if (firstName.equals("")) {
      throw new IllegalArgumentException("first name cannot be empty");
    }

    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) throws IllegalArgumentException {
    if (lastName == null) {
      throw new IllegalArgumentException("last name cannot be null");
    }

    if (lastName.equals("")) {
      throw new IllegalArgumentException("last name cannot be empty");
    }

    this.lastName = lastName;
  }

  public String getPreferredFirstName() {
    if (preferredName == null || preferredName.isEmpty()) {
      return firstName;
    } else {
      return preferredName;
    }
  }

  public String getPreferredName() {
    return preferredName;
  }

  public void setPreferredName(String preferredName) {
    if (preferredName == null) {
      preferredName = "";
    }

    this.preferredName = preferredName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) throws IllegalArgumentException {
    if (emailAddress == null) {
      throw new IllegalArgumentException("email address cannot be null");
    }

    if (emailAddress.equals("")) {
      throw new IllegalArgumentException("email address cannot be empty");
    }

    this.emailAddress = emailAddress;
  }

  public boolean equals(Object object) {
    if (object == null || ! (object instanceof User)) {
      return false;
    }
    User user = (User) object;

    return uuid.equals(user.getUuid()) && username.equals(user.getUsername());
  }

  public String toString() {
    return ""
      + "              Role: " + role + "\n"
      + "              UUID: " + uuid + "\n"
      + "          Username: " + username + "\n"
      + "          Password: " + password + "\n"
      + "              Name: " + firstName + " "
                     + ((getPreferredName() != preferredName)? "" : "(" + preferredName + ") ")
                     + lastName + "\n"
      + "             Email: " + emailAddress;
  }
}
