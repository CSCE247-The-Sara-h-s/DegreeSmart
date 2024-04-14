package degreesmart.model;

import java.util.UUID;
import java.util.ArrayList;

public class Course {
	private UUID uuid;
	private Subject subject;
	private String number;
	private String name;
	private String description;
	private double creditHours;
	private ArrayList<CourseRequirement> prerequisites;
	private ArrayList<Course> corequisites;
	private ArrayList<Semester> semestersOffered;

	public Course(UUID uuid, Subject subject, String number) {
		this.uuid = uuid;
		this.subject = subject;
		this.number = number;
		prerequisites = new ArrayList<CourseRequirement>();
		corequisites = new ArrayList<Course>();
		semestersOffered = new ArrayList<Semester>();
		creditHours = 0.0;
		description = "";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public double getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(double creditHours) {
		if (creditHours <= 0.0) {
			creditHours = 0.0;
		}

		this.creditHours = creditHours;
	}

	public String getShortName() {
		return subject + " " + number;
	}

	public boolean equals(Object object) {
		if (object == null || ! (object instanceof Course)) {
			return false;
		}
		Course course = (Course)object;

		return uuid.equals(course.getUuid());
	}

	public String toString() {
		String prereqs = "";
		for (CourseRequirement prereq : prerequisites) {
			prereqs += "\n          - " + prereq;
		}
		prereqs = (prereqs.length() > 0)? prereqs : "None";

		ArrayList<String> coreqs = new ArrayList<String>();
		for (Course coreq : corequisites) {
			coreqs.add(coreq.getSubject() + " " + coreq.getNumber());
		}

		return ""
			+ "       UUID: " + uuid + "\n"
			+ "    Subject: " + subject + "\n"
			+ "     Number: " + number + "\n"
			+ "       Name: " + name + "\n"
			+ "  Semesters: " + semestersOffered + "\n"
			+ "  Credit Hr: " + creditHours + "\n"
			+ "Description: " + description + "\n"
			+ "      Coreq: " + ((coreqs.size() > 0)? coreqs : "None") + "\n"
			+ "     Prereq: " + prereqs;
	}
}
