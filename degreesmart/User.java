package degreesmart;

public abstract class User {
  private String id;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String preferredName;
  private String emailAddress;

  public User(String username, String password, String email, String firstName, String lastName) {

  }

  public String getSystemId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {

  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {

  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {

  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName() {

  }

  public String getPreferredName() {
    return preferredName;
  }

  public void setPreferredName(String firstName) {

  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    
  }

}
