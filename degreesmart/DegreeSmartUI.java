package degreesmart;

import java.util.ArrayList;
import java.util.UUID;

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
		ArrayList<User> users = UserList.getInstance().getUsers();
		System.out.println(" Loaded " + users.size() + " users from UserList.\n");

		boolean admin = false;
		boolean advisor = false;
		boolean student = false;
		boolean parent = false;
		for (User user : users) {
			if (!admin && user instanceof Administrator) {
				System.out.println(user + "\n");
				admin = true;
			}

			if (!advisor && user instanceof Advisor) {
				System.out.println(user + "\n");
				advisor = true;
			}

			if (!student && user instanceof Student) {
				System.out.println(user + "\n");
				student = true;
			}

			if (!parent && user instanceof Parent) {
				System.out.println(user + "\n");
				parent = true;
			}
		}
	}

	public void scenario2() {
		ArrayList<Course> courses = CourseList.getInstance().getCourses();
		System.out.println(" Loaded " + courses.size() + " courses from CourseList.");

		ArrayList<String> selectedCourses = new ArrayList<String>();
		selectedCourses.add("CSCE 355");
		selectedCourses.add("CHEM 111");
		selectedCourses.add("CSCE 330");

		for (Course c : courses) {
			if (selectedCourses.contains(c.getShortName())) {
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
		System.out.println(" Testing Student...\n");

		Student student = (Student) UserList.getInstance().getUser("Jsmith");
		student.setAdvisor((Advisor) UserList.getInstance().getUser("KClark"));
		student.addAdvisingNote(student.getAdvisor(), "Student was advised to take CSCE 355, CHEM 111.");
		student.addAdvisingNote(student.getAdvisor(), "Student was advised to take CSCE 247, RUSS 121.");

		student.addParent(new Parent(UUID.randomUUID(), "TestParent-1", "pw", "email", "first", "last"));
		student.addParent(new Parent(UUID.randomUUID(), "TestParent-2", "pw", "email", "first", "last"));
		student.addAccessRequest(new Parent(UUID.randomUUID(), "TestParent-3", "pw", "email", "first", "last"));
		student.addAccessRequest(new Parent(UUID.randomUUID(), "TestParent-4", "pw", "email", "first", "last"));

		student.addCompletedCourse(CourseList.getInstance().getCourse(Subject.CSCE, "247"),
				Grade.A, Semester.SPRING, 2024);
		student.addCompletedCourse(CourseList.getInstance().getCourse(Subject.CSCE, "355"),
				Grade.B, Semester.SPRING, 2024);
		student.addCompletedCourse(CourseList.getInstance().getCourse(Subject.CHEM, "111"),
				Grade.C, Semester.SPRING, 2024);
		student.addCompletedCourse(CourseList.getInstance().getCourse(Subject.RUSS, "121"),
				Grade.D, Semester.SPRING, 2024);

		System.out.println(student + "\n");
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
