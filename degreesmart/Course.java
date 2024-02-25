package degreesmart;
import java.util.ArrayList;

import javax.security.auth.Subject;

public class Course {
    private String id;
    private String subject;
    private String number;
    private String name;
    private ArrayList<CoureRequirement> prerequisites;
    private ArrayList<Course> corequisities;
    private ArrayList<Semester> semesterOffered;
    private ArrayList<CoreCategory> coreCategories;
    private double creditHours;

    public Course(String subject, String name){

    }

    public String getSystemId(){

    }

    public Subject getSubject(){

    }

    public void setSubject(Subject subject){

    }

    public String getNumber(){

    }

    public void setNumber(String number){

    }

    public String getName(){

    }

    public void setName(String name){

    }

    public ArrayList<Prerequisite> getPrerequisites(){

    }

    public void addPrerequisite(Prerequisite prerequisite){

    }

    public void removePrerequisite(Prerequisite prerequisite){

    }

    public ArrayList<Course> getCorequisities(){

    }

    public void addCorequisite(Course course){

    }

    public void removeCorequisite(Course course){

    }

    public ArrayList<Semester> getSemesterOffered(){

    }

    public void addSemesterOffered(Semester semester){

    }

    public void removeSemesterOffered(Semester semester){

    }

    public ArrayList<CoreCategory> getCoreCategories(){

    }

    public void addCoreCategory(CoreCategory category){

    }

    public void removeCoreCategory(CoreCategory category){

    }

    public double getCreditHoure(){

    }

    public void setCreditHours(double creditHours){
        
    }

}
