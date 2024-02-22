package degreesmart;

import java.util.ArrayList;

public class Student extends User {
  private String uscId;
  private Advisor advisor;
  private ArrayList<Parent> parents;
  private ArrayList<AdvisingNote> advisingNotes;
  private ArrayList<Scholarship> scholarships;
  private GraduationPlan graduationPlan;

  public Student(String firstName, String lastName, String username, String password, 
      String major, String uscId) {
    super(username, password, uscId, firstName, lastName);
  }

  public String getUscId() {
    return uscId;
  }

  public void setUscId(String uscId) {

  }

  public Advisor getAdvisor() {
    return advisor;
  }

  public void setAdvisor(Advisor advisor) {

  }

  public ArrayList<Parent> getParents() {
    return new ArrayList<Parent>();
  }

  public void addParent(String username) {

  }

  public void removeParent(Parent parent) {

  }

  public ArrayList<String> getAccessRequests() {
    return new ArrayList<String>();
  }

  public void addAccessRequest(String username) {

  }

  public void removeAccessRequest(String username) {

  }

  public ArrayList<CompletedCourse> getCompletedCourses() {
    return new ArrayList<CompletedCourse>();
  }

  public void addCompletedCourse(CompletedCourse course) {

  }

  public void removeCompletedCourse(CompletedCourse course) {

  }

  public ArrayList<AdvisingNote> getAdvisingNotes() {
    return new ArrayList<AdvisingNote>();
  }

  public void addAdvisingNote(Advisor advisor, String note) {

  }

  public void removeAdvisingNote(AdvisingNote note) {

  }

  public ArrayList<Scholarship> getScholarships() {
    return new ArrayList<Scholarship>();
  }

  public void addScholarship(Scholarship scholarship) {

  }

  public void removeScholarship(Scholarship scholarship) {

  }

  public GraduationPlan getGraduationPlan() {
    return graduationPlan;
  }
}
