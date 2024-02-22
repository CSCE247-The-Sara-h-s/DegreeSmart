package degreesmart;

import java.util.ArrayList;

public class Advisor extends User {
  private boolean approved;
  private ArrayList<Student> assignedStudents;

  public Advisor(String username, String password, String email, String firstName, String lastName) {
    super(username, password, email, firstName, lastName);
  }

  public boolean getApprived() {
    return approved;
  }

  public void setApproved() {

  }

  public ArrayList<Student> getAssignedStudents() {
    return new ArrayList<Student>();
  }

  public void addAssignedStudent(Student student) {

  }

  public void removeAssignedStudent(Student student) {

  }

  public void addAdvisingNote(Student student, String note) {

  }

  public void removeAdvisingNote(Student student, String note) {

  }

  public void addCompletedCourse(Student student, CompletedCourse course) {

  }

  public void removeCompletedCourse(Student student, CompletedCourse course) {

  }
}
