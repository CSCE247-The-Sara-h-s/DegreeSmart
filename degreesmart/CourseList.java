package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseList {
	private ArrayList<Course> courses;
	private HashMap<UUID, Course> coursesByUuid;
	private HashMap<String, UUID> uuidsBySubjectAndNumber;
	private static final CourseList courseList;

	private CourseList() {

	}

	public CourseList getInstance() {
		return this();
	}

	public ArrayList<Course> getCourses() {
		return new ArrayList<Course>();
	}

	public Course getCourse(UUID uuid) {
		return new Course();
	}

	public Course getCourse(Subject subject, String number) {
		return new Course();
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
