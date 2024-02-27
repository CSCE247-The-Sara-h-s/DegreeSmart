package degreesmart;

import java.util.ArrayList;

public class Advisor extends User {
  private boolean approved;
  private ArrayList<Student> assignedStudents;

  public Advisor(String username, String password, String email, String firstName, String lastName) {
    super(username, password, email, firstName, lastName);
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

  public void addAdvisingNote(Student student, String message) {
    student.addAdvisingNote(this, message);
  }

  public boolean removeAdvisingNote(Student student, AdvisingNote note) {
    return student.removeAdvisingNote(note);
  }

  public boolean addCompletedCourse(Student student, CompletedCourse course) {
    return student.addCompletedCourse(course);
  }

  public boolean removeCompletedCourse(Student student, CompletedCourse course) {
    return student.removeCompletedCourse(course);
  }
}
