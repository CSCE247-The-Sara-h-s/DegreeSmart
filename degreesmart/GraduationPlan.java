package degreesmart;

import java.util.ArrayList;
import java.util.HashMap;

public class GraduationPlan {
	private ArrayList<RequirementSet> requirementSets;
	private int preferredGraduationYear;
	private Semester preferredGraduationSemester;
	private double preferredMinCreditHoursPerSemester;
	private double preferredMaxCreditHoursPerSemester;
	private	Hashmap<String, Course> selectedCourses;
	private ArrayList<PlannedCourse> semesterSchedule;

	public GraduationPlan() {

	}

	public ArrayList<RequirementSet> getRequirementSets() {
		return new ArrayList<RequirementSet;
	}

	public boolean addRequirementSet(RequirementSet requirementSet) {
		return true;
	}

	public boolean removeRequirementSet(RequirementSet requirementSet) {
		return true;
	}
	
	public int getPreferredGraduationYear() {
		return preferredGraduationYear;
	}

	public void setPreferredGraduationYear(int preferredGraduationYear) {

	}

	public Semester getPreferredGraduationSemester() {
		return preferredGraduationSemester;
	}

	public void setPreferredGraduationSemester(Semester semester) {

	}

	public double getPreferredMinCreditHoursPerSemester() {
		return preferredMinCreditHoursPerSemester;
	}

	public void setPreferredMinCreditHoursPerSemester(double preferredMinCreditHoursPerSemester) {
		return preferredMinCreditHoursPerSemester;
	}

	public double getPreferredMaxCreditHoursPerSemester() {
		return preferredMinCreditHoursPerSemester;
	}

	public void setPreferredMaxCreditHoursPerSemester(double preferredMaxCreditHoursPerSemester) {
		return preferredMaxCreditHoursPerSemester;
	}

	private void generateSemesterSchedule() {

	}

	public ArrayList<PlannedCourse> getSemesterSchedule() {
		return semesterSchedule;
	}
}
