package degreesmart.model;

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
		scenarios.add(() -> scriptedScenarioZero());
		scenarios.add(() -> scriptedScenarioThree());
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

	public void scriptedScenarioZero() {
		// Scanner stdin = new Scanner(System.in);
		// String adminUsername = "Rgreen";
		// String adminPassword = "PurpleDaisy22";

		// System.out.println(" Scripted Scenario Zero\t(Press ENTER to advance)\n\n");
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n Attempting to login with username='" + adminUsername
		// 	+ "', password='" + adminPassword + "'");
		// application.logIn(adminUsername, adminPassword);
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n System Status");
		// System.out.println(" - TOTAL ------------ Users:  " + application.getUsers().size());
		// System.out.println("             Administrators:  " + application.getAdministrators().size());
		// System.out.println("                   Advisors:  " + application.getAdvisors().size());
		// System.out.println("                   Students:  " + application.getStudents().size());
		// System.out.println("                    Parents:  " + application.getParents().size());
		// System.out.println("        Unapproved Advisors:  " + application.getUnapprovedAdvisors().size());
		// System.out.println("        Unassigned Students:  " + application.getUnassignedStudents().size());
		// System.out.println();
		// System.out.println(" - TOTAL ---------- Courses:  " + application.getCourses().size());
		// System.out.println();
		// System.out.println(" - TOTAL - Requirement Sets:  " + application.getRequirementSets().size());
		// System.out.println("                     Majors:  " + application.getAllMajors().size());
		// System.out.println("                     Minors:  " + application.getMinors().size());
		// System.out.println("          Application Areas:  " + application.getApplicationAreas().size());
		// stdin.nextLine();

		// System.out.println("\n Logging out '" + application.getActiveUser().getUsername() + "'");
		// application.logOut();
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

	}

	public void scriptedScenarioOne() {
		// Scanner stdin = new Scanner(System.in);
		// String studentUsername = "Hdawson";
		// String studentPassword = "ILoveFootball35";

		// System.out.println(" Scripted Scenario One\t(Press ENTER to advance)\n\n");
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n Attempting to login with username='" + studentUsername
		// 	+ "', password='" + studentPassword + "'");
		// application.logIn(studentUsername, studentPassword);
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n Logging out '" + application.getActiveUser().getUsername() + "'");
		// application.logOut();
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();
	}

	public void scriptedScenarioTwo() {
		// Scanner stdin = new Scanner(System.in);
		// String adminUsername = "Rgreen";
		// String adminPassword = "PurpleDaisy22";
		// String newUsername = "";
		// String newPassword = "";
		// String studentId = "T59710340";

		// System.out.println(" Scripted Scenario Two\t(Press ENTER to advance)\n\n");
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// application.logIn(adminUsername, adminPassword);
		// if (!application.userLoggedIn()) {
		// 	return;
		// }
		// if (application.getStudentByUscId(studentId) != null) {
		// 	application.getStudentByUscId(studentId).setAdvisor(null);
		// }
		// ArrayList<User> users = application.getUsers();
		// application.logOut();

		// for (User u : users) {
		// 	if (u.getFirstName().equals("Osbert") && u.getLastName().equals("Odden")) {
		// 		System.out.println(" User '" + u.getFirstName() + " " + u.getLastName() + "'"
		// 				+ " already exists! Attempting to delete user\n");
		// 			application.logIn(u.getUsername(), u.getPassword());
		// 	}
		// }

		// if (application.userLoggedIn() && application.deleteAccount()) {
		// 	System.out.println(" Account deleted\n");
		// 	return;
		// }

		// System.out.println("\n Creating a new Advisor acccount\n");
		// do {
		// 	if (newUsername.length() != 0) {
		// 		System.out.println("\n '" + newUsername + "' is already in use! "
		// 			+ "Please choose a different username.\n");
		// 	}
		// 	newUsername = "";
		// 	newPassword = "";

		// 	do {
		// 		System.out.print(" Enter a username:  ");
		// 		newUsername = stdin.nextLine();
		// 	} while (newUsername.length() == 0);

		// 	do {
		// 		System.out.print(" Enter a password:  ");
		// 		newPassword = stdin.nextLine();
		// 	} while (newPassword.length() == 0);

		// 	application.createAccount(
		// 		Role.ADVISOR, newUsername, newPassword, "osbert_odden@email.sc.edu", "Osbert", "Odden");
		// } while (!application.userLoggedIn());
		// System.out.println("\n Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n Logging out '" + application.getActiveUser().getUsername() + "'");
		// application.logOut();
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n Attempting to login with username='" + adminUsername
		// 	+ "', password='" + adminPassword + "'");
		// application.logIn(adminUsername, adminPassword);
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println(" Approving Advisor: '" + newUsername + "'");
		// for (Advisor advisor : application.getUnapprovedAdvisors()) {
		// 	if (advisor.getUsername().equals(newUsername)) {
		// 		application.approveAdvisor(advisor);
		// 		break;
		// 	}
		// }

		// System.out.println("\n Logging out '" + application.getActiveUser().getUsername() + "'");
		// application.logOut();
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n Attempting to login with username='" + newUsername
		// 	+ "', password='" + newPassword + "'");
		// application.logIn(newUsername, newPassword);
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n Searching for Student: '" + studentId + "'");
		// Student student = application.getStudentByUscId(studentId);
		// System.out.println(student);
		// stdin.nextLine();

		// System.out.println("\n Adding '" + studentId + "' as advisee");
		// application.assignStudent(student);
		// System.out.println(student);
		// stdin.nextLine();

		// System.out.println("\n Writing advising note");
		// application.addAdvisingNote(student, "Since you have already taken 2 STAT courses, "
		// 	+ "would you like to declare Statistics as your application area?");
		// System.out.println(student);
		// stdin.nextLine();

		// System.out.println("\n Logging out '" + application.getActiveUser().getUsername() + "'");
		// application.logOut();
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();
	}

	public void scriptedScenarioThree() {
		// Scanner stdin = new Scanner(System.in);
		// User user = null;

		// for (User u : UserList.getInstance().getUsers()) {
		// 	if (u.getFirstName().equals("Osbert") && u.getLastName().equals("Odden")) {
		// 		user = u;
		// 	}
		// }

		// if (user == null) {
		// 	return;
		// }

		// System.out.println(" Scripted Scenario Three\t(Press ENTER to advance)\n\n");
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n Attempting to login with username='" + user.getUsername()
		// 	+ "', password='" + user.getPassword() + "'");
		// application.logIn(user.getUsername(), user.getPassword());
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();

		// System.out.println("\n Logging out '" + application.getActiveUser().getUsername() + "'");
		// application.logOut();
		// System.out.println(" Current User");
		// System.out.println(application.getActiveUser());
		// stdin.nextLine();
	}

	public static void main(String args[]) {
		DegreeSmartUI degreeSmartInterface = new DegreeSmartUI();

		degreeSmartInterface.run();
	}
}
