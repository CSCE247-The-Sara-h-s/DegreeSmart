package degreesmart.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class DataWriter extends DataConstants {
	public static void saveUsers() {
		JSONArray usersJSON = new JSONArray();

		for (User user : UserList.getInstance().getUsers()) {
			if (user.role != Role.STUDENT) {
				usersJSON.add(userToJSON(user));
			} else {
				usersJSON.add(studentToJSON((Student) user));
			}
		}

		try {
			FileWriter file = new FileWriter(DataLoader.class.getResource(USER_FILE).toURI().toString().substring(5));
			file.write(usersJSON.toJSONString());
			file.flush();

			// https://coderanch.com/t/384661/java/find-physical-path-current-java
			// This is an awful hack.
			String path = DataWriter.class.getResource("DataWriter.class").toString().substring(6);
			if (path.matches(".*/target/classes.*")) {
				path = path.replace("target/classes/degreesmart/model/DataWriter.class", "src/main/resources" + USER_FILE);

				if (Files.exists(Paths.get(path))) {
					file = new FileWriter("/" + path);
					file.write(usersJSON.toJSONString());
					file.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JSONObject studentToJSON(Student student) {
		JSONObject studentJSON = userToJSON(student);
		studentJSON.put(STUDENT_USC_ID, student.getUscId());

		if (student.getAdvisor() != null) {
			studentJSON.put(STUDENT_ADVISOR, student.getAdvisor().getUuid().toString());
		}

		JSONArray parentsJSON = new JSONArray();
		for (Parent parent : student.getParents()) {
			parentsJSON.add(parent.getUuid().toString());
		}
		studentJSON.put(STUDENT_PARENTS, parentsJSON);

		JSONArray requestsJSON = new JSONArray();
		for (Parent parent : student.getAccessRequests()) {
			requestsJSON.add(parent.getUuid().toString());
		}
		studentJSON.put(STUDENT_ACCESS_REQUESTS, requestsJSON);

		JSONArray notesJSON = new JSONArray();
		for (AdvisingNote advisingNote : student.getAdvisingNotes()) {
			JSONObject noteJSON = new JSONObject();
			noteJSON.put(ADVISING_NOTE_NOTE, advisingNote.getNote());
			noteJSON.put(ADVISING_NOTE_AUTHOR,
				(advisingNote.getAuthor() == null)? null : advisingNote.getAuthor().getUuid().toString());
			noteJSON.put(ADVISING_NOTE_TIME, advisingNote.getTimeString());
			notesJSON.add(noteJSON);
		}
		studentJSON.put(STUDENT_ADVISING_NOTES, notesJSON);

		JSONArray scholarshipsJSON = new JSONArray();
		// for (Scholarship scholarship : student.getScholarships()) {
		// 	scholarshipsJSON.add(scholarship.getUuid().toString());
		// }
		studentJSON.put(STUDENT_SCHOLARSHIPS, scholarshipsJSON);

		JSONArray requirementSetsJSON = new JSONArray();
		for (RequirementSet set : student.getGraduationPlan().getRequirementSets()) {
			requirementSetsJSON.add(set.getUuid().toString());
		}
		studentJSON.put(STUDENT_REQUIREMENT_SETS, requirementSetsJSON);

		JSONArray completedCoursesJSON = new JSONArray();
		for (CompletedCourse completedCourse : student.getCompletedCourses()) {
			JSONObject completedCourseJSON = new JSONObject();
			completedCourseJSON.put(STUDENT_COURSE, completedCourse.getCourse().getUuid().toString());
			completedCourseJSON.put(STUDENT_COURSE_GRADE, completedCourse.getGrade().name());
			completedCourseJSON.put(STUDENT_COURSE_SEMESTER, completedCourse.getSemester().name());
			completedCourseJSON.put(STUDENT_COURSE_YEAR, ((Integer)completedCourse.getYear()).toString());
			completedCoursesJSON.add(completedCourseJSON);
		}
		studentJSON.put(STUDENT_COMPLETED_COURSES, completedCoursesJSON);

		JSONArray plannedCoursesJSON = new JSONArray();
		for (PlannedCourse plannedCourse : student.getPlannedCourses()) {
			JSONObject plannedCourseJSON = new JSONObject();
			plannedCourseJSON.put(STUDENT_COURSE, plannedCourse.getCourse().getUuid().toString());
			plannedCourseJSON.put(STUDENT_COURSE_SEMESTER, plannedCourse.getSemester().name());
			plannedCourseJSON.put(STUDENT_COURSE_YEAR, ((Integer)plannedCourse.getYear()).toString());
			plannedCoursesJSON.add(plannedCourseJSON);
		}
		studentJSON.put(STUDENT_PLANNED_COURSES, plannedCoursesJSON);

		return studentJSON;
	}

	private static JSONObject userToJSON(User user) {
		JSONObject userJSON = new JSONObject();

		userJSON.put(USER_ROLE, user.getRole().toString());
		userJSON.put(USER_UUID, user.getUuid().toString());
		userJSON.put(USER_USERNAME, user.getUsername());
		userJSON.put(USER_PASSWORD, user.getPassword());
		userJSON.put(USER_FIRST_NAME, user.getFirstName());
		userJSON.put(USER_PREFERRED_NAME,
			(user.getPreferredName() == user.getFirstName())? "" : user.getPreferredName());
		userJSON.put(USER_LAST_NAME, user.getLastName());
		userJSON.put(USER_EMAIL_ADDRESS, user.getEmailAddress());

		return userJSON;
	}

	private static JSONObject courseToJSON(Course course){
		JSONObject courseJSON = new JSONObject();

		courseJSON.put(COURSE_UUID, course.getUuid());
		courseJSON.put(COURSE_NAME, course.getName());
		courseJSON.put(COURSE_NUMBER, course.getNumber().toString());
		courseJSON.put(COURSE_SUBJECT, course.getSubject());
		courseJSON.put(COURSE_CREDIT_HOURS, course.getCreditHours());
		courseJSON.put(COURSE_PREREQUISITES, course.getPrerequisites());
		courseJSON.put(COURSE_COREQUISITES, course.getCorequisites());
		courseJSON.put(COURSE_DESCRIPTION, course.getDescription());
		courseJSON.put(COURSE_SEMESTERS, course.getSemestersOffered());
		return courseJSON;
	}

	public static void saveCourses() {
		JSONArray coursesJSON = new JSONArray();

		for (Course course : CourseList.getInstance().getCourses()) {
			coursesJSON.add(courseToJSON(course));
		}

		try {
			FileWriter file = new FileWriter(DataLoader.class.getResource(COURSE_FILE).toURI().toString().substring(5));
			file.write(coursesJSON.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}

	}

	public static void saveRequirementSets() {

	}
}
