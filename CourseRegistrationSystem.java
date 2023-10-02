import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<String> enrolledStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent(String studentName) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(studentName);
            return true;
        } else {
            System.out.println("Course is full. Cannot enroll " + studentName + ".");
            return false;
        }
    }

    public boolean removeStudent(String studentName) {
        if (enrolledStudents.contains(studentName)) {
            enrolledStudents.remove(studentName);
            return true;
        } else {
            System.out.println(studentName + " is not enrolled in this course.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nSchedule: " + schedule +
                "\nEnrolled Students: " + enrolledStudents;
    }
}

class Student {
    private String studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(String courseCode, List<Course> courses) {
        Course selectedCourse = null;
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse != null && !registeredCourses.contains(courseCode)) {
            if (selectedCourse.enrollStudent(name)) {
                registeredCourses.add(courseCode);
                System.out.println(name + " has registered for course " + courseCode + ".");
            }
        } else if (selectedCourse == null) {
            System.out.println("Course with code " + courseCode + " not found.");
        } else {
            System.out.println(name + " is already registered for course " + courseCode + ".");
        }
    }

    public void dropCourse(String courseCode, List<Course> courses) {
        Course selectedCourse = null;
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse != null && registeredCourses.contains(courseCode)) {
            if (selectedCourse.removeStudent(name)) {
                registeredCourses.remove(courseCode);
                System.out.println(name + " has dropped course " + courseCode + ".");
            }
        } else if (selectedCourse == null) {
            System.out.println("Course with code " + courseCode + " not found.");
        } else {
            System.out.println(name + " is not registered for course " + courseCode + ".");
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + registeredCourses;
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Introduction to Programming", "Basic programming concepts", 30, List.of("Mon, Wed 10:00 AM - 11:30 AM")));
        courses.add(new Course("CS202", "Data Structures", "Advanced data structure concepts", 25, List.of("Tue, Thu 2:00 PM - 3:30 PM")));

        List<Student> students = new ArrayList<>();
        students.add(new Student("S101", "Alice"));
        students.add(new Student("S102", "Bob"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. View Courses");
            System.out.println("2. View Students");
            System.out.println("3. Register Student for Course");
            System.out.println("4. Drop Student from Course");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses) {
                        System.out.println(course);
                    }
                    break;
                case 2:
                    System.out.println("\nRegistered Students:");
                    for (Student student : students) {
                        System.out.println(student);
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.next();
                    for (Student student : students) {
                        if (student.getStudentID().equals(studentID)) {
                            student.registerForCourse(courseCode, courses);
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.next();
                    for (Student student : students) {
                        if (student.getStudentID().equals(studentID)) {
                            student.dropCourse(courseCode, courses);
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
