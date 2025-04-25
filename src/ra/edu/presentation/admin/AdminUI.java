package ra.edu.presentation.admin;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class AdminUI {
    public static void displayAdminMenu(Scanner sc) {

        do {
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║         TRANG QUAN LY         ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Quan ly khoa hoc           ║");
            System.out.println("║ 2. Quan ly sinh vien          ║");
            System.out.println("║ 3. Quan ly dang ky khoa hoc   ║");
            System.out.println("║ 4. Thong ke                   ║");
            System.out.println("║ 5. Thoat                      ║");
            System.out.println("╚═══════════════════════════════╝");
            int choice= Validator.validateInteger("Nhap lua chon: ",sc);
            switch (choice){
                case 1:
                    CourseManager.displayCourseMenu(sc);
                    break;
                case 2:
                    StudentManager.displayStudentMenu(sc);
                    break;
                case 3:
                    EnrollmentManager.displayEnrollmentMenu(sc);
                    break;
                case 4:
                    StatistictManager.displayStatistictMenu(sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Lua chon ko hop le. Hay nhap lai");
            }
        }while (true);
    }
}
