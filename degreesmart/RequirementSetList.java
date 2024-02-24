package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class RequirementSetList {
	private ArrayList<RequirementSet> requirementSets;
	private HashMap<UUID, RequirementSet> requirementSetsByUuid;
	private HashMap<String, UUID> uuidsByCategoryAndName;
	private static RequirementSetList requirementSetList;

	private RequirementSetList() {}

	public RequirementSetList getInstance() {
		return new RequirementSetList();
	}

	public ArrayList<RequirementSet> getRequirementSets() {
		return new ArrayList<RequirementSet>();
	}

	public RequirementSet getRequirementSet(UUID uuid) {
		return requirementSets.get(0);
	}

	public RequirementSet getRequirementSet(RequirementSetCategory category, String name) {
		return requirementSets.get(0);
	}

	public boolean createRequirementSet(RequirementSet requirementSet) {
		return true;
	}

	public boolean deleteRequirementSet(RequirementSet requirementSet) {
		return true;
	}

	public boolean modifyRequirementSet(RequirementSet requirementSet) {
		return true;
	}
}
