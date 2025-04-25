package ra.edu.validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {
    private static final String PHONE_VIETTEL_PREFIXES = "086|096|097|098|039|038|037|036|035|034|033|032";
    private static final String PHONE_VINAPHONE_PREFIXES = "091|094|088|083|084|085|081|082";
    private static final String PHONE_MOBIFONE_PREFIXES = "070|079|077|076|078|089|090|093|096";

    public static int validateInteger(String message, Scanner scanner) {
        System.out.println(message);
        while (true) {
            String input = scanner.nextLine();
            try {
                int value = Integer.parseInt(input);
                if(value<0){
                    System.out.println("Hay nhap lai so nguyen duong");
                    continue;
                }
                return value;

            } catch (NumberFormatException e) {
                System.out.println("Hay nhap so nguyen");
            }
        }
    }

//    public static double validateDouble(String message, Scanner scanner, double min, double max) {
//        System.out.println(message);
//        while (true) {
//            String input = scanner.nextLine();
//            try {
//                double value = Double.parseDouble(input);
//                if (value >= min && value <= max) {
//                    return value;
//                }
//                System.out.printf("Please enter a number between %.2f and %.2f\n", min, max);
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter a valid number");
//            }
//        }
//    }

//    public static float validateFloat(String message, Scanner scanner, float min, float max) {
//        System.out.println(message);
//        while (true) {
//            String input = scanner.nextLine();
//            try {
//                float value = Float.parseFloat(input);
//                if (value >= min && value <= max) {
//                    return value;
//                }
//                System.out.printf("Please enter a number between %.2f and %.2f\n", min, max);
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter a valid number");
//            }
//        }
//    }

    public static boolean validateBoolean(String message, Scanner scanner) {
        System.out.println(message + " (true/false):");
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Hay nhap true hay false");
        }
    }

    public static LocalDate validateDate(String message, Scanner scanner) {
        System.out.println(message + " (dd/MM/yyyy):");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            String input = scanner.nextLine();
            try {
                LocalDate value = LocalDate.parse(input, formatter);
                if(value.isAfter(LocalDate.now())){
                    System.out.println("Nam khong duoc lon hon ngay hien tai");
                    continue;
                }
                return value;
            } catch (Exception e) {
                System.out.println("Hay nhap theo dinh dang ngay (dd/MM/yyyy)");
            }
        }
    }

    public static <T extends Enum<T>> T validateEnum(String message, Scanner scanner, Class<T> enumClass) {
        System.out.println(message);
        while (true) {
            System.out.println("Hay nhap theo dinh dang sau");
            for (T constanst : enumClass.getEnumConstants()) {
                System.out.print(constanst + "\t");
            }
            String input = scanner.nextLine().trim();

            System.out.println();
            for (T constanst : enumClass.getEnumConstants()) {

                if (constanst.name().equalsIgnoreCase(input)) {
                    return constanst;
                }
            }


        }

    }
    public static String validateString(String message, Scanner scanner, int min, int max) {
        System.out.println(message);
        do {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Hay nhap lai");
                continue;
            }
            if (input.length() < min || input.length() > max) {
                System.out.println("Hay nhap lai do dai tu " + min + " den " + max);
            } else {
                return input;
            }

        } while (true);

    }

    public static String validatePhone(String message, Scanner scanner) {
        String phoneRegex = "(" + PHONE_VIETTEL_PREFIXES + "|" + PHONE_VINAPHONE_PREFIXES + "|" + PHONE_MOBIFONE_PREFIXES + ")\\d{7}";
        System.out.println(message);
        do {
            String sdt = scanner.nextLine();
            if (!sdt.isEmpty() && Pattern.matches(phoneRegex, sdt.trim())) {
                return sdt;
            } else {
                System.out.println("Hay nhap theo sdt VN");
            }
        } while (true);

    }

}