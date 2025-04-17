package ra.edu.presentation.student;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class studentUI {
    public static void displayStudentMenu(Scanner sc) {

        do {
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║         STUDENT MENU          ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Danh sach khoa hoc         ║");
            System.out.println("║ 2. Dang ky khoa hoc           ║");
            System.out.println("║ 3. Xem khoa hoc da dang ky    ║");
            System.out.println("║ 4. Huy dang ky khoa hoc       ║");
            System.out.println("║ 5. Thoat                      ║");
            System.out.println("╚═══════════════════════════════╝");
            int choice= Validator.validateInteger("nhap lua chon: ",sc);
            switch (choice){
                case 1:

                    break;
                case 2:
//
                    break;
                case 5:
                    return;
                default:
                    System.out.println("lua chon ko hop le. Hay nhap lai");
            }
        }while (true);
    }
}
