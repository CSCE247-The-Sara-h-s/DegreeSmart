package degreesmart;

import java.util.ArrayList;

public class Application {
    private UserList userlist;
    private CourseList CourseList;
    private RequirementSetList requirementSetList;
    private User activeUser;

    public User createAccount(String username, String password, String email, String firstName, String lastName) {

    }

    public User logIn(String username, String password) {

    }

    public void logOut() {

    }

    public void setUsername(User user, String username) {

    }

    public void setPassword(User user, String password) {

    }

    public void setName(User user, String firstName, String lastName) {

    }

    public void setEmailAddress(User user, String emailAddress) {

    }

    public ArrayList<User> getUsers() {
        
    }

    public User getUser(String username) {

    }
    
    public ArrayList<User> getUsers(String firstName, String lastName) {

    }

    public ArrayList<Advisor> getAdvisors() {

    }

    public ArrayList<Student> getStudents() {

    }

    public ArrayList<Advisor> getUnapprovedAdvisors() {

    }

    public ArrayList<Student> getUnassignedStudents() {

    }

    public Student getStudent(Student uscId) {

    }

    public ArrayList<Student> getStudents(String firstName, String lastName) {

    }

    public ArrayList<Course> getCourses() {

    }

    public Course getCourse(Subject subject, String number) {

    }

    public ArrayList<Course> getCourses(Subject subject) {

    }

    public ArrayList<Course> getCourses(CoreCategory category) {

    }

    public ArrayList<RequirementSet> getRequirementSets() {

    }

    public ArrayList<RequirementSet> getRequirementSets(RequirementSetCategory category) {

    }
    
    public RequirementSet getRequirementSets(String name, RequirementSetCategory category) {
        
    }

    public RequirementSet getCarolinaCore() {

    }

    public ArrayList<RequirementSet> getMajors() {

    }

    public ArrayList<RequirementSet> getApplicationAreas() {

    }

    public void approveAdvisor(Advisor advisor) {

    }

    public void addAssignedStudent(Advisor advisor, Student student) {

    }

    public void removeAssignedStuent(Advisor advisor, Student student) {

    }

    public void createCourse(String subject, String name, ArrayList<Semester> semestersOffered, double creditHours) {

    }

    public void deleteCourse(Course course) {

    }

    public void modifyCourse(Course modifiedCourse) {

    }

    public void createRequirementSet(RequirementSet requirementSet) {

    }

    public void deleteRequirementSet(RequirementSet requirementSet) {

    }

    public void modifyRequirementSet(RequirementSet modifiedSet) {

    }

    // TODO: FIGURE OUT RETURN TYPE
    public ArrayList<String> getScholarshipStatus(Student student) {

    }

    public void addAdvisingNote(Student student, String note) {

    }

    public void addCompletedCourse(Student student, Course course, Grade grade, Semester semester, int year) {

    }

    public void removeCompletedCourse(Student student, CompletedCourse course) {

    }

    public ArrayList<CompletedCourse> getCompletedCourses(Student student) {

    }

    public double getGpa(Student student) {

    }

    public double getCompletedCreditHours(Student student) {

    }

    public void sendAccessRequest(String username) {

    }

    public void approveAccessRequest(String username) {

    }

    public void ignoreAcessRequest(String username) {

    }

    public void setUscId() {

    }

    public double getGpa() {

    }

    public double getCompletedCreditHours() {

    }

    public ArrayList<String> getAccessRequests() {

    }

    public void approveAccessRequest() {

    }

    public void ignoreAccessRequest() {
        
    }

    public void removeParent() {

    }

    public String getAdvisingNotes() {
        
    }

    public Advisor getAdvisor() {

    }

    public Parent getParents() {

    }

    public void addScholarship () {

    }

    public void removeScholarship () {

    }

    public ArrayList<String> getScholarshipStatus() {

    }

    public void selectMajor() {

    }

    public void selectHypotheticalMajor() {

    }

    public void selectApplicationArea() {

    }

    public void selectCourseForRequirement() {

    }

    public void removeSelectedCourseFromRequirement() {
        
    }

    public Course getCoursesThatApplyToRequirement() {

    }

    public ArrayList<PlannedCourse> getSemesterSchedule() {

    }

    public void moveCourseToDifferentSemester() {

    }

}

