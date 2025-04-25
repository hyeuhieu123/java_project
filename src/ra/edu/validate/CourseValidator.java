package ra.edu.validate;

import ra.edu.business.dao.Course.CourseDAOImp;

import java.util.Scanner;

public class CourseValidator {
    private static final CourseDAOImp courseDAO = new CourseDAOImp();

    public static String validateCourseName(String message, Scanner scanner, int minLength, int maxLength) {
        System.out.println(message);
        do {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Ten khoa hoc ko duoc de trong ");
                continue;
            }

            if (input.length() < minLength || input.length() > maxLength) {
                System.out.printf("Ten khoa hoc phai co do dai tu %d den %d ki tu%n", minLength, maxLength);
                continue;
            }

            if (courseDAO.isNameExists(input)) {
                System.out.println("Ten da ton tai, vui long nhap ten khac");
                continue;
            }

            return input;
        } while (true);
    }
}
