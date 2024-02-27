package degreesmart;

import java.util.ArrayList;

public class DegreeSmartUI {
	private Application application;

	public DegreeSmartUI() {
		application = Application.getInstance();
	}

	public void run() {
		ArrayList<Runnable> scenarios = new ArrayList<>(); // https://stackoverflow.com/a/67292304
		scenarios.add(() -> scenario1());
		scenarios.add(() -> scenario2());
		scenarios.add(() -> scenario3());

		for (int i = 0; i < scenarios.size(); i++) {
			System.out.println("\n  ------- Scenario #" + (i + 1) + " ------- ");
			try {
				scenarios.get(i).run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void scenario1() {

	}

	public void scenario2() {

	}

	public void scenario3() {

	}

	public static void main(String args[]) {
		DegreeSmartUI degreeSmartInterface = new DegreeSmartUI();

		degreeSmartInterface.run();
	}
}
