package degreesmart;

import java.util.ArrayList;
import java.util.UUID;

public class Parent extends User {
  private ArrayList<Student> children;
  private ArrayList<Student> pendingAccessRequests;

  public Parent(UUID uuid, String username, String password, String email, String firstName, String lastName) {
    super(uuid, username, password, email, firstName, lastName);
    role = Role.PARENT;
    children = new ArrayList<Student>();
    pendingAccessRequests = new ArrayList<Student>();
  }

  public ArrayList<Student> getChildren() {
    return children;
  }

  public boolean addChild(Student student) {
    boolean added = false;

    if (student != null && !children.contains(student)) {
      added = children.add(student);
    }

    return added;
  }

  public boolean removeChild(Student student) {
      return children.remove(student);
  }

  public ArrayList<Student> getPendingAccessRequests() {
    return pendingAccessRequests;
  }

  public boolean addPendingAccessRequest(Student student) {
    boolean added = false;

    if (student != null && !pendingAccessRequests.contains(student)) {
      added = pendingAccessRequests.add(student);
    }

    return added;
  }

  public boolean removePendingAccessRequest(Student student) {
    return pendingAccessRequests.remove(student);
  }

  public String toString() {
    ArrayList<String> childList = new ArrayList<String>();
    for (Student child : children) {
      childList.add(child.getUsername());
    }

    ArrayList<String> requestList = new ArrayList<String>();
    for (Student request : pendingAccessRequests) {
      requestList.add(request.getUsername());
    }

    return super.toString() + "\n"
      + "          Children: " + childList + "\n"
      + "          Requests: " + requestList;
  }
}
