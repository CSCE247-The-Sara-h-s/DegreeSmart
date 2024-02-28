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
		// scenarios.add(() -> scenario3());

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
		for (Course course : courses) {
			System.out.println("     UUID: " + course.getUuid());
			System.out.println("  Subject: " + course.getSubject());
			System.out.println("   Number: " + course.getNumber());
			System.out.println("     Name: " + course.getName());
			System.out.println("Semesters: " + course.getSemestersOffered());
			System.out.println("Credit Hr: " + course.getCreditHours());

			if (course.getPrerequisites().size() > 0) {
				System.out.println("  Prereqs: ");
				for (CourseRequirement prereq : course.getPrerequisites()) {
					System.out.print("     -      ");
					if (prereq.getMinGrade() != Grade.F) {
						System.out.print(prereq.getMinGrade() + " or higher in ");
					}

					ArrayList<Course> options = prereq.getCourseOptions();
					for (int i = 0; i < options.size(); i++) {
						System.out.print(options.get(i).getSubject() + " " + options.get(i).getNumber());
						if (i != options.size() - 1) {
							System.out.print(" or ");
						} else {
							System.out.println("\n");
						}
					}
				}
			}
			System.out.println();
		}
	}

	public void scenario3() {

	}

	public static void main(String args[]) {
		DegreeSmartUI degreeSmartInterface = new DegreeSmartUI();

		degreeSmartInterface.run();
	}
}
