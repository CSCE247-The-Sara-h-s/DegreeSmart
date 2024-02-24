package degreesmart;

import java.util.ArrayList;

public class CourseRequirement extends Requirement {
	private ArrayList<Course> courseOptions;
	private Grade minGrade;

	public CourseRequirement() {
		
	}

	public ArrayList<Course> getCourseOptions() {
		return new ArrayList<Course>();
	}

	public boolean addCourseOption(Course course) {
		return true;
	}

	public boolean removeCourseOption(Course course) {
		return true;
	}

	public Grade getMinGrade() {
		return minGrade;
	}

	public void setMinGrade(Grade grade) {

	}
}
