package degreesmart.model;

import java.util.ArrayList;

public class Application {
    private UserList userList;
    private CourseList courseList;
    private RequirementSetList requirementSetList;
    private User activeUser;
    private static Application application;
    private Student selectedStudent;

    protected Application() {
        courseList = CourseList.getInstance();
        requirementSetList = RequirementSetList.getInstance();
        userList = UserList.getInstance();
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

    public String createAccount(Role role, String username, String password, String emailAddress, String firstName,
            String lastName, String uscId) {
        if (userLoggedIn()) {
            throw new IllegalStateException("Cannot create new account while a user is logged in");
        }

        try {
            userList.createUser(role, username, password, emailAddress, firstName, lastName);
            if (userList.getUser(username) != null && userList.getUser(username).getRole() == Role.STUDENT) {
                UserList.getInstance().changeUscId((Student) userList.getUser(username), uscId);
            }
            activeUser = (userList.getUser(username) == null)? activeUser : userList.getUser(username);
        } catch (Exception e) {
            try {
                userList.deleteUser(userList.getUser(username));
            } catch (Exception e2) {
            }

            return "Failed to create account: " + e.getMessage();
        }

        if (Application.getInstance().getActiveUser() == null) {
            return "Failed to create account: Unknown error";
        }

        return "";
    }

    public String createAccount(Role role, String username, String password, String emailAddress, String firstName,
            String lastName) {
        return createAccount(role, username, password, emailAddress, firstName, lastName, "");
    }

    public boolean deleteAccount() {
        boolean deleted = false;
        if (userLoggedIn()) {
            deleted = userList.deleteUser(Application.getInstance().getActiveUser());
            logOut();
        }
        return deleted;
    }

    public boolean userLoggedIn() {
        return !userList.getGuest().equals(Application.getInstance().getActiveUser());
    }

    public String logIn(String username, String password) {
        if (userLoggedIn()) {
            throw new IllegalStateException("User is already logged in");
        }

        if (username.length() == 0) {
            return "Login failed: Username is required";
        } else if (password.length() == 0) {
            return "Login failed: Password is required";
        }

        User user = userList.getUser(username);
        if (user == null) {
            return "Login failed: Invalid credentials\n"
            + "-----------------\n[DEV] username '" + username +"' does not exist\n-----------------";
        } else if (!user.getPassword().equals(password)) {
            return "Login failed: Invalid credentials\n"
             + "-----------------\n[DEV] " + username + "'s password is '" + user.getPassword() + "'\n-----------------";
        }

        activeUser = user;
        return "";
    }

    public void logOut() {
        activeUser = userList.getGuest();
        userList.saveUsers();
    }

    public String getFirstName() {
        if (userLoggedIn()) {
            return Application.getInstance().getActiveUser().getFirstName();
        }
        return "";
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
            userList.changeUsername(Application.getInstance().getActiveUser(), username);
        }

        return canChange;
    }

    public boolean changePassword(String password) {
        boolean canChange = userLoggedIn() && validUsername(password);

        if (canChange) {
            Application.getInstance().getActiveUser().setPassword(password);
        }

        return canChange;
    }

    public boolean changeFirstName(String firstName) {
        boolean canChange = userLoggedIn() && validName(firstName);

        if (canChange) {
            Application.getInstance().getActiveUser().setFirstName(firstName);
        }

        return canChange;
    }

    public boolean changeLastName(String lastName) {
        boolean canChange = userLoggedIn() && validName(lastName);

        if (canChange) {
            Application.getInstance().getActiveUser().setLastName(lastName);
        }

        return canChange;
    }

    public boolean changePreferredName(String preferredName) {
        boolean canChange = userLoggedIn() && validName(preferredName);

        if (canChange) {
            Application.getInstance().getActiveUser().setPreferredName(preferredName);
        }

        return canChange;
    }

    public boolean changeEmailAddress(String emailAddress) {
        boolean canChange = userLoggedIn() && validEmailAddress(emailAddress);

        if (canChange) {
            Application.getInstance().getActiveUser().setEmailAddress(emailAddress);
        }

        return canChange;
    }
    
