package degreesmart;

import java.util.ArrayList;

public class Transcript {
    private final double QUALITY_POINT_STEP = 0.5;
    private ArrayList<CompletedCourse> completedCourses;
    private double gpa;
    private double completedCreditHours;

    public Transcript() {
        completedCourses = new ArrayList<CompletedCourse>();
        gpa = 0.0;
        completedCreditHours = 0.0;
    }

    public ArrayList<CompletedCourse> getCompletedCourses() {
        return completedCourses;
    }

    public boolean addCompletedCourse(CompletedCourse completedCourse) {
        if (completedCourses.add(completedCourse)) {
            completedCreditHours += completedCourse.getCourse().getCreditHours();
            updateGpa();
            return true;
        } else {
            return false;
        }
    }

    public boolean removeCompletedCourse(CompletedCourse completedCourse) {
        if (completedCourses.remove(completedCourse)) {
            completedCreditHours -= completedCourse.getCourse().getCreditHours();
            updateGpa();
            return true;
        } else {
            return false;
        }
    }

    public double getGpa() {
        return gpa;
    }

    public double getCompletedCreditHours() {
        return completedCreditHours;
    }

    private void updateGpa() {
        double totalEarnedHours = 0.0;

        for (CompletedCourse completedCourse : completedCourses) {
            double courseEarnedHours = 0.0;
            switch (completedCourse.getGrade()) {
                case A:
                    courseEarnedHours = 4.0;
                    break;
                case B_PLUS:
                    courseEarnedHours = 3.5;
                    break;
                case B:
                    courseEarnedHours = 3.0;
                    break;
                case C_PLUS:
                    courseEarnedHours = 2.5;
                    break;
                case C:
                    courseEarnedHours = 2.0;
                    break;
                case D_PLUS:
                    courseEarnedHours = 1.5;
                    break;
                case D:
                    courseEarnedHours = 1.0;
                    break;
                case F:
                    courseEarnedHours = 0.0;
                    break;
            }

            totalEarnedHours += courseEarnedHours * completedCourse.getCourse().getCreditHours();
        }

        gpa = totalEarnedHours / completedCreditHours;
    }
}
