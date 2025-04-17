package ra.edu.utils;

import ra.edu.business.model.Student;

public class CurrentStudent {
    private static Student currentStudent;
    public static void setCurrentStudent(Student student) {
        currentStudent = student;
    }
    public static void getCurrentStudent() {
        if (currentStudent != null) {
            System.out.println("hello " + currentStudent.getName());
        } else {
            System.out.println("ko lay duoc nguoi dung ");
        }
    }
    public static void clearCurrentStudent() {
        currentStudent = null;
    }
}
