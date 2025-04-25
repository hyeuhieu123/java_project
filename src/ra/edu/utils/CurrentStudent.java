package ra.edu.utils;

import ra.edu.business.model.Student;

public class CurrentStudent {
    private static Student currentStudent;
    public static String currentAccountPassword;
    public static void setCurrentStudent(Student student,String password) {

        currentStudent = student;
        currentAccountPassword=password;
        System.out.println("Hello "+student.getName());
    }

    public static String getCurrentAccountPassword() {
        return currentAccountPassword;
    }

    public static Student getCurrentStudent() {
        return currentStudent;
    }
    public static void clearCurrentStudent() {
        currentStudent = null;
    }
}
