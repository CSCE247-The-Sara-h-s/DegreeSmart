package degreesmart;

public class Term implements Comparable<Term> {
	private Semester semester;
	private int year;

	public Term(Semester semester, int year) {
		setSemester(semester);
		setYear(year);
	}

	public Semester getSemester() {
		return semester;
	}

	public int getYear() {
		return year;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public void setYear(int year) {
		if (year < 0) {
			year = 0;
		}

		this.year = year;
	}

	public boolean equals(Object object) {
		if (object == null || ! (object instanceof Term)) {
			return false;
		}
		Term course = (Term)object;

		return semester.equals(course.getSemester())
			&& year == course.getYear();
	}

	public int compareTo(Term term) {
		if (year < term.getYear()) {
			return -1;
		} else if (year > term.getYear()) {
			return 1;
		} else {
			return semester.compareTo(term.getSemester());
		}
	}
}
