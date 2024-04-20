package degreesmart.model;

import java.util.ArrayList;

public class StudentApplication extends Application {
	private static StudentApplication studentApplication;

	public static StudentApplication getInstance() throws IllegalArgumentException {
		Role role = Application.getInstance().getActiveUser().getRole();

		if (role != Role.STUDENT) {
			throw new IllegalStateException("Logged in user must have '" + role + "' role");
		}

		if (studentApplication == null) {
			studentApplication = new StudentApplication();
		}

		return studentApplication;
	}

	public Student getActiveUser() {
		return (Student) Application.getInstance().getActiveUser();
	}

	public String getUscId() {
		return getActiveUser().getUscId();
	}

	public Advisor getAdvisor() {
		return getActiveUser().getAdvisor();
	}

	public ArrayList<Parent> getParents() {
		return getActiveUser().getParents();
	}

	public ArrayList<Parent> getAccessRequests() {
		return getActiveUser().getAccessRequests();
	}

	public ArrayList<AdvisingNote> getAdvisingNotes() {
		return getActiveUser().getAdvisingNotes();
	}

	public ArrayList<Scholarship> getScholarships() {
		return getActiveUser().getScholarships();
	}

	public ArrayList<RequirementSet> getMajors() {
		return getActiveUser().getMajors();
	}

	public ArrayList<RequirementSet> getMinors() {
		return getActiveUser().getMinors();
	}

	public ArrayList<RequirementSet> getDegreeRequirements() {
		return getActiveUser().getDegreeRequirements();
	}

	public String getClassification() {
		return "null";
	}

	public double getCreditHours() {
		return getActiveUser().getAttemptedHours();
	}

	public double getOverallGpa() {
		return getActiveUser().getGpa();
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

	public ArrayList<CompletedCourse> getCompletedCourses() {
		return getActiveUser().getCompletedCourses();
	}

	public ArrayList<PlannedCourse> getCurrentCourses() {
		return getActiveUser().getCurrentCourses();
	}

	public ArrayList<PlannedCourse> getPlannedCourses() {
		return getActiveUser().getPlannedCourses();
	}

	public String changeUscId(String uscId) {
		if (uscId == null || uscId == "") {
			return "Failed to modify USC ID: USC ID cannot be empty.";
		} else if (UserList.getInstance().getStudent(uscId).equals(getActiveUser())) {
			return "";
		} else if (UserList.getInstance().getStudent(uscId) != null) {
			return "Failed to modify USC ID: USC ID is already in use.";
		} else {
			UserList.getInstance().changeUscId(getActiveUser(), uscId);
			return "";
		}
	}

	public String approveAccessRequest(Parent parent) {
		if (parent == null) {
			throw new IllegalArgumentException("Parent cannot be null");
		}

		if (!getActiveUser().getAccessRequests().contains(parent)) {
			return "Failed to approve access request: '"
				+ parent.getUsername() + "' has not requested acess.";
		} else {
			getActiveUser().removeAccessRequest(parent);
			getActiveUser().addParent(parent);

			parent.removePendingAccessRequest(getActiveUser());
			parent.addChild(getActiveUser());
			return "";
		}
	}

	public String addPlannedCourse() {
		return "";
	}

	public String changePlannedCourse() {
		return "";
	}

	public String deletePlannedCourse() {
		return "";
	}
}
