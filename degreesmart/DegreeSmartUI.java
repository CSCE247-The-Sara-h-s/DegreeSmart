package degreesmart;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Scanner;

public class DegreeSmartUI {
	private Application application;

	public DegreeSmartUI() {
		application = Application.getInstance();
	}

	public void run() {
		ArrayList<Runnable> scenarios = new ArrayList<>(); // https://stackoverflow.com/a/67292304
		scenarios.add(() -> scriptedScenarioOne());
		scenarios.add(() -> scriptedScenarioTwo());

		for (int i = 0; i < scenarios.size(); i++) {
			System.out.println("\n\n\n---------------------------------------------"
				+ "---------------------------------------------");

			try {
				scenarios.get(i).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void scriptedScenarioOne() {
		Scanner stdin = new Scanner(System.in);
		String invalidUsername = "username";
		String invalidPassword = "password";
		String adminUsername = "Rgreen";
		String adminPassword = "PurpleDaisy22";
		String studentUsername = "brax_west";
		String studentPassword = "password";

		System.out.println(" Scripted Scenario One\t(Press ENTER to advance)\n\n");
		System.out.println(" Current User");
		System.out.println(application.getActiveUser());
		stdin.nextLine();

		System.out.println("\n Attempting to login with username='" + invalidUsername
			+ "', password='" + invalidPassword + "'");
		application.logIn(invalidUsername, invalidPassword);
		System.out.println(" Current User");
		System.out.println(application.getActiveUser());
		stdin.nextLine();

		System.out.println("\n Attempting to login with username='" + adminUsername
			+ "', password='" + adminPassword + "'");
		application.logIn(adminUsername, adminPassword);
		System.out.println(" Current User");
		System.out.println(application.getActiveUser());
		stdin.nextLine();

		System.out.println("\n System Status");
		System.out.println("           TOTAL Users: " + application.getUsers().size());
		// System.out.println("        Administrators: " + application.getAdministrators().size());
		System.out.println("              Advisors: " + application.getAdvisors().size());
		System.out.println("              Students: " + application.getStudents().size());
		// System.out.println("               Parents: " + application.getParents().size());
		System.out.println("   Unapproved Advisors: " + application.getUnapprovedAdvisors().size());
		System.out.println("   Unassigned Students: " + application.getUnassignedStudents().size());
		System.out.println("         TOTAL Courses: " + application.getCourses().size());
		// System.out.println("TOTAL Requirement Sets: " + application.getRequirementSets().size());
		// System.out.println("                Majors: " + application.getMajors().size());
		// System.out.println("                Minors: " + application.getMinors().size());
		// System.out.println("     Application Areas: " + application.getApplicationAreas().size());
		stdin.nextLine();

		System.out.println("\n Logging out '" + application.getActiveUser().getUsername() + "'");
		application.logOut();
		System.out.println(" Current User");
		System.out.println(application.getActiveUser());
		stdin.nextLine();

		System.out.println("\n Attempting to login with username='" + studentUsername
			+ "', password='" + studentPassword + "'");
		application.logIn(studentUsername, studentPassword);
		System.out.println(" Current User");
		System.out.println(application.getActiveUser());
		stdin.nextLine();

	}

	public void scriptedScenarioTwo() {

	}

	public static void main(String args[]) {
		DegreeSmartUI degreeSmartInterface = new DegreeSmartUI();

		degreeSmartInterface.run();
	}
}
