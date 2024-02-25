package degreesmart;

public abstract class Requirement {
	private int numChoices;

	public Requirement() {
		numChoices = 1;
	}

	public Requirement(int numChoices) {
		this.numChoices = numChoices;
	}

	public int getNumChoices() {
		return numChoices;
	}

	public void setNumChoices(int numChoices) {
		this.numChoices = numChoices;
	}
}
