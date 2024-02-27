package degreesmart;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class RequirementSetList {
	private ArrayList<RequirementSet> requirementSets;
	private HashMap<UUID, RequirementSet> requirementSetsByUuid;
	private HashMap<String, UUID> uuidsByCategoryAndName;
	private static RequirementSetList requirementSetList;

	private RequirementSetList() {
		ArrayList<RequirementSet> requirementSets = new ArrayList<RequirementSet>();
		HashMap<UUID, RequirementSet> requirementSetsByUuid = new HashMap<UUID, RequirementSet>();
		HashMap<String, UUID> uuidsByCategoryAndName = new HashMap<String, UUID>();
	}

	public RequirementSetList getInstance() {
		if (requirementSetList == null) {
			requirementSetList = new RequirementSetList();
		}
		return requirementSetList;
	}

	public ArrayList<RequirementSet> getRequirementSets() {
		return requirementSets;
	}

	public RequirementSet getRequirementSet(UUID uuid) {
		return requirementSetsByUuid.get(uuid);
	}

	public RequirementSet getRequirementSet(RequirementSetCategory category, String name) {
		return getRequirementSet(uuidsByCategoryAndName.get(category + " " + name));
	}

	public boolean createRequirementSet(RequirementSet requirementSet) {
		if (!requirementSetsByUuid.containsKey(requirementSet.getUuid())) {
			uuidsByCategoryAndName.put(requirementSet.getCategory() + " " + requirementSet.getName(), requirementSet.getUuid());
			requirementSetsByUuid.put(requirementSet.getUuid(), requirementSet);
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteRequirementSet(RequirementSet requirementSet) {
		if (requirementSets.remove(requirementSet)) {
			requirementSetsByUuid.remove(requirementSet.getUuid());
			uuidsByCategoryAndName.remove(requirementSet.getCategory() + " " + requirementSet.getName());
			return true;
		} else {
			return false;
		}
	}

	public boolean modifyRequirementSet(RequirementSet requirementSet) {
		RequirementSet old = requirementSetsByUuid.get(requirementSet.getUuid());

		if (old != null) {
			uuidsByCategoryAndName.remove(old.getCategory() + " " + old.getName());
			uuidsByCategoryAndName.put(requirementSet.getCategory() + " " + requirementSet.getName(), requirementSet.getUuid());
			requirementSetsByUuid.replace(requirementSet.getUuid(), requirementSet);
			return true;
		} else {
			return false;
		}
	}
}
