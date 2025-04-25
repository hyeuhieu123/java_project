package ra.edu.presentation.admin;

import ra.edu.business.model.Student;
import ra.edu.business.service.Student.StudentService;
import ra.edu.business.service.Student.StudentServiceImp;
import ra.edu.utils.PaginationUtil;
import ra.edu.utils.TableConfig;
import ra.edu.validate.StudentValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
        private StudentService studentService;
        public StudentManager() {
            this.studentService = new StudentServiceImp();
        }
        public static  void displayStudentMenu( Scanner sc){
            do {
                StudentManager studentManager = new StudentManager();
                System.out.println("╔═════════════════════════════════════╗");
                System.out.println("║         QUAN LY SINH VIEN           ║");
                System.out.println("╠═════════════════════════════════════╣");
                System.out.println("║ 1. Danh sach sinh vien              ║");
                System.out.println("║ 2. Them sinh vien                   ║");
                System.out.println("║ 3. Cap nhat sinh vien               ║");
                System.out.println("║ 4. Xoa sinh vien                    ║");
                System.out.println("║ 5. Tim kiem hoc vien (ten / email)  ║");
                System.out.println("║ 6. Sap xep sinh vien theo ten       ║");
                System.out.println("║ 7. Exit                             ║");
                System.out.println("╚═════════════════════════════════════╝");
                int choice = Validator.validateInteger("Nhap lua chon", sc);
                switch (choice) {
                    case 1:
                        studentManager.displayStudent(sc);
                        break;
                    case 2:
                        studentManager.addStudent(sc);
                        break;
                    case 3:
                    studentManager.updateStudent(sc);
                        break;
                    case 4:
                    studentManager.deleteStudent(sc);
                        break;
                    case 5:
                        System.out.println("1. Tim kiem theo ten");
                        System.out.println("2. Tim kiem theo email");
                        int option = Validator.validateInteger("Nhap lua chon ", sc);
                        switch (option) {
                            case 1:
                                studentManager.ListStudentByName(sc);
                                break;
                            case 2:
                                studentManager.listStudentByEmail(sc);
                                break;
                            default:
                                System.out.println("Lua chon khong hop le");
                        }
                        break;
                    case 6:
                        studentManager.sortStudent(sc);
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Lua chon ko hop le. Hay nhap lai");
                }
            } while (true);
        }
        public  void displayStudent(Scanner sc){
            int current_page=1;
            do {
                TableConfig<Student> studentTableConfig = studentService.getDataPag(current_page);
                List<Student> students = studentTableConfig.getItems();
                System.out.println("╔════════════════════╦════════════════════╦════════════════════╦══════════════════════════════╦════════════════════╦════════════════════╦════════════════════╗");
                System.out.printf("║%-20s║%-20s║%-20s║%-30s║%-20s║%-20s║%-20s║\n",
                        "MS SV", "Ten", "Ngay sinh", "Email", "Gioi tinh", "SDT", "Ngay tao");
                System.out.println("╠════════════════════╬════════════════════╬════════════════════╬══════════════════════════════╬════════════════════╬════════════════════╬════════════════════╣");
                for(Student std : students){
                    std.displayData();
                }
                System.out.println("╚════════════════════╩════════════════════╩════════════════════╩══════════════════════════════╩════════════════════╩════════════════════╩════════════════════╝");
                current_page = PaginationUtil.handlePagination(sc, current_page, studentTableConfig.getTotalPages());
                if (current_page == 0) {
                    return; // Exit pagination
                }

            }while(true);

        }
        public void addStudent(Scanner sc){
            Student student = new Student();
            student.inputData(sc);
            if(studentService.save(student)) {
                System.out.println("Them moi thanh cong");
            }else {
                    System.out.println("Them moi that bai");
                }
        }
        public void updateStudent(Scanner sc){
            int id= Validator.validateInteger("Nhap id sinh vien can sua", sc);
            Student student = studentService.findStudentById(id);
            if(student == null){
                System.out.println("Khong tim thay sinh vien");
                return;
            }
            System.out.println("╔════════════════════╦════════════════════╦════════════════════╦══════════════════════════════╦════════════════════╦════════════════════╦════════════════════╗");
            System.out.printf("║%-20s║%-20s║%-20s║%-30s║%-20s║%-20s║%-20s║\n",
                    "MS SV", "Ten", "Ngay sinh", "Email", "Gioi tinh", "SDT", "Ngay tao");
            System.out.println("╠════════════════════╬════════════════════╬════════════════════╬══════════════════════════════╬════════════════════╬════════════════════╬════════════════════╣");
            student.displayData();
            System.out.println("╚════════════════════╩════════════════════╩════════════════════╩══════════════════════════════╩════════════════════╩════════════════════╩════════════════════╝");


            System.out.println("╔═══════════════════════════════╗");
            System.out.println("║         CAP NHAT SINH VIEN    ║");
            System.out.println("╠═══════════════════════════════╣");
            System.out.println("║ 1. Cap nhat ten               ║");
            System.out.println("║ 2. Cap nhat ngay sinh         ║");
            System.out.println("║ 3. Cap nhat email             ║");
            System.out.println("║ 4. Cap nhat gioi tinh         ║");
            System.out.println("║ 5. Cap nhat so dien thoai     ║");
            System.out.println("║ 6. Exit                       ║");
            System.out.println("╚═══════════════════════════════╝");
            int choice = Validator.validateInteger("Nhap lua chon", sc);
            switch (choice){
                case 1:
                    String name = Validator.validateString("Nhap ten sinh vien", sc,1,20);
                    student.setName(name);
                    break;
                case 2:
                    LocalDate dob = Validator.validateDate("Nhap ngay sinh", sc);
                    student.setDob(dob);
                    break;
                case 3:
                    String email = StudentValidator.validateStudentEmail("Nhap email", sc);
                    student.setEmail(email);
                    break;
                case 4:
                    Boolean sex = Validator.validateBoolean("Nhap gioi tinh ",sc);
                    student.setSex(sex);
                    break;
                    case 5:
                        String phone = Validator.validatePhone("Nhap so dien thoai", sc);
                        student.setPhone(phone);
                        break;
                case 6:
                    return;
                default:
                    System.out.println("Lua chon ko hop le");

            }
            if(studentService.update(student)){
                System.out.println("Cap nhat thanh cong");
            }else {
                System.out.println("Cap nhat that bai");
            }
}

        public void deleteStudent(Scanner sc){
           int id = Validator.validateInteger("Nhap id can xoa ",sc);
                List<Student> students = studentService.studentInEnrollment();
            Student student = studentService.findStudentById(id);
            if(student==null){
                System.out.println("Khong tim thay sinh vien");
                return;
            }

                if( students.stream().anyMatch(std -> std.getId() == student.getId())){
                    System.out.println("Khong the xoa sinh vien nay vi da dang ky hoc");
                    return;
                }

            System.out.println("╔════════════════════╦════════════════════╦════════════════════╦══════════════════════════════╦════════════════════╦════════════════════╦════════════════════╗");
            System.out.printf("║%-20s║%-20s║%-20s║%-30s║%-20s║%-20s║%-20s║\n",
                    "MS SV", "Ten", "Ngay sinh", "Email", "Gioi tinh", "SDT", "Ngay tao");
            System.out.println("╠════════════════════╬════════════════════╬════════════════════╬══════════════════════════════╬════════════════════╬════════════════════╬════════════════════╣");
            student.displayData();
            System.out.println("╚════════════════════╩════════════════════╩════════════════════╩══════════════════════════════╩════════════════════╩════════════════════╩════════════════════╝");
                System.out.println("Ban co chac muon xoa sinh vien nay khong? (y/n)");
                String confirm = Validator.validateString("Nhap y/n", sc, 1, 1);
                if(confirm.equalsIgnoreCase("y")) {
                    if (studentService.delete(student) == 1) {
                        System.out.println("Xoa thanh cong");
                    } else {
                        System.out.println("Xoa that bai");
                    }


                }
        }

        public void ListStudentByName(Scanner sc){
            String name = Validator.validateString("Nhap ten can tim",sc,1,20);
            int current_page =1;
            do {
                TableConfig<Student>  table= studentService.findByName(current_page,name);
                List<Student> students = table.getItems();
                if (students.isEmpty()) {
                    System.out.println("Khong tim thay sinh vien nao ten: " + name);
                    return;
                }
                System.out.println("╔════════════════════╦════════════════════╦════════════════════╦══════════════════════════════╦════════════════════╦════════════════════╦════════════════════╗");
                System.out.printf("║%-20s║%-20s║%-20s║%-30s║%-20s║%-20s║%-20s║\n",
                        "MS SV", "Ten", "Ngay sinh", "Email", "Gioi tinh", "SDT", "Ngay tao");
                System.out.println("╠════════════════════╬════════════════════╬════════════════════╬══════════════════════════════╬════════════════════╬════════════════════╬════════════════════╣");
                for(Student std : students){
                    std.displayData();
                }
                System.out.println("╚════════════════════╩════════════════════╩════════════════════╩══════════════════════════════╩════════════════════╩════════════════════╩════════════════════╝");
                current_page = PaginationUtil.handlePagination(sc,current_page,table.getTotalPages());

                if(current_page==0){
                 break;
                }

            }while(true);
        }
    public void listStudentByEmail(Scanner sc) {
        String email = Validator.validateString("Nhap email can tim ", sc,1,20);
        int currentPage = 1;
        do {
            TableConfig<Student> tableConfig = studentService.findByEmail(currentPage, email);
            List<Student> students = tableConfig.getItems();

            if (students.isEmpty()) {
                System.out.println("Khong tim thay sinh vien nao co email: " + email);
                return;
            }
            System.out.println("╔════════════════════╦════════════════════╦════════════════════╦══════════════════════════════╦════════════════════╦════════════════════╦════════════════════╗");
            System.out.printf("║%-20s║%-20s║%-20s║%-30s║%-20s║%-20s║%-20s║\n",
                    "MS SV", "Ten", "Ngay sinh", "Email", "Gioi tinh", "SDT", "Ngay tao");
            System.out.println("╠════════════════════╬════════════════════╬════════════════════╬══════════════════════════════╬════════════════════╬════════════════════╬════════════════════╣");
            for(Student std : students){
                std.displayData();
            }
            System.out.println("╚════════════════════╩════════════════════╩════════════════════╩══════════════════════════════╩════════════════════╩════════════════════╩════════════════════╝");

            currentPage = PaginationUtil.handlePagination(sc, currentPage, tableConfig.getTotalPages());
            if (currentPage == 0) {
                break;
            }
        } while (true);
    }
    public void sortStudent(Scanner sc) {
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║         SAP XEP SINH VIEN     ║");
        System.out.println("╠═══════════════════════════════╣");
        System.out.println("║ 1. Sap xep theo ten tang dan  ║");
        System.out.println("║ 2. Sap xep theo ten giam dan  ║");
        System.out.println("║ 3. Thoat                       ║");
        System.out.println("╚═══════════════════════════════╝");

        int choice = Validator.validateInteger("Nhap lua chon", sc);
        int currentPage = 1;

        switch (choice) {
            case 1:
                do {
                    TableConfig<Student> tableConfig = studentService.sortByNameASC(currentPage);
                    List<Student> students = tableConfig.getItems();

                    System.out.println("╔════════════════════╦════════════════════╦════════════════════╦══════════════════════════════╦════════════════════╦════════════════════╦════════════════════╗");
                    System.out.printf("║%-20s║%-20s║%-20s║%-30s║%-20s║%-20s║%-20s║\n",
                            "MS SV", "Ten", "Ngay sinh", "Email", "Gioi tinh", "SDT", "Ngay tao");
                    System.out.println("╠════════════════════╬════════════════════╬════════════════════╬══════════════════════════════╬════════════════════╬════════════════════╬════════════════════╣");
                    for(Student std : students){
                        std.displayData();
                    }
                    System.out.println("╚════════════════════╩════════════════════╩════════════════════╩══════════════════════════════╩════════════════════╩════════════════════╩════════════════════╝");
                    currentPage = PaginationUtil.handlePagination(sc, currentPage, tableConfig.getTotalPages());
                    if (currentPage == 0) {
                        return;
                    }
                } while (true);

            case 2:
                do {
                    TableConfig<Student> tableConfig = studentService.sortByNameDESC(currentPage);
                    List<Student> students = tableConfig.getItems();

                    System.out.println("╔════════════════════╦════════════════════╦════════════════════╦══════════════════════════════╦════════════════════╦════════════════════╦════════════════════╗");
                    System.out.printf("║%-20s║%-20s║%-20s║%-30s║%-20s║%-20s║%-20s║\n",
                            "MS SV", "Ten", "Ngay sinh", "Email", "Gioi tinh", "SDT", "Ngay tao");
                    System.out.println("╠════════════════════╬════════════════════╬════════════════════╬══════════════════════════════╬════════════════════╬════════════════════╬════════════════════╣");
                    for(Student std : students){
                        std.displayData();
                    }
                    System.out.println("╚════════════════════╩════════════════════╩════════════════════╩══════════════════════════════╩════════════════════╩════════════════════╩════════════════════╝");
                    currentPage = PaginationUtil.handlePagination(sc, currentPage, tableConfig.getTotalPages());
                    if (currentPage == 0) {
                        return;
                    }
                } while (true);

            case 3:
                return;

            default:
                System.out.println("Lua chon ko hop le. Hay nhap lai");
        }
    }
}
