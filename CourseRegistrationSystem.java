import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basic concepts of programming", 30, "Mon/Wed 10:00 AM"));
        courses.add(new Course("ENG201", "English Composition", "Developing writing skills", 25, "Tue/Thu 2:00 PM"));
        courses.add(new Course("MATH301", "Calculus I", "Fundamental concepts of calculus", 20, "Mon/Wed/Fri 9:00 AM"));

   
        students.add(new Student(1, "John Doe"));
        students.add(new Student(2, "Jane Smith"));

        while (true) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. Course Listing");
            System.out.println("2. Student Registration");
            System.out.println("3. Course Removal");
            System.out.println("4. Display Registered Courses");
            System.out.println("5. Exit");

            System.out.print("Choose an option (1-5): ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    displayCourseListing(courses);
                    break;
                case 2:
                    registerStudent(scanner, students, courses);
                    break;
                case 3:
                    dropCourse(scanner, students, courses);
                    break;
                case 4:
                    displayRegisteredCourses(scanner, students);
                    break;
                case 5:
                    System.out.println("Exiting Course Registration System. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }

    private static void displayCourseListing(List<Course> courses) {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getCapacity() + " slots available)");
        }
    }

    private static void registerStudent(Scanner scanner, List<Student> students, List<Course> courses) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();

        System.out.print("Enter course code to register: ");
        scanner.nextLine(); // Consume newline character
        String courseCode = scanner.nextLine();

        Student student = findStudentById(studentId, students);
        Course course = findCourseByCode(courseCode, courses);

        if (student != null && course != null) {
            student.registerForCourse(course);
        } else {
            System.out.println("Invalid student ID or course code. Registration failed.");
        }
    }

    private static void dropCourse(Scanner scanner, List<Student> students, List<Course> courses) {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();

        System.out.print("Enter course code to drop: ");
        scanner.nextLine(); // Consume newline character
        String courseCode = scanner.nextLine();

        Student student = findStudentById(studentId, students);
        Course course = findCourseByCode(courseCode, courses);

        if (student != null && course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Invalid student ID or course code. Course removal failed.");
        }
    }

    private static void displayRegisteredCourses(Scanner scanner, List<Student> students) {
        System.out.print("Enter student ID to display registered courses: ");
        int studentId = scanner.nextInt();

        Student student = findStudentById(studentId, students);

        if (student != null) {
            student.displayRegisteredCourses();
        } else {
            System.out.println("Invalid student ID. Cannot display registered courses.");
        }
    }

    private static Student findStudentById(int studentId, List<Student> students) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
