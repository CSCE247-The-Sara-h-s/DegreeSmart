package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class RequirementSetList {
	private ArrayList<RequirementSet> requirementSets;
	private HashMap<UUID, RequirementSet> requirementSetsByUuid;
	private HashMap<String, UUID> uuidsByCategoryAndName;
	private static final RequirementSetList requirementSetList;

	private RequirementSetList() {

	}

	public RequirementSetList getInstance() {
		return this();
	}

	public ArrayList<RequirementSet> getRequirementSets() {
		return new ArrayList<RequirementSet>();
	}

	public RequirementSet getRequirementSet(UUID uuid) {
		return new RequirementSet();
	}

	public RequirementSet getRequirementSet(RequirementSetCategory category, String name) {
		return new RequirementSet();
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
