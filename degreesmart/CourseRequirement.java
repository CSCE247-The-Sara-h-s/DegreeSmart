package degreesmart;

import java.util.ArrayList;

public class CourseRequirement extends Requirement {
	private ArrayList<Course> courseOptions;
	private Grade minGrade;

	public CourseRequirement() {
		courseOptions = new ArrayList<Course>();
		minGrade = Grade.F;
	}

	public ArrayList<Course> getCourseOptions() {
		return courseOptions;
	}

	public boolean addCourseOption(Course course) {
		if (!courseOptions.contains(course)) {
			return courseOptions.add(course);
		} else {
			return false;
		}
	}

	public boolean removeCourseOption(Course course) {
		return courseOptions.remove(course);
	}

	public Grade getMinGrade() {
		return minGrade;
	}

	public void setMinGrade(Grade minGrade) {
		this.minGrade = minGrade;
	}

	public boolean equals(Object object) {
		if (object == null || ! (object instanceof CourseRequirement)) {
			return false;
		}
		CourseRequirement requirement = (CourseRequirement)object;

		return minGrade.equals(requirement.getMinGrade())
			&& getNumChoices() == requirement.getNumChoices()
			&& courseOptions.size() == requirement.getCourseOptions().size()
			&& courseOptions.containsAll(requirement.getCourseOptions())
			&& requirement.getCourseOptions().containsAll(courseOptions);
	}

	public String toString() {
		String str = minGrade + " or higher in ";

		for (int i = 0; i < courseOptions.size(); i++) {
			str += courseOptions.get(i).getSubject() + " " + courseOptions.get(i).getNumber();

			if (i != courseOptions.size() - 1) {
				str += " or ";
			}
		}

		return str;
	}
}
