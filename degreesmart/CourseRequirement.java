package degreesmart;

import java.util.ArrayList;

public class CourseRequirement extends Requirement {
	private ArrayList<Course> options;
	private Grade grade;

	public CourseRequirement() {
		options = new ArrayList<Course>();
		grade = Grade.F;
	}

	public CourseRequirement(int choices, Grade grade) {
		super(choices);
		this.grade = grade;
		options = new ArrayList<Course>();
	}

	public ArrayList<Course> getCourseOptions() {
		return options;
	}

	public boolean addCourseOption(Course option) {
		if (!options.contains(option)) {
			return options.add(option);
		} else {
			return false;
		}
	}

	public boolean removeCourseOption(Course option) {
		return options.remove(option);
	}

	public Grade getMinGrade() {
		return grade;
	}

	public void setMinGrade(Grade grade) {
		this.grade = grade;
	}

	public boolean equals(Object object) {
		if (object == null || ! (object instanceof CourseRequirement)) {
			return false;
		}
		CourseRequirement requirement = (CourseRequirement)object;

		return grade.equals(requirement.getMinGrade())
			&& getNumChoices() == requirement.getNumChoices()
			&& options.size() == requirement.getCourseOptions().size()
			&& options.containsAll(requirement.getCourseOptions())
			&& requirement.getCourseOptions().containsAll(options);
	}

	public String toString() {
		String str = grade + " or higher in ";

		for (int i = 0; i < options.size(); i++) {
			str += options.get(i).getSubject() + " " + options.get(i).getNumber();

			if (i != options.size() - 1) {
				str += " or ";
			}
		}

		return str;
	}
}
