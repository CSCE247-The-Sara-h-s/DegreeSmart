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

	public Course(UUID uuid, Subject subject, String number) {
		this.uuid = uuid;
		this.subject = subject;
		this.number = number;
		prerequisites = new ArrayList<CourseRequirement>();
		corequisites = new ArrayList<Course>();
		semestersOffered = new ArrayList<Semester>();
		coreCategories = new ArrayList<CoreCategory>();
		creditHours = 0.0;
	}

	public Course(Subject subject, String number) {
		// TODO - uuid collisions?
		this(UUID.randomUUID(), subject, number);
	}

	public UUID getUuid() {
		return uuid;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ArrayList<CourseRequirement> getPrerequisites() {
		return prerequisites;
	}

	public boolean addPrerequisite(CourseRequirement prerequisite) {
		if (!prerequisites.contains(prerequisite)) {
			return prerequisites.add(prerequisite);
		} else {
			return false;
		}
	}

	public boolean removePrerequisite(CourseRequirement prerequisite) {
		return prerequisites.remove(prerequisite);
	}

	public ArrayList<Course> getCorequisites() {
		return corequisites;
	}

	public boolean addCorequisite(Course corequisite) {
		if (!corequisites.contains(corequisite)) {
			return corequisites.add(corequisite);
		} else {
			return false;
		}
	}

	public boolean removeCorequisite(Course corequisite) {
		return corequisites.remove(corequisite);
	}

	public ArrayList<Semester> getSemestersOffered() {
		return semestersOffered;
	}

	public boolean addSemesterOffered(Semester semester) {
		if (!semestersOffered.contains(semester)) {
			return semestersOffered.add(semester);
		} else {
			return false;
		}
	}

	public boolean removeSemesterOffered(Semester semester) {
		return semestersOffered.remove(semester);
	}

	public ArrayList<CoreCategory> getCoreCategories() {
		return coreCategories;
	}

	public boolean addSemesterOffered(CoreCategory coreCategory) {
		if (!semestersOffered.contains(coreCategory)) {
			return coreCategories.add(coreCategory);
		} else {
			return false;
		}
	}

	public boolean removeSemesterOffered(CoreCategory coreCategory) {
		return coreCategories.remove(coreCategory);
	}

	public double getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(double creditHours) {
		if (creditHours <= 0.0) {
			creditHours = 0.0;
		}

		this.creditHours = creditHours;
	}
}