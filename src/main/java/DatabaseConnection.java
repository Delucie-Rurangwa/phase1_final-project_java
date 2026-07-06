import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

    public class DatabaseConnection {
        private static final String URL = "jdbc:postgresql://localhost:5432/university_db";
        private static final String USER = "postgres";
        private static final String PASSWORD = "java";

        public static Connection getConnection() throws SQLException {
            try {
                // Forces Java to load the 42.7.11 jar file driver into execution memory
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println("PostgreSQL JDBC Driver missing: " + e.getMessage());
            }
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }

        public static void initializeDatabase() {
            String createStudentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id VARCHAR(50) PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100) UNIQUE NOT NULL, " +
                    "is_graduate BOOLEAN NOT NULL);";

            String createCoursesTable = "CREATE TABLE IF NOT EXISTS courses (" +
                    "course_code VARCHAR(50) PRIMARY KEY, " +
                    "course_name VARCHAR(100) NOT NULL);";

            String createEnrollmentsTable = "CREATE TABLE IF NOT EXISTS enrollments (" +
                    "student_id VARCHAR(50), " +
                    "course_code VARCHAR(50), " +
                    "PRIMARY KEY (student_id, course_code), " +
                    "FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (course_code) REFERENCES courses(course_code) ON DELETE CASCADE);";

            try (Connection conn = getConnection();
                 Statement stmt = conn.createStatement()) {

                stmt.execute(createStudentsTable);
                stmt.execute(createCoursesTable);
                stmt.execute(createEnrollmentsTable);
                System.out.println("Database tables checked/initialized successfully!");

            } catch (SQLException e) {
                System.err.println("Database initialization failed: " + e.getMessage());
            }
        }
    }

