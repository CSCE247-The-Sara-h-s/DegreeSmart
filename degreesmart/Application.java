package degreesmart;

import java.util.ArrayList;

public class Application {
    private UserList userList;
    private CourseList courseList;
    private RequirementSetList requirementSetList;
    private User activeUser;
    private static Application application;

    private Application() {
        courseList = CourseList.getInstance();
        requirementSetList = RequirementSetList.getInstance(courseList.getCourses());
        userList = UserList.getInstance(courseList.getCourses(), requirementSetList.getRequirementSets());
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

    public boolean createAccount(Role role, String username, String password, String emailAddress, String firstName,
            String lastName) {
        boolean canCreate = !userLoggedIn() && role != null
            && validUsername(username) && validPassword(password) && validEmailAddress(emailAddress)
            && validName(firstName) && validName(lastName);

        if (canCreate) {
           userList.createUser(role, username, password, emailAddress, firstName, lastName);
           activeUser = (userList.getUser(username) == null)? activeUser : userList.getUser(username);
        }

        return canCreate;
    }

    public boolean deleteAccount(User user) {
        boolean deleted = false;
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            deleted = userList.deleteUser(user);
        }
        return deleted;
    }

    public boolean deleteAccount() {
        boolean deleted = false;
        if (userLoggedIn()) {
            deleted = userList.deleteUser(activeUser);
            logOut();
        }
        return deleted;
    }

    public boolean userLoggedIn() {
        return !userList.getGuest().equals(activeUser);
    }

    public boolean logIn(String username, String password) {
        if (!userLoggedIn()) {
            User user = userList.getUser(username);
            if (user != null && user.getPassword().equals(password)) {
                activeUser = user;
            }
        }
        return userLoggedIn();
    }

    public void logOut() {
        activeUser = userList.getGuest();
        userList.saveUsers();
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

    private boolean validUscId(String uscId) {
        return uscId != null && userList.getStudent(uscId) == null;
    }

    public boolean changeUsername(String username) {
        boolean canChange = userLoggedIn() && validUsername(username);

        if (canChange) {
            userList.changeUsername(activeUser, username);
        }

        return canChange;
    }

    public boolean changeUscId(String uscId) {
        boolean canChange = userLoggedIn() && validUscId(uscId) && activeUser.getRole() == Role.STUDENT;

        if (canChange) {
            userList.changeUscId((Student)activeUser, uscId);
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

    public boolean changeFirstName(String firstName) {
        boolean canChange = userLoggedIn() && validName(firstName);

        if (canChange) {
            activeUser.setFirstName(firstName);
        }

        return canChange;
    }

    public boolean changeLastName(String lastName) {
        boolean canChange = userLoggedIn() && validName(lastName);

        if (canChange) {
            activeUser.setLastName(lastName);
        }

        return canChange;
    }

    public boolean changePreferredName(String preferredName) {
        boolean canChange = userLoggedIn() && validName(preferredName);

        if (canChange) {
            activeUser.setPreferredName(preferredName);
        }

        return canChange;
    }

    public boolean changeEmailAddress(String emailAddress) {
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

    public ArrayList<Student> getUnassignedStudents() {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            return userList.getUnassignedStudents();
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

    public ArrayList<Advisor> getUnapprovedAdvisors() {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            return userList.getUnapprovedAdvisors();
        }
        return null;
    }

    public ArrayList<Administrator> getAdministrators() {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            return userList.getAdministrators();
        }
        return null;
    }

    public ArrayList<Parent> getParents() {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
            return userList.getParents();
        }
        return null;
    }

    public boolean approveAdvisor(Advisor advisor) {
        boolean canApprove = activeUser.getRole() == Role.ADMINISTRATOR;
        if (canApprove) {
            advisor.setAdvisorRole();
        }
        return canApprove;
    }

    public boolean assignStudent(Student student) {
        boolean canAssign = activeUser.getRole() == Role.ADVISOR;
        if (canAssign) {
            ((Advisor)activeUser).addAssignedStudent(student);
            student.setAdvisor((Advisor)activeUser);
        }
        return canAssign;
    }

    public boolean addAssignedStudent(Advisor advisor, Student student) {
        boolean canAssign = activeUser.getRole() == Role.ADMINISTRATOR;
        if (canAssign) {
            advisor.addAssignedStudent(student);
            student.setAdvisor(advisor);
        }
        return canAssign;
    }

    public boolean removeAssignedStuent(Student student) {
        boolean canAssign = activeUser.getRole() == Role.ADVISOR;
        if (canAssign) {
           ((Advisor)activeUser).removeAssignedStudent(student);
           student.setAdvisor(null);
        }
        return canAssign;
    }

    public boolean removeAssignedStuent(Advisor advisor, Student student) {
        boolean canAssign = activeUser.getRole() == Role.ADMINISTRATOR;
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

    public ArrayList<RequirementSet> getRequirementSets() {
        if (userLoggedIn()) {
            return requirementSetList.getRequirementSets();
        }
        return null;
    }

    public ArrayList<RequirementSet> getMajors() {
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
        if (student != null && activeUser.getRole() == Role.ADVISOR) {
            student.addAdvisingNote((Advisor)activeUser, message);
            return student.getAdvisingNotes();
        }
        return null;
    }

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^/
//  Everything above this line is done.
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

    public void addScholarship () {
        if (activeUser.getRole() == Role.ADMINISTRATOR) {
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

    public RequirementSet createRequirementSet(String name, RequirementType type) {
        if (activeUser instanceof Administrator) {
            return requirementSetList.createRequirementSet(name, type);
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
