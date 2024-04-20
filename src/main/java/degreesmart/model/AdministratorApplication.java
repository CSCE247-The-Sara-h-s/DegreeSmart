package degreesmart.model;

import java.util.ArrayList;

public class AdministratorApplication extends Application {
	private static AdministratorApplication application;
	private Administrator activeUser;

	private AdministratorApplication() {
		activeUser = (Administrator) Application.getInstance().getActiveUser();
	}

	public static AdministratorApplication getInstance() throws IllegalArgumentException {
		if (Application.getInstance().getActiveUser().getRole() != Role.ADMINISTRATOR) {
			throw new IllegalStateException("Logged in user must have '" + Role.ADMINISTRATOR + "' role");
		}

		if (application == null) {
			application = new AdministratorApplication();
		}
		
		return application;
	}

	public Administrator getActiveUser() {
		return activeUser;
	}

	public void logOut() {
		activeUser = null;
		application = null;
		super.logOut();
	}

	public boolean deleteAccount(User user) {
        return UserList.getInstance().deleteUser(user);
    }

    public ArrayList<User> getUsers() {
    	return UserList.getInstance().getUsers();
    }

    public ArrayList<User> getUsers(String firstName, String lastName) {
        return UserList.getInstance().getUsers(firstName, lastName);
    }

    public User getUser(String username) {
    	return UserList.getInstance().getUser(username);
    }

    public ArrayList<Student> getStudents() {
    	return UserList.getInstance().getStudents();
    }

    public Student getStudent(String uscId) {
    	return UserList.getInstance().getStudent(uscId);
    }
}
