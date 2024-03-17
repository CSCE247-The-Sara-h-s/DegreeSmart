package degreesmart;

import java.util.ArrayList;

public class Application {
    private UserList userList;
    private CourseList courseList;
    private RequirementSetList requirementSetList;
    private User activeUser;
    private static Application application;

    private Application() {
        userList = UserList.getInstance();
        courseList = CourseList.getInstance();
        requirementSetList = RequirementSetList.getInstance();
        activeUser = userList.getGuest();
    }

    public static Application getInstance() {
        if (application == null) {
            application = new Application();
        }
        return application;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public User createAccount(Role role, String username, String password, String emailAddress, String firstName,
            String lastName) {
        boolean canCreate = !userLoggedIn() && role != null
            && validUsername(username) && validPassword(password) && validEmailAddress(emailAddress)
            && validName(firstName) && validName(lastName);

        if (canCreate) {
           userList.createUser(role, username, password, emailAddress, firstName, lastName);
        }

        return ((canCreate)? userList.getUser(username) : activeUser);
    }

    public boolean userLoggedIn() {
        return !userList.getGuest().equals(activeUser);
    }

    public User logIn(String username, String password) {
        if (!userLoggedIn()) {
            User user = userList.getUser(username);
            if (user != null && user.getPassword().equals(password)) {
                activeUser = user;
            }
        }
        return activeUser;
    }

    public User logOut() {
        activeUser = userList.getGuest();
        return activeUser;
    }

    private boolean validUsername(String username) {
        return username != null && userList.getUser(username) == null;
    }

    private boolean validPassword(String password) {
        return password != null;
    }

    private boolean validEmailAddress(String emailAddress) {
        return emailAddress != null;
    }

    private boolean validName(String name) {
        return name != null;
    }

    public boolean changeUsername(String username) {
        boolean canChange = userLoggedIn() && validUsername(username);

        if (canChange) {
            userList.changeUsername(activeUser, username);
        }

        return canChange;
    }

    public boolean changePassword(String password) {
        boolean canChange = userLoggedIn() && validUsername(password);

        if (canChange) {
            activeUser.setPassword(password);
        }

        return canChange;
    }

    public boolean setFirstName(String firstName) {
        boolean canChange = userLoggedIn() && validName(firstName);

        if (canChange) {
            activeUser.setFirstName(firstName);
        }

        return canChange;
    }

    public boolean setLastName(String lastName) {
        boolean canChange = userLoggedIn() && validName(lastName);

        if (canChange) {
            activeUser.setLastName(lastName);
        }

        return canChange;
    }

    public boolean setPreferredName(String preferredName) {
        boolean canChange = userLoggedIn() && validName(preferredName);

        if (canChange) {
            activeUser.setPreferredName(preferredName);
        }

        return canChange;
    }

    public boolean setEmailAddress(String emailAddress) {
        boolean canChange = userLoggedIn() && validEmailAddress(emailAddress);

        if (canChange) {
            activeUser.setEmailAddress(emailAddress);
        }

        return canChange;
    }

    public ArrayList<User> getUsers() {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            return userList.getUsers();
        }
        return null;
    }

    public ArrayList<User> getUsers(String firstName, String lastName) {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            return userList.getUsers(firstName, lastName);
        }
        return null;
    }

    public User getUser(String username) {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            return userList.getUser(username);
        }
        return null;
    }
    
    public ArrayList<Student> getStudents() {
        if (activeUser.getRole() == Role.ADMINISTRATOR || activeUser.getRole() == Role.ADVISOR) {
            return userList.getStudents();
        }
        return null;
    }

    public ArrayList<Student> getStudents(String firstName, String lastName) {
        if (activeUser.getRole() == Role.ADMINISTRATOR || activeUser.getRole() == Role.ADVISOR) {
            return userList.getStudents(firstName, lastName);
        }
        return null;
    }

    public Student getStudentByUsername(String username) {
        if (activeUser.getRole() == Role.ADMINISTRATOR || activeUser.getRole() == Role.ADVISOR) {
            return (Student)userList.getUser(username);
        }
        return null;
    }

    public Student getStudentByUscId(String uscId) {
        if (activeUser.getRole() == Role.ADMINISTRATOR || activeUser.getRole() == Role.ADVISOR) {
            return userList.getStudent(uscId);
        }
        return null;
    }

    public ArrayList<Advisor> getAdvisors() {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            return userList.getAdvisors();
        }
        return null;
    }

    public ArrayList<User> getUnapprovedAdvisors() {
        return userList.getUsers();
    }

    public ArrayList<User> getAssignedStudents() {
        return userList.getUsers();
    }

    public ArrayList<User> getUnassignedStudents() {
        return userList.getUsers();
    }

    public ArrayList<Course> getCourses() {
        return courseList.getCourses();
    }

    public void approveAdvisor(Advisor advisor) {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            advisor.setAdvisorRole();
        }
    }

    public void addAssignedStudent(Advisor advisor, Student student) {
        if (activeUser.getRole() == Role.ADMINISTRATOR
                || (activeUser.equals(advisor) && activeUser.getRole() == Role.ADVISOR)) {
            advisor.addAssignedStudent(student);
        }
    }

    public void removeAssignedStuent(Advisor advisor, Student student) {
        if (activeUser.getRole() == Role.ADMINISTRATOR
                || (activeUser.equals(advisor) && activeUser.getRole() == Role.ADVISOR)) {
           advisor.removeAssignedStudent(student);
        }
    }

    public Course getCourse(Subject subject, String number) {
        return courseList.getCourses().get(0);
    }

    public ArrayList<Course> getCourses(Subject subject) {
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
        if (activeUser instanceof Administrator) {
            Student activeStudent = (Student) activeUser;
            activeStudent.setUscId(null);
        }
    }

    public void getUscId() {

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
        if (activeUser instanceof Administrator) {
            Student activeStudent = (Student) activeUser;
            activeStudent.addScholarship(null);
        }
    }

    public void removeScholarship () {
        // confused here.. should Administrator have a removeScholarship method?
        if (activeUser instanceof Administrator) {
            Student activeStudent = (Student) activeUser;
            activeStudent.removeScholarship(null);
        }
    }

    public Course createCourse(Subject subject, String number) {
        if (activeUser instanceof Administrator) {
            return courseList.createCourse(subject, number);
        }
        return null;
    }

    public void deleteCourse(Course course) {
        if (activeUser instanceof Administrator) {
            courseList.deleteCourse(course);
        }
    }

    public void modifyCourse(Course modifiedCourse) {
        if (activeUser instanceof Administrator) {
            courseList.modifyCourse(modifiedCourse);
        }
    }

    public RequirementSet createRequirementSet(RequirementSetCategory category, String name) {
        if (activeUser instanceof Administrator) {
            return requirementSetList.createRequirementSet(category, name);
        }
        return null;
    }

    public void deleteRequirementSet(RequirementSet requirementSet) {
        if (activeUser instanceof Administrator) {
            requirementSetList.deleteRequirementSet(requirementSet);
        }
    }

    public void modifyRequirementSet(RequirementSet modifiedRequirementSet) {
        if (activeUser instanceof Administrator) {
             requirementSetList.modifyRequirementSet(modifiedRequirementSet);
        }
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
