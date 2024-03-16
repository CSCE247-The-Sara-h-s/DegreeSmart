package degreesmart;

import java.util.ArrayList;
import java.util.UUID;

public class Advisor extends User {
  private ArrayList<Student> assignedStudents;

  public Advisor(UUID uuid, String username, String password, String email, String firstName, String lastName) {
    super(uuid, username, password, email, firstName, lastName);
    role = Role.USER;
    assignedStudents = new ArrayList<Student>();
  }

  public void unsetAdvisorRole() {
    role = Role.USER;
  }

  public void setAdvisorRole() {
    role = Role.ADVISOR;
  }

  public ArrayList<Student> getAssignedStudents() {
    return assignedStudents;
  }

  public boolean addAssignedStudent(Student student) {
    if (!assignedStudents.contains(student)) {
      return assignedStudents.add(student);
    } else {
      return false;
    }
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
      + "        Students: " + studentList;
  }
}
