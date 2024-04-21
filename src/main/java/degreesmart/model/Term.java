package degreesmart.model;

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

	public void setSemester(Semester semester) throws IllegalArgumentException {
		if (semester == null) {
            throw new IllegalArgumentException("semester cannot be null");
        }
		this.semester = semester;
	}

	public void setYear(int year) throws IllegalArgumentException {
		if (year <= 0) {
			throw new IllegalArgumentException("year cannot be less than one");
		}

		this.year = year;
	}

	public boolean equals(Object object) {
		if (object == null || ! (object instanceof Term)) {
			return false;
		}
		Term term = (Term)object;

		return semester.equals(term.getSemester())
			&& year == term.getYear();
	}

	public boolean isNewerThan(Term term) {
		return this.compareTo(term) > 0;
	}

	public boolean isOlderThan(Term term) {
		return this.compareTo(term) < 0;
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

	public String toString() {
		return semester + " " + year;
	}
}
