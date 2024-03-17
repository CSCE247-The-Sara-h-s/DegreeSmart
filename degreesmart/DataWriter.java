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
			studentsJSON.add(userToJSON(student));
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
