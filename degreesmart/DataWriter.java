package degreesmart;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
	public static void saveUsers() {
		UserList userList = UserList.getInstance();
		ArrayList<User> users = userList.getUsers();
		JSONArray jsonUsers = new JSONArray();

		for(int i = 0; i < users.size(); i++) {
			// jsonUsers.add(users.get(i));
		}

		// where to assign students?
		try (FileWriter file = new FileWriter(ADVISOR_FILE_NAME)) {
			file.write(jsonUsers.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveCourses() {

	}

	public static void saveRequirementSets() {

	}
}
