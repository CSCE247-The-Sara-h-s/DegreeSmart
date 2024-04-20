package degreesmart.model;

import java.util.UUID;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CourseList {
	private ArrayList<Course> courses;
	private HashMap<UUID, Course> coursesByUuid;
	private HashMap<String, Course> coursesByShortName;
	private static CourseList courseList;

	private CourseList() {
		courses = DataLoader.getInstance().courses;
		coursesByUuid = DataLoader.getInstance().uuidToCourse;
		coursesByShortName = new HashMap<String, Course>();

		for (Course course : courses) {
			coursesByShortName.put(course.getShortName(), course);
		}
	}

	public static CourseList getInstance() {
		if (courseList == null) {
			courseList= new CourseList();
		}
		
		return courseList;
	}

	private UUID getNextUuid() {
		UUID uuid;
		do {
			uuid = UUID.randomUUID();
		} while (coursesByUuid.containsKey(uuid));
		return uuid;
	}

	private String getShortName(Subject subject, String number) {
		return subject + " " + number;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public Course getCourse(UUID uuid) {
		return coursesByUuid.get(uuid);
	}

	public ArrayList<Course> searchCourses(String query) {
		ArrayList<Course> results = new ArrayList<Course>();
		query = Arrays.asList(query.trim().split("\\s+")).stream()
			.map(String::toLowerCase)
			.map(Pattern::quote)
			.map(q -> "(?=.*" + q + ")")
			.collect(Collectors.joining());
		query = "^" + query + ".*$";

		for (Course c : courses) {
			String str = c.getShortName() + '\0'
				+ c.getName() + '\0'
				+ c.getDescription();

			if (str.toLowerCase().matches(query)) {
				results.add(c);
			}
		}

		return results;
	}

	public Course getCourse(Subject subject, String number) {
		return coursesByShortName.get(getShortName(subject, number));
	}

	public Course createCourse(Subject subject, String number) {
		if (getCourse(subject, number) != null) {
			return null;
		} else {
			Course course = new Course(getNextUuid(), subject, number);
			coursesByUuid.put(course.getUuid(), course);
			coursesByShortName.put(course.getShortName(), course);
			return course;
		}
	}

	public boolean deleteCourse(Course course) {
		if (courses.remove(course)) {
			coursesByUuid.remove(course.getUuid());
			coursesByShortName.remove(course.getShortName());
			return true;
		} else {
			return false;
		}
	}

	public boolean modifyCourse(Course course) {
		Course original = coursesByUuid.get(course.getUuid());

		if (original == null) {
			return false;
		} else {
			coursesByUuid.put(course.getUuid(), course);
			coursesByShortName.remove(original.getShortName());
			coursesByShortName.put(course.getShortName(), course);
			return true;
		}
	}
}
