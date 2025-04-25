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
            System.out.println("║         Login Page            ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Login                      ║");
            System.out.println("║ 2. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");

            int choice = Validator.validateInteger("Enter your choice:", sc);
            switch (choice) {
                case 1:
                    loginUI.login(sc);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.err.println("Please choose 1-2");
            }
        }
    }
}