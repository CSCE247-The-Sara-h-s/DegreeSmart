package degreesmart.model;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Collections;
import java.time.LocalDate;
import java.time.Month;

public class SemesterPlan {
	private ArrayList<RequirementTree> requirements;
	private ArrayList<SemesterNode> nodesWithoutARequirement;
	private HashMap<String, ArrayList<SemesterNode>> nodes;
	private HashMap<Term, ArrayList<SemesterNode>> semesters;
	private ArrayList<RequirementTree> branches;

	public SemesterPlan(ArrayList<RequirementTree> requirements) {
		this.requirements = requirements;
		nodes = new HashMap<String, ArrayList<SemesterNode>>();
		branches = new ArrayList<RequirementTree>();
		semesters = new HashMap<Term, ArrayList<SemesterNode>>();
		nodesWithoutARequirement = new ArrayList<SemesterNode>();

		autoAssignUnplannedNodes();
	}

	public SemesterNode createNode(RequirementTree requirement) {
		SemesterNode node = new SemesterNode(this, requirement);

		if (node.getRequirement() == null) {
			nodesWithoutARequirement.add(node);
		} else {
			String str = String.join("\0", node.getPath());
			if (!nodes.containsKey(str)) {
				nodes.put(str, new ArrayList<SemesterNode>());
			}
			nodes.get(str).add(node);
		}

		return node;
	}

	public void selectBranch(RequirementTree requirement) {

	}

	public ArrayList<RequirementTree> getRequirements() {
		return requirements;
	}

	public ArrayList<SemesterNode> getCompleted() {
		ArrayList<SemesterNode> completed = new ArrayList<SemesterNode>();
		for (ArrayList<SemesterNode> nodeList : nodes.values()) {
			completed.addAll(nodeList.stream()
			.filter((n) -> n.getState() == SemesterState.COMPLETED)
			.collect(Collectors.toCollection(ArrayList::new)));
		}
		completed.addAll(nodesWithoutARequirement.stream()
			.filter((n) -> n.getState() == SemesterState.COMPLETED)
			.collect(Collectors.toCollection(ArrayList::new)));
		return completed;
	}

	public ArrayList<SemesterNode> getCurrent() {
		ArrayList<SemesterNode> current = new ArrayList<SemesterNode>();
		for (ArrayList<SemesterNode> nodeList : nodes.values()) {
			current.addAll(nodeList.stream()
			.filter((n) -> n.getState() == SemesterState.CURRENT)
			.collect(Collectors.toCollection(ArrayList::new)));
		}
		current.addAll(nodesWithoutARequirement.stream()
			.filter((n) -> n.getState() == SemesterState.CURRENT)
			.collect(Collectors.toCollection(ArrayList::new)));
		return current;
	}

	public ArrayList<SemesterNode> getPlanned() {
		ArrayList<SemesterNode> planned = new ArrayList<SemesterNode>();
		for (ArrayList<SemesterNode> nodeList : nodes.values()) {
			planned.addAll(nodeList.stream()
			.filter((n) -> n.getState() == SemesterState.PLANNED)
			.collect(Collectors.toCollection(ArrayList::new)));
		}
		planned.addAll(nodesWithoutARequirement.stream()
			.filter((n) -> n.getState() == SemesterState.PLANNED)
			.collect(Collectors.toCollection(ArrayList::new)));
		return planned;
	}

	public Term getLastCompletedTerm() {
		if (getCompleted().size() > 0) {
			ArrayList<SemesterNode> tmp = getCompleted();
			Collections.sort(tmp, Collections.reverseOrder());
			return tmp.get(0).getTerm();
		} else {
			return null;
		}
	}

	public Term getCurrentTerm() {
		if (getCurrent().size() > 0) {
			return getCurrent().get(0).getTerm();
		}
		return null;
	}

	public Term getFirstPlannedTerm() {
		if (getCompleted().size() > 0) {
			ArrayList<SemesterNode> tmp = getCompleted();
			Collections.sort(tmp);
			return tmp.get(0).getTerm();
		} else {
			return null;
		}
	}

	public Term getLastPlannedTerm() {
		if (getCompleted().size() > 0) {
			ArrayList<SemesterNode> tmp = getCompleted();
			Collections.sort(tmp, Collections.reverseOrder());
			return tmp.get(0).getTerm();
		} else {
			return null;
		}
	}

	private Term getNextTermFromLocalDate() {
		int year = LocalDate.now().getYear();
		Month month = LocalDate.now().getMonth();

		Semester semester;
		if (month.compareTo(Month.MAY) < 0) {
			semester = Semester.FALL;
		} else {
			semester = Semester.SPRING;
			year = year + 1;
		}

		return new Term(semester, year);
	}

	protected boolean isCompletedSemester(Semester semester, int year) {
		if (getLastCompletedTerm() == null) {
			return false;
		} else {
			return (new Term(semester, year)).compareTo(getLastCompletedTerm()) < 1;
		}
	}

	public ArrayList<SemesterNode> getUnplannedNodes() {
		return new ArrayList<SemesterNode>();
	}

	public ArrayList<RequirementTree> getUndecidedBranches() {
		return branches;
	}

	public void recalculate() {
		semesters = new HashMap<Term, ArrayList<SemesterNode>>();

		for (ArrayList<SemesterNode> nodeList : nodes.values()) {
			for (SemesterNode node : nodeList) {
				if (!semesters.keySet().contains(node.getTerm())) {
					semesters.put(node.getTerm(), new ArrayList<SemesterNode>());
				}
				semesters.get(node.getTerm()).add(node);
			}
		}
	}

	private Term getNthTerm(Term base, int n) {
		Term term = new Term(base.getSemester(), base.getYear());

		int count = 0;
		while (count++ < n) {
			if (term.getSemester() == Semester.SPRING) {
				term.setSemester(Semester.FALL);
			} else {
				term.setSemester(Semester.SPRING);
				term.setYear(term.getYear() + 1);
			}
		}
		return term;
	}

	public void autoAssignUnplannedNodes() {
		Term start = getFirstPlannedTerm();
		if (start == null) {
			start = getNextTermFromLocalDate();
		}

		for (RequirementTree requirement : requirements) {
			for (RequirementTree branch : requirement.getBranches()) {
				if (branch.isLeaf()) {
					if (!branch.hasBranch()) {
						for (Course course : branch.getCourses()) {

							if (!nodes.keySet().contains(String.join("\0", branch.getPathFromRoot()))
								|| !nodes.get(String.join("\0", branch.getPathFromRoot())).stream()
										.map((n) -> n.getCourse())
										.collect(Collectors.toCollection(ArrayList::new))
										.contains(course)) {
								SemesterNode node = createNode(branch);
								node.setCourse(course);
								if (node.getRequirement().getSemester() != -1) {
									node.setTerm(getNthTerm(start, node.getRequirement().getSemester() - 1));
								}	
							}
						}
					} else {
						SemesterNode node = createNode(branch);
						if (node.getRequirement().getSemester() != -1) {
							node.setTerm(getNthTerm(start, node.getRequirement().getSemester() - 1));
						}
					}
				} else {
					if (!nodes.containsKey(branch.getPathFromRoot())) {
						branches.add(branch);
					}
				}
			}	
		}
	}
}
