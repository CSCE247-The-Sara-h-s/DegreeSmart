package degreesmart;

import java.util.UUID;
import java.util.ArrayList;

public class RequirementSet {
	private UUID uuid;
	private String name;
	private RequirementSetCategory category;
	private ArrayList<Requirement> requirements;

	public RequirementSet(UUID uuid, String name, RequirementSetCategory category) {
		this.uuid = uuid;
		this.name = name;
		this.category = category;
		requirements = new ArrayList<Requirement>();
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RequirementSetCategory getCategory() {
		return category;
	}

	public void setCategory(RequirementSetCategory category) {
		this.category = category;
	}

	public ArrayList<Requirement> getRequirements() {
		return requirements;
	}

	public boolean addRequirement(Requirement requirement) {
		return requirements.add(requirement);
	}

	public boolean removeRequirement(Requirement requirement) {
		return requirements.remove(requirement);
	}

	public boolean equals(Object object) {
		if (object == null || ! (object instanceof RequirementSet)) {
			return false;
		}
		RequirementSet requirementSet = (RequirementSet)object;

		return uuid.equals(requirementSet.getUuid());
	}

	public String toString() {
		return ""
			+ "        uuid: " + uuid
			+ "    category: " + category
			+ "requirements: " + "TODO";
	}
}