    public ArrayList<Student> getStudents() {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR || Application.getInstance().getActiveUser().getRole() == Role.ADVISOR) {
            return userList.getStudents();
        }
        return null;
    }

    public ArrayList<Student> getStudents(String firstName, String lastName) {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR || Application.getInstance().getActiveUser().getRole() == Role.ADVISOR) {
            return userList.getStudents(firstName, lastName);
        }
        return null;
    }

    public ArrayList<Student> getUnassignedStudents() {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            return userList.getUnassignedStudents();
        }
        return null;
    }

    public Student getStudentByUscId(String uscId) {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR || Application.getInstance().getActiveUser().getRole() == Role.ADVISOR) {
            return userList.getStudent(uscId);
        }
        return null;
    }

    public ArrayList<Advisor> getAdvisors() {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            return userList.getAdvisors();
        }
        return null;
    }

    public ArrayList<Advisor> getUnapprovedAdvisors() {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            return userList.getUnapprovedAdvisors();
        }
        return null;
    }

    public ArrayList<Administrator> getAdministrators() {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            return userList.getAdministrators();
        }
        return null;
    }

    public ArrayList<Parent> getParents() {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            return userList.getParents();
        }
        return null;
    }

    public boolean approveAdvisor(Advisor advisor) {
        boolean canApprove = Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR;
        if (canApprove) {
            advisor.setAdvisorRole();
        }
        return canApprove;
    }

    public void selectStudent(Student student) {
        selectedStudent = student;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public boolean assignStudent(Student student) {
        boolean canAssign = Application.getInstance().getActiveUser().getRole() == Role.ADVISOR;
        if (canAssign) {
            ((Advisor)Application.getInstance().getActiveUser()).addAssignedStudent(student);
            student.setAdvisor((Advisor)Application.getInstance().getActiveUser());
        }
        return canAssign;
    }

    public boolean addAssignedStudent(Advisor advisor, Student student) {
        boolean canAssign = Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR;
        if (canAssign) {
            advisor.addAssignedStudent(student);
            student.setAdvisor(advisor);
        }
        return canAssign;
    }

    public boolean removeAssignedStuent(Student student) {
        boolean canAssign = Application.getInstance().getActiveUser().getRole() == Role.ADVISOR;
        if (canAssign) {
           ((Advisor)Application.getInstance().getActiveUser()).removeAssignedStudent(student);
           student.setAdvisor(null);
        }
        return canAssign;
    }

    public boolean removeAssignedStuent(Advisor advisor, Student student) {
        boolean canAssign = Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR;
        if (canAssign) {
           advisor.removeAssignedStudent(student);
           student.setAdvisor(null);
        }
        return canAssign;
    }

    public ArrayList<Course> getCourses() {
        if (userLoggedIn()) {
            return courseList.getCourses();
        }
        return null;
    }

    public ArrayList<Course> searchCourses(String query) {
        if (userLoggedIn()) {
            return courseList.searchCourses(query);
        }
        return null;
    }

    public ArrayList<RequirementSet> getRequirementSets() {
        if (userLoggedIn()) {
            return requirementSetList.getRequirementSets();
        }
        return null;
    }

    public ArrayList<RequirementSet> getAllMajors() {
        if (userLoggedIn()) {
             return requirementSetList.getMajors();
        }
        return null;
    }

    public ArrayList<RequirementSet> getMinors() {
        if (userLoggedIn()) {
             return requirementSetList.getMinors();
        }
        return null;
    }

    public ArrayList<RequirementSet> getApplicationAreas() {
        if (userLoggedIn()) {
             return requirementSetList.getApplicationAreas();
        }
        return null;
    }

    public ArrayList<AdvisingNote> addAdvisingNote(Student student, String message) {
        if (student != null && Application.getInstance().getActiveUser().getRole() == Role.ADVISOR) {
            student.addAdvisingNote((Advisor)Application.getInstance().getActiveUser(), message);
            return student.getAdvisingNotes();
        }
        return null;
    }

    public ArrayList<User> searchUsers(String query) {
        if (userLoggedIn()) {
            return userList.searchUsers(query);
        }
        return null;
    }

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^/
//  Everything above this line is done.
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^/
    
    public void addScholarship () {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            ((Student)Application.getInstance().getActiveUser()).addScholarship(null);
        }
    }

    public void removeScholarship () {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            ((Student)Application.getInstance().getActiveUser()).removeScholarship(null);
        }
    }

    public Course createCourse(Subject subject, String number) {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            return courseList.createCourse(subject, number);
        }
        return null;
    }

    public void deleteCourse(Course course) {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            courseList.deleteCourse(course);
        }
    }

    public void modifyCourse(Course modifiedCourse) {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            courseList.modifyCourse(modifiedCourse);
        }
    }

    public RequirementSet createRequirementSet(String name, RequirementType type) {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            return requirementSetList.createRequirementSet(name, type);
        }
        return null;
    }

    public void deleteRequirementSet(RequirementSet requirementSet) {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
            requirementSetList.deleteRequirementSet(requirementSet);
        }
    }

    public void modifyRequirementSet(RequirementSet modifiedRequirementSet) {
        if (Application.getInstance().getActiveUser().getRole() == Role.ADMINISTRATOR) {
             requirementSetList.modifyRequirementSet(modifiedRequirementSet);
        }
    }

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^/
//  I believe everything above this line is done if anyone would like to review them and confirm :)
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^/

    public Course getCourse(Subject subject, String number) {
        return courseList.getCourses().get(0);
    }

    public ArrayList<Course> getCourses(Subject subject) {
        return courseList.getCourses();
    }

    public ArrayList<RequirementSet> getRequirementSets(RequirementType category) {
        return requirementSetList.getRequirementSets();
    }
    
    public RequirementSet getRequirementSet(String name, RequirementType category) {
        return requirementSetList.getRequirementSets().get(0);
    }

    public ArrayList<Scholarship> getIneligibleScholarships(Student student) {
        return new ArrayList<Scholarship>();
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

    public double getGpa() {
        return 0.0;
    }

    public double getCompletedCreditHours() {
        return 0.0;
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

    public ArrayList<Course> getCoursesThatApplyToRequirement() {
        return courseList.getCourses();
    }

    public ArrayList<PlannedCourse> getSemesterSchedule() {
        return new ArrayList<PlannedCourse>();
    }

    public void moveCourseToDifferentSemester() {

    }
}
