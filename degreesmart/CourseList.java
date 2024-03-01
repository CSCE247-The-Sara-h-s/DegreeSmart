package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseList {
	private ArrayList<Course> courses;
	private HashMap<UUID, Course> coursesByUuid;
	private HashMap<String, UUID> uuidsBySubjectAndNumber;
	private static CourseList courseList;

	private CourseList() {
		//array list of course build hashmap 
	}

	public static CourseList getInstance() {
		return new CourseList();
	}

	public ArrayList<Course> getCourses() {
		return new ArrayList<Course>();
	}

	public Course getCourse(UUID uuid) {
		return courses.get(0);
	}

	public Course getCourse(Subject subject, String number) {
		return courses.get(0);
	}

	public boolean createCourse(Course course) {
		return true;
	}

	public boolean deleteCourse(Course course) {
		return true;
	}

	public boolean modifyCourse(Course course) {
		return true;
	}
}
