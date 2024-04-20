package degreesmart.model;

import java.util.UUID;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserList {
    private ArrayList<User> users;
    private ArrayList<Student> students;
    private ArrayList<Advisor> advisors;
    private ArrayList<Administrator> administrators;
    private ArrayList<Parent> parents;
    private HashMap<UUID, User> usersByUuid;
    private HashMap<String, User> usersByUsername;
    private HashMap<String, Student> studentsByUscId;
    private Guest guest;
    private static UserList userList;

    private UserList() {
        users = new ArrayList<User>();
        students = new ArrayList<Student>();
        advisors = new ArrayList<Advisor>();
        administrators = new ArrayList<Administrator>();
        parents = new ArrayList<Parent>();
        usersByUuid = new HashMap<UUID, User>();
        usersByUsername = new HashMap<String, User>();
        studentsByUscId = new HashMap<String, Student>();

        for (User user : DataLoader.getInstance().users) {
            createUser(user);
        }
    }

    public static UserList getInstance() {
        if (userList == null) {
			userList = new UserList();
		}
		
		return userList;
    }

    public void clear() {
        users.clear();
        students.clear();
        advisors.clear();
        administrators.clear();
        parents.clear();
        usersByUuid.clear();
        usersByUsername.clear();
        studentsByUscId.clear();
    }

    public void saveUsers() {
        DataWriter.saveUsers();
    }

    public User getGuest() {
        if (guest == null) {
            guest = new Guest(UUID.fromString(UUID.randomUUID().toString().replaceAll("[a-zA-Z0-9]", "0")));
        }

        return guest;
    }

    private UUID getNextUuid() {
		UUID uuid;
		do {
			uuid = UUID.randomUUID();
		} while (usersByUuid.containsKey(uuid));

		return uuid;
	}

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<User> getUsers(String firstName, String lastName) {
        ArrayList<User> filteredUsers = new ArrayList<User>();

        for (User user : users) {
            if (user.getLastName().equalsIgnoreCase(lastName)
                && user.getFirstName().equalsIgnoreCase(firstName)) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }

    public ArrayList<Administrator> searchAdministators(String query, ArrayList<Administrator> usersToSearch) {
        ArrayList<Administrator> results = new ArrayList<Administrator>();
        ArrayList<User> genericUsers = new ArrayList<User>();
        genericUsers.addAll(usersToSearch);

        for (User u : searchUsers(query, genericUsers)) {
            results.add((Administrator) u);
        }

        return results;
    }

    public ArrayList<Parent> searchParents(String query, ArrayList<Parent> usersToSearch) {
        ArrayList<Parent> results = new ArrayList<Parent>();
        ArrayList<User> genericUsers = new ArrayList<User>();
        genericUsers.addAll(usersToSearch);

        for (User u : searchUsers(query, genericUsers)) {
            results.add((Parent) u);
        }

        return results;
    }

    public ArrayList<Advisor> searchAdvisors(String query, ArrayList<Advisor> usersToSearch) {
        ArrayList<Advisor> results = new ArrayList<Advisor>();
        ArrayList<User> genericUsers = new ArrayList<User>();
        genericUsers.addAll(usersToSearch);

        for (User u : searchUsers(query, genericUsers)) {
            results.add((Advisor) u);
        }

        return results;
    }

    public ArrayList<Student> searchStudents(String query, ArrayList<Student> usersToSearch) {
        ArrayList<Student> results = new ArrayList<Student>();
        ArrayList<User> genericUsers = new ArrayList<User>();
        genericUsers.addAll(usersToSearch);

        for (User u : searchUsers(query, genericUsers)) {
            results.add((Student) u);
        }

        return results;
    }

    public ArrayList<Administrator> searchAdministators(String query) {
        ArrayList<Administrator> results = new ArrayList<Administrator>();
        ArrayList<User> genericUsers = new ArrayList<User>();
        genericUsers.addAll(administrators);

        for (User u : searchUsers(query, genericUsers)) {
            results.add((Administrator) u);
        }

        return results;
    }

    public ArrayList<Parent> searchParents(String query) {
        ArrayList<Parent> results = new ArrayList<Parent>();
        ArrayList<User> genericUsers = new ArrayList<User>();
        genericUsers.addAll(parents);

        for (User u : searchUsers(query, genericUsers)) {
            results.add((Parent) u);
        }

        return results;
    }

    public ArrayList<Advisor> searchAdvisors(String query) {
        ArrayList<Advisor> results = new ArrayList<Advisor>();
        ArrayList<User> genericUsers = new ArrayList<User>();
        genericUsers.addAll(advisors);

        for (User u : searchUsers(query, genericUsers)) {
            results.add((Advisor) u);
        }

        return results;
    }

    public ArrayList<Student> searchStudents(String query) {
        ArrayList<Student> results = new ArrayList<Student>();
        ArrayList<User> genericUsers = new ArrayList<User>();
        genericUsers.addAll(students);

        for (User u : searchUsers(query, genericUsers)) {
            results.add((Student) u);
        }

        return results;
    }

    private ArrayList<User> searchUsers(String query, ArrayList<User> usersToSearch) {
        ArrayList<User> results = new ArrayList<User>();

        query = Arrays.asList(query.trim().split("\\s+")).stream()
            .map(String::toLowerCase)
            .map(Pattern::quote)
            .map(q -> "(?=.*" + q + ")")
            .collect(Collectors.joining());
        query = "^" + query + ".*$";

        for (User u : usersToSearch) {
            String str = u.getUsername() + '\0'
                + u.getFirstName() + '\0'
                + u.getLastName() + '\0'
                + u.getPreferredName() + '\0'
                + u.getEmailAddress();

            if (u.getRole() == Role.STUDENT) {
                str += '\0' + ((Student) u).getUscId();
            }

            if (str.toLowerCase().matches(query)) {
                results.add(u);
            }
        }

        return results;
    }

    public ArrayList<User> searchUsers(String query) {
        return searchUsers(query, users);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Student> getStudents(String firstName, String lastName) {
        ArrayList<Student> filteredStudents = new ArrayList<Student>();

        for (Student student : students) {
            if (student.getLastName().equalsIgnoreCase(lastName)
                    && student.getFirstName().equalsIgnoreCase(firstName)) {
                filteredStudents.add(student);
            }
        }

        return filteredStudents;
    }

    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    public ArrayList<Parent> getParents() {
        return parents;
    }

    public ArrayList<Advisor> getUnapprovedAdvisors() {
        ArrayList<Advisor> unapprovedAdvisors = new ArrayList<Advisor>();

        for (Advisor advisor : advisors) {
            if (advisor.getRole() == Role.UNAPPROVED_ADVISOR) {
                unapprovedAdvisors.add(advisor);
            }
        }

        return unapprovedAdvisors;
    }

    public ArrayList<Student> getUnassignedStudents() {
        ArrayList<Student> unassignedStudents = new ArrayList<Student>();

        for (Student student : students) {
            if (student.getAdvisor() == null) {
                unassignedStudents.add(student);
            }
        }

        return unassignedStudents;
    }

    private User getUser(UUID uuid) {
        return usersByUuid.get(uuid);
    }

    public User getUser(String username) {
        return usersByUsername.get(username.toLowerCase());
    }

    public Student getStudent(String uscId) {
        return studentsByUscId.get(uscId.toLowerCase());
    }

    public boolean createUser(User user) {
        boolean created = user != null && !users.contains(user)
            && !usersByUuid.containsKey(user.getUuid())
            && !usersByUsername.containsKey(user.getUsername().toLowerCase())
            && (!(user instanceof Student)
                || ( ((Student)user).getUscId() != null
                    && !studentsByUscId.containsKey(((Student)user).getUscId().toLowerCase())))
            && users.add(user);

        if (created) {
            usersByUuid.put(user.getUuid(), user);
            usersByUsername.put(user.getUsername().toLowerCase(), user);

            switch (user.getRole()) {
                case STUDENT:
                    students.add((Student)user);
                    studentsByUscId.put(((Student)user).getUscId().toLowerCase(), (Student)user);
                    break;
                case ADVISOR:
                case UNAPPROVED_ADVISOR:
                    advisors.add((Advisor)user);
                    break;
                case ADMINISTRATOR:
                    administrators.add((Administrator)user);
                    break;
                case PARENT:
                    parents.add((Parent)user);
                    break;
            }
        }

        return created;
    }

    public void createUser(Role role, String username, String password, String email, String firstName, String lastName) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        } else if (username != null && usersByUsername.containsKey(username.toLowerCase())) {
            throw new IllegalArgumentException("Username is already in use");
        }

        User user;
        UUID uuid = getNextUuid();

        switch (role) {
            case ADMINISTRATOR:
                user = new Administrator(uuid, username, password, email, firstName, lastName);
                break;
            case ADVISOR:
            case UNAPPROVED_ADVISOR:
                user = new Advisor(uuid, username, password, email, firstName, lastName);
                advisors.add((Advisor)user);
                break;
            case STUDENT:
                user = new Student(uuid, username, password, email, firstName, lastName);
                students.add((Student)user);
                break;
            case PARENT:
                user = new Parent(uuid, username, password, email, firstName, lastName);
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }


        users.add(user);
        usersByUuid.put(user.getUuid(), user);
        usersByUsername.put(user.getUsername().toLowerCase(), user);
    }

    public boolean deleteUser(User user) {
        boolean removed = user != null && users.remove(user);

        if (removed) {
			usersByUuid.remove(user.getUuid());
			usersByUsername.remove(user.getUsername().toLowerCase());

            switch (user.getRole()) {
                case STUDENT:
                    students.remove((Student)user);
                    studentsByUscId.remove(((Student)user).getUscId().toLowerCase());
                    break;
                case ADVISOR:
                case UNAPPROVED_ADVISOR:
                    advisors.remove((Advisor)user);
                    break;
            }
		}

        return removed;
    }

    public boolean changeUsername(User user, String username) {
        boolean shouldChange = user != null && users.contains(user)
            && !usersByUsername.containsKey(user.getUsername().toLowerCase());

        if (shouldChange) {
            usersByUsername.remove(user.getUsername().toLowerCase());
            try {
                user.setUsername(username);
            } catch (IllegalArgumentException e) {
                return false;
            } finally {
                usersByUsername.put(user.getUsername().toLowerCase(), user);
            }
        }

        return shouldChange;
    }

    public void changeUscId(Student student, String uscId) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        } else if (!users.contains(student)) {
            throw new IllegalStateException("User does not exist");
        } else if (student.getUscId() != null && studentsByUscId.containsKey(student.getUscId().toLowerCase())) {
            throw new IllegalStateException("USC ID is already in use");
        }

        String oldUscId = student.getUscId();
        student.setUscId(uscId);
        if (oldUscId != null) {
            studentsByUscId.remove(oldUscId.toLowerCase());
        }
        studentsByUscId.put(student.getUscId().toLowerCase(), student);
    }
}
