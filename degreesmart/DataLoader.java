package degreesmart;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
	public static final ArrayList<User> getUsers() {
		// go through each student and assign to advisor
		// load advisor
		// as you go through student, go to advisor class and add student to their list 
		return new ArrayList<User>();
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
		return new ArrayList<RequirementSet>();
	}
}
