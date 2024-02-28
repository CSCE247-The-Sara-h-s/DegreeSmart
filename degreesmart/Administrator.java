package degreesmart;

import java.util.UUID;

public class Administrator extends User {
  public Administrator(UUID uuid, String username, String password, String email, String firstName, 
      String lastName) {
    super(uuid, username, password, email, firstName, lastName);
  }

  public void approveAdvisor(Advisor advisor) {

  }

  public void addAssignedStudent(Advisor advisor, Student student) {

  }

  public void removeAssignedStudent(Advisor advisor, Student student) {

  }

  public void createCourse(Course course) {

  }

  public void deleteCourse(Course course) {

  }

  public void modifyCourse(Course modifiedCourse) {

  }

  public void createRequirementSet(RequirementSet requirementSet) {

  }

  public void deleteRequirementSet(RequirementSet requirementSet) {

  }

  public void modifyRequirementSet(RequirementSet modifiedSet) {
    
  }

}

