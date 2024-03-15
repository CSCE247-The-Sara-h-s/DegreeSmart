package degreesmart;

import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
  private final double QUALITY_POINT_STEP = 0.5;
  private String uscId;
  private Advisor advisor;
  private ArrayList<Parent> parents;
  private ArrayList<Parent> accessRequests;
  private ArrayList<AdvisingNote> advisingNotes;
  private ArrayList<Scholarship> scholarships;
  private ArrayList<CompletedCourse> completedCourses;
  private GraduationPlan graduationPlan;

  public Student(
      UUID uuid, String username, String password, String email, String firstName, String lastName, 
      String uscId) {
    super(uuid, username, password, email, firstName, lastName);
    parents = new ArrayList<Parent>();
    accessRequests = new ArrayList<Parent>();
    advisingNotes = new ArrayList<AdvisingNote>();
    scholarships = new ArrayList<Scholarship>();
    completedCourses = new ArrayList<CompletedCourse>();
    graduationPlan = new GraduationPlan();

    this.uscId = uscId;
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
    return completedCourses;
  }

  public boolean addCompletedCourse(Course course, Grade grade, Semester semester, int year) {
    return completedCourses.add(new CompletedCourse(course, grade, semester, year));
  }

  public boolean removeCompletedCourse(CompletedCourse completedCourse) {
    return completedCourses.remove(completedCourse);
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

  public double getAttemptedHours() {
    double attemptedHours = 0.0;
    for (CompletedCourse c : completedCourses) {
        attemptedHours += c.getCourse().getCreditHours();
    }
    return attemptedHours;
  }

  public double getGpa() {
    double earnedHours = 0.0;

    for (CompletedCourse c : completedCourses) {
      double qualityPoints = 0.0;

      switch (c.getGrade()) {
        case A:
          qualityPoints += QUALITY_POINT_STEP;
        case B_PLUS:
          qualityPoints += QUALITY_POINT_STEP;
        case B:
          qualityPoints += QUALITY_POINT_STEP;
        case C_PLUS:
          qualityPoints += QUALITY_POINT_STEP;
        case C:
          qualityPoints += QUALITY_POINT_STEP;
        case D_PLUS:
          qualityPoints += QUALITY_POINT_STEP;
        case D:
          qualityPoints += QUALITY_POINT_STEP;
          qualityPoints += QUALITY_POINT_STEP;
      }

      earnedHours += c.getCourse().getCreditHours() * qualityPoints;
    }

    if (earnedHours == 0.0) {
      return 0.0;
    } else {
      return earnedHours / getAttemptedHours();
    }
  }

  public ArrayList<PlannedCourse> generateSemesterSchedule() {
    ArrayList<ArrayList<PlannedCourse>> plannedCourses = graduationPlan.getSelectedCourses();

    return new ArrayList<PlannedCourse>();
  }

  public String toString() {
    String advisorString = "";
    if (advisor != null) {
      advisorString = advisor.getLastName() + ", " + advisor.getPreferredName();
    }

    ArrayList<String> parentList = new ArrayList<String>();
    for (Parent parent : parents) {
      parentList.add(parent.getUsername());
    }

    ArrayList<String> requesterList = new ArrayList<String>();
    for (Parent requester : accessRequests) {
      requesterList.add(requester.getUsername());
    }

    ArrayList<String> advisingNoteList = new ArrayList<String>();
    for (AdvisingNote advisingNote : advisingNotes) {
      advisingNoteList.add(advisingNote.toString());
    }

    ArrayList<String> completedCourseList = new ArrayList<String>();
    for (CompletedCourse c : completedCourses) {
        completedCourseList.add(c.toString());
    }

    String transcript = "";
    if (completedCourses.size() > 0) {
      transcript = "\n   -  " + String.join("\n   -  ", completedCourseList) + "\n";
    }

    return ""
      + "            Role: Student\n"
      + super.toString() + "\n"
      + "          USC ID: " + uscId + "\n"
      + "         Advisor: " + advisorString + "\n"
      + "         Parents: " + parentList + "\n"
      + " Access Requests: " + requesterList + "\n"
      + "  Advising Notes: " + String.join("\n\t", advisingNoteList) + "\n"
      + "    Scholarships: " + "<TODO>" + "\n"
      + " Attempted Hours: " + getAttemptedHours() + "\n"
            + "     Overall GPA: " + String.format("%.4f", getGpa()) + "\n"
            + "      Transcript: " + transcript + "\n"
      + " Graduation Plan: " + "<TODO>";
  }
}
