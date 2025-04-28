package ra.edu.presentation.student;

import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.service.Course.CourseService;
import ra.edu.business.service.Course.CourseServiceImp;
import ra.edu.business.service.Student.StudentService;
import ra.edu.business.service.Student.StudentServiceImp;
import ra.edu.utils.CurrentStudent;
import ra.edu.utils.PaginationUtil;
import ra.edu.utils.TableConfig;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class studentUI {
    private StudentService studentService;
    private CourseService  courseService;
    public studentUI() {
        this.studentService = new StudentServiceImp();
        this.courseService = new CourseServiceImp();
    }

    public static void displayStudentMenu(Scanner sc) {
        do {
            studentUI studentUI = new studentUI();
            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║        TRANG SINH VIEN        ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Danh sach khoa hoc         ║");
            System.out.println("║ 2. Tim kiem khoa hoc          ║");
            System.out.println("║ 3. Dang ky khoa hoc           ║");
            System.out.println("║ 4. Xem khoa hoc da dang ky    ║");
            System.out.println("║ 5. Huy dang ky khoa hoc       ║");
            System.out.println("║ 6. Thay doi mat khau          ║");
            System.out.println("║ 7. Thoat                      ║");
            System.out.println("╚═══════════════════════════════╝");
            int choice= Validator.validateInteger("Nhap lua chon: ",sc);
            switch (choice){
                case 1:
                    studentUI.displayCourse(sc);
                    break;
                case 2:
                    studentUI.findCourseByName(sc);
                    break;
                case 3:
                    studentUI.assignCourse(sc);
                    break;
                case 4:
                    studentUI.showEnrollmentsByStudent(sc);
                    break;
                case 5:
                    studentUI.cancleEnrollment(sc);
                    break;
                case 6:
                    studentUI.changePassword(sc);
                    break;
                case 7:
                    return;

                default:
                    System.out.println("Lua chon ko hop le. Hay nhap lai");
            }
        }while (true);
    }
    public void displayCourse(Scanner sc) {
        int currentPage = 1;
        do {
            TableConfig<Course> tableConfig = courseService.getDataPag(currentPage);
            List<Course> courses = tableConfig.getItems();
            System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
            System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                    "Ma Khoa Hoc", "Ten Khoa Hoc", "Thoi Gian", "Giang Vien", "Trang thai");

            System.out.println("╠════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╣");
            for (Course co : courses) {
                co.displayData();
            }
            System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");
            currentPage = PaginationUtil.handlePagination(sc, currentPage, tableConfig.getTotalPages());
            if (currentPage == 0) {
                return; // Exit pagination
            }
        } while (true);
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
                    "Ma Khoa Hoc", "Ten Khoa Hoc", "Thoi Gian", "Giang Vien", "Trang thai");

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
    public void assignCourse(Scanner sc){
        int idCourse=Validator.validateInteger("Nhap id khoa hoc muon dang ky",sc);
        Course course = courseService.findCourseById(idCourse);
        if(course ==null){
            System.out.println("Ko tim thay khoa hoc");
            return;
        }
      int result =    studentService.assignCourse(CurrentStudent.getCurrentStudent(),course);
        if (result==1){
            System.out.println("Dang ky thanh cong");
        }else{
            System.out.println("Moi khoa hoc chi duoc dang ky 1 lan");
        }

    }
    public void showEnrollmentsByStudent(Scanner sc){
        int currentPage = 1;
        do {
            TableConfig<Enrollment> tableConfig = studentService.currentStudentEnrollmentPaging(currentPage, CurrentStudent.getCurrentStudent());
            List<Enrollment> enrollments = tableConfig.getItems();

            if (enrollments.isEmpty()) {
                System.out.println("Sinh vien chua dang ky khoa hoc nao!");
                return;
            }
            System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
            System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                    "Ma giao dich", "Ten hoc vien", "Ten khoa hoc", "Thoi gian dang ky", "Trang thai");

            System.out.println("╠════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╣");
            for (Enrollment enrollment : enrollments) {
              enrollment.displayData();
            }
            System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");

            currentPage = PaginationUtil.handlePagination(sc, currentPage, tableConfig.getTotalPages());
            if (currentPage == 0) {
                break;
            }
        } while (true);
    }

    public void cancleEnrollment(Scanner sc){
        int id = Validator.validateInteger("Nhap id giao dich can huy",sc);
       int rs= studentService.CancleEnrollment(CurrentStudent.getCurrentStudent(),id);

       switch (rs){
           case -1:
               System.out.println("Khong tim thay giao dich");
               break;
           case 2:
               System.out.println("Huy that bai. Giao dich da duoc huy truoc do");
               break;
           case 3:
               System.out.println("Giao dich da duoc duyet. Khong huy duoc");
               break;
           case 1:
               System.out.println("Huy thanh cong ");
       }

    }
    public void changePassword(Scanner sc){
        String oldPassword = Validator.validateString("Nhap mat khau cu",sc,0,20);
        if(!oldPassword.equals(CurrentStudent.getCurrentAccountPassword())){
            System.out.println("Mat khau cu khong dung");
            return;
        }
        String newPassword =Validator.validateString("Nhap mat khau moi",sc,1,20);
        if(newPassword.equals(CurrentStudent.getCurrentAccountPassword())){
            System.out.println("Khong duoc trung voi mat khau cu");
            return;
        }
        do {
            String newPassword2 = Validator.validateString("Nhap lai mat khau moi",sc,0,20);
            if(newPassword.equals(newPassword2)){
                break;
            }


        }while (true);

        if(studentService.changePassword(newPassword,CurrentStudent.getCurrentStudent())){
            System.out.println("Cap nhat mat khau thanh cong");
        }else{
            System.out.println("Cap nhat mat khau that bai");
        }

    }
}
