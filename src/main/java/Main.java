import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.initializeDatabase();
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the University Management Console System!");

        while (true) {
            try {
                System.out.println("--- MAIN MENU ---");
                System.out.println("1. Add a Student to Database");
                System.out.println("2. Add a Course to Database");
                System.out.println("3. Enroll a Student in a Course");
                System.out.println("4. View All Registered Students");
                System.out.println("5. View Student Enrollment Report (SQL JOIN)");
                System.out.println("6. Remove a Student");
                System.out.println("7. Exit Application");
                System.out.print("Please select an option (1-7): ");

                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String sId = scanner.nextLine();
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Student Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Is this a Graduate Student? (true/false): ");
                        boolean isGrad = Boolean.parseBoolean(scanner.nextLine());
                        studentDAO.saveStudent(new Student(sId, name, email, isGrad));
                        break;
                    case 2:
                        System.out.print("Enter Course Code: ");
                        String code = scanner.nextLine();
                        System.out.print("Enter Course Name: ");
                        String cName = scanner.nextLine();
                        courseDAO.saveCourse(new Course(code, cName));
                        break;
                    case 3:
                        System.out.print("Enter Student ID: ");
                        String enrollmentStudentId = scanner.nextLine();
                        System.out.print("Enter Course Code: ");
                        String enrollmentCourseCode = scanner.nextLine();
                        courseDAO.enrollStudentInCourse(enrollmentStudentId, enrollmentCourseCode);
                        break;
                    case 4:
                        System.out.println("\n--- Registered Database Students ---");
                        for (Student s : studentDAO.getAllStudents()) System.out.println(s);
                        break;
                    case 5:
                        courseDAO.displayEnrolledStudentsWithCourses();
                        break;
                    case 6:
                        System.out.print("Enter Student ID to remove: ");
                        String removeId = scanner.nextLine();
                        studentDAO.deleteStudent(removeId);
                        break;
                    case 7:
                        System.out.println("Exiting application system. Goodbye!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid selection. Choose between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Input Error: Please type a valid numeric digit choice.");
            } catch (Exception e) {
                System.out.println("⚠️ An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}