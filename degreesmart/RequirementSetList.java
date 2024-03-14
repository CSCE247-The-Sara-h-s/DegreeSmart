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

	public static RequirementSetList getInstance() {
		if (requirementSetList == null) {
			requirementSetList = new RequirementSetList();
		}
		return requirementSetList;
	}

	private UUID getNextUuid() {
		UUID uuid;
		do {
			uuid = UUID.randomUUID();
		} while (requirementSetsByUuid.containsKey(uuid));
		return uuid;
	}

	private String getCategoryNameKey(RequirementSet requirementSet) {
		return requirementSet.getCategory() + " " + requirementSet.getName();
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

	public RequirementSet createRequirementSet(RequirementSetCategory category, String name) {
		if (getRequirementSet(category, name) != null) {
			return null;
		} else {
			RequirementSet requirementSet = new RequirementSet(getNextUuid(), name, category);
			requirementSetsByUuid.put(requirementSet.getUuid(), requirementSet);
			uuidsByCategoryAndName.put(getCategoryNameKey(requirementSet), requirementSet.getUuid());
			return requirementSet;
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
