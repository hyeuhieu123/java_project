package ra.edu;

import ra.edu.presentation.login.LoginUI;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            LoginUI loginUI = new LoginUI();
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║         Trang dang nhap       ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Dang nhap admin            ║");
            System.out.println("║ 2. Dang nhap sinh vien        ║");
            System.out.println("║ 3. Thoat                      ║");
            System.out.println("╚═══════════════════════════════╝");
            System.out.print("Nhap lua chon: ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    loginUI.loginAdmin(sc);
                    break;
                case 2:
                    loginUI.loginStudent(sc);
                    break;
                case 3:

                    System.exit(0);
                default:
                    System.out.println("Lua chon khong hop le. Vui long nhap lai!");
            }
        }
    }
}