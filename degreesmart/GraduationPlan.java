package degreesmart;

import java.util.ArrayList;

public class GraduationPlan {
	private ArrayList<RequirementSet> requirementSets;
	private ArrayList<Requirement> requirements;
	private	ArrayList<ArrayList<PlannedCourse>> selectedCourses;

	private int preferredGraduationYear;
	private Semester preferredGraduationSemester;
	private double preferredMinCreditHoursPerSemester;
	private double preferredMaxCreditHoursPerSemester;

	public GraduationPlan() {
		requirementSets = new ArrayList<RequirementSet>();
		requirements = new ArrayList<Requirement>();
		selectedCourses = new ArrayList<ArrayList<PlannedCourse>>();
	}

	public ArrayList<RequirementSet> getRequirementSets() {
		return requirementSets;
	}

	public boolean addRequirementSet(RequirementSet requirementSet) {
		requirementSets.add(requirementSet);

		ArrayList<Requirement> requirementsToAdd = requirementSet.getRequirements();
		for (int i = 0; i < requirementsToAdd.size(); ++i) {
			if (!requirements.contains(requirementsToAdd.get(i))) {
				requirements.add(requirementsToAdd.get(i));
				selectedCourses.add(new ArrayList<PlannedCourse>());
			}

			selectedCourses.get(requirements.indexOf(requirementsToAdd.get(i))).add(null);
		}
		return true;
	}

	public boolean removeRequirementSet(RequirementSet requirementSet) {
		if (!requirementSets.remove(requirementSet)) {
			return false;
		}

		ArrayList<Requirement> requirementsToRemove = requirementSet.getRequirements();
		for (int i = 0; i < requirementsToRemove.size(); ++i) {
			int index = requirements.indexOf(requirementsToRemove.get(i));

			selectedCourses.get(i).remove(selectedCourses.get(i).size() - 1);
			if (selectedCourses.get(i).size() == 0) {
				selectedCourses.remove(i);
			}
		}

		return true;
	}
	
	public int getPreferredGraduationYear() {
		return preferredGraduationYear;
	}

	public void setPreferredGraduationYear(int preferredGraduationYear) {
		this.preferredGraduationYear = preferredGraduationYear;
	}

	public Semester getPreferredGraduationSemester() {
		return preferredGraduationSemester;
	}

	public void setPreferredGraduationSemester(Semester semester) {
		this.preferredGraduationSemester = preferredGraduationSemester;
	}

	public double getPreferredMinCreditHoursPerSemester() {
		return preferredMinCreditHoursPerSemester;
	}

	public void setPreferredMinCreditHoursPerSemester(double preferredMinCreditHoursPerSemester) {
		this.preferredMinCreditHoursPerSemester = preferredMinCreditHoursPerSemester;
	}

	public double getPreferredMaxCreditHoursPerSemester() {
		return preferredMinCreditHoursPerSemester;
	}

	public void setPreferredMaxCreditHoursPerSemester(double preferredMaxCreditHoursPerSemester) {
		this.preferredMaxCreditHoursPerSemester = preferredMaxCreditHoursPerSemester;
	}

	public ArrayList<ArrayList<PlannedCourse>> getSelectedCourses() {
		return selectedCourses;
	}

	public ArrayList<Requirement> getRequirements() {
		return requirements;
	}

	public String toString() {
		return "";
	}
}
