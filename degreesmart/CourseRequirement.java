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
}
