package ra.edu;
import ra.edu.presentation.login.LoginUI;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoginUI loginUI = new LoginUI();

        while (true) {
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║         TRANG DANG NHAP       ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Dang nhap                  ║");
            System.out.println("║ 2. Thoat                      ║");
            System.out.println("╚═══════════════════════════════╝");

            int choice = Validator.validateInteger("Nhap lua chon", sc);
            switch (choice) {
                case 1:
                    loginUI.login(sc);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.err.println("Hay nhap tu 1-2");
            }
        }
    }
}