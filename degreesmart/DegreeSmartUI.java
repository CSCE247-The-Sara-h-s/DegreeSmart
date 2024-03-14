package degreesmart;

import java.util.ArrayList;

public class DegreeSmartUI {
	private Application application;

	public DegreeSmartUI() {
		application = Application.getInstance();
	}

	public void run() {
		ArrayList<Runnable> scenarios = new ArrayList<>(); // https://stackoverflow.com/a/67292304
		scenarios.add(() -> scenario1());
		scenarios.add(() -> scenario2());
		scenarios.add(() -> scenario3());
		scenarios.add(() -> scenario4());
		scenarios.add(() -> scenario5());

		for (int i = 0; i < scenarios.size(); i++) {
			System.out.println("\n\n\n---------------------------------------------"
				+ "---------------------------------------------\n"
				+ "--------------------------------------- Scenario " + (i + 1) 
				+ " ---------------------------------------");
			try {
				scenarios.get(i).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void scenario1() {
		System.out.println(" Loading users from UserList...\n");
		UserList userList = UserList.getInstance();
		ArrayList<User> users = userList.getUsers();
		System.out.println(" Loaded " + users.size() + " users.\n");

		for (User user : users) {
			System.out.println(user + "\n");
		}
	}

	public void scenario2() {
		System.out.println(" Loading courses from CourseList...\n");
		CourseList courseList = CourseList.getInstance();
		ArrayList<Course> courses = courseList.getCourses();
		System.out.println(" Loaded " + courses.size() + " courses.");

		ArrayList<String> selectedCourses = new ArrayList<String>();
		selectedCourses.add("CSCE 247");
		selectedCourses.add("CSCE 355");
		selectedCourses.add("MATH 374");
		selectedCourses.add("CSCE 212");
		selectedCourses.add("CSCE 212");
		selectedCourses.add("CHEM 111");
		selectedCourses.add("MATH 241");
		selectedCourses.add("CSCE 330");

		for (Course c : courses) {
			if (selectedCourses.contains(c.getSubject() + " " + c.getNumber())) {
				System.out.println("\n" + c);
			}
		}
	}

	public void scenario3() {
		try {
			System.out.println(" Loading requirement sets from RequirementSetList...\n");
			RequirementSetList requirementSetList = RequirementSetList.getInstance();
			ArrayList<RequirementSet> requirementSets = requirementSetList.getRequirementSets();
			System.out.println(" Loaded " + requirementSets.size() + " requirement sets.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n Failed to load requirement sets from RequirementSetList.");
			System.out.println("\n\n Loading requirement sets directly...\n");
			ArrayList<RequirementSet> requirementSets = DataLoader.getRequirementSets();
			System.out.println(" Loaded " + requirementSets.size() + " requirement sets.");
		}
		
	}

	public void scenario4() {

	}

	public void scenario5() {
		System.out.println(" Checking the logIn and Logout method\n Loading users from UserList...\n");
		UserList userList = UserList.getInstance();
		ArrayList<User> users = userList.getUsers();
		Application appy = Application.getInstance();
		System.out.println(" Loaded " + users.size() + " users.\n");

		for (User user : users) {
			System.out.println("       Role: " + user.getClass().getSimpleName());
			System.out.println("       UUID: " + user.getUuid());
			System.out.println("   Username: " + user.getUsername());
			System.out.println("   Password: " + user.getPassword());
			if (appy.logIn(user.getUsername(), user.getPassword()) != null) {
				System.out.println(user.getUsername() + " has been logged in");				
			} 
			else {
				System.out.println("Login Failed");
			}
			
		}
		for( User user : users){
			System.out.println("\n Testing Invaild Logins\n");
			System.out.println("Ivaild Username Test:");
			if (appy.logIn("TacoLover123", user.getPassword()) != null) {
				System.out.println(user.getUsername() + " has been logged in");				
			} else {
				System.out.println("Login Failed");
			}
			System.out.println("Ivaild Password Test:");
			if (appy.logIn(user.getUsername(), "IncorrectPassord")!= null) {
				System.out.println(user.getUsername() + " has been logged in");				
			} else {
				System.out.println("Login Failed");
			}
			break;
		}
		
			System.out.println();
	}
	

	public static void main(String args[]) {
		DegreeSmartUI degreeSmartInterface = new DegreeSmartUI();

		degreeSmartInterface.run();
	}
}
