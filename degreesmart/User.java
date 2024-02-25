package degreesmart;

public abstract class User {
  private String id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String preferredName;
  private String emailAddress;

  public User(String username, String password, String emailAddress, String firstName, String lastName) {
    this.username = username;
    this.password = password;
    this.emailAddress = emailAddress;
    this.firstName= firstName;
    this.lastName = lastName;
  }

  public String getSystemId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName() {
    this.lastName = lastName;
  }

  public String getPreferredName() {
    return preferredName;
  }

  public void setPreferredName(String firstName) {
    this.firstName = preferredName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

}