package degreesmart.model;

import java.util.ArrayList;

public class StudentApplication extends Application {
	private static StudentApplication studentApplication;
	private Student user;

	private StudentApplication() {
		user = (Student) Application.getInstance().getActiveUser();
	}

	public static StudentApplication getInstance() throws IllegalArgumentException {
		Role role = Application.getInstance().getActiveUser().getRole();

		if (role != Role.STUDENT) {
			throw new IllegalArgumentException("Logged in user must have '" + role + "' role");
		}

		if (studentApplication == null) {
			studentApplication = new StudentApplication();
		}

		return studentApplication;
	}

	public Student getActiveUser() {
		return user;
	}

	public void logOut() {
		user = null;
		studentApplication = null;
		super.logOut();
	}

	public String getUscId() {
		return user.getUscId();
	}

	public Advisor getAdvisor() {
		return user.getAdvisor();
	}

	public ArrayList<Parent> getParents() {
		return user.getParents();
	}

	public ArrayList<Parent> getAccessRequests() {
		return user.getAccessRequests();
	}

	public ArrayList<AdvisingNote> getAdvisingNotes() {
		return user.getAdvisingNotes();
	}

	public ArrayList<Scholarship> getScholarships() {
		return user.getScholarships();
	}

	public ArrayList<RequirementSet> getMajors() {
		return user.getMajors();
	}

	public ArrayList<RequirementSet> getMinors() {
		return user.getMinors();
	}

	public ArrayList<RequirementSet> getDegreeRequirements() {
		return user.getDegreeRequirements();
	}

	public double getCreditHours() {
		return -99.99;
	}

	public double getOverallGpa() {
		return -99.99;
	}

	public double getMajorGpa() {
		return -99.99;
	}

	public double getTotalDegreeCredits() {
		return -99.99;
	}

	public ArrayList<Course> getPossibleCourses() {
		return CourseList.getInstance().getCourses();
	}

	public ArrayList<Course> getPossibleCourses(CourseRequirement requirement) {
		if (requirement == null) {
			throw new IllegalArgumentException("requirement cannot be null");
		}

		return requirement.getCourseOptions();
	}

	public ArrayList<RequirementSet> getPossibleMajors() {
		return RequirementSetList.getInstance().getMajors();
	}

	public ArrayList<RequirementSet> getPossibleMinors() {
		return RequirementSetList.getInstance().getMinors();
	}

	public String changeUscId(String uscId) {
		if (uscId == null) {
			throw new IllegalArgumentException("USC ID cannot be null");
		}

		if (uscId == "") {
			return "Failed to modify USC ID: USC ID cannot be empty.";
		} else if (UserList.getInstance().getStudent(uscId).equals(user)) {
			return "";
		} else if (UserList.getInstance().getStudent(uscId) != null) {
			return "Failed to modify USC ID: USC ID is already in use.";
		} else {
			UserList.getInstance().changeUscId(user, uscId);
			return "";
		}
	}

	public String approveAccessRequest(Parent parent) {
		if (parent == null) {
			throw new IllegalArgumentException("Parent cannot be null");
		}

		if (!user.getAccessRequests().contains(parent)) {
			return "Failed to approve access request: '"
				+ parent.getUsername() + "' has not requested acess.";
		} else {
			user.removeAccessRequest(parent);
			user.addParent(parent);

			parent.removePendingAccessRequest(user);
			parent.addChild(user);
			return "";
		}
	}
}
