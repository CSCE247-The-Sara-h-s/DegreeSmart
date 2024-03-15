package degreesmart;

public class PlannedCourse {
    private Course course;
    private Term term;

    public PlannedCourse(Course course, Term term) {
        setCourse(course);
        setTerm(term);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
