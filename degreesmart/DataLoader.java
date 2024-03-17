package degreesmart;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
	public static final ArrayList<User> getUsers(
			ArrayList<Course> courses, ArrayList<RequirementSet> requirementSets) {
		ArrayList<User> users = new ArrayList<User>();
		HashMap<UUID, Advisor> uuidToAdvisor = new HashMap<UUID, Advisor>();
		HashMap<UUID, Student> uuidToStudent = new HashMap<UUID, Student>();
		HashMap<UUID, Parent> uuidToParent = new HashMap<UUID, Parent>();
		JSONParser parser = new JSONParser();
		FileReader reader;
		
		// first pass
		JSONArray administratorsJSON = null;
		try {
			reader = new FileReader(ADMINISTRATOR_FILE);
			administratorsJSON = (JSONArray) new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; administratorsJSON != null && i < administratorsJSON.size(); i++) {
			JSONObject administratorJSON = (JSONObject) administratorsJSON.get(i);
			String uuid = (String) administratorJSON.get(USER_UUID);
			String username = (String) administratorJSON.get(USER_USERNAME);
			String password = (String) administratorJSON.get(USER_PASSWORD);
			String firstName = (String) administratorJSON.get(USER_FIRST_NAME);
			String lastName = (String) administratorJSON.get(USER_LAST_NAME);
			String emailAddress = (String) administratorJSON.get(USER_EMAIL_ADDRESS);

			users.add(new Administrator(UUID.fromString(uuid), username, password, emailAddress,
					firstName, lastName));
		}

		JSONArray advisorsJSON = null;
		HashMap<UUID, JSONArray> adviseeJSON = new HashMap<UUID, JSONArray>();
		try {
			reader = new FileReader(ADVISOR_FILE);
			advisorsJSON = (JSONArray)new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; advisorsJSON != null && i < advisorsJSON.size(); i++) {
			JSONObject advisorJSON = (JSONObject) advisorsJSON.get(i);
			String uuid = (String) advisorJSON.get(USER_UUID);
			String username = (String) advisorJSON.get(USER_USERNAME);
			String password = (String) advisorJSON.get(USER_PASSWORD);
			String firstName = (String) advisorJSON.get(USER_FIRST_NAME);
			String lastName = (String) advisorJSON.get(USER_LAST_NAME);
			String emailAddress = (String) advisorJSON.get(USER_EMAIL_ADDRESS);
			JSONArray advisees = (JSONArray) advisorJSON.get(ADVISOR_ADVISEES);

			Advisor advisor = new Advisor(UUID.fromString(uuid), username, password, emailAddress,
					firstName, lastName);
			users.add(advisor);
			uuidToAdvisor.put(advisor.getUuid(), advisor);
			adviseeJSON.put(advisor.getUuid(), advisees);
		}

		JSONArray studentsJSON = null;
		HashMap<UUID, String> students_advisorJSON = new HashMap<UUID, String>();
		HashMap<UUID, JSONArray> students_parentJSON = new HashMap<UUID, JSONArray>();
		HashMap<UUID, JSONArray> accessJSON = new HashMap<UUID, JSONArray>();
		try {
			reader = new FileReader(STUDENT_FILE);
			studentsJSON = (JSONArray)new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; studentsJSON != null && i < studentsJSON.size(); i++) {
			JSONObject studentJSON = (JSONObject) studentsJSON.get(i);
			String uuid = (String) studentJSON.get(USER_UUID);
			String username = (String) studentJSON.get(USER_USERNAME);
			String password = (String) studentJSON.get(USER_PASSWORD);
			String firstName = (String) studentJSON.get(USER_FIRST_NAME);
			String lastName = (String) studentJSON.get(USER_LAST_NAME);
			String emailAddress = (String) studentJSON.get(USER_EMAIL_ADDRESS);
			String uscId = (String) studentJSON.get(STUDENT_USC_ID);
			String advisor = (String) studentJSON.get(STUDENT_ADVISOR);
			JSONArray parents = (JSONArray) studentJSON.get(STUDENT_PARENTS);
			JSONArray accessRequests = (JSONArray) studentJSON.get(STUDENT_ACCESS_REQUESTS);
			JSONArray advisingNotes = (JSONArray) studentJSON.get(STUDENT_ADVISING_NOTES);
			JSONArray scholarships = (JSONArray) studentJSON.get(STUDENT_SCHOLARSHIPS);
			JSONArray student_requirementSets = (JSONArray) studentJSON.get(STUDENT_REQUIREMENT_SETS);
			JSONArray completedCourses = (JSONArray) studentJSON.get(STUDENT_COMPLETED_COURSES);
			JSONArray plannedCourses = (JSONArray) studentJSON.get(STUDENT_PLANNED_COURSES);

			Student student = new Student(UUID.fromString(uuid), username, password, emailAddress,
					firstName, lastName);
			users.add(student);
			student.setUscId(uscId);
			uuidToStudent.put(student.getUuid(), student);
			students_advisorJSON.put(student.getUuid(), advisor);
			students_parentJSON.put(student.getUuid(), parents);
			accessJSON.put(student.getUuid(), accessRequests);
		}

		JSONArray parentsJSON = null;
		HashMap<UUID, JSONArray> requestJSON = new HashMap<UUID, JSONArray>();
		HashMap<UUID, JSONArray> childJSON = new HashMap<UUID, JSONArray>();
		try {
			reader = new FileReader(PARENT_FILE);
			parentsJSON = (JSONArray)new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; parentsJSON != null && i < parentsJSON.size(); i++) {
			JSONObject parentJSON = (JSONObject) parentsJSON.get(i);
			String uuid = (String) parentJSON.get(USER_UUID);
			String username = (String) parentJSON.get(USER_USERNAME);
			String password = (String) parentJSON.get(USER_PASSWORD);
			String firstName = (String) parentJSON.get(USER_FIRST_NAME);
			String lastName = (String) parentJSON.get(USER_LAST_NAME);
			String emailAddress = (String) parentJSON.get(USER_EMAIL_ADDRESS);
			String uscId = (String) parentJSON.get(STUDENT_USC_ID);
			JSONArray accessRequests = (JSONArray) parentJSON.get(STUDENT_ADVISOR);
			JSONArray children = (JSONArray) parentJSON.get(STUDENT_PARENTS);

			Parent parent = new Parent(UUID.fromString(uuid), username, password, emailAddress,
					firstName, lastName);
			users.add(parent);
			uuidToParent.put(parent.getUuid(), parent);
			childJSON.put(parent.getUuid(), children);
			requestJSON.put(parent.getUuid(), accessRequests);
		}

		// second pass
		for (User user : users) {

		}

		return users;
	}

	public static final ArrayList<Course> getCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		HashMap<UUID, Course> uuidToCourse = new HashMap<UUID, Course>();
		JSONParser parser = new JSONParser();
		FileReader reader;

		JSONArray coursesJSON;
		try {
			reader = new FileReader(COURSE_FILE);
			coursesJSON = (JSONArray)new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
		// first pass
		HashMap<UUID, JSONArray> prereqJson = new HashMap<UUID, JSONArray>();
		HashMap<UUID, JSONArray> coreqJson = new HashMap<UUID, JSONArray>();
		for (int i = 0; i < coursesJSON.size(); i++) {
			JSONObject courseJSON = (JSONObject) coursesJSON.get(i);

			String uuid = (String) courseJSON.get(COURSE_UUID);
			String subject = (String) courseJSON.get(COURSE_SUBJECT);
			String number = (String) courseJSON.get(COURSE_NUMBER);
			String name = (String) courseJSON.get(COURSE_NAME);
			String description = (String) courseJSON.get(COURSE_DESCRIPTION);
			String creditHours = (String) courseJSON.get(COURSE_CREDIT_HOURS);
			JSONArray semesters = (JSONArray) courseJSON.get(COURSE_SEMESTERS);
			JSONArray prerequisites = (JSONArray) courseJSON.get(COURSE_PREREQUISITES);
			JSONArray corequisites = (JSONArray) courseJSON.get(COURSE_COREQUISITES);

			Course course = new Course(UUID.fromString(uuid), Subject.valueOf(subject), number);
			course.setCreditHours(Double.parseDouble(creditHours));
			course.setName(name);
			course.setDescription(description);

			uuidToCourse.put(course.getUuid(), course);
			prereqJson.put(course.getUuid(), prerequisites);
			coreqJson.put(course.getUuid(), corequisites);

			for (int k = 0; k < semesters.size(); k++) {
				String semester = (String) semesters.get(k);
				course.addSemesterOffered(Semester.valueOf(semester));
			}

			courses.add(course);
		}

		// second pass
		for (Course course : courses) {
			JSONArray prerequisites = prereqJson.get(course.getUuid());

			for (int i = 0; i < prerequisites.size(); i++) {
				JSONObject prerequisite = (JSONObject) prerequisites.get(i);
				String minGrade = (String) prerequisite.get(REQUIREMENT_GRADE);
				Long choices = (Long) prerequisite.get(REQUIREMENT_CHOICES);
				JSONArray courseOptions = (JSONArray) prerequisite.get(REQUIREMENT_OPTIONS);

				CourseRequirement requirement = new CourseRequirement(Math.toIntExact(choices), Grade.valueOf(minGrade));

				for (int k = 0; k < courseOptions.size(); k++) {
					String uuid = (String) courseOptions.get(k);
					Course courseOption = uuidToCourse.get(UUID.fromString(uuid));
					requirement.addCourseOption(courseOption);
				}

				course.addPrerequisite(requirement);
			}

			JSONArray corequisites = coreqJson.get(course.getUuid());

			for (int i = 0; i < corequisites.size(); i++) {
				String uuid = (String) corequisites.get(i);
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
			reader = new FileReader(REQUIREMENT_FILE);
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
			JSONArray requirements = (JSONArray)personJSON.get(REQUIREMENT_SET_REQUIREMENTS);

			RequirementSet requirementSet = new RequirementSet(uuid, name, category);
			reqHashMap.put(requirementSet.getUuid(), requirements);

			requirementSets.add(requirementSet);
		}

		// second pass -> store the requirement set from first pass with requirements of second pass in hashmap
		for (RequirementSet requirementSet : requirementSets) {
			JSONArray requirements = reqHashMap.get(requirementSet.getUuid());

			for (int k = 0; k < requirements.size(); k++) {
				JSONObject requirement = (JSONObject) requirements.get(k);

				// check for nested vs course requirement HERE 

				// WHAT WE ORIGINALLY HAD -> this is only for nestedRequirement
				String minGrade = (String) requirement.get(REQUIREMENT_GRADE);	// are these the correct object keys?
				JSONArray courseOptions = (JSONArray) requirement.get(REQUIREMENT_OPTIONS);

				// minimum grade -> course requirement
				// arraylist of requirement, declare new coursrequirement but put it in arraylist of requirement
				// Requirement req = new 

				//requirements.add(req);
			}
		} 
		return requirementSets;

	}
}
