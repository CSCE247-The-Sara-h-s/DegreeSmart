package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class UserList {
    private ArrayList<User> users;
    private ArrayList<Student> students;
    private ArrayList<Advisor> advisors;
    private HashMap<UUID, User> usersByUuid;
    private HashMap<String, User> usersByUsername;
    private HashMap<String, Student> studentsByUscId;
    private Guest guest;
    private static UserList userList;

    private UserList() {
        users = new ArrayList<User>();
        students = new ArrayList<Student>();
        advisors = new ArrayList<Advisor>();
        usersByUuid = new HashMap<UUID, User>();
        usersByUsername = new HashMap<String, User>();
        studentsByUscId = new HashMap<String, Student>();

        for (User user : DataLoader.getUsers()) {
            createUser(user);
        }
    }

    public static UserList getInstance() {
        if (userList == null) {
			userList = new UserList();
		}
		
		return userList;
    }

    public User getGuest() {
        if (guest == null) {
            UUID uuid = UUID.fromString(UUID.randomUUID().toString().replaceAll("[a-zA-Z0-9]", "0"));
            guest = new Guest(uuid, "GUEST_USER");
        }

        return guest;
    }

    public UUID getNextUuid() {
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

    private User getUser(UUID uuid) {
        return usersByUuid.get(uuid);
    }

    public User getUser(String username) {
        return usersByUsername.get(username);
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
            }
		}

        return created;
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

    public boolean updateUsername(User user) {
        boolean shouldChange = user != null && users.contains(user)
            && !usersByUsername.containsKey(user.getUsername());

        if (shouldChange) {
            usersByUsername.remove(getUser(user.getUuid()).getUsername());
            usersByUsername.put(user.getUsername(), user);
        }

        return shouldChange;
    }

    public boolean updateUscId(Student student) {
        boolean shouldChange = student != null && users.contains(student)
            && !studentsByUscId.containsKey(student.getUscId());

        if (shouldChange) {
            studentsByUscId.remove(student.getUscId());
            studentsByUscId.put(student.getUscId(), student);
        }

        return shouldChange;
    }
}
