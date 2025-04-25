package ra.edu.presentation.admin;

import ra.edu.business.service.Statistict.StatistictSERVICE;
import ra.edu.business.service.Statistict.StatistictServiceImp;
import ra.edu.utils.PaginationUtil;
import ra.edu.utils.TableConfig;
import ra.edu.validate.Validator;

import java.util.Map;
import java.util.Scanner;

public class StatistictManager {
    private StatistictSERVICE statictistService;
    public StatistictManager() {
        this.statictistService = new StatistictServiceImp();
    }

    public static void displayStatistictMenu(Scanner sc){
        do{
            StatistictManager statistictManager = new StatistictManager();
            System.out.println("╔═══════════════════════════════════════════╗");
            System.out.println("║              THONG KE                     ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║ 1. Tong so luong sinh vien va khoa hoc    ║");
            System.out.println("║ 2. Tong sinh vien theo tung khoa          ║");
            System.out.println("║ 3. Top 5 khoa hoc dong sinh vien nhat     ║");
            System.out.println("║ 4. Cac khoa hoc tren 10 sinh vien nhat    ║");
            System.out.println("║ 5. Thoat                                  ║");
            System.out.println("╚═══════════════════════════════════════════╝");
            int choice = Validator.validateInteger("Nhap lua chon", sc);
            switch (choice) {
                case 1:
                    statistictManager.displayTotalStudentAndCourse();
                    break;
                case 2:
                    statistictManager.displayTotalStudentEachCourse(sc);
                    break;
                case 3:
                 statistictManager.displayTop5PopularCourses();
                    break;
                case 4:
                    statistictManager.displayCourseAbove10Student();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Nhap lua chon khong hop le. Vui long chon tu 1 den 5");
            }

        }while (true);
    }
    public void displayTotalStudentAndCourse(){
        int totalStudent = statictistService.getTotalStudent();
        int totalCourse = statictistService.getTotalCourse();
        System.out.println("Tong so luong sinh vien: " + totalStudent);
        System.out.println("Tong so luong khoa hoc: " + totalCourse);
    }
    public void displayTotalStudentEachCourse(Scanner sc){
       int currentPage = 1;

       do {
           TableConfig<String> table = statictistService.totalStudentEachCourse(currentPage);
           Map<String, Integer> enrollments = table.getItemsMap();
           System.out.println("╔══════════════════════════════╦════════════════════╗");
           System.out.printf("║ %-28s ║ %-18s ║%n", "Ten Khoa Hoc", "So Luong SV");
           System.out.println("╠══════════════════════════════╬════════════════════╣");
           for (Map.Entry<String, Integer> entry : enrollments.entrySet()) {
               System.out.printf("║ %-28s ║ %-18d ║%n", entry.getKey(), entry.getValue());
           }
           System.out.println("╚══════════════════════════════╩════════════════════╝");
           currentPage = PaginationUtil.handlePagination(sc, currentPage, table.getTotalPages());
           if(currentPage == 0) {
               break;
           }
       }while (true);
    }
    public void displayTop5PopularCourses(){
        Map<String, Integer> top5Courses = statictistService.findTop5PopularCourses();
        if (top5Courses.isEmpty()) {
            System.out.println("Khong co khoa hoc nao trong danh sach.");
            return;
        }
        System.out.println("╔══════════════════════════════╦════════════════════╗");
        System.out.printf("║ %-28s ║ %-18s ║%n", "Ten Khoa Hoc", "So Luong SV");
        System.out.println("╠══════════════════════════════╬════════════════════╣");
        for (Map.Entry<String, Integer> entry : top5Courses.entrySet()) {
            System.out.printf("║ %-28s ║ %-18d ║%n", entry.getKey(), entry.getValue());
        }
        System.out.println("╚══════════════════════════════╩════════════════════╝");
    }
    public void displayCourseAbove10Student(){
        Map<String, Integer> coursesAbove10 = statictistService.courseAbove10Student();
        if (coursesAbove10.isEmpty()) {
            System.out.println("Khong co khoa hoc nao co tren 10 sinh vien.");
            return;
        }
        System.out.println("╔══════════════════════════════╦════════════════════╗");
        System.out.printf("║ %-28s ║ %-18s ║%n", "Ten Khoa Hoc", "So Luong SV");
        System.out.println("╠══════════════════════════════╬════════════════════╣");
        for (Map.Entry<String, Integer> entry : coursesAbove10.entrySet()) {
            System.out.printf("║ %-28s ║ %-18d ║%n", entry.getKey(), entry.getValue());
        }
        System.out.println("╚══════════════════════════════╩════════════════════╝");
    }

}
