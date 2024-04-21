package degreesmart.model;

import java.util.UUID;
import java.util.ArrayList;

public class Set {
	private String name;
	private RequirementTree requirements;

	public Set(ArrayList<RequirementTree> requirementTrees, RequirementType type) {
		requirements = new RequirementTree(type.toString(), 0);

		if (requirementTrees != null) {
			for (RequirementTree tree : requirementTrees) {
				requirements.addPath(tree);
			}
		}
	}

	public RequirementTree getRequirementTree() {
		return requirements;
	}

	public static RequirementTree generateComputerScience() {
		RequirementTree cs = new RequirementTree("MAJOR", 0);
		RequirementTree cs_root = new RequirementTree("Computer Science", 0);
		cs.addPath(cs_root);

		ArrayList<Course> speaking = new ArrayList<Course>();
		ArrayList<Course> writing = new ArrayList<Course>();
		ArrayList<Course> phys1 = new ArrayList<Course>();
		ArrayList<Course> phys2 = new ArrayList<Course>();
		ArrayList<Course> chem1 = new ArrayList<Course>();
		ArrayList<Course> chem2 = new ArrayList<Course>();

		ArrayList<Course> courses;

		for (Course course : CourseList.getInstance().getCourses()) {
			courses = new ArrayList<Course>();
			courses.add(course);

			switch (course.getShortName()) {
			case "ENGL 101":
			case "MATH 141":
			case "CSCE 145":
			case "CSCE 190":
				cs_root.addPath(new RequirementTree("", 1, courses, Grade.C, 1));
				break;
			case "ENGL 102":
			case "MATH 142":
			case "CSCE 146":
			case "CSCE 215":
				cs_root.addPath(new RequirementTree("", 1, courses, Grade.C, 2));
				break;
			case "CSCE 211":
			case "CSCE 240":
			case "MATH 374":
				cs_root.addPath(new RequirementTree("", 1, courses, Grade.C, 3));
				break;
			case "CSCE 212":
			case "CSCE 247":
				cs_root.addPath(new RequirementTree("", 1, courses, Grade.C, 4));
				break;
			case "MATH 241":
				cs_root.addPath(new RequirementTree("", 1, courses, 4));
				break;
			case "CSCE 311":
			case "CSCE 330":
			case "CSCE 350":
			case "CSCE 390":
				cs_root.addPath(new RequirementTree("", 1, courses, Grade.C, 5));
				break;
			case "CSCE 416":
				cs_root.addPath(new RequirementTree("", 1, courses, Grade.C, 6));
				break;
			case "STAT 509":
				cs_root.addPath(new RequirementTree("", 1, courses, 6));
				break;
			case "CSCE 490":
			case "CSCE 355":
				cs_root.addPath(new RequirementTree("", 1, courses, Grade.C, 7));
				break;
			case "MATH 344":
			case "MATH 344L":
				cs_root.addPath(new RequirementTree("", 1, courses, 7));
				break;
			case "CSCE 492":
				cs_root.addPath(new RequirementTree("", 1, courses, Grade.C, 8));
				break;
			case "SPCH 140":
			case "SPCH 145":
			case "SPCH 230":
				speaking.add(course);
				break;
			case "PHYS 211":
			case "PHYS 211L":
				phys1.add(course);
				break;
			case "PHYS 212":
			case "PHYS 212L":
				phys2.add(course);
				break;
			case "CHEM 111":
			case "CHEM 111L":
				chem1.add(course);
				break;
			case "CHEM 112":
			case "CHEM 112L":
				chem2.add(course);
				break;
			case "ENGL 462":
			case "ENGL 463":
				writing.add(course);
				break;
			}
		}

		RequirementTree phys_or_chem = new RequirementTree("Physics or Chemisty", 1);
		cs_root.addPath(phys_or_chem);
		RequirementTree phys_path = new RequirementTree("Physics", 0);
		RequirementTree chem_path = new RequirementTree("Chemistry", 0);
		phys_or_chem.addPath(phys_path);
		phys_or_chem.addPath(chem_path);

		chem_path.addPath(new RequirementTree("", 0, chem1, Grade.C, 2));
		phys_path.addPath(new RequirementTree("", 0, phys1, Grade.C, 2));
		chem_path.addPath(new RequirementTree("", 0, chem2, 3));
		phys_path.addPath(new RequirementTree("", 0, phys2, 3));

		cs_root.addPath(new RequirementTree(
			"Business or Technical Writing", 1, writing, 5));

		cs_root.addPath(new RequirementTree(
			"Carolina Core - Effective, Engaged, and Persuasive Communication", 1, speaking, 3));

		RequirementTree cs_app_area = new RequirementTree("Application Area", 1);
		RequirementTree generic_apps = new RequirementTree("Generic", 0);
		RequirementTree specific = new RequirementTree("TODO - specific", 0);
		cs_app_area.addPath(generic_apps);
		cs_app_area.addPath(specific);

		RequirementTree app_classes = new RequirementTree("Application Area Elective", 1);
		RequirementTree csce_classes = new RequirementTree("Computer Science Major Elective", 3, 
			((CourseRequirement)
				RequirementSetList.getInstance().getApplicationAreas().get(0)
					.getRequirements().get(0)).getCourseOptions(), Grade.C, -1);
		generic_apps.addPath(app_classes);
		generic_apps.addPath(csce_classes);
		cs_root.addPath(cs_app_area);

		for (RequirementSet rs : RequirementSetList.getInstance().getApplicationAreas()) {
			app_classes.addPath(new RequirementTree(rs.getName().replace(" Generic Application Area -", ""), 3, 
				((CourseRequirement) rs.getRequirements().get(1)).getCourseOptions(), -1));
		}

		for (RequirementSet rs : RequirementSetList.getInstance().getRequirementSets()) {
			if (rs.getType() == RequirementType.APPLICATION_AREA) {
				continue;
			}
			switch (rs.getName() + " " + rs.getType()) {
			case "AIU CAROLINA_CORE":
				cs_root.addPath(new RequirementTree("Carolina Core - Aesthetic and Interpretive Understanding", 1, 
					((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions(), 1));
				break;
			case "SCI CAROLINA_CORE":
				// cs_root.addPath(new RequirementTree("Laboratory Science Elective", 1, 
				// 	((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions()));
				break;
			case "GSS CAROLINA_CORE":
				cs_root.addPath(new RequirementTree("Carolina Core - Social Sciences", 1, 
					((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions(), 4));
				break;
			case "GHS CAROLINA_CORE":
				cs_root.addPath(new RequirementTree("Carolina Core - Historical Thinking", 1, 
					((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions(), 8));
				break;
			case "Liberal Arts ELECTIVE":
				RequirementTree liberal_arts = new RequirementTree("", 0);
				cs_root.addPath(liberal_arts);
				liberal_arts.addPath(new RequirementTree("Liberal Arts Elective", 1, 
					((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions(), 6));
				liberal_arts.addPath(new RequirementTree("Liberal Arts Elective", 1, 
					((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions(), 8));
				liberal_arts.addPath(new RequirementTree("Liberal Arts Elective", 1, 
					((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions(), 8));
				break;
			case "Computer Information Systems MAJOR":
				break;
			case "Computer Science MAJOR":
			case "Business Information Management MINOR":
			}
		}

		RequirementTree gfl = new RequirementTree(
			"Carolina Core - Global Citizenship and Multicultural Understanding", 1);
		String uuid;

		courses = new ArrayList<Course>();
		uuid = "444eb709-c8c3-43d8-ad00-1bcb581f807d";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("Arabic", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "310408af-87a5-49a6-b32d-ec583e44ba6b";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("Mandarin Chinese", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "8699fb7e-844c-4fcd-b212-7a5ac8f5303e";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		uuid = "06fd9a7c-6141-4ca3-ba63-efc1c4f8dce4";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("French", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "ffb67b43-b300-4eb9-a1b2-7665fc643050";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		uuid = "a44d65c9-5d2e-4b7f-8d18-83c2fb1ecf0e";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("German", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "b513385f-de6c-4368-a8fa-e004f75fa3d4";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("Ancient Greek", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "ced4953c-d6b7-4a67-9f19-1bcfae7c5ad5";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("Italian", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "dc653f65-c00f-4472-b479-ace3bd80f53c";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("Japanese", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "ba1a7952-7839-4445-8623-e570c28596ab";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		uuid = "52444a70-db5d-4df8-8fc3-d53a5a0352e7";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("Latin", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "6a54f445-2f30-4584-8398-d9a1ff696683";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("Portuguese", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "63c4691b-181a-4c1d-875c-1c799dc01a5a";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("Russian", 0, courses, -1));

		courses = new ArrayList<Course>();
		uuid = "4038e4be-d18e-4287-8938-16341579d5a1";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		uuid = "d7ba67f4-be30-43bf-bdd2-eaa3ee4df143";
		courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		gfl.addPath(new RequirementTree("Spanish", 0, courses, -1));

		cs_root.addPath(gfl);

		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getPathFromRoot());

		return cs;
	}

	public static Set generateComputerInformationSystems() {
		RequirementTree cs_root = new RequirementTree("Computer Information Systems", 0);
		ArrayList<RequirementTree> tmp = new ArrayList<RequirementTree>();
		tmp.add(cs_root);

		Set cs = new Set(tmp, RequirementType.MAJOR);

		// ArrayList<Course> speaking = new ArrayList<Course>();
		// ArrayList<Course> writing = new ArrayList<Course>();
		// ArrayList<Course> phys = new ArrayList<Course>();
		// ArrayList<Course> chem = new ArrayList<Course>();

		// ArrayList<Course> courses;

		// for (Course course : CourseList.getInstance().getCourses()) {
		// 	switch (course.getShortName()) {
		// 	case "ENGL 101":
		// 	case "MATH 141":
		// 	case "CSCE 145":
		// 	case "CSCE 190":
		// 	case "ENGL 102":
		// 	case "MATH 142":
		// 	case "CSCE 146":
		// 	case "CSCE 215":
		// 	case "CSCE 211":
		// 	case "CSCE 240":
		// 	case "MATH 374":
		// 	case "CSCE 212":
		// 	case "CSCE 247":
		// 		courses = new ArrayList<Course>();
		// 		courses.add(course);
		// 		cs_root.addPath(new RequirementTree("", 1, courses, Grade.C));
		// 		break;
		// 	case "MATH 241":
		// 		courses = new ArrayList<Course>();
		// 		courses.add(course);
		// 		cs_root.addPath(new RequirementTree("", 1, courses));
		// 		break;
		// 	case "CSCE 311":
		// 	case "CSCE 330":
		// 	case "CSCE 350":
		// 	case "CSCE 390":
		// 	case "CSCE 416":
		// 		courses = new ArrayList<Course>();
		// 		courses.add(course);
		// 		cs_root.addPath(new RequirementTree("", 1, courses, Grade.C));
		// 		break;
		// 	case "STAT 509":
		// 		courses = new ArrayList<Course>();
		// 		courses.add(course);
		// 		cs_root.addPath(new RequirementTree("", 1, courses));
		// 		break;
		// 	case "CSCE 490":
		// 	case "CSCE 355":
		// 		courses = new ArrayList<Course>();
		// 		courses.add(course);
		// 		cs_root.addPath(new RequirementTree("", 1, courses, Grade.C));
		// 		break;
		// 	case "MATH 344":
		// 	case "MATH 344L":
		// 		courses = new ArrayList<Course>();
		// 		courses.add(course);
		// 		cs_root.addPath(new RequirementTree("", 1, courses));
		// 		break;
		// 	case "CSCE 492":
		// 		courses = new ArrayList<Course>();
		// 		courses.add(course);
		// 		cs_root.addPath(new RequirementTree("", 1, courses, Grade.C));
		// 		break;
		// 	case "SPCH 140":
		// 	case "SPCH 145":
		// 	case "SPCH 230":
		// 		speaking.add(course);
		// 		break;
		// 	case "PHYS 211":
		// 	case "PHYS 211L":
		// 	case "PHYS 212":
		// 	case "PHYS 212L":
		// 		phys.add(course);
		// 		break;
		// 	case "CHEM 111":
		// 	case "CHEM 111L":
		// 	case "CHEM 112":
		// 	case "CHEM 112L":
		// 		chem.add(course);
		// 		break;
		// 	case "ENGL 462":
		// 	case "ENGL 463":
		// 		writing.add(course);
		// 		break;
		// 	}
		// }

		// RequirementTree phys_or_chem = new RequirementTree("Physics or Chemistry", 1);
		// phys_or_chem.addPath(new RequirementTree("", 0, phys, Grade.C));
		// phys_or_chem.addPath(new RequirementTree("", 0, chem));

		// cs_root.addPath(new RequirementTree(
		// 	"Business or Technical Writing", 1, writing));

		// cs_root.addPath(new RequirementTree(
		// 	"Carolina Core - Effective, Engaged, and Persuasive Communication: Spoken Component", 1, speaking));

		// RequirementTree cs_app_area = new RequirementTree("Application Area", 0);
		// RequirementTree app_classes = new RequirementTree("", 1);
		// RequirementTree csce_classes = new RequirementTree("", 3, 
		// 	((CourseRequirement)
		// 		RequirementSetList.getInstance().getApplicationAreas().get(0)
		// 			.getRequirements().get(0)).getCourseOptions(), Grade.C);
		// cs_app_area.addPath(app_classes);
		// cs_app_area.addPath(csce_classes);
		// cs_root.addPath(cs_app_area);

		// for (RequirementSet rs : RequirementSetList.getInstance().getApplicationAreas()) {
		// 	app_classes.addPath(new RequirementTree(rs.getName().replace(" Application Area -", ""), 3, 
		// 		((CourseRequirement) rs.getRequirements().get(1)).getCourseOptions()));
		// }

		// for (RequirementSet rs : RequirementSetList.getInstance().getRequirementSets()) {
		// 	if (rs.getType() == RequirementType.APPLICATION_AREA) {
		// 		continue;
		// 	}
		// 	System.out.println(rs.getName() + " " + rs.getType());
		// 	switch (rs.getName() + " " + rs.getType()) {
		// 	case "AIU - CAROLINA_CORE":
		// 		cs_root.addPath(new RequirementTree("Carolina Core - Aesthetic and Interpretive Understanding", 1, 
		// 			((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions()));
		// 		break;
		// 	case "SCI - CAROLINA_CORE":
		// 		cs_root.addPath(new RequirementTree("Laboratory Science Elective", 1, 
		// 			((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions()));
		// 		break;
		// 	case "GSS - CAROLINA_CORE":
		// 		cs_root.addPath(new RequirementTree("Carolina Core - Social Sciences", 1, 
		// 			((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions()));
		// 		break;
		// 	case "GHS - CAROLINA_CORE":
		// 		cs_root.addPath(new RequirementTree("Carolina Core - Historical Thinking", 1, 
		// 			((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions()));
		// 		break;
		// 	case "Liberal Arts - ELECTIVE":
		// 		cs_root.addPath(new RequirementTree("Liberal Arts Elective", 3, 
		// 			((CourseRequirement) rs.getRequirements().get(0)).getCourseOptions()));
		// 		break;
		// 	case "Computer Information Systems MAJOR":
		// 		System.out.println(rs);
		// 		break;
		// 	case "Computer Science MAJOR":
		// 	case "Business Information Management MINOR":
		// 	}
		// }

		// RequirementTree gfl = new RequirementTree(
		// 	"Carolina Core - Global Citizenship and Multicultural Understanding: Foreign Language", 1);
		// String uuid;

		// courses = new ArrayList<Course>();
		// uuid = "444eb709-c8c3-43d8-ad00-1bcb581f807d";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Arabic", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "310408af-87a5-49a6-b32d-ec583e44ba6b";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Mandarin Chinese", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "8699fb7e-844c-4fcd-b212-7a5ac8f5303e";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "06fd9a7c-6141-4ca3-ba63-efc1c4f8dce4";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("French", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "ffb67b43-b300-4eb9-a1b2-7665fc643050";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "a44d65c9-5d2e-4b7f-8d18-83c2fb1ecf0e";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("German", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "b513385f-de6c-4368-a8fa-e004f75fa3d4";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Ancient Greek", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "ced4953c-d6b7-4a67-9f19-1bcfae7c5ad5";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Italian", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "dc653f65-c00f-4472-b479-ace3bd80f53c";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Japanese", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "ba1a7952-7839-4445-8623-e570c28596ab";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "52444a70-db5d-4df8-8fc3-d53a5a0352e7";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Latin", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "6a54f445-2f30-4584-8398-d9a1ff696683";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Portuguese", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "63c4691b-181a-4c1d-875c-1c799dc01a5a";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Russian", 0, courses));

		// courses = new ArrayList<Course>();
		// uuid = "4038e4be-d18e-4287-8938-16341579d5a1";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "d7ba67f4-be30-43bf-bdd2-eaa3ee4df143";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Spanish", 0, courses));

		// cs_root.addPath(gfl);

		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getPathFromRoot());

		return cs;
	}


	public static void getGfl2() {
		// courses = new ArrayList<Course>();
		// uuid = "444eb709-c8c3-43d8-ad00-1bcb581f807d";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "9ebb028a-6840-43cd-a30d-be8b0f6d2f17";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Arabic", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "310408af-87a5-49a6-b32d-ec583e44ba6b";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "5852e7df-29ef-47e0-a5a8-46294af8f1cf";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Mandarin Chinese", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "8699fb7e-844c-4fcd-b212-7a5ac8f5303e";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "06fd9a7c-6141-4ca3-ba63-efc1c4f8dce4";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "6ecbbe93-a491-488a-a614-23026dd85243";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("French", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "ffb67b43-b300-4eb9-a1b2-7665fc643050";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "a44d65c9-5d2e-4b7f-8d18-83c2fb1ecf0e";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "5e33b669-c8a0-48d2-ac8a-42b6ad234100";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("German", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "b513385f-de6c-4368-a8fa-e004f75fa3d4";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "6bd396d9-76a5-40f6-aec0-63208d3dab00";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Ancient Greek", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "ced4953c-d6b7-4a67-9f19-1bcfae7c5ad5";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "e558945a-abb7-492c-9b1f-7d2e7d42e9e8";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Italian", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "dc653f65-c00f-4472-b479-ace3bd80f53c";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "00f9c462-2ef1-41fb-bfb7-a6ae18fd92fb";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Japanese", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "ba1a7952-7839-4445-8623-e570c28596ab";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "52444a70-db5d-4df8-8fc3-d53a5a0352e7";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "daf610a1-c9fc-4074-ae24-4aa7e2d0f790";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Latin", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "6a54f445-2f30-4584-8398-d9a1ff696683";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "1f2a433f-87c3-4f29-b1ce-bdce7b667bc2";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Portuguese", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "63c4691b-181a-4c1d-875c-1c799dc01a5a";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "af45d508-bcc8-4655-9411-3b4be65d42a6";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Russian", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());

		// courses = new ArrayList<Course>();
		// uuid = "4038e4be-d18e-4287-8938-16341579d5a1";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "d7ba67f4-be30-43bf-bdd2-eaa3ee4df143";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// uuid = "e26d409b-bc8c-4230-b0df-141a0688a262";
		// courses.add(CourseList.getInstance().getCourse(UUID.fromString(uuid)));
		// gfl.addPath(new RequirementTree("Spanish", 0, courses));
		// System.out.println("\n\n\n\n\n");
		// System.out.println(gfl.getPaths().get(gfl.getPaths().size() - 1).getName() + "  -  " +gfl.getPaths().get(gfl.getPaths().size() - 1).getCourses());
	}
}
