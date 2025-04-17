package ra.edu.presentation.admin;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class adminUI {
    public static void displayAdminMenu(Scanner sc) {

        do {
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║         ADMIN MENU            ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Manage Courses             ║");
            System.out.println("║ 2. Manage Students            ║");
            System.out.println("║ 5. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");
            int choice= Validator.validateInteger("nhap lua chon: ",sc);
            switch (choice){
                case 1:
                    CourseManager.displayCourseMenu(sc);
                    break;
                case 2:
//                    StudentManager.displayStudentMenu(sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("lua chon ko hop le. Hay nhap lai");
            }
        }while (true);
    }
}
