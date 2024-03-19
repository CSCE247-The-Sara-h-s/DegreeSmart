package degreesmart;

import java.util.ArrayList;

public class NestedRequirement extends Requirement {
	private ArrayList<Requirement> options;

	public NestedRequirement(int choices) {
		super(choices);
		options = new ArrayList<Requirement>();
	}

	public ArrayList<Requirement> getRequirementOptions() {
		return options;
	}

	public boolean addRequirementOption(Requirement option) {
		if (!options.contains(option)) {
			return options.add(option);
		} else {
			return false;
		}
	}

	public boolean removeRequirementOption(Requirement option) {
		return options.remove(option);
	}

	public String toString() {
		if (options.size() == 1) {
			return options.get(0).toString();
		}

		ArrayList<String> str = new ArrayList<String>();

		for (Requirement req : options) {
			str.add(req.toString().replaceAll("\n\t", "\n\t\t"));
		}

		return String.join("\n\t- ", str);
	}
}
