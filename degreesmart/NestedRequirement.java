package degreesmart;

import java.util.ArrayList;

public class NestedRequirement extends Requirement {
	private ArrayList<Requirement> requirementOptions;

	public NestedRequirement() {
		requirementOptions = new ArrayList<Requirement>();
	}

	public ArrayList<Requirement> getRequirementOptions() {
		return requirementOptions;
	}

	public boolean addRequirementOption(Requirement requirement) {
		if (!requirementOptions.contains(requirement)) {
			return requirementOptions.add(requirement);
		} else {
			return false;
		}
	}

	public boolean removeRequirementOption(Requirement requirement) {
		return requirementOptions.remove(requirement);
	}
}
