package degreesmart;

public class CompletedCourse {
	private Course course;
	private Grade grade;
	private Semester semester;
	private int year;

	public CompletedCourse(Course course, Grade grade, Semester semester, int year) {
		this.course = course;
		this.grade = grade;
		this.semester = semester;
		this.year = year;
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
		// does a failed class count as a completed course?
		this.grade = grade;
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
		this.year = year;
	}
}
