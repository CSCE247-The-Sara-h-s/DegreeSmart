package degreesmart;

public class RequirementSet {
	private String name;
	private RequirementSetCategory category;
	private ArrayList<Requirement> requirements;

	public RequirementSet(String name, RequirementSetCategory category) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {

	}

	public RequirementSetCategory getCategory() {
		return category;
	}

	public void setCategory(RequirementSetCategory category) {

	}

	public ArrayList<Requirement> getRequirements() {
		return requirements;
	}

	public boolean addRequirement(Requirement requirement) {
		return true;
	}

	public boolean removeRequirement(Requirement requirement) {
		return true;
	}
}
