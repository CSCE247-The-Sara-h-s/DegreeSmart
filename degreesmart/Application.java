package degreesmart;

import java.util.ArrayList;

public class Application {
    private UserList userList;
    private CourseList courseList;
    private RequirementSetList requirementSetList;
    private User activeUser;
    private static Application application;

    // TODO: check if activeUser is null 


    private Application() {
        // userList = UserList.getInstance();
        this.userList = userList;
        this.courseList = courseList;
        this.requirementSetList = requirementSetList;
        this.activeUser = activeUser;
    }

    public static Application getInstance() {
        if (application == null) {
            application = new Application();
        }
        return application;
    }

    public User createAccount(String username, String password, String email, String firstName, String lastName) {
        // create a new user
        // userList.createUser
        // return user

        return userList.getUsers().get(0);
    }

    public User logIn(String username, String password) {
        for (User user : userList.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                activeUser = user;
                return user;
            }
        }
        // Username not found/ Password is incorrect 
        return null;
    }


    public void logOut() {
        // set user to active user
        // set username and password to null
    }

    public void setUsername(User user, String username) {
        activeUser.setUsername(username);
    }

    public void setPassword(User user, String password) {
        activeUser.setPassword(password);
    }

    public void setName(User user, String firstName, String lastName) {

    }

    public void setPreferredName(User user, String firstName, String lastName) {

    }

    public void setEmailAddress(User user, String emailAddress) {

    }

    // add methods for anything a user should be able to do

    public ArrayList<User> getUsers() {
        return userList.getUsers();
    }

    public User getUser(String username) {
        return userList.getUsers().get(0);
    }
    
    public ArrayList<User> getUsers(String firstName, String lastName) {
        return userList.getUsers();
    }

    public ArrayList<User> getAdvisors() {
        return userList.getUsers();
    }

    public ArrayList<User> getStudents() {
        return userList.getUsers();
    }

    public ArrayList<User> getUnapprovedAdvisors() {
        return userList.getUsers();
    }

    public ArrayList<User> getUnassignedStudents() {
        return userList.getUsers();
    }

    public Student getStudent(Student uscId) {
        return (Student)userList.getUsers().get(0);
    }

    public ArrayList<User> getStudents(String firstName, String lastName) {
        return userList.getUsers();
    }

    public ArrayList<Course> getCourses() {
        return courseList.getCourses();
    }

    public Course getCourse(Subject subject, String number) {
        return courseList.getCourses().get(0);
    }

    public ArrayList<Course> getCourses(Subject subject) {
        return courseList.getCourses();
    }

    public ArrayList<Course> getCourses(CoreCategory category) {
        return courseList.getCourses();
    }

    public ArrayList<RequirementSet> getRequirementSets() {
        return requirementSetList.getRequirementSets();
    }

    public ArrayList<RequirementSet> getRequirementSets(RequirementSetCategory category) {
        return requirementSetList.getRequirementSets();
    }
    
    public RequirementSet getRequirementSet(String name, RequirementSetCategory category) {
        return requirementSetList.getRequirementSets().get(0);
    }

    public RequirementSet getCarolinaCore() {
        return requirementSetList.getRequirementSets().get(0);
    }

    public ArrayList<RequirementSet> getMajors() {
        return requirementSetList.getRequirementSets();
    }

    public ArrayList<RequirementSet> getApplicationAreas() {
        return requirementSetList.getRequirementSets();
    }

    public void approveAdvisor(Advisor advisor) {
        if (activeUser instanceof Administrator) {
            Administrator activeAdministrator = (Administrator) activeUser;
            activeAdministrator.approveAdvisor(advisor);
        }
    }

    public void addAssignedStudent(Advisor advisor, Student student) {
        if (activeUser instanceof Advisor){
            Advisor activeAdvisor = (Advisor) activeUser;
            activeAdvisor.addAssignedStudent(student);
        }
    }

    public void removeAssignedStuent(Advisor advisor, Student student) {
        if (activeUser instanceof Advisor) {
            Advisor activeAdvisor = (Advisor) activeUser;
            activeAdvisor.removeAssignedStudent(student);
        }
    }

    // check user role from here down (admin)

    public ArrayList<Scholarship> getIneligibleScholarships(Student student) {
        return new ArrayList<Scholarship>();
    }

    public void addAdvisingNote(Student student, String message) {

    }
    
    public boolean removeAdvisingNote(Student student, AdvisingNote note) {
        return true;
    }
    
    public void addCompletedCourse(Student student, Course course, Grade grade, Semester semester, int year) {

    }

    public void removeCompletedCourse(Student student, CompletedCourse course) {

    }

    public ArrayList<CompletedCourse> getCompletedCourses(Student student) {
        return new ArrayList<CompletedCourse>();
    }

    public double getGpa(Student student) {
        return 0.0;
    }

    public double getCompletedCreditHours(Student student) {
        return 0.0;
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
        return 0.0;
    }

    public double getCompletedCreditHours() {
        return 0.0;
    }

    public ArrayList<String> getAccessRequests() {
        return new ArrayList<String>();
    }

    public void approveAccessRequest() {

    }

    public void ignoreAccessRequest() {
        
    }

    public void removeParent() {

    }

    public ArrayList<AdvisingNote> getAdvisingNotes() {
        return new ArrayList<AdvisingNote>();
    }

    public Advisor getAdvisor() {
        return (Advisor)userList.getUsers().get(0);
    }

    public Parent getParent() {
        return (Parent)userList.getUsers().get(0);
    }

    public void addScholarship () {

    }

    public void removeScholarship () {

    }

    public void createCourse(String subject, String name, ArrayList<Semester> semestersOffered, double creditHours) {
        if (activeUser instanceof Administrator) {
            Administrator activeAdministrator = (Administrator) activeUser;
            activeAdministrator.createCourse(null);
        }
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

    // Ignore everything below this line
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

    public ArrayList<Course> getCoursesThatApplyToRequirement() {
        return courseList.getCourses();
    }

    public ArrayList<PlannedCourse> getSemesterSchedule() {
        return new ArrayList<PlannedCourse>();
    }

    public void moveCourseToDifferentSemester() {

    }
}
