package degreesmart;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/* TODO - convert to Singleton */
public class DataLoader extends DataConstants {
	// private ArrayList<User> users;
	// private ArrayList<Course> courses;
	// private ArrayList<RequirementSet> requirementSets;
	// private HashMap<UUID, User> uuidToUser;
	// private HashMap<UUID, Course> uuidToCourse;
	// private HashMap<UUID, RequirementSet> uuidToRequirementSet;
	// private static DataLoader dataLoader;

	// private DataLoader() {
	// 	ArrayList<User> users = new ArrayList<User>();
	// }

	// public static DataLoader getInstance() {
	// 	if (dataLoader == null) {
	// 		dataLoader = new DataLoader();
	// 	}
		
	// 	return dataLoader;
	// }

	public static final ArrayList<User> getUsers(
			ArrayList<Course> courses, ArrayList<RequirementSet> requirementSets) {
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Student> students = new ArrayList<Student>();
		HashMap<UUID, Advisor> uuidToAdvisor = new HashMap<UUID, Advisor>();
		HashMap<UUID, Parent> uuidToParent = new HashMap<UUID, Parent>();
		JSONParser parser = new JSONParser();
		FileReader reader;

		HashMap<UUID, Course> uuidToCourse = new HashMap<UUID, Course>();
		HashMap<UUID, RequirementSet> uuidToSet = new HashMap<UUID, RequirementSet>();
		// HashMap<UUID, Scholarship> uuidToScholarship = new HashMap<UUID, Scholarship>();

		for (Course course : courses) {
			uuidToCourse.put(course.getUuid(), course);
		}

		for (RequirementSet requirementSet : requirementSets) {
			uuidToSet.put(requirementSet.getUuid(), requirementSet);
		}
		
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
			String preferredName = (String) administratorJSON.get(USER_PREFERRED_NAME);
			String lastName = (String) administratorJSON.get(USER_LAST_NAME);
			String emailAddress = (String) administratorJSON.get(USER_EMAIL_ADDRESS);

			Administrator administrator = new Administrator(UUID.fromString(uuid), username, password, 
				emailAddress, firstName, lastName);
			administrator.setPreferredName(preferredName);

			users.add(administrator);
		}

		JSONArray advisorsJSON = null;
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
			String preferredName = (String) advisorJSON.get(USER_PREFERRED_NAME);
			String lastName = (String) advisorJSON.get(USER_LAST_NAME);
			String emailAddress = (String) advisorJSON.get(USER_EMAIL_ADDRESS);
			String approved = (String) advisorJSON.get(ADVISOR_APPROVED);

			Advisor advisor = new Advisor(UUID.fromString(uuid), username, password, emailAddress,
					firstName, lastName);
			advisor.setPreferredName(preferredName);
			if (Boolean.parseBoolean(approved)) {
				advisor.setAdvisorRole();
			}

