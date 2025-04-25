package ra.edu.presentation.admin;

import ra.edu.business.model.Course;
import ra.edu.business.service.Course.CourseService;
import ra.edu.business.service.Course.CourseServiceImp;
import ra.edu.utils.PaginationUtil;
import ra.edu.utils.TableConfig;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CourseManager {
    private final CourseService courseService;

    public CourseManager() {
        this.courseService = new CourseServiceImp();
    }


    public static void displayCourseMenu(Scanner sc) {
        do {
            CourseManager courseManager = new CourseManager();
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║         QUAN LY KHOA HOC      ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Danh sach khoa hoc         ║");
            System.out.println("║ 2. Them khoa hoc              ║");
            System.out.println("║ 3. Cap nhat khoa hoc          ║");
            System.out.println("║ 4. Xoa khoa hoc               ║");
            System.out.println("║ 5. Tim kiem khoa hoc theo ten ║");
            System.out.println("║ 6. Sap xep khoa hoc theo ten  ║");
            System.out.println("║ 7. Thoat                      ║");
            System.out.println("╚═══════════════════════════════╝");
            int choice = Validator.validateInteger("Nhap lua chon", sc);
            switch (choice) {
                case 1:
                    courseManager.displayData(sc);
                    break;
                case 2:
                    courseManager.addCourse(sc);
                    break;
                case 3:
                    courseManager.updateCourse(sc);
                    break;
                case 4:
                  courseManager.deleteCourse(sc);
                    break;
                case 5:
                    courseManager.findCourseByName(sc);
                    break;
                case 6:
                    courseManager.sortCourse(sc);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Lua chon ko hop le. Hay nhap lai");
            }
        } while (true);
    }

    public void displayData(Scanner sc) {
        int currentPage = 1;
        do {
            TableConfig<Course> tableConfig = courseService.getDataPag(currentPage);
            List<Course> courses = tableConfig.getItems();

            // Header
            System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
            System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                    "Ma Khoa Hoc", "Ten Khoa Hoc", "Thoi Gian", "Giang Vien", "Ngay Tao");

            System.out.println("╠════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╣");

// Footer


// Display courses in loop
            for (Course course : courses) {
                course.displayData();
            }

// Footer
            System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");
            currentPage = PaginationUtil.handlePagination(sc, currentPage, tableConfig.getTotalPages());
            if (currentPage == 0) {
                return;
            }
        } while (true);
    }

    public void addCourse(Scanner sc) {
        Course course = new Course();
        course.inputData(sc);
        if (courseService.save(course)) {
            System.out.println("Them thanh cong");
        } else {
            System.out.println("Them that bai");
        }

    }

    public void updateCourse(Scanner sc) {
        int id = Validator.validateInteger("Nhap id khoa hoc", sc);
        Course course = courseService.findCourseById(id);
        if (course == null) {
            System.out.println("Khong tim thay khoa hoc");
            return;
        }
        System.out.println("Thong tin khoa hoc");
        System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
        System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                "Ma Khoa Hoc", "Ten Khoa Hoc", "Thoi Gian", "Giang Vien", "Ngay Tao");

        System.out.println("╠════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╣");
        course.displayData();
        System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");
        boolean isExit = false;

            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║         CAP NHAT KHOA HOC     ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Cap nhat ten               ║");
            System.out.println("║ 2. Cap nhat thoi luong        ║");
            System.out.println("║ 3. Cap nhat giang vien        ║");
            System.out.println("║ 5. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");
            int choice = Validator.validateInteger("Nhap lua chon", sc);
            switch (choice) {
                case 1:
                    String name = Validator.validateString("Nhap ten khoa hoc", sc, 1, 100);
                    course.setName(name);
                    break;
                case 2:
                    int duration = Validator.validateInteger("Nhap thoi gian", sc);
                    course.setDuration(duration);
                    break;
                case 3:
                    String instructor = CourseValidator.validateCourseName("Nhap ten khoa hoc ",sc,1,20);
                    course.setInstructor(instructor);
                    break;

                case 4:
                    return;
                default:
                    System.out.println("Lua chon ko hop le. Hay Nhap lai");
            }


        if (courseService.update(course)) {
            System.out.println("Cap nhat thanh cong");
        } else {
            System.out.println("Cap nhat that bai");
        }
    }

    public void findCourseByName(Scanner sc){
        String name = Validator.validateString("Nhap ten khoa hoc", sc, 1, 100);
        int current_page = 1;
        do {
            TableConfig<Course> tableConfig = courseService.findCourseByName(current_page, name);
            List<Course> courses = tableConfig.getItems();
            if (courses.isEmpty()){
                System.out.println("khong tim thay khoa hoc");
                return;
            }
            System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
            System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                    "Ma Khoa Hoc", "Ten Khoa Hoc", "Thoi Gian", "Giang Vien", "Ngay Tao");

            System.out.println("╠════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╣");
            for (Course co : courses) {
                co.displayData();
            }
            System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");
            current_page = PaginationUtil.handlePagination(sc, current_page, tableConfig.getTotalPages());
            if (current_page == 0) {
                return; // Exit pagination
            }
        } while (true);
    }

    public void sortCourse(Scanner sc){
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║        SAP XEP KHOA HOC       ║");
        System.out.println("╠═══════════════════════════════╣");
        System.out.println("║ 1. Sap xep theo ten tang dang ║");
        System.out.println("║ 2. Sap xep theo ten giam dan  ║");
        System.out.println("║ 3. Thoat                      ║");
        System.out.println("╚═══════════════════════════════╝");
        int choice = Validator.validateInteger("Nhap lua chon", sc);
        int currentPage = 1;
        switch (choice) {

            case 1:
                 currentPage = 1;
                do {
                    TableConfig<Course> tableConfig = courseService.sortByNameASC(currentPage);
                    List<Course> courses = tableConfig.getItems();
                    System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
                    System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                            "Ma Khoa Hoc", "Ten Khoa Hoc", "Thoi Gian", "Giang Vien", "Ngay Tao");

                    System.out.println("╠════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╣");
                    for (Course co : courses) {
                        co.displayData();
                    }
                    System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");
                    currentPage = PaginationUtil.handlePagination(sc, currentPage, tableConfig.getTotalPages());
                    if (currentPage == 0) {
                     return;
                    }
                } while (true);

            case 2:
                 currentPage = 1;
                do {
                    TableConfig<Course> tableConfig = courseService.sortByNameDESC(currentPage);
                    List<Course> courses = tableConfig.getItems();
                    System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
                    System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                            "Ma Khoa Hoc", "Ten Khoa Hoc", "Thoi Gian", "Giang Vien", "Ngay Tao");

                    System.out.println("╠════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╣");
                    for (Course co : courses) {
                        co.displayData();
                    }
                    System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");
                    currentPage = PaginationUtil.handlePagination(sc, currentPage, tableConfig.getTotalPages());
                    if (currentPage == 0) {
                    return;
                    }
                } while (true);



            default:
                System.out.println("Lua chon ko hop le. Hay nhap lai");
        }
    }
    public void deleteCourse(Scanner sc){
        int id = Validator.validateInteger("Nhap id khoa hoc", sc);
        Course course = courseService.findCourseById(id);
        if (course == null) {
            System.out.println("Khong tim thay khoa hoc");
            return;
        }
        System.out.println("Thong tin khoa hoc");
        System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
        System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                "Ma Khoa Hoc", "Ten Khoa Hoc", "Thoi Gian", "Giang Vien", "Ngay Tao");

        System.out.println("╠════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╣");
        course.displayData();
        System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");
       String confirm = Validator.validateString("ban co chac chan muon xoa khong (y/n)", sc, 1, 1);

        if ( confirm.equalsIgnoreCase("y")) {
            int result = courseService.delete(course);
            if (result==1) {
                System.out.println("Xoa thanh cong");
            }
            else if (result == 2) {
                System.out.println("Xoa that bai. khoa hoc da duoc dang ky");
            }
        } else if ( confirm.equalsIgnoreCase("n")) {
            System.out.println("Huy xoa");
        }


    }
}


