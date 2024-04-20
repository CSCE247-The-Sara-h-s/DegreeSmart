package degreesmart.model;

public class PlannedCourse implements Comparable<PlannedCourse> {
    private Course course;
    private Term term;

    public PlannedCourse(Course course, Semester semester, int year) {
        setCourse(course);
        term = new Term(semester, year);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) throws IllegalArgumentException {
        if (course == null) {
            throw new IllegalArgumentException("course cannot be null");
        }

        this.course = course;
    }

    public Semester getSemester() {
        return term.getSemester();
    }

    public void setSemester(Semester semester) {
        term.setSemester(semester);
    }

    public int getYear() {
        return term.getYear();
    }

    public void setYear(int year) {
        term.setYear(year);
    }

    public Term getTerm() {
        return term;
    }

    public int compareTo(PlannedCourse p) {
        return p.getTerm().compareTo(term);
    }

    public String toString() {
        return course.getShortName() + ", " + term;
    }

    public boolean equals(Object object) {
        if (object == null || ! (object instanceof PlannedCourse)) {
            return false;
        }
        PlannedCourse plannedCourse = (PlannedCourse)object;

        return plannedCourse.getCourse().equals(course)
            && plannedCourse.getYear() == term.getYear()
            && plannedCourse.getSemester().equals(term.getSemester());
    }
}
