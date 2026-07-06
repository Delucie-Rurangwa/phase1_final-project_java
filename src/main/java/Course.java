import java.util.HashMap;
import java.util.Map;

public class Course {
    private String courseCode;
    private String courseName;
    private Map<String, Student> enrolledStudents;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.enrolledStudents = new HashMap<>();
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public Map<String, Student> getEnrolledStudents() { return enrolledStudents; }

    @Override
    public String toString() {
        return "Course: [" + courseCode + "] " + courseName + " | Total Enrolled: " + enrolledStudents.size();
    }
}