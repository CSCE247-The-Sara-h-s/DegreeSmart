package degreesmart;

import java.util.ArrayList;
import java.util.UUID;

public class Parent extends User {
  private ArrayList<Student> children;
  private ArrayList<Student> pendingAccessRequests;

  public Parent(UUID uuid, String username, String password, String email, String firstName, String lastName) {
    super(uuid, username, password, email, firstName, lastName);
    children = new ArrayList<Student>();
    pendingAccessRequests = new ArrayList<Student>();
  }

  public ArrayList<Student> getChildren() {
    return children;
  }

  public boolean addChild(Student student) {
    if (!children.contains(student)) {
      return children.add(student);
    } else {
      return false;
    }
  }

  public boolean removeChild(Student student) {
    return children.remove(student);
  }

  public ArrayList<Student> getPendingAccessRequests() {
    return pendingAccessRequests;
  }

  public boolean addPendingAccessRequest(Student student) {
    if (!pendingAccessRequests.contains(student)) {
      return pendingAccessRequests.add(student);
    } else {
      return false;
    }
  }

  public boolean removePendingAccessRequest(Student student) {
    return pendingAccessRequests.remove(student);
  }
}
