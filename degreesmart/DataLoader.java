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
		HashMap<UUID, JSONArray> prereqJson = new HashMap<UUID, JSONArray>();
		HashMap<UUID, JSONArray> coreqJson = new HashMap<UUID, JSONArray>();
		HashMap<UUID, Course> uuidToCourse = new HashMap<UUID, Course>();
		JSONParser parser = new JSONParser();
		FileReader reader;
		JSONArray coursesJSON;
		
		try {
			reader = new FileReader(COURSE_FILE_NAME);
			coursesJSON = (JSONArray)new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
		// first pass
		for (int i = 0; i < coursesJSON.size(); i++) {
			JSONObject courseJSON = (JSONObject)coursesJSON.get(i);

			String uuid = (String) courseJSON.get(COURSE_UUID);
			String subject = (String) courseJSON.get(COURSE_SUBJECT);
			String number = (String) courseJSON.get(COURSE_NUMBER);
			String name = (String) courseJSON.get(COURSE_NAME);
			String description = (String) courseJSON.get(COURSE_DESCRIPTION);
			String creditHours = (String) courseJSON.get(COURSE_CREDIT_HOURS);
			JSONArray semesters = (JSONArray) courseJSON.get(COURSE_SEMESTERS_OFFERED);
			JSONArray prerequisites = (JSONArray) courseJSON.get(COURSE_PREREQUISITES);
			JSONArray corequisites = (JSONArray) courseJSON.get(COURSE_COREQUISITES);

			Course course = new Course(UUID.fromString(uuid), Subject.valueOf(subject), number);
			uuidToCourse.put(course.getUuid(), course);
			prereqJson.put(course.getUuid(), prerequisites);
			coreqJson.put(course.getUuid(), corequisites);

			course.setCreditHours(Double.parseDouble(creditHours));
			course.setName(name);
			course.setDescription(description);

			for (int k = 0; k < semesters.size(); k++) {
				String semester = (String) semesters.get(k);
				course.addSemesterOffered(Semester.valueOf(semester));
			}

			courses.add(course);
		}

		// second pass
		for (Course course : courses) {
			JSONArray prerequisites = prereqJson.get(course.getUuid());

			for (int k = 0; k < prerequisites.size(); k++) {
				JSONObject prerequisite = (JSONObject) prerequisites.get(k);
				String minGrade = (String) prerequisite.get(COURSE_REQUIREMENT_MIN_GRADE);
				JSONArray courseOptions = (JSONArray) prerequisite.get(COURSE_REQUIREMENT_COURSE_OPTIONS);

				CourseRequirement requirement = new CourseRequirement();
				requirement.setMinGrade(Grade.valueOf(minGrade));

				for (int m = 0; m < courseOptions.size(); m++) {
					String uuid = (String)courseOptions.get(m);
					Course courseOption = uuidToCourse.get(UUID.fromString(uuid));
					requirement.addCourseOption(courseOption);
				}

				course.addPrerequisite(requirement);
			}

			JSONArray corequisites = coreqJson.get(course.getUuid());

			for (int k = 0; k < corequisites.size(); k++) {
				String uuid = (String) corequisites.get(k);
				Course corequisite = uuidToCourse.get(UUID.fromString(uuid));

				course.addCorequisite(corequisite);
			}
		}

		return courses;
	}
		
	public static final ArrayList<RequirementSet> getRequirementSets() {
		ArrayList<RequirementSet> requirementSets = new ArrayList<RequirementSet>();
		HashMap<UUID, JSONArray> reqHashMap = new HashMap<UUID, JSONArray>();
		// TODO: inititalize hashmap for requirements section
		JSONParser parser = new JSONParser();
		FileReader reader;
		JSONArray requirementsJSON;

		
		try {
			reader = new FileReader(REQUIREMENT_FILE_NAME);
			requirementsJSON = (JSONArray)new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// first pass -> create instance of requirementSet with uuid, name, category
		for(int i= 0; i < requirementsJSON.size(); i++) {
			JSONObject personJSON = (JSONObject)requirementsJSON.get(i);
			UUID uuid = UUID.fromString((String)personJSON.get(REQUIREMENT_SET_UUID));
			String name = (String)personJSON.get(REQUIREMENT_SET_NAME);
			String categoryString = (String) personJSON.get(REQUIREMENT_SET_CATEGORY);
			RequirementSetCategory category = RequirementSetCategory.valueOf(categoryString);	
			String requirements = (String)personJSON.get(REQUIREMENT_SET_REQUIREMENTS);

			requirementSets.add(new RequirementSet(uuid ,name, category));
		}

		// second pass -> store the requirement set from first pass with requirements of second pass in hashmap
		for (RequirementSet requirementSet : requirementSets) {
			JSONArray requirements = reqHashMap.get(requirementSet.getUuid());

			for (int k = 0; k < requirements.size(); k++) {
				JSONObject requirement = (JSONObject) requirements.get(k);
				String minGrade = (String) requirement.get(COURSE_REQUIREMENT_MIN_GRADE);	// are these the correct object keys?
				JSONArray courseOptions = (JSONArray) requirement.get(COURSE_REQUIREMENT_COURSE_OPTIONS);
			}
		}
		return requirementSets;

	}
}
