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
		courses = DataLoader.getCourses();
		ArrayList<Course> courses = new ArrayList<Course>();
		HashMap<UUID, Course> coursesByUuid = new HashMap<UUID, Course>();
		HashMap<String, UUID> uuidsBySubjectandNumber = new HashMap<String, UUID>();
	}

	public static CourseList getInstance() {
		if (courseList == null) {
			courseList= new CourseList();
		}
		
		return courseList;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public Course getCourse(UUID uuid) {
		return coursesByUuid.get(uuid);
	}

	public Course getCourse(Subject subject, String number) {
		return getCourse(uuidsBySubjectAndNumber.get(subject+ " "+ number));
	}

	public boolean createCourse(Course course) {
		if(!coursesByUuid.containsKey(course.getUuid())){
			uuidsBySubjectAndNumber.put(course.getSubject() + " " + course.getNumber(), course.getUuid());
			coursesByUuid.put(course.getUuid(), course);
			return true;
		} else{
			return false;
		}
	}

	public boolean deleteCourse(Course course) {
		if(courses.remove(course)){
			coursesByUuid.remove(course.getUuid());
			uuidsBySubjectAndNumber.remove(course.getSubject() + " " + course.getNumber());
			return true;
		} else {
			return false;
		}
	}

	public boolean modifyCourse(Course course) {
		Course original = coursesByUuid.get(course.getUuid());

		if(original == null){
			return false;
		} else {
			coursesByUuid.remove(course.getUuid());
			coursesByUuid.put(course.getUuid(), course);
			uuidsBySubjectAndNumber.remove(original.getSubject() + " " + original.getNumber());
			uuidsBySubjectAndNumber.put(course.getSubject() + " " + course.getNumber(), course.getUuid());
			return true;
		}
	}
}
