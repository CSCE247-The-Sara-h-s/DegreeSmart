package degreesmart;

public class PlannedCourse {
    private Course course;
    private int year;
    private Semester semester;

    public PlannedCourse(Course course, Semester semester, int year) {
        setCourse(course);
        setSemester(semester);
        setYear(year);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        // TODO - how should we validate the year?
        year = (year < 0)? 0 : year;
        this.year = year;   
    }
}
