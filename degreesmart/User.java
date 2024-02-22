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
    return "";
  }

  public String getUsername() {
    return "";
  }

  public void setUsername(String username) {

  }

  public String getPassword() {
    return "";
  }

  public void setPassword(String password) {

  }

  public String getFirstName() {
    return "";
  }

  public void setFirstName(String firstName) {

  }

  public String getLastName() {
    return "";
  }

  public void setLastName() {

  }

  public String getPreferredName() {
    return "";
  }

  public void setPreferredName(String firstName) {

  }

  public String getEmailAddress() {
    return "";
  }

  public void setEmailAddress(String emailAddress) {
    
  }

}
