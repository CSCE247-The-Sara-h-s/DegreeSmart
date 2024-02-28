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
		// go through each student and assign to advisor
		// load advisor
		// as you go through student, go to advisor class and add student to their list 
		 
		ArrayList<User> users = new ArrayList<User>();

		try {
			FileReader reader = new FileReader(ADVISOR_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);

			for(int i = 0; i < usersJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)usersJSON.get(i);
				// is this supposed to be "adivsor:"
				UUID id = UUID.fromString((String)personJSON.get(USER_UUID));
				String username = (String)personJSON.get(USER_USERNAME);
				String password = (String)personJSON.get(USER_PASSWORD);
				String firstName = (String)personJSON.get(USER_FIRST_NAME);
				String lastName = (String)personJSON.get(USER_LAST_NAME);
				String emailAddress = (String)personJSON.get(USER_EMAIL_ADDRESS);
				
				// TODO: FIGURE OUT ASSIGNED STUENT PART
				// JSONArray approved = 
				
				// users.add(new User(id, username, password, emailAddress, firstName, lastName));
	
			}

			return users;

		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;

		// return new ArrayList<User>();
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
