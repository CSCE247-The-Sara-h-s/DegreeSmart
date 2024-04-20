package degreesmart.model;

public class CompletedCourse implements Comparable<CompletedCourse> {
	private Course course;
	private Grade grade;
	private Term term;

	public CompletedCourse(Course course, Grade grade, Semester semester, int year) {
		this.course = course;
		this.grade = grade;
		term = new Term(semester, year);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
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

	public int compareTo(CompletedCourse c) {
        return c.getTerm().compareTo(term);
    }

	public String toString() {
		return course.getShortName() + ", "
			+ course.getCreditHours() + ", "
			+ grade + ", "
			+ term;
	}
}
