package degreesmart;

import java.util.ArrayList;
import java.util.UUID;

public class Advisor extends User {
  private boolean approved;
  private ArrayList<Student> assignedStudents;

  public Advisor(UUID uuid, String username, String password, String email, String firstName, String lastName) {
    super(uuid, username, password, email, firstName, lastName);
    assignedStudents = new ArrayList<Student>();
    approved = false;
  }

  public boolean getApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
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

    return ""
      + "            Role: Advisor\n"
      + super.toString() + "\n"
      + "        Approved: " + approved + "\n"
      + "        Students: " + studentList;
  }
}
