package degreesmart;

import java.util.ArrayList;

public class NestedRequirement extends Requirement {
	private ArrayList<Requirement> requirementOptions;

	public NestedRequirement() {
		
	}

	public ArrayList<Requirement> getRequirementOptions() {
		return new ArrayList<Requirement>();
	}

	public boolean addRequirementOption(Requirement requirement) {
		return true;
	}

	public boolean removeRequirementOption(Requirement requirement) {
		return true;
	}
}
