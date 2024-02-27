package degreesmart;

import java.util.ArrayList;

public class Transcript {
    private ArrayList<CompletedCourse> completedCourses;
    private double gpa;
    private double completedCreditHours;

    public Transcript() {

    }

    public ArrayList<CompletedCourse> getCompletedCourses() {
        return completedCourses;
    }

    public boolean addCompletedCourse(CompletedCourse course) {
        return true;
    }

    public boolean removeCompletedCourse(CompletedCourse course) {
        return true;
    }

    public double getGpa() {
        return 0.0;
    }

    public double getCompletedCreditHours() {
        return 0.0;
    }

    private void updateGpa() {

    }

    private void updateCompletedCreditHours() {

    }
}
