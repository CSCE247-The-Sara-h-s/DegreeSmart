package degreesmart;

import java.util.UUID;
import java.util.ArrayList;

public class Course {
	private UUID uuid;
	private Subject subject;
	private String number;
	private String name;
	private ArrayList<CourseRequirement> prerequisites;
	private ArrayList<Course> corequisites;
	private ArrayList<Semester> semestersOffered;
	private ArrayList<CoreCategory> coreCategories;
	private double creditHours;

	public Course(Subject subject, String number) {

	}

	private void setUuid() {

	}

	public UUID getUuid() {
		return uuid;
	}

	public Subject getSubject() {
		return Subject.CSCE;
	}

	public void setSubject(Subject subject) {

	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {

	}

	public ArrayList<CourseRequirement> getPrerequisites() {
		return prerequisites;
	}

	public boolean addPrerequisite(Requirement prerequisite) {
		return true;
	}

	public boolean removePrerequisite(Requirement prerequisite) {
		return true;
	}

	public ArrayList<Course> getCorequisites() {
		return corequisites;
	}

	public boolean addCorequisite(Course corequisite) {
		return true;
	}

	public boolean removeCorequisite(Course corequisite) {
		return true;
	}

	public ArrayList<Semester> getSemestersOffered() {
		return semestersOffered;
	}

	public boolean addSemesterOffered(Semester semester) {
		return true;
	}

	public boolean removeSemesterOffered(Semester semester) {
		return true;
	}

	public ArrayList<CoreCategory> getCoreCategories() {
		return coreCategories;
	}

	public boolean addSemesterOffered(CoreCategory coreCategory) {
		return true;
	}

	public boolean removeSemesterOffered(CoreCategory coreCategory) {
		return true;
	}

	public double getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(double creditHours) {
		
	}
}