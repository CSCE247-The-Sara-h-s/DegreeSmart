package degreesmart;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class DataWriter extends DataConstants {
	public static void saveAdministrators() {
		JSONArray administratorsJSON = new JSONArray();

		for (Administrator administrator : UserList.getInstance().getAdministrators()) {
			administratorsJSON.add(userToJSON(administrator));
		}

		try {
			FileWriter file = new FileWriter(ADMINISTRATOR_FILE);
			file.write(administratorsJSON.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveAdvisors() {
		JSONArray advisorsJSON = new JSONArray();

		for (Advisor advisor : UserList.getInstance().getAdvisors()) {
			JSONObject advisorJSON = userToJSON(advisor);
			advisorJSON.put(ADVISOR_APPROVED, ((Boolean)advisor.getApproved()).toString());
			advisorsJSON.add(advisorJSON);
		}

		try {
			FileWriter file = new FileWriter(ADVISOR_FILE);
			file.write(advisorsJSON.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveParents() {
		JSONArray parentsJSON = new JSONArray();

		for (Parent parent : UserList.getInstance().getParents()) {
			parentsJSON.add(userToJSON(parent));
		}

		try {
			FileWriter file = new FileWriter(PARENT_FILE);
			file.write(parentsJSON.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveStudents() {
		JSONArray studentsJSON = new JSONArray();

		for (Student student : UserList.getInstance().getStudents()) {
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
				plannedCourseJSON.put(STUDENT_COURSE_SEMESTER, plannedCourse.getTerm().getSemester().name());
				plannedCourseJSON.put(STUDENT_COURSE_YEAR, ((Integer)plannedCourse.getTerm().getYear()).toString());
				plannedCoursesJSON.add(plannedCourseJSON);
			}
			studentJSON.put(STUDENT_PLANNED_COURSES, plannedCoursesJSON);

			studentsJSON.add(studentJSON);
		}

		try {
			FileWriter file = new FileWriter(STUDENT_FILE);
			file.write(studentsJSON.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static JSONObject userToJSON(User user) {
		JSONObject userJSON = new JSONObject();

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

	public static void saveCourses() {

	}

	public static void saveRequirementSets() {

	}
}
