package ra.edu.presentation.admin;

import ra.edu.business.model.Course;
import ra.edu.business.service.Course.CourseService;
import ra.edu.business.service.Course.CourseServiceImp;
import ra.edu.utils.TableConfig;
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
            System.out.println("║         COURSE MANAGER        ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. View Course                ║");
            System.out.println("║ 2. Add Course                 ║");
            System.out.println("║ 3. Update Course              ║");
            System.out.println("║ 4. Delete Course              ║");
            System.out.println("║ 5. Search Course              ║");
            System.out.println("║ 6. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");
            int choice = Validator.validateInteger("nhap lua chon", sc);
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

                    break;
                case 5:

                    break;
                case 6:
                    return;
                default:
                    System.out.println("lua chon ko hop le. Hay nhap lai");
            }
        } while (true);
    }

    public void displayData(Scanner sc) {
        int current_page = 1;
        int total_page = courseService.getTotalPage();
        do {
            System.out.println(current_page + "/" + courseService.getTotalPage());
            List<Course> list = courseService.getDataPag(current_page);
            System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", "ID", "Name", "Duration", "Instructor", "Create Date");
            for (Course co : list) {
                co.displayData();
            }
            System.out.println("1. trang sau | 2. trang truoc | 3. quay lai");
            int choice = Validator.validateInteger("nhap lua chon", sc);
            switch (choice) {
                case 1:
                    if (current_page < total_page) current_page++;
                    break;
                case 2:
                    if (current_page > 1) current_page--;
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public void addCourse(Scanner sc) {
        Course course = new Course();
        course.inputData(sc);
        if (courseService.save(course)) {
            System.out.println("Thêm thành công");
        } else {
            System.out.println("Thêm thất bại");
        }

    }

    public void updateCourse(Scanner sc) {
        int id = Validator.validateInteger("nhap id khoa hoc", sc);
        Course course = courseService.findCourseById(id);
        if (course == null) {
            System.out.println("khong tim thay khoa hoc");
            return;
        }
        System.out.println("thong tin khoa hoc");
        course.displayData();
        boolean isExit = false;

            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║         UPDATE COURSE         ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Update Name                ║");
            System.out.println("║ 2. Update Duration            ║");
            System.out.println("║ 3. Update Instructor          ║");
            System.out.println("║ 4. Update Create Date         ║");
            System.out.println("║ 5. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");
            int choice = Validator.validateInteger("nhap lua chon", sc);
            switch (choice) {
                case 1:
                    String name = Validator.validateString("nhap ten khoa hoc", sc, 1, 100);
                    course.setName(name);
                    break;
                case 2:
                    int duration = Validator.validateInteger("nhap thoi gian", sc);
                    course.setDuration(duration);
                    break;
                case 3:
                    String instructor = Validator.validateString("nhap giang vien", sc, 1, 100);
                    course.setInstructor(instructor);
                    break;
                case 4:
                    LocalDate create_date = Validator.validateDate("nhap ngay tao", sc);
                    course.setCreate_date(create_date);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("lua chon ko hop le. Hay nhap lai");
            }


        if (courseService.update(course)) {
            System.out.println("cap nhat thanh cong");
        } else {
            System.out.println("cap nhat that bai");
        }
    }
}


