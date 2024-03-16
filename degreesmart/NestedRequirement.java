package degreesmart;

import java.util.ArrayList;

public class NestedRequirement extends Requirement {
	private ArrayList<RequirementSet> requirementOptions;

	public NestedRequirement() {
		requirementOptions = new ArrayList<RequirementSet>();
	}

	public ArrayList<RequirementSet> getRequirementOptions() {
		return requirementOptions;
	}

	public boolean addRequirementOption(RequirementSet requirement) {
		if (!requirementOptions.contains(requirement)) {
			return requirementOptions.add(requirement);
		} else {
			return false;
		}
	}

	public boolean removeRequirementOption(RequirementSet requirement) {
		return requirementOptions.remove(requirement);
	}
}
