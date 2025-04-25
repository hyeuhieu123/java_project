package ra.edu.presentation.login;

import ra.edu.business.model.Account;
import ra.edu.business.service.Student.StudentService;
import ra.edu.business.service.Student.StudentServiceImp;
import ra.edu.business.service.login.loginService;

import ra.edu.presentation.admin.AdminUI;
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

    public void login(Scanner sc) {
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║        TRANG DANG NHAP        ║");
        System.out.println("╠═══════════════════════════════╣");
        Account account = new Account();
        account.inputData(sc);
        System.out.println("╚═══════════════════════════════╝");
        account = loginService.login(account.getEmail(), account.getPassword());

         if(account!=null){
             if( account.getRole().equals("admin")) {
                 AdminUI.displayAdminMenu(sc);
             }else if (account.getRole().equals("student")){
                 CurrentStudent.setCurrentStudent(account.getStudent(),account.getPassword());
                 studentUI.displayStudentMenu(sc);
             }
         }
        System.out.println("Thong tin dang nhap khong ton tai");

    }

}
