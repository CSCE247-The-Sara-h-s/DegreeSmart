package degreesmart.model;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.HashMap;

public class RequirementTree {
	private String name;
	private RequirementTree parent;
	private int numberOfDecisions;
	private ArrayList<RequirementTree> paths;
	private ArrayList<Course> courses;
	private Grade grade;
	private Integer semester;

	public RequirementTree(String name, int numberOfDecisions) {
		this(name, numberOfDecisions, null, null, null);
	}

	public RequirementTree(String name, int numberOfDecisions, ArrayList<Course> courses,
			Integer semester) {
		this(name, numberOfDecisions, courses, null, semester);
	}

	public RequirementTree(String name, int numberOfDecisions, ArrayList<Course> courses,
			Grade grade, Integer semester) {
		this.name = name;
		this.numberOfDecisions = numberOfDecisions;

		if (courses == null) {
			paths = new ArrayList<RequirementTree>();
		} else {
			this.courses = new ArrayList<>(courses);
			this.grade = (grade == null)? Grade.D : grade;
			this.semester = semester;
		}
	}

	public ArrayList<String> getPathFromRoot() {
		ArrayList<String> path = new ArrayList<String>();
		RequirementTree ptr = this;

		while (ptr != null) {
			path.add((ptr.getName() == null)? " " : ptr.getName());
			ptr = ptr.getParent();
		}

		Collections.reverse(path);

		return path;
	}

	public String getName() {
		return name;
	}

	public Integer getSemester() {
		return semester;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public ArrayList<Course> getPossibleCourses() {
		ArrayList<Course> possibleCourses = new ArrayList<Course>();

		if (isLeaf()) {
			possibleCourses.addAll(courses);
		} else {
			for (RequirementTree path : paths) {
				possibleCourses.addAll(path.getPossibleCourses());
			}
		}

		return possibleCourses;
	}

	public ArrayList<RequirementTree> getPaths() {
		return paths;
	}

	public Grade getGrade() {
		return grade;
	}

	public RequirementTree getParent() {
		return parent;
	}

	protected void setParent(RequirementTree parent) {
		this.parent = parent;
	}

	public int getChoices() {
		return numberOfDecisions;
	}

	public void addPath(RequirementTree path) {
		if (!isLeaf()) {
			paths.add(path);
			path.setParent(this);
		}
	}

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return paths == null;
	}

	public boolean hasBranch() {
		if (isLeaf()) {
			return numberOfDecisions > 0 && numberOfDecisions < courses.size();
		} else {
			return numberOfDecisions > 0 && numberOfDecisions < paths.size();
		}
	}

	private ArrayList<RequirementTree> getBranches(boolean includeSelf) {
		ArrayList<RequirementTree> branches = new ArrayList<RequirementTree>();

		if ((isLeaf() || hasBranch()) && includeSelf) {
			branches.add(this);
		} else {
			for (RequirementTree path : paths) {
				branches.addAll(path.getBranches(true));
			}
		}

		return branches;
	}

	public ArrayList<RequirementTree> getBranches() {
		return getBranches(false);
	}

	public ArrayList<RequirementTree> getLeaves() {
		ArrayList<RequirementTree> leaves = new ArrayList<RequirementTree>();

		if (isLeaf()) {
			leaves.add(this);
		} else {
			for (RequirementTree path : paths) {
				leaves.addAll(path.getLeaves());
			}
		}

		return leaves;
	}

	public RequirementTree copy() {
		ArrayList<Course> coursesCopy = (isLeaf())? new ArrayList<>(courses) : null;

		RequirementTree copy = new RequirementTree(
			name, numberOfDecisions, coursesCopy, grade, semester);

		if (!isLeaf()) {
			for (RequirementTree path : paths) {
				copy.addPath(path.copy());
			}
		}

		return copy;
	}
}
