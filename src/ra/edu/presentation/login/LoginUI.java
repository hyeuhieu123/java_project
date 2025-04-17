package ra.edu.presentation.login;

import ra.edu.business.model.Admin;
import ra.edu.business.model.Student;
import ra.edu.business.service.Student.StudentService;
import ra.edu.business.service.Student.StudentServiceImp;
import ra.edu.business.service.login.loginService;

import ra.edu.presentation.admin.adminUI;
import ra.edu.presentation.student.studentUI;
import ra.edu.utils.CurrentStudent;


import java.util.Scanner;

public class LoginUI {
    private final loginService loginService;
    private final StudentService studentService;
    public LoginUI() {
        this.loginService = new loginService();
        this.studentService = new StudentServiceImp();

    }

    public void loginAdmin(Scanner sc) {
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║         ADMIN LOGIN           ║");
        System.out.println("╠═══════════════════════════════╣");
        Admin  admin = new Admin();
        admin.inputData(sc);
        System.out.println("╚═══════════════════════════════╝");
        if(loginService.loginAdmin(admin.getUsername(),admin.getPassword())) {
            adminUI.displayAdminMenu(sc);
        } else {
            System.out.println("Sai thong tin dang nhap. Vui long kiem tra lai!");
            return;
        }

    }
    public void loginStudent(Scanner sc) {
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║         STUDENT LOGIN         ║");
        System.out.println("╠═══════════════════════════════╣");
        Student student = new Student();
        student.inputDataLogin(sc);
        System.out.println("╚═══════════════════════════════╝");
        if (loginService.loginStudent(student.getEmail(),student.getPassword())){
            CurrentStudent.setCurrentStudent(studentService.findStudentByEmail(student.getEmail()));
            studentUI.displayStudentMenu(sc);
        } else {
            System.out.println("Sai thong tin dang nhap. Vui long kiem tra lai!");
            return;
        }

    }
}
