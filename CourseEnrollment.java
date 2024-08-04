import java.util.Scanner;

class Course {
    private int courseID;
    private String courseName;
    private int credits;

    public Course(int courseID, String courseName, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.credits = credits;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseID + ", Course Name: " + courseName + ", Credits: " + credits;
    }
}
 
class Enrollment {
    private int[][] studentCourses;
    private int[] count;
    private int maxStudents;
    private int maxCourses;

    public Enrollment(int maxStudents, int maxCourses) {
        this.maxStudents = maxStudents;
        this.maxCourses = maxCourses;
        studentCourses = new int[maxStudents][maxCourses];
        count = new int[maxStudents];
    }

    public void enroll(int studentID, int courseID) {
        if (studentID >= maxStudents || studentID < 0) {
            System.out.println("Invalid student ID");
            return;
        }
        if (count[studentID] == maxCourses) {
            System.out.println("Cannot enroll in more courses");
            return;
        }
        studentCourses[studentID][count[studentID]++] = courseID;
        System.out.println("Student enrolled in course successfully.");
    }

    public void drop(int studentID, int courseID) {
        if (studentID >= maxStudents || studentID < 0) {
            System.out.println("Invalid student ID");
            return;
        }
        boolean found = false;
        for (int i = 0; i < count[studentID]; i++) {
            if (studentCourses[studentID][i] == courseID) {
                studentCourses[studentID][i] = studentCourses[studentID][count[studentID] - 1];
                count[studentID]--;
                found = true;
                System.out.println("Course dropped successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Course not found for the student.");
        }
    }

    public void getEnrolledCourses(int studentID, Course[] courseCatalog) {
        if (studentID >= maxStudents || studentID < 0) {
            System.out.println("Invalid student ID");
            return;
        }
        System.out.println("Enrolled courses for student ID " + studentID + ":");
        for (int i = 0; i < count[studentID]; i++) {
            int courseID = studentCourses[studentID][i];
            for (Course course : courseCatalog) {
                if (course.getCourseID() == courseID) {
                    System.out.println(course);
                    break;
                }
            }
        }
    }
}

public class CourseEnrollment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Course[] courseCatalog = {
            new Course(101, "Mathematics", 3),
            new Course(102, "Physics", 4),
            new Course(103, "Chemistry", 3),
            new Course(104, "Computer Science", 3)
        };
        Enrollment enrollment = new Enrollment(100, 10);

        while (true) {
            System.out.println("\nCourse Enrollment System");
            System.out.println("1. Enroll in a course");
            System.out.println("2. Drop a course");
            System.out.println("3. View enrolled courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    int courseID = scanner.nextInt();
                    enrollment.enroll(studentID, courseID);
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    int dropStudentID = scanner.nextInt();
                    System.out.print("Enter Course ID to drop: ");
                    int dropCourseID = scanner.nextInt();
                    enrollment.drop(dropStudentID, dropCourseID);
                    break;
                case 3:
                    System.out.print("Enter Student ID to view enrolled courses: ");
                    int viewStudentID = scanner.nextInt();
                    enrollment.getEnrolledCourses(viewStudentID, courseCatalog);
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
