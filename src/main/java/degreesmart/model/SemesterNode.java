package degreesmart.model;

import java.util.ArrayList;

public class SemesterNode implements Comparable<SemesterNode> {
	private Course course;
	private RequirementTree requirement;
	private Term term;
	private Grade grade;
	private SemesterState state;
	private SemesterPlan plan;

	public SemesterNode(SemesterPlan plan, RequirementTree requirement) {
		state = SemesterState.PLANNED;
		this.requirement = requirement;
		this.plan = plan;
	}

	public ArrayList<Course> getPossibleCourses() {
		return requirement.getCourses();
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		if (requirement != null && course != null && !requirement.getCourses().contains(course)) {
			throw new IllegalArgumentException("Cannot apply course to requirement");
		}

		this.course = course;
	}

	public RequirementTree getRequirement() {
		return requirement;
	}

	public ArrayList<String> getPath() {
		return requirement.getPathFromRoot();
	}

	public String getRequirementName() {
		return "TODO";
	}

	public Term getTerm() {
		return term;
	}


	public void setTerm(Term term) {
		setTerm(term.getSemester(), term.getYear());
	}

	public void setTerm(Semester semester, int year) {
		if (plan == null) {
			term = new Term(semester, year);
		} else {
			if (state != SemesterState.COMPLETED && plan.isCompletedSemester(semester, year)) {
				throw new IllegalArgumentException("Invalid date for incomplete semester");
			}

			term = new Term(semester, year);
			plan.recalculate();
		}
	}

	public Grade getGrade() {
		return grade;
	}

	public SemesterState getState() {
		return state;
	}

	public void setCompleted(Grade grade) {
		state = SemesterState.COMPLETED;
		this.grade = grade;
		plan.recalculate();
		System.out.println(this);
	}

	public void setCurrent() {
		state = SemesterState.CURRENT;
		grade = null;
		plan.recalculate();
	}

	public void setPlanned() {
		state = SemesterState.PLANNED;
		grade = null;
		plan.recalculate();
	}

	public int compareTo(SemesterNode node) {
		return term.compareTo(node.getTerm());
	}

	public String toString() {
		String displayName = "";

		if (course != null) {
			displayName = course.getShortName();
		} else if (requirement != null) {
			displayName = requirement.getName();
		} else {
			displayName = "N/A";
		}

		if (displayName == null || displayName.trim().length() < 0) {
			displayName = String.join(" - ", getPath());
		}

		return state + " - " + term + " - " + displayName;
	}
}
