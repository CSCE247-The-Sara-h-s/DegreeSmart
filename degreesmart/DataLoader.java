package degreesmart;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
	public static final ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		HashMap<UUID, User> uuidToUser = new HashMap<UUID, User>();
		HashMap<UUID, JSONArray> advisorAssignedStudentsHash = new HashMap<UUID, JSONArray>();

		try {
			FileReader reader = new FileReader(ADMINISTRATOR_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray administratorsJSON = (JSONArray)new JSONParser().parse(reader);

			for (int i = 0; i < administratorsJSON.size(); i++) {
				JSONObject administratorJSON = (JSONObject)administratorsJSON.get(i);
				UUID uuid = UUID.fromString((String)administratorJSON.get(USER_UUID));
				String username = (String)administratorJSON.get(USER_USERNAME);
				String password = (String)administratorJSON.get(USER_PASSWORD);
				String firstName = (String)administratorJSON.get(USER_FIRST_NAME);
				String lastName = (String)administratorJSON.get(USER_LAST_NAME);
				String emailAddress = (String)administratorJSON.get(USER_EMAIL_ADDRESS);

				User administrator = new Administrator(uuid, username, password, emailAddress, firstName, lastName);
				users.add(administrator);
			}

			reader = new FileReader(ADVISOR_FILE_NAME);
			parser = new JSONParser();
			JSONArray advisorsJSON = (JSONArray)new JSONParser().parse(reader);

			for (int i = 0; i < advisorsJSON.size(); i++) {
				JSONObject advisorJSON = (JSONObject)advisorsJSON.get(i);
				UUID uuid = UUID.fromString((String)advisorJSON.get(USER_UUID));
				String username = (String)advisorJSON.get(USER_USERNAME);
				String password = (String)advisorJSON.get(USER_PASSWORD);
				String firstName = (String)advisorJSON.get(USER_FIRST_NAME);
				String lastName = (String)advisorJSON.get(USER_LAST_NAME);
				String emailAddress = (String)advisorJSON.get(USER_EMAIL_ADDRESS);
				JSONArray assignedStudentsJSON = (JSONArray)advisorJSON.get(ADVISOR_ASSIGNED_STUDENTS);

				Advisor advisor = new Advisor(uuid, username, password, emailAddress, firstName, lastName);
				uuidToUser.put(advisor.getUuid(), advisor);
				advisorAssignedStudentsHash.put(advisor.getUuid(), assignedStudentsJSON);

				users.add(advisor);
			}

			reader = new FileReader(STUDENT_FILE_NAME);
			parser = new JSONParser();
			JSONArray studentsJSON = (JSONArray)new JSONParser().parse(reader);
			for (int i = 0; i < studentsJSON.size(); i++) {
				JSONObject studentJSON = (JSONObject)studentsJSON.get(i);
				UUID uuid = UUID.fromString((String)studentJSON.get(USER_UUID));
				String username = (String)studentJSON.get(USER_USERNAME);
				String password = (String)studentJSON.get(USER_PASSWORD);
				String firstName = (String)studentJSON.get(USER_FIRST_NAME);
				String lastName = (String)studentJSON.get(USER_LAST_NAME);
				String emailAddress = (String)studentJSON.get(USER_EMAIL_ADDRESS);
				String uscId = (String)studentJSON.get(STUDENT_USC_ID);

				Student student = new Student(uuid, username, password, emailAddress, firstName, lastName, uscId);
				uuidToUser.put(student.getUuid(), student);

				users.add(student);
			}

			for (User user : users) {
				if (user.getClass().getSimpleName().equals("Advisor")) {
					JSONArray assignedStudentsJSON = advisorAssignedStudentsHash.get(user.getUuid());
					for (int k = 0; k < assignedStudentsJSON.size(); k++) {
						((Advisor)user).addAssignedStudent((Student)uuidToUser.get(UUID.fromString((String)assignedStudentsJSON.get(k))));						
					}
				}
			}

			return users;

		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static final ArrayList<Course> getCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		HashMap<UUID, JSONArray> prereqHash = new HashMap<UUID, JSONArray>();
		HashMap<UUID, Course> uuidToCourse = new HashMap<UUID, Course>();
		
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
			
			// first pass
			for (int i = 0; i < coursesJSON.size(); i++) {
				JSONObject courseJSON = (JSONObject)coursesJSON.get(i);
				UUID uuid = UUID.fromString((String)courseJSON.get(COURSE_UUID));
				Subject subject = Subject.valueOf((String)courseJSON.get(COURSE_SUBJECT));
				String number = (String)courseJSON.get(COURSE_NUMBER);
				String name = (String)courseJSON.get(COURSE_NAME);
				// String description = (String)courseJSON.get(COURSE_DESCRIPTION);
				JSONArray semesterJSON = (JSONArray)courseJSON.get(COURSE_SEMESTERS_OFFERED);
				double creditHours = Double.parseDouble((String)courseJSON.get(COURSE_CREDIT_HOURS));
				JSONArray prerequisitesJSON = (JSONArray)courseJSON.get(COURSE_PREREQUISITES);

				Course course = new Course(uuid, subject, number);
				uuidToCourse.put(course.getUuid(), course);
				prereqHash.put(course.getUuid(), prerequisitesJSON);

				for (int k = 0; k < semesterJSON.size(); k++) {
					try {
						course.addSemesterOffered(Semester.valueOf((String)semesterJSON.get(k)));
					} catch (Exception e) {
					}
				}
				course.setCreditHours(creditHours);
				course.setName(name);

				courses.add(course);
			}

			// second pass
			for (Course course : courses) {
				JSONArray prerequisitesJSON = prereqHash.get(course.getUuid());
				for (int k = 0; k < prerequisitesJSON.size(); k++) {
					CourseRequirement prerequisite = new CourseRequirement();
					JSONArray courseOptionsJSON = (JSONArray)((JSONObject)prerequisitesJSON.get(k)).get(COURSE_REQUIREMENT_COURSE_OPTIONS);

					for (int m = 0; m < courseOptionsJSON.size(); m++) {
						prerequisite.addCourseOption(uuidToCourse.get(UUID.fromString((String)courseOptionsJSON.get(m))));
					}

					prerequisite.setMinGrade(Grade.valueOf((String)((JSONObject)prerequisitesJSON.get(k)).get(COURSE_REQUIREMENT_MIN_GRADE)));

					course.addPrerequisite(prerequisite);
				}

			}
			
			return courses;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
	public static final ArrayList<RequirementSet> getRequirementSets() {
		ArrayList<RequirementSet> requirementSets = new ArrayList<RequirementSet>();
		
		try {
			FileReader reader = new FileReader(REQUIREMENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray requirementsJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < requirementsJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)requirementsJSON.get(i);
				UUID id = UUID.fromString((String)personJSON.get(REQUIREMENT_SET_UUID));
				String name = (String)personJSON.get(REQUIREMENT_SET_NAME);
				String categoryString = (String) personJSON.get(REQUIREMENT_SET_CATEGORY);
				RequirementSetCategory category = RequirementSetCategory.valueOf(categoryString);	
				String requirements = (String)personJSON.get(REQUIREMENT_SET_REQUIREMENTS);

				requirementSets.add(new RequirementSet(id ,name, category));
			}
			
			return requirementSets;
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

		
	}
}
