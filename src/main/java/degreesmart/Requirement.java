package degreesmart;

public class Requirement {
	private int choices;

	public Requirement() {
		choices = 1;
	}

	public Requirement(int choices) {
		this.choices = choices;
	}

	public int getNumChoices() {
		return choices;
	}

	public void setNumChoices(int choices) {
		this.choices = choices;
	}
}