			users.add(advisor);
			uuidToAdvisor.put(advisor.getUuid(), advisor);
		}

		JSONArray parentsJSON = null;
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
			String preferredName = (String) parentJSON.get(USER_PREFERRED_NAME);
			String lastName = (String) parentJSON.get(USER_LAST_NAME);
			String emailAddress = (String) parentJSON.get(USER_EMAIL_ADDRESS);
			String uscId = (String) parentJSON.get(STUDENT_USC_ID);

			Parent parent = new Parent(UUID.fromString(uuid), username, password, emailAddress,
					firstName, lastName);
			parent.setPreferredName(preferredName);

			users.add(parent);
			uuidToParent.put(parent.getUuid(), parent);
		}

		JSONArray studentsJSON = null;
		try {
			reader = new FileReader(STUDENT_FILE);
			studentsJSON = (JSONArray)new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; studentsJSON != null && i < studentsJSON.size(); i++) {
			JSONObject studentJSON = (JSONObject) studentsJSON.get(i);
			String uuidStr = (String) studentJSON.get(USER_UUID);
			String username = (String) studentJSON.get(USER_USERNAME);
			String password = (String) studentJSON.get(USER_PASSWORD);
			String firstName = (String) studentJSON.get(USER_FIRST_NAME);
			String preferredName = (String) studentJSON.get(USER_PREFERRED_NAME);
			String lastName = (String) studentJSON.get(USER_LAST_NAME);
			String emailAddress = (String) studentJSON.get(USER_EMAIL_ADDRESS);
			String uscId = (String) studentJSON.get(STUDENT_USC_ID);
			String advisorStr = (String) studentJSON.get(STUDENT_ADVISOR);
			JSONArray parents = (JSONArray) studentJSON.get(STUDENT_PARENTS);
			JSONArray accessRequests = (JSONArray) studentJSON.get(STUDENT_ACCESS_REQUESTS);
			JSONArray advisingNotes = (JSONArray) studentJSON.get(STUDENT_ADVISING_NOTES);
			JSONArray scholarships = (JSONArray) studentJSON.get(STUDENT_SCHOLARSHIPS);
			JSONArray student_requirementSets = (JSONArray) studentJSON.get(STUDENT_REQUIREMENT_SETS);
			JSONArray completedCourses = (JSONArray) studentJSON.get(STUDENT_COMPLETED_COURSES);
			JSONArray plannedCourses = (JSONArray) studentJSON.get(STUDENT_PLANNED_COURSES);

			Student student = new Student(UUID.fromString(uuidStr), username, password, emailAddress,
					firstName, lastName);
			users.add(student);
			student.setPreferredName(preferredName);
			student.setUscId(uscId);
			if (advisorStr != null && advisorStr.length() != 0) {
				Advisor advisor = uuidToAdvisor.get(UUID.fromString(advisorStr));
				student.setAdvisor(advisor);
				advisor.addAssignedStudent(student);
			}

			for (int k = 0; k < parents.size(); k++) {
				String uuid = (String) parents.get(k);
				Parent parent = uuidToParent.get(UUID.fromString(uuid));
				student.addParent(parent);
				parent.addChild(student);
			}

			for (int k = 0; k < accessRequests.size(); k++) {
				String uuid = (String) accessRequests.get(k);
				Parent parent = uuidToParent.get(UUID.fromString(uuid));
				student.addAccessRequest(parent);
				parent.addPendingAccessRequest(student);
			}

			for (int k = 0; k < advisingNotes.size(); k++) {
				JSONObject advisingNote = (JSONObject) advisingNotes.get(k);
				String note = (String) advisingNote.get(ADVISING_NOTE_NOTE);
				String author = (String) advisingNote.get(ADVISING_NOTE_AUTHOR);
				String time = (String) advisingNote.get(ADVISING_NOTE_TIME);

				Advisor noteAuthor = 
					(author == null || author.length() == 0)? null : uuidToAdvisor.get(UUID.fromString(author));

				student.addAdvisingNote(new AdvisingNote(noteAuthor, note, time));
			}

			// for (int k = 0; k < scholarships.size(); k++) {
			// 	String uuid = (String) scholarships.get(k);
			// 	Scholarship scholarship = uuidToScholarship.get(UUID.fromString(uuid));

			// 	student.addScholarship(scholarship);
			// }

			for (int k = 0; k < student_requirementSets.size(); k++) {
				String uuid = (String) student_requirementSets.get(k);
				student.getGraduationPlan().addRequirementSet(uuidToSet.get(UUID.fromString(uuid)));
			}

			for (int k = 0; k < completedCourses.size(); k++) {
				JSONObject completedCourse = (JSONObject) completedCourses.get(k);
				String course = (String) completedCourse.get(STUDENT_COURSE);
				String grade = (String) completedCourse.get(STUDENT_COURSE_GRADE);
				String semester = (String) completedCourse.get(STUDENT_COURSE_SEMESTER);
				String year = (String) completedCourse.get(STUDENT_COURSE_YEAR);

				student.addCompletedCourse(uuidToCourse.get(UUID.fromString(course)), 
					Grade.valueOf(grade), Semester.valueOf(semester), Integer.parseInt(year));
			}

			for (int k = 0; k < plannedCourses.size(); k++) {
				JSONObject plannedCourse = (JSONObject) plannedCourses.get(k);
				String course = (String) plannedCourse.get(STUDENT_COURSE);
				String semester = (String) plannedCourse.get(STUDENT_COURSE_SEMESTER);
				String year = (String) plannedCourse.get(STUDENT_COURSE_YEAR);

				student.addPlannedCourse(uuidToCourse.get(UUID.fromString(course)),
					Semester.valueOf(semester), Integer.parseInt(year));
			}
		}

		return users;
	}

	public static final ArrayList<Course> getCourses() {
		ArrayList<Course> courses = new ArrayList<Course>();
		HashMap<UUID, Course> uuidToCourse = new HashMap<UUID, Course>();
		JSONParser parser = new JSONParser();
		FileReader reader;

		JSONArray coursesJSON = null;
		try {
			reader = new FileReader(COURSE_FILE);
			coursesJSON = (JSONArray) new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// first pass
		HashMap<UUID, JSONArray> prereqJSON = new HashMap<UUID, JSONArray>();
		HashMap<UUID, JSONArray> coreqJSON = new HashMap<UUID, JSONArray>();
		for (int i = 0; coursesJSON != null && i < coursesJSON.size(); i++) {
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
			prereqJSON.put(course.getUuid(), prerequisites);
			coreqJSON.put(course.getUuid(), corequisites);

			for (int k = 0; k < semesters.size(); k++) {
				String semester = (String) semesters.get(k);
				course.addSemesterOffered(Semester.valueOf(semester));
			}

			courses.add(course);
		}

		// second pass
		for (Course course : courses) {
			JSONArray prerequisites = prereqJSON.get(course.getUuid());

			for (int i = 0; i < prerequisites.size(); i++) {
				JSONObject prerequisite = (JSONObject) prerequisites.get(i);
				String minGrade = (String) prerequisite.get(REQUIREMENT_GRADE);
				Long choices = (Long) prerequisite.get(REQUIREMENT_CHOICES);
				JSONArray courseOptions = (JSONArray) prerequisite.get(REQUIREMENT_OPTIONS);

				CourseRequirement requirement = new CourseRequirement(
					Math.toIntExact(choices), Grade.valueOf(minGrade));

				for (int k = 0; k < courseOptions.size(); k++) {
					String uuid = (String) courseOptions.get(k);
					Course courseOption = uuidToCourse.get(UUID.fromString(uuid));
					requirement.addCourseOption(courseOption);
				}

				course.addPrerequisite(requirement);
			}

			JSONArray corequisites = coreqJSON.get(course.getUuid());

			for (int i = 0; i < corequisites.size(); i++) {
				String uuid = (String) corequisites.get(i);
				Course corequisite = uuidToCourse.get(UUID.fromString(uuid));

				course.addCorequisite(corequisite);
			}
		}

		return courses;
	}
		
	public static final ArrayList<RequirementSet> getRequirementSets(ArrayList<Course> courses) {
		ArrayList<RequirementSet> requirementSets = new ArrayList<RequirementSet>();
		HashMap<UUID, RequirementSet> uuidToSet = new HashMap<UUID, RequirementSet>();
		JSONParser parser = new JSONParser();
		FileReader reader;

		HashMap<UUID, Course> uuidToCourse = new HashMap<UUID, Course>();
		for (Course course : courses) {
			uuidToCourse.put(course.getUuid(), course);
		}

		JSONArray requirementSetsJSON = null;
		try {
			reader = new FileReader(REQUIREMENT_FILE);
			requirementSetsJSON = (JSONArray) new JSONParser().parse(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// first pass
		HashMap<UUID, JSONArray> reqJSON = new HashMap<UUID, JSONArray>();
		for (int i = 0; requirementSetsJSON != null && i < requirementSetsJSON.size(); i++) {
			JSONObject requirementSetJSON = (JSONObject) requirementSetsJSON.get(i);

			String uuid = (String) requirementSetJSON.get(REQUIREMENT_SET_UUID);
			String name = (String) requirementSetJSON.get(REQUIREMENT_SET_NAME);
			String category = (String) requirementSetJSON.get(REQUIREMENT_SET_CATEGORY);
			JSONArray requirements = (JSONArray) requirementSetJSON.get(REQUIREMENT_SET_REQUIREMENTS);

			RequirementSet requirementSet = new RequirementSet(
				UUID.fromString(uuid), name, RequirementType.valueOf(category));

			uuidToSet.put(requirementSet.getUuid(), requirementSet);
			reqJSON.put(requirementSet.getUuid(), requirements);

			requirementSets.add(requirementSet);
		}

		// second pass
		for (RequirementSet requirementSet : requirementSets) {
			JSONArray requirements = reqJSON.get(requirementSet.getUuid());

			for (int i = 0; i < requirements.size(); i++) {
				JSONObject requirement = (JSONObject) requirements.get(i);
				Long choices = (Long) requirement.get(REQUIREMENT_CHOICES);
				JSONArray options = (JSONArray) requirement.get(REQUIREMENT_OPTIONS);
				String grade = (String) requirement.get(REQUIREMENT_GRADE);

				Requirement req;
				if (grade == null) {
					if (options.get(0) instanceof String) {
						req = new SetRequirement(Math.toIntExact(choices));
						for (int k = 0; k < options.size(); k++) {
							String uuid = (String) options.get(k);
							((SetRequirement)req).addSetOption(uuidToSet.get(UUID.fromString(uuid)));
						}
					} else {
						req = new NestedRequirement(Math.toIntExact(choices));
						// req = getNestedRequirement(requirement, courses);
					}
				} else {
					req = new CourseRequirement(Math.toIntExact(choices), Grade.valueOf(grade));

					for (int k = 0; k < options.size(); k++) {
						String uuid = (String) options.get(k);
						((CourseRequirement)req).addCourseOption(uuidToCourse.get(UUID.fromString(uuid)));
					}
				}
				requirementSet.addRequirement(req);
			}
		}

		return requirementSets;
	}

	// private static getRequirement(JSONObject reqJSON, ArrayList<Courses> courses) {
	// 	// if grade --> return CourseRequirement
	// 	// if array of strings -> return SetRequirement
	// 	// else, recursee
	// }
}
