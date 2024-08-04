import java.util.Scanner;

class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name;
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Course ID: " + courseID + ", Grade: " + grade;
    }
}

class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int[] courseCredits;
    private int studentCount;
    private int gradeCount;

    public GradingSystem(int maxStudents, int maxCourses) {
        students = new Student[maxStudents];
        grades = new Grade[maxStudents * maxCourses];
        courseCredits = new int[maxCourses];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount++] = student;
        } else {
            System.out.println("Maximum number of students reached.");
        }
    }

    public void addGrade(Grade grade) {
        if (gradeCount < grades.length) {
            grades[gradeCount++] = grade;
        } else {
            System.out.println("Maximum number of grades reached.");
        }
    }

    public void addCourseCredits(int courseID, int credits) {
        if (courseID < courseCredits.length) {
            courseCredits[courseID] = credits;
        } else {
            System.out.println("Invalid course ID.");
        }
    }

    public double calculateGPA(int studentID) {
        int totalPoints = 0;
        int totalCredits = 0;
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                int courseID = grades[i].getCourseID();
                char grade = grades[i].getGrade();
                int points = gradeToPoints(grade);
                int credits = courseCredits[courseID];
                totalPoints += points * credits;
                totalCredits += credits;
            }
        }
        if (totalCredits == 0) return 0.0;
        return (double) totalPoints / totalCredits;
    }

    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A': return 10;
            case 'B': return 9;
            case 'C': return 8;
            case 'D': return 7;
            case 'F': return 0;
            default: return 0;
        }
    }

    public void printGradeReport(int studentID) {
        System.out.println("Grade Report for Student ID: " + studentID);
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                System.out.println(grades[i]);
            }
        }
        System.out.println("GPA: " + calculateGPA(studentID));
    }
}

public class GradingSystemMGMT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradingSystem system = new GradingSystem(100, 10);

        while (true) {
            System.out.println("\nGrading and Result Declaration System");
            System.out.println("1. Add new student");
            System.out.println("2. Add grade for student");
            System.out.println("3. Add course credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Print grade report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int studentID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    system.addStudent(new Student(studentID, name));
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    System.out.print("Enter Course ID: ");
                    int courseID = scanner.nextInt();
                    System.out.print("Enter Grade (A/B/C/D/F): ");
                    char grade = scanner.next().charAt(0);
                    system.addGrade(new Grade(studentID, courseID, grade));
                    System.out.println("Grade added successfully.");
                    break;
                case 3:
                    System.out.print("Enter Course ID: ");
                    courseID = scanner.nextInt();
                    System.out.print("Enter Credits: ");
                    int credits = scanner.nextInt();
                    system.addCourseCredits(courseID, credits);
                    System.out.println("Course credits added successfully.");
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    double gpa = system.calculateGPA(studentID);
                    System.out.println("GPA for Student ID " + studentID + ": " + gpa);
                    break;
                case 5:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextInt();
                    system.printGradeReport(studentID);
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
