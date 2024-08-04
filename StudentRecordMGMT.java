import java.util.Scanner;

class Student {
    private int studentID;
    private String name;
    private int age;
    private String department;

    public Student(int studentID, String name, int age, String department) {
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Age: " + age + ", Department: " + department;
    }
}

class StudentRecordSystem {
    private Student[] students;
    private int count;

    public StudentRecordSystem() {
        students = new Student[10];
        count = 0;
    }

    public void addStudent(Student student) {
        if (count == students.length) {
            resizeArray();
        }
        students[count++] = student;
    }

    private void resizeArray() {
        Student[] newStudents = new Student[students.length * 2];
        System.arraycopy(students, 0, newStudents, 0, students.length);
        students = newStudents;
    }

    public Student getStudent(int studentID) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentID() == studentID) {
                return students[i];
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (int i = 0; i < count; i++) {
            System.out.println(students[i]);
        }
    }
}

public class StudentRecordMGMT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRecordSystem system = new StudentRecordSystem();

        while (true) {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Search for a student by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();
                    system.addStudent(new Student(id, name, age, department));
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.println("All Students:");
                    system.displayAllStudents();
                    break;
                case 3:
                    System.out.print("Enter Student ID to search: ");
                    int searchID = scanner.nextInt();
                    Student student = system.getStudent(searchID);
                    if (student != null) {
                        System.out.println("Student found: " + student);
                    } else {
                        System.out.println("Student not found.");
                    }
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
