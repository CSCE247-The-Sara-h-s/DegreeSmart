package degreesmart;

import java.util.ArrayList;
import java.util.UUID;

public class Advisor extends User {
  private ArrayList<Student> assignedStudents;

  public Advisor(UUID uuid, String username, String password, String email, String firstName, String lastName) {
    super(uuid, username, password, email, firstName, lastName);
    unsetAdvisorRole();
    assignedStudents = new ArrayList<Student>();
  }

  public boolean getApproved() {
    return role == Role.ADVISOR;
  }

  public void unsetAdvisorRole() {
    role = Role.UNAPPROVED_ADVISOR;
  }

  public void setAdvisorRole() {
    role = Role.ADVISOR;
  }

  public ArrayList<Student> getAssignedStudents() {
    return assignedStudents;
  }

  public boolean addAssignedStudent(Student student) {
    boolean added = false;

    if (student != null && !assignedStudents.contains(student)) {
      added = assignedStudents.add(student);
    }
    
    return added;
  }

  public boolean removeAssignedStudent(Student student) {
    return assignedStudents.remove(student);
  }

  public String toString() {
    ArrayList<String> studentList = new ArrayList<String>();
    for (Student student : assignedStudents) {
      studentList.add(student.getUsername());
    }

    return super.toString() + "\n"
      + "          Students: " + studentList;
  }
}
