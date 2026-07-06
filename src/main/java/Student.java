import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Double> grades;
    private boolean isGraduate;

    public Student(String id, String name, String email, boolean isGraduate) {
        super(id, name, email);
        this.grades = new ArrayList<>();
        this.isGraduate = isGraduate;
    }

    public List<Double> getGrades() { return grades; }
    public void addGrade(double grade) { this.grades.add(grade); }
    public boolean isGraduate() { return isGraduate; }

    public double calculateGPA() {
        if (grades.isEmpty()) return 4.0;
        double sum = 0;
        for (double g : grades) sum += g;
        double avg = sum / grades.size();
        return isGraduate ? Math.max(0.0, (avg / 25) - 0.5) : (avg / 25);
    }

    @Override
    public String toString() {
        return super.toString() + ", Status: " + (isGraduate ? "Graduate" : "Undergraduate") + ", GPA: " + String.format("%.2f", calculateGPA());
    }
}