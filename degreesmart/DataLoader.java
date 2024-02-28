package degreesmart;

import java.io.FileReader;
import java.util.ArrayList;
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
				
				users.add(new User(id, username, password, emailAddress, firstName, lastName));
	
			}

			return users;

		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;

		// return new ArrayList<User>();
	}

	public static final ArrayList<Course> getCourses() {
				ArrayList<Course> course = new ArrayList<Course>();
		
		try {
				// add file path
			FileReader reader = new FileReader("course.json");
			JSONParser parser = new JSONParser();
			JSONArray coursesJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < coursesJSON.size(); i++) {
				JSONObject classJSON = (JSONObject)coursesJSON.get(i);
				String id = (String)classJSON.get(id);
				String subject = (String)classJSON.get(subject);
				String number = (String)classJSON.get(number);

				//TODO: FIGURE OUT PRE-REQS
				
			}
			
			return course;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
		
		//return new ArrayList<Course>();
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
				String category = (String)personJSON.get(REQUIREMENT_SET_CATEGORY);
				String requirements = (String)personJSON.get(REQUIREMENT_SET_REQUIREMENTS);

				requirementSets.add(new RequirementSet(name, category));
			}
			
			return requirementSets;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
