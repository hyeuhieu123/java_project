package ra.edu.utils;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class PaginationUtil {
    public static int handlePagination(Scanner sc, int currentPage, int totalPages) {
        System.out.println(" Trang "+currentPage + "/" + totalPages);
        System.out.printf("%15s%15s%15s\n",currentPage==1?"":"1.Trang truoc", currentPage==totalPages?"":"2.Trang sau", "3.Thoat");
        int choice = Validator.validateInteger("Nhap lua chon ", sc);

        switch (choice) {

            case 1:
                if (currentPage > 1) {
                    currentPage--;
                }
                break;
            case 2:
                if (currentPage < totalPages) {
                    currentPage++;
                }
                break;
            case 3:
                return 0;
            default:
                System.err.println("Lua chon khong hop le. Vui long chon tu 1 den 3");
        }
        return currentPage;
    }
}