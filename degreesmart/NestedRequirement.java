package degreesmart;

import java.util.ArrayList;

public class NestedRequirement extends Requirement {
	private ArrayList<RequirementSet> options;

	public NestedRequirement() {
		options = new ArrayList<RequirementSet>();
	}

	public ArrayList<RequirementSet> getRequirementOptions() {
		return options;
	}

	public boolean addRequirementOption(RequirementSet option) {
		if (!options.contains(option)) {
			return options.add(option);
		} else {
			return false;
		}
	}

	public boolean removeRequirementOption(RequirementSet option) {
		return options.remove(option);
	}
}
