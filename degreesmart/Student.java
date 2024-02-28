package degreesmart;

import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
  private String uscId;
  private Advisor advisor;
  private ArrayList<Parent> parents;
  private ArrayList<Parent> accessRequests;
  private ArrayList<AdvisingNote> advisingNotes;
  private ArrayList<Scholarship> scholarships;
  private Transcript transcript;
  private GraduationPlan graduationPlan;

  public Student(
      UUID uuid, String username, String password, String email, String firstName, String lastName, 
      String uscId) {
    super(uuid, username, password, email, firstName, lastName);
    parents = new ArrayList<Parent>();
    advisingNotes = new ArrayList<AdvisingNote>();
    scholarships = new ArrayList<Scholarship>();
    graduationPlan = new GraduationPlan();
  }

  public String getUscId() {
    return uscId;
  }

  public void setUscId(String uscId) {
    this.uscId = uscId;
  }

  public Advisor getAdvisor() {
    return advisor;
  }

  public void setAdvisor(Advisor advisor) {
    this.advisor = advisor;
  }

  public ArrayList<Parent> getParents() {
    return parents;
  }

  public boolean addParent(Parent parent) {
    if (!parents.contains(parent)) {
      return parents.add(parent);
    } else {
      return false;
    }
  }

  public boolean removeParent(Parent parent) {
    return parents.remove(parent);
  }

  public ArrayList<Parent> getAccessRequests() {
    return accessRequests;
  }

  public boolean addAccessRequest(Parent parent) {
    if (!accessRequests.contains(parent)) {
      return accessRequests.add(parent);
    } else {
      return false;
    }
  }

  public boolean removeAccessRequest(Parent parent) {
    return accessRequests.remove(parent);
  }

  public ArrayList<CompletedCourse> getCompletedCourses() {
    return transcript.getCompletedCourses();
  }

  public boolean addCompletedCourse(CompletedCourse course) {
    return transcript.addCompletedCourse(course);
  }

  public boolean removeCompletedCourse(CompletedCourse course) {
    return transcript.removeCompletedCourse(course);
  }

  public ArrayList<AdvisingNote> getAdvisingNotes() {
    return advisingNotes;
  }

  public void addAdvisingNote(Advisor advisor, String note) {
    advisingNotes.add(new AdvisingNote(advisor, note));
  }

  public boolean removeAdvisingNote(AdvisingNote note) {
    return advisingNotes.remove(note);
  }

  public ArrayList<Scholarship> getScholarships() {
    return scholarships;
  }

  public boolean addScholarship(Scholarship scholarship) {
    return scholarships.add(scholarship);
  }

  public boolean removeScholarship(Scholarship scholarship) {
    return scholarships.remove(scholarship);
  }

  public GraduationPlan getGraduationPlan() {
    return graduationPlan;
  }
}
