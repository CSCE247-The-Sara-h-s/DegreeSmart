package degreesmart;

import java.util.ArrayList;

public class CourseRequirement extends Requirement {
	private ArrayList<Course> options;
	private Grade grade;

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
		int max = 3;

		String courses = "";
		int count = 0;
		for (Course option : options) {
			if (options.size() > max + 1 && count == max) {
				courses += "... (+" + (options.size() - count) + " courses)";
				break;
			} else {
				courses += option.getShortName() 
					+ ((++count != options.size())? " or " : "");
			}
		}

		String choiceStr = "";
		if (getNumChoices() == options.size()) {
			courses = courses.replaceAll(" or ", " and ");
		} else if (getNumChoices() > 1) {
			choiceStr = getNumChoices() + " of ";
		}

		return ""
			+ grade + " or higher in " 
			+ choiceStr
			+ courses;
	}
}
