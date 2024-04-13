package degreesmart;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
	protected ArrayList<User> users;
	protected HashMap<UUID, User> uuidToUser;
	protected ArrayList<Course> courses;
	protected HashMap<UUID, Course> uuidToCourse;
	protected ArrayList<RequirementSet> sets;
	protected HashMap<UUID, RequirementSet> uuidToSet;
	private static DataLoader dataLoader;

	private DataLoader() {
		users = new ArrayList<User>();
		uuidToUser = new HashMap<UUID, User>();
		courses = new ArrayList<Course>();
		uuidToCourse = new HashMap<UUID, Course>();
		sets = new ArrayList<RequirementSet>();
		uuidToSet = new HashMap<UUID, RequirementSet>();

		loadCourses();
		loadSets();
		loadUsers();
	}

	public static DataLoader getInstance() {
		if (dataLoader == null) {
			dataLoader = new DataLoader();
		}
		
		return dataLoader;
	}

	private JSONArray readJSONFile(String file) {
		JSONArray json = new JSONArray();

		try {
			json = (JSONArray) (new JSONParser()).parse(new FileReader(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

	private void loadCourses() {
		JSONArray coursesJSON = readJSONFile(DataConstants.COURSE_FILE);

		for (Object object : coursesJSON) {
			JSONObject courseJSON = (JSONObject) object;

			String uuid =        (String) courseJSON.get(COURSE_UUID);
			String subject =     (String) courseJSON.get(COURSE_SUBJECT);
			String number =      (String) courseJSON.get(COURSE_NUMBER);
			String name =        (String) courseJSON.get(COURSE_NAME);
			String description = (String) courseJSON.get(COURSE_DESCRIPTION);
			String creditHours = (String) courseJSON.get(COURSE_CREDIT_HOURS);

			Course course = new Course(UUID.fromString(uuid), Subject.valueOf(subject), number);
			course.setCreditHours(Double.parseDouble(creditHours));
			course.setName(name);
			course.setDescription(description);
			courses.add(course);
			uuidToCourse.put(course.getUuid(), course);
		}

		for (Object object : coursesJSON) {
			JSONObject courseJSON = (JSONObject) object;

			String uuid =             (String) courseJSON.get(COURSE_UUID);
			JSONArray semesters =     (JSONArray) courseJSON.get(COURSE_SEMESTERS);
			JSONArray corequisites =  (JSONArray) courseJSON.get(COURSE_COREQUISITES);
			JSONArray prerequisites = (JSONArray) courseJSON.get(COURSE_PREREQUISITES);

			Course course = uuidToCourse.get(UUID.fromString(uuid));

			for (Object obj : semesters) {
				course.addSemesterOffered(Semester.valueOf((String) obj));
			}

			for (Object obj : corequisites) {
				course.addCorequisite(uuidToCourse.get(UUID.fromString((String) obj)));
			}

			for (Object obj : prerequisites) {
				JSONObject prerequisiteJSON = (JSONObject) obj;

				String grade =      (String) prerequisiteJSON.get(REQUIREMENT_GRADE);
				Long choices =      (Long) prerequisiteJSON.get(REQUIREMENT_CHOICES);
				JSONArray options = (JSONArray) prerequisiteJSON.get(REQUIREMENT_OPTIONS);

				CourseRequirement requirement = new CourseRequirement(
					Math.toIntExact(choices), Grade.valueOf(grade));

				for (Object o : options) {
					requirement.addCourseOption(uuidToCourse.get(UUID.fromString((String) o)));
				}

				course.addPrerequisite(requirement);
			}
		}
	}

	private void loadSets() {
		JSONArray setsJSON = readJSONFile(DataConstants.REQUIREMENT_FILE);

		for (Object object : setsJSON) {
			JSONObject setJSON = (JSONObject) object;

			String uuid =     (String) setJSON.get(REQUIREMENT_SET_UUID);
			String name =     (String) setJSON.get(REQUIREMENT_SET_NAME);
			String category = (String) setJSON.get(REQUIREMENT_SET_CATEGORY);

			RequirementSet set = new RequirementSet(
				UUID.fromString(uuid), name, RequirementType.valueOf(category));

			sets.add(set);
			uuidToSet.put(set.getUuid(), set);
		}

		for (Object object : setsJSON) {
			JSONObject setJSON = (JSONObject) object;

			String uuid =            (String) setJSON.get(REQUIREMENT_SET_UUID);
			JSONArray requirements = (JSONArray) setJSON.get(REQUIREMENT_SET_REQUIREMENTS);

			RequirementSet set = uuidToSet.get(UUID.fromString(uuid));

			for (Object obj : requirements) {
				JSONObject requirementJSON = (JSONObject) obj;

				Long choices =      (Long) requirementJSON.get(REQUIREMENT_CHOICES);
				String grade =      (String) requirementJSON.get(REQUIREMENT_GRADE);
				JSONArray options = (JSONArray) requirementJSON.get(REQUIREMENT_OPTIONS);

				Requirement requirement;
				if (grade != null) {
					requirement = new CourseRequirement(
						Math.toIntExact(choices), Grade.valueOf(grade));

					for (Object o : options) {
						((CourseRequirement) requirement).addCourseOption(
							uuidToCourse.get(UUID.fromString((String) o)));
					}
				} else if (! (options.get(0) instanceof String)) {
					requirement = new NestedRequirement(Math.toIntExact(choices));

					// TODO
				} else {
					requirement = new SetRequirement(Math.toIntExact(choices));

					for (Object o : options) {
						((SetRequirement) requirement).addSetOption(
							uuidToSet.get(UUID.fromString((String) o)));
					}
				}

				set.addRequirement(requirement);
			}
		}
	}

	private void loadUsers() {
		HashMap<UUID, JSONObject> studentsJSON = new HashMap<UUID, JSONObject>();

		JSONArray usersJSON = readJSONFile(DataConstants.USER_FILE);

		for (Object object : usersJSON) {
			User user = loadUser((JSONObject) object);

			if (user == null) {
				continue;
			}

			users.add(user);
			uuidToUser.put(user.getUuid(), user);

			if (user.getRole() == Role.STUDENT) {
				studentsJSON.put(user.getUuid(), (JSONObject) object);
			}
		}

		for (User user : users) {
			if (user.getRole() == Role.STUDENT) {
				loadStudent((Student) user, studentsJSON.get(user.getUuid()));
			}
		}
	}

	private User loadUser(JSONObject userJSON) {
		String role =      (String) userJSON.get(USER_ROLE);
		String uuid =      (String) userJSON.get(USER_UUID);
		String username =  (String) userJSON.get(USER_USERNAME);
		String password =  (String) userJSON.get(USER_PASSWORD);
		String firstName = (String) userJSON.get(USER_FIRST_NAME);
		String altName =   (String) userJSON.get(USER_PREFERRED_NAME);
		String lastName =  (String) userJSON.get(USER_LAST_NAME);
		String email =     (String) userJSON.get(USER_EMAIL_ADDRESS);

		User user = null;

		switch (Role.valueOf(role)) {
		case ADMINISTRATOR:
			user = new Administrator(
				UUID.fromString(uuid), username, password, email, firstName, lastName);
			break;
		case ADVISOR:
			user = new Advisor(
				UUID.fromString(uuid), username, password, email, firstName, lastName);
				((Advisor) user).setAdvisorRole();
			break;
		case UNAPPROVED_ADVISOR:
			user = new Advisor(
				UUID.fromString(uuid), username, password, email, firstName, lastName);
			break;
		case PARENT:
			user = new Parent(
				UUID.fromString(uuid), username, password, email, firstName, lastName);
			break;
		case STUDENT:
			user = new Student(
				UUID.fromString(uuid), username, password, email, firstName, lastName);
			break;
		}

		if (user != null) {
			user.setPreferredName(altName);
		}

		return user;
	}

	private void loadStudent(Student student, JSONObject studentJSON) {
		String advisor = (String) studentJSON.get(STUDENT_ADVISOR);
		String uscId =   (String) studentJSON.get(STUDENT_USC_ID);
		JSONArray parents =      (JSONArray) studentJSON.get(STUDENT_PARENTS);
		JSONArray requests =     (JSONArray) studentJSON.get(STUDENT_ACCESS_REQUESTS);
		JSONArray sets =         (JSONArray) studentJSON.get(STUDENT_REQUIREMENT_SETS);
		JSONArray completed =    (JSONArray) studentJSON.get(STUDENT_COMPLETED_COURSES);
		JSONArray planned =      (JSONArray) studentJSON.get(STUDENT_PLANNED_COURSES);
		JSONArray notes =        (JSONArray) studentJSON.get(STUDENT_ADVISING_NOTES);

		try {
			student.setAdvisor((Advisor) uuidToUser.get(UUID.fromString(advisor)));
		} catch (NullPointerException e) {
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		try {
			student.setUscId(uscId);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		for (Object object : parents) {
			student.addParent((Parent) uuidToUser.get(UUID.fromString((String) object)));
		}

		for (Object object : requests) {
			student.addAccessRequest((Parent) uuidToUser.get(UUID.fromString((String) object)));
		}

		for (Object object : sets) {
			student.getGraduationPlan().addRequirementSet(uuidToSet.get(UUID.fromString((String) object)));
		}

		for (Object object : completed) {
			JSONObject completedCourse = (JSONObject) object;

			String course =   (String) completedCourse.get(STUDENT_COURSE);
			String grade =    (String) completedCourse.get(STUDENT_COURSE_GRADE);
			String semester = (String) completedCourse.get(STUDENT_COURSE_SEMESTER);
			String year =     (String) completedCourse.get(STUDENT_COURSE_YEAR);

			student.addCompletedCourse(uuidToCourse.get(UUID.fromString(course)), 
				Grade.valueOf(grade), Semester.valueOf(semester), Integer.parseInt(year));
		}

		for (Object object : planned) {
			JSONObject plannedCourse = (JSONObject) object;

			String course =   (String) plannedCourse.get(STUDENT_COURSE);
			String semester = (String) plannedCourse.get(STUDENT_COURSE_SEMESTER);
			String year =     (String) plannedCourse.get(STUDENT_COURSE_YEAR);

			student.addPlannedCourse(uuidToCourse.get(UUID.fromString(course)), 
				Semester.valueOf(semester), Integer.parseInt(year));
		}

		for (Object object : notes) {
			JSONObject advisingNote = (JSONObject) object;

			String note =   (String) advisingNote.get(ADVISING_NOTE_NOTE);
			String author = (String) advisingNote.get(ADVISING_NOTE_AUTHOR);
			String time =   (String) advisingNote.get(ADVISING_NOTE_TIME);

			User user = 
				(author == null || author.length() == 0)? null : uuidToUser.get(UUID.fromString(author));

			student.addAdvisingNote(new AdvisingNote((Advisor) user, note, time));
		}
	}
}
