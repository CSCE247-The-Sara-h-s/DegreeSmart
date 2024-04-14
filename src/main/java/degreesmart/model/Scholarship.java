package degreesmart.model;
public class Scholarship {
    private String name;
    private double minGpa;
    private double minCreditHours;

    public Scholarship(String name) {
        this(name, 0.0, 0.0);
    }

    public Scholarship(String name, double minGpa, double minCreditHours) {
        setName(name);
        setMinGpa(minGpa);
        setMinCreditHours(minCreditHours);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinGpa() {
        return minGpa;
    }

    public void setMinGpa(double minGpa) {
        minGpa = (minGpa < 0)? 0.0 : minGpa;
        this.minGpa = minGpa;
    }

    public double getMinCreditHours() {
        return minCreditHours;
    }

    public void setMinCreditHours(double minCreditHours) {
        minCreditHours = (minCreditHours < 0)? 0.0 : minCreditHours;
        this.minCreditHours = minCreditHours;
    }
}
