package ra.edu.validate;
import ra.edu.business.dao.Student.StudentDAOImp;

import java.util.Scanner;

public class StudentValidator {
    private static final StudentDAOImp studentDao = new StudentDAOImp();

    public static String validateStudentEmail(String message, Scanner scanner) {
        System.out.println(message);
        do {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Email ko duoc de trong");
                continue;
            }
            if (studentDao.isEmailExists(input)) {
                System.out.println("Email da ton tai. nhap email khac ");
                continue;
            }
            if (!input.matches("[a-zA-Z0-9]+@gmail.com")) {
                System.out.println("Hay nhap dung dinh dang @gmail.com");
                continue;
            }

            return input;

        }while (true) ;

        }
    }



