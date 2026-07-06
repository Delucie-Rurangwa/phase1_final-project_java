import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDAO {
    public void saveCourse(Course course) {
        String sql = "INSERT INTO courses (course_code, course_name) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.executeUpdate();
            System.out.println("Course saved to database successfully.");
        } catch (SQLException e) {
            System.err.println("Error saving course: " + e.getMessage());
        }
    }

    public void enrollStudentInCourse(String studentId, String courseCode) {
        String sql = "INSERT INTO enrollments (student_id, course_code) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            stmt.setString(2, courseCode);
            stmt.executeUpdate();
            System.out.println("Enrollment linked successfully.");
        } catch (SQLException e) {
            System.err.println("Error during enrollment setup: " + e.getMessage());
        }
    }

    public void displayEnrolledStudentsWithCourses() {
        String sql = "SELECT s.id AS student_id, s.name AS student_name, c.course_code, c.course_name " +
                "FROM enrollments e " +
                "JOIN students s ON e.student_id = s.id " +
                "JOIN courses c ON e.course_code = c.course_code";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n=== Current University Enrollment Roster (SQL JOIN) ===");
            boolean hasRecords = false;
            while (rs.next()) {
                hasRecords = true;
                System.out.printf("Student: %s (%s) -> Enrolled In: [%s] %s\n",
                        rs.getString("student_name"), rs.getString("student_id"),
                        rs.getString("course_code"), rs.getString("course_name"));
            }
            if (!hasRecords) System.out.println("No enrollments found in the system database.");
            System.out.println("=======================================================\n");
        } catch (SQLException e) {
            System.err.println("Error displaying enrollment report: " + e.getMessage());
        }
    }
}