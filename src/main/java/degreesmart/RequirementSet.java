package degreesmart;

import java.util.UUID;
import java.util.ArrayList;

public class RequirementSet {
	private UUID uuid;
	private String name;
	private RequirementType type;
	private ArrayList<Requirement> requirements;
	private ArrayList<RequirementSet> requirementSets;

	public RequirementSet(UUID uuid, String name, RequirementType type) {
		this.uuid = uuid;
		this.name = name;
		this.type = type;
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

	public RequirementType getType() {
		return type;
	}

	public void setType(RequirementType type) {
		this.type = type;
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
		ArrayList<String> reqList = new ArrayList<String>();
		for (Requirement req : requirements) {
			reqList.add(req.toString());
		}

		String reqStr = "";
		if (requirements.size() > 0) {
			reqStr = "\n\t-  " + String.join("\n\t-  ", reqList);
		}

		return name + " - " + type + reqStr;
	}
}
