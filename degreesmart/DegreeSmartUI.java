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

		for (int i = 0; i < scenarios.size(); i++) {
			System.out.println("\n---------------------------------------------------------------");
			System.out.println("      ------------------- Scenario #" + (i + 1) + " ------------------- ");
			try {
				scenarios.get(i).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void scenario1() {
		System.out.println(" Load Users Test. (Temp demo; not a real scenario!)\n");
		ArrayList<User> users = DataLoader.getUsers();

		for (User user : users) {
			System.out.println("       Role: " + user.getClass().getSimpleName());
			System.out.println("       UUID: " + user.getUuid());
			System.out.println("   Username: " + user.getUsername());
			System.out.println("   Password: " + user.getPassword());
			System.out.println(" Email Addr: " + user.getEmailAddress());
			System.out.println(" First Name: " + user.getFirstName());
			System.out.println("  Last Name: " + user.getLastName());
			if (user.getClass().getSimpleName().equals("Advisor")) {
				System.out.println("   approved: " + ((Advisor)user).getApproved());
				System.out.print("   students: ");
				int count = 0;
				for (Student student : ((Advisor)user).getAssignedStudents()) {
					System.out.print(student.getFirstName() + " " + student.getLastName());

					if (count++ != ((Advisor)user).getAssignedStudents().size() - 1) {
						System.out.print(", ");
					} else {
						System.out.println();
					}
				}
			}
			System.out.println();
		}
	}

	public void scenario2() {
		System.out.println(" Load Courses Test. (Temp demo; not a real scenario!)\n");
		ArrayList<Course> courses = DataLoader.getCourses();
		System.out.println(" Loaded " + courses.size() + " courses.");

		ArrayList<String> selectedCourses = new ArrayList<String>();
		selectedCourses.add("CSCE 247");
		selectedCourses.add("CSCE 355");
		selectedCourses.add("MATH 374");
		selectedCourses.add("CSCE 212");
		selectedCourses.add("CSCE 212");
		selectedCourses.add("CHEM 111");
		selectedCourses.add("MATH 241");

		for (Course c : courses) {
			if (selectedCourses.contains(c.getSubject() + " " + c.getNumber())) {
				System.out.println("\n" + c);
			}
		}
	}

	public void scenario3() {

	}

	public static void main(String args[]) {
		DegreeSmartUI degreeSmartInterface = new DegreeSmartUI();

		degreeSmartInterface.run();
	}
}
