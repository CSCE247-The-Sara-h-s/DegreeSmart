package degreesmart.model;

import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {
  private String uscId;
  private Advisor advisor;
  private ArrayList<Parent> parents;
  private ArrayList<Parent> accessRequests;
  private ArrayList<AdvisingNote> advisingNotes;
  private ArrayList<Scholarship> scholarships;
  private ArrayList<CompletedCourse> completedCourses;
  private ArrayList<PlannedCourse> plannedCourses;
  private GraduationPlan graduationPlan;

  public Student(UUID uuid, String username, String password, String email, String firstName, String lastName) {
    super(uuid, username, password, email, firstName, lastName);
    role = Role.STUDENT;

    parents = new ArrayList<Parent>();
    accessRequests = new ArrayList<Parent>();
    advisingNotes = new ArrayList<AdvisingNote>();
    scholarships = new ArrayList<Scholarship>();
    completedCourses = new ArrayList<CompletedCourse>();
    plannedCourses = new ArrayList<PlannedCourse>();
    graduationPlan = new GraduationPlan();
  }

  public String getUscId() {
    return uscId;
  }

  public void setUscId(String uscId) throws IllegalArgumentException {
    if (uscId == null) {
      throw new IllegalArgumentException("USC ID cannot be null");
    }

    if (uscId.equals("")) {
      throw new IllegalArgumentException("USC ID cannot be empty");
    }

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

  public ArrayList<PlannedCourse> getPlannedCourses() {
    return plannedCourses;
  }

  public boolean addPlannedCourse(Course course, Semester semester, int year) {
    return plannedCourses.add(new PlannedCourse(course, semester, year));
  }

  public ArrayList<AdvisingNote> getAdvisingNotes() {
    return advisingNotes;
  }

  public void addAdvisingNote(AdvisingNote advisingNote) {
    advisingNotes.add(advisingNote);
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

  public ArrayList<RequirementSet> getMajors() {
    ArrayList<RequirementSet> majors = new ArrayList<RequirementSet>();

    for (RequirementSet set : graduationPlan.getRequirementSets()) {
      if (set.getType() == RequirementType.MAJOR) {
        majors.add(set);
      }
    }

    return majors;
  }

  public ArrayList<RequirementSet> getMinors() {
    ArrayList<RequirementSet> minors = new ArrayList<RequirementSet>();

    for (RequirementSet set : graduationPlan.getRequirementSets()) {
      if (set.getType() == RequirementType.MINOR) {
        minors.add(set);
      }
    }

    return minors;
  }

  public ArrayList<RequirementSet> getDegreeRequirements() {
    return graduationPlan.getRequirementSets();
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
      double qualityPoints;

      switch (c.getGrade()) {
        case A:
          qualityPoints = 4.0;
          break;
        case B_PLUS:
          qualityPoints = 3.5;
          break;
        case B:
          qualityPoints = 3.0;
          break;
        case C_PLUS:
          qualityPoints = 2.5;
          break;
        case C:
          qualityPoints = 2.0;
          break;
        case D_PLUS:
          qualityPoints = 1.5;
          break;
        case D:
          qualityPoints = 1.0;
          break;
        default:
          qualityPoints = 0.0;
      }

      earnedHours += c.getCourse().getCreditHours() * qualityPoints;
    }

    return (earnedHours == 0.0)? 0.0 : earnedHours / getAttemptedHours();
  }

  public ArrayList<PlannedCourse> generateSemesterSchedule() {
    ArrayList<ArrayList<PlannedCourse>> plannedCourses = graduationPlan.getSelectedCourses();

    return new ArrayList<PlannedCourse>();
  }

  public String toString() {
    String advisorString = "";
    if (advisor != null) {
      advisorString = advisor.getUsername();
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

    String notes = "";
    if (advisingNotes.size() > 0) {
      notes = "\n\t\t   -  " + String.join("\n\t\t   -  ", advisingNoteList);
    }

    ArrayList<String> completedCourseList = new ArrayList<String>();
    for (CompletedCourse c : completedCourses) {
        completedCourseList.add(c.toString());
    }

    String transcript = "";
    if (completedCourses.size() > 0) {
      transcript = "\n\t\t   -  " + String.join("\n\t\t   -  ", completedCourseList);
    }

    ArrayList<String> plannedCourseList = new ArrayList<String>();
    for (PlannedCourse c : plannedCourses) {
        plannedCourseList.add(c.toString());
    }

    String plannedCourse = "";
    if (plannedCourses.size() > 0) {
      plannedCourse = "\n\t\t   -  " + String.join("\n\t\t   -  ", plannedCourseList);
    }

    ArrayList<String> requirements = new ArrayList<String>();
    for (RequirementSet req : graduationPlan.getRequirementSets()) {
      requirements.add(req.toString());
    }

    String reqs = "";
    if (requirements.size() > 0) {
      reqs = "\n -  " + String.join("\n -  ", requirements);
    }

    return super.toString() + "\n"
      + "            USC ID: " + uscId + "\n"
      + "           Advisor: " + advisorString + "\n"
      + "           Parents: " + parentList + "\n"
      + "   Access Requests: " + requesterList + "\n"
      + "    Advising Notes: " + notes + "\n"
      + "      Scholarships: " + " " + "\n"
      + "      Requirements: " + reqs + "\n"
      + "   Attempted Hours: " + getAttemptedHours() + "\n"
      + "       Overall GPA: " + String.format("%.4f", getGpa()) + "\n"
      + " Completed Courses: " + transcript + "\n"
      + "   Planned Courses: " + plannedCourse + "\n";
  }

  public boolean equals(Object object) {
    if (object == null || ! (object instanceof Student)) {
      return false;
    }
    Student student = (Student) object;

    return super.equals(object)
      && ((uscId == null && student.getUscId() == null)
        || (uscId != null && uscId.equals(student.getUscId())));
  }
}
