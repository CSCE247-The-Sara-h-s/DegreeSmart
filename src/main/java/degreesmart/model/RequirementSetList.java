package degreesmart.model;

import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

public class RequirementSetList {
	private ArrayList<RequirementSet> sets;
	private ArrayList<RequirementSet> majors;
	private ArrayList<RequirementSet> minors;
	private ArrayList<RequirementSet> applicationAreas;
	private HashMap<UUID, RequirementSet> uuidToSet;
	private HashMap<String, RequirementSet> fullNameToSet;
	private static RequirementSetList requirementSetList;

	private RequirementSetList() {
		sets = DataLoader.getInstance().sets;
		uuidToSet = DataLoader.getInstance().uuidToSet;
		majors = new ArrayList<RequirementSet>();
		minors = new ArrayList<RequirementSet>();
		applicationAreas = new ArrayList<RequirementSet>();
		fullNameToSet = new HashMap<String, RequirementSet>();

		for (RequirementSet set : sets) {
			fullNameToSet.put(getFullName(set), set);

			switch (set.getType()) {
				case MAJOR:
					majors.add(set);
					break;
				case MINOR:
					minors.add(set);
					break;
				case APPLICATION_AREA:
					applicationAreas.add(set);
					break;
			}
		}
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
		} while (uuidToSet.containsKey(uuid));
		return uuid;
	}

	private String getFullName(RequirementSet set) {
		return getFullName(set.getName(), set.getType());
	}

	private String getFullName(String name, RequirementType type) {
		return name + " " + type;
	}

	public ArrayList<RequirementSet> getRequirementSets() {
		return sets;
	}

	public RequirementSet getRequirementSet(UUID uuid) {
		return uuidToSet.get(uuid);
	}

	public RequirementSet getRequirementSet(String name, RequirementType type) {
		return fullNameToSet.get(name + " " + type);
	}

	public ArrayList<RequirementSet> getMajors() {
		return majors;
	}

	public ArrayList<RequirementSet> getMinors() {
		return minors;
	}

	public ArrayList<RequirementSet> getApplicationAreas() {
		return applicationAreas;
	}

	public RequirementSet createRequirementSet(String name, RequirementType type) {
		if (getRequirementSet(name, type) != null) {
			return null;
		} else {
			RequirementSet set = new RequirementSet(getNextUuid(), name, type);
			uuidToSet.put(set.getUuid(), set);
			fullNameToSet.put(getFullName(set), set);
			return set;
		}
	}

	public boolean deleteRequirementSet(RequirementSet set) {
		if (sets.remove(set)) {
			uuidToSet.remove(set.getUuid());
			fullNameToSet.remove(getFullName(set));
			return true;
		} else {
			return false;
		}
	}

	public boolean modifyRequirementSet(RequirementSet set) {
		RequirementSet old = uuidToSet.get(set.getUuid());

		if (old != null) {
			fullNameToSet.remove(getFullName(old));
			fullNameToSet.put(getFullName(set), set);
			uuidToSet.replace(set.getUuid(), set);
			return true;
		} else {
			return false;
		}
	}
}
