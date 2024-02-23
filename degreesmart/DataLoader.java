package degreesmart;

import java.util.ArrayList;

public class DataLoader extends DataConstants {
	public static final ArrayList<User> getUsers() {
		return new ArrayList<User>();
	}

	public static final ArrayList<Course> getCourses() {
		return new ArrayList<Course>();
	}
	
	public static final ArrayList<RequirementSet> getRequirementSets() {
		return new ArrayList<RequirementSet>();
	}
}
