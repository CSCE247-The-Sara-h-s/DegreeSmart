package degreesmart;

import java.util.ArrayList;

public class Parent extends User {
  private ArrayList<Student> children;
  private ArrayList<String> pendingAccessRequests;

  public Parent(String username, String password, String email, String firstName, String lastName) {
    super(username, password, email, firstName, lastName);
  }

  public ArrayList<Student> getChildren() {
    return new ArrayList<Student>();
  }

  public void addChild(Student student) {

  }

  public void removeChild(Student student) {

  }

  public ArrayList<String> getPendingAccessRequests() {
    return new ArrayList<String>();
  }

  public void addPendingAccessRequest() {

  }

  public void removePendingAccessRequest(String username) {

  }
}
