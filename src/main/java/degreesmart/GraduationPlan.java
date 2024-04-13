package degreesmart;

import java.util.ArrayList;
import java.util.Collections;

public class GraduationPlan {
	private ArrayList<RequirementSet> requirementSets;
	private ArrayList<Requirement> requirements;
	private	ArrayList<ArrayList<PlannedCourse>> selectedCourses;
	// private int preferredGraduationYear;
	// private Semester preferredGraduationSemester;
	// private double preferredMinCreditHoursPerSemester;
	// private double preferredMaxCreditHoursPerSemester;

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
			// int index = requirements.indexOf(requirementsToRemove.get(i));

			// selectedCourses.get(i).remove(selectedCourses.get(i).size() - 1);
			// if (selectedCourses.get(i).size() == 0) {
			// 	selectedCourses.remove(i);
			// }
		}

		return true;
	}

	public boolean addPlannedCourse(Requirement requirement, PlannedCourse course) {
		if (!requirements.contains(requirement)) {
			return false;
		}

		ArrayList<PlannedCourse> courses = selectedCourses.get(requirements.indexOf(requirement));

		for (int i = 0; i < courses.size(); ++i) {
			if (courses.get(i) != null) {
				courses.set(i, course);
			}
		}

		return false;
	}

	public boolean removePlannedCourse(Requirement requirement, PlannedCourse course) {
		if (!requirements.contains(requirement)) {
			return false;
		}

		ArrayList<PlannedCourse> courses = selectedCourses.get(requirements.indexOf(requirement));
		return removePlannedCourse(requirement, courses.indexOf(course));
	}

	public boolean removePlannedCourse(Requirement requirement, int index) {
		if (!requirements.contains(requirement)) {
			return false;
		}

		ArrayList<PlannedCourse> courses = selectedCourses.get(requirements.indexOf(requirement));
		if (index <= 0 || index <= courses.size()) {
			return false;
		}

		selectedCourses.set(index, null);
		return true;
	}

	public ArrayList<PlannedCourse> getSchedule() {
		ArrayList<PlannedCourse> schedule = new ArrayList<PlannedCourse>();

		for (ArrayList<PlannedCourse> courses : selectedCourses) {
			for (PlannedCourse course : courses) {
				schedule.add(course);
			}
		}

		// Collections.sort(schedule, (c1, c2) -> c1.getTerm().compareTo(c2.getTerm()));

		return schedule;
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
