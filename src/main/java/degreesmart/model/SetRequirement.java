package degreesmart.model;

import java.util.ArrayList;

public class SetRequirement extends Requirement {
	private ArrayList<RequirementSet> options;

	public SetRequirement(int choices) {
		super(choices);
		options = new ArrayList<RequirementSet>();
	}

	public ArrayList<RequirementSet> getSetOptions() {
		return options;
	}

	public boolean addSetOption(RequirementSet option) {
		if (!options.contains(option)) {
			return options.add(option);
		} else {
			return false;
		}
	}

	public boolean removeSetOption(RequirementSet option) {
		return options.remove(option);
	}

	public String toString() {
		if (options.size() == 0) {
			return "";
		}

		int max = 5;
		ArrayList<String> str = new ArrayList<String>();

		for (int i = 0; i < options.size(); i++) {
			if (options.size() > max + 1 && i == max) {
				str.add("... (+" + (options.size() - i) + " requirement sets)");
				break;
			} else {
				str.add(options.get(i).getName() + ", " + options.get(i).getType());
				for (int k = 0; k < options.get(i).getRequirements().size(); k++) {
					str.set(i, str.get(i) + "\n\t\t - " + options.get(i).getRequirements().get(k));
				}
			}	
		}

		return String.join("\n\t   or ", str);
	}
}