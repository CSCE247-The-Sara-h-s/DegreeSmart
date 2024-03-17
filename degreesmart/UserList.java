package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

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

    private UserList(ArrayList<Course> courses, ArrayList<RequirementSet> requirementSets) {
        users = new ArrayList<User>();
        students = new ArrayList<Student>();
        advisors = new ArrayList<Advisor>();
        administrators = new ArrayList<Administrator>();
        parents = new ArrayList<Parent>();
        usersByUuid = new HashMap<UUID, User>();
        usersByUsername = new HashMap<String, User>();
        studentsByUscId = new HashMap<String, Student>();

        for (User user : DataLoader.getUsers(courses, requirementSets)) {
            createUser(user);
        }
    }

    public static UserList getInstance(ArrayList<Course> courses, ArrayList<RequirementSet> requirementSets) {
        if (userList == null) {
			userList = new UserList(courses, requirementSets);
		}
		
		return userList;
    }

    public static UserList getInstance() {
        return userList;
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
            if (user.getLastName().equals(lastName) && user.getFirstName().equals(firstName)) {
                filteredUsers.add(user);
            }
        }

        return filteredUsers;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Student> getStudents(String firstName, String lastName) {
        ArrayList<Student> filteredStudents = new ArrayList<Student>();

        for (Student student : students) {
            if (student.getLastName().equals(lastName) && student.getFirstName().equals(firstName)) {
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

    private User getUser(UUID uuid) {
        return usersByUuid.get(uuid);
    }

    public User getUser(String username) {
        return usersByUsername.get(username);
    }

    public Student getStudent(String uscId) {
        return studentsByUscId.get(uscId);
    }

    public boolean createUser(User user) {
        boolean created = user != null && !users.contains(user) && users.add(user);

        if (created) {
            usersByUuid.put(user.getUuid(), user);
            usersByUsername.put(user.getUsername(), user);

            switch (user.getRole()) {
                case STUDENT:
                    students.add((Student)user);
                    studentsByUscId.put(((Student)user).getUscId(), (Student)user);
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
        User user = null;
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
        }

        if (user != null) {
            usersByUuid.put(user.getUuid(), user);
            usersByUsername.put(user.getUsername(), user);
        }
    }

    public boolean deleteUser(User user) {
        boolean removed = user != null && users.remove(user);

        if (removed) {
			usersByUuid.remove(user.getUuid());
			usersByUsername.remove(user.getUsername());

            switch (user.getRole()) {
                case STUDENT:
                    students.remove((Student)user);
                    studentsByUscId.remove(((Student)user).getUscId());
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
            && !usersByUsername.containsKey(user.getUsername());

        if (shouldChange) {
            usersByUsername.remove(user.getUsername());
            user.setUsername(username);
            usersByUsername.put(user.getUsername(), user);
        }

        return shouldChange;
    }

    public boolean changeUscId(Student student, String uscId) {
        boolean shouldChange = student != null && users.contains(student)
            && !studentsByUscId.containsKey(student.getUscId());

        if (shouldChange) {
            studentsByUscId.remove(student.getUscId());
            student.setUscId(uscId);
            studentsByUscId.put(student.getUscId(), student);
        }

        return shouldChange;
    }
}
