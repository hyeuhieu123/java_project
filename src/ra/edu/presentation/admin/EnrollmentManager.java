package ra.edu.presentation.admin;

import ra.edu.business.model.Enrollment;
import ra.edu.business.service.Enrollment.EnrollmentSERVICE;
import ra.edu.business.service.Enrollment.EnrollmentServiceImp;
import ra.edu.utils.PaginationUtil;
import ra.edu.utils.TableConfig;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class EnrollmentManager {
    private EnrollmentSERVICE enrollmentService;
    public EnrollmentManager() {
        this.enrollmentService = new EnrollmentServiceImp();
    }
    public static void displayEnrollmentMenu(Scanner sc) {
        do {
            EnrollmentManager enrollmentManager = new EnrollmentManager();

            System.out.println("╔═════════════════════════════════════════════════════╗");
            System.out.println("║                 ENROLLMENT MANAGER                  ║");
            System.out.println("╠═════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Hien thi danh sach sv dang ky theo tung khoa hoc ║");
            System.out.println("║ 2. Duyet sinh vien dang ky khoa hoc                 ║");
            System.out.println("║ 3. Xoa sinh vien khoi khoa hoc                      ║");
            System.out.println("║ 4. Thoat                                            ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");
            int choice = Validator.validateInteger("Nhap lua chon", sc);
            switch (choice) {
                case 1:
                    enrollmentManager.displayEnrollment(sc);
                    break;
                case 2:
                    enrollmentManager.approveEnrollment(sc);
                    break;
                case 3:
                    enrollmentManager.deleteEnrollment(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Vui long chon tu 1 den 4");
            }


        } while (true);
    }
    public void displayEnrollment(Scanner sc){
        int currentPage = 1;
        do {
            TableConfig<Enrollment> tableConfig = enrollmentService.getDataPag(currentPage);
            List<Enrollment> enrollments = tableConfig.getItems();
            System.out.println("╔════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╦════════════════════════════════╗");
            System.out.printf("║ %-30s ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                    "Ma Giao Dich", "Ten SV", "Ten Khoa Hoc", "Ngay Dang Ky", "Trang Thai");
            System.out.println("╠════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╬════════════════════════════════╣");

// Display enrollments in a loop
            for (Enrollment enrollment : enrollments) {
                enrollment.displayData();
            }

// Footer
            System.out.println("╚════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╩════════════════════════════════╝");
            currentPage = PaginationUtil.handlePagination(sc, currentPage, tableConfig.getTotalPages());
            if(currentPage == 0) {
                break;
            }
        }while(true);
    }
    public void approveEnrollment(Scanner sc){
        int id = Validator.validateInteger("Nhap ma giao dich muon duyet",sc);
        Enrollment enrollment = enrollmentService.findEnrollmentById(id);
        if (enrollment == null){
            System.out.println("Khong tim thay giao dich");
            return;
        }
        int result = enrollmentService.confirmEnrollment(enrollment);
        if (result == 1){
            System.out.println("Duyet thanh cong");
        } else if (result ==2) {
            System.out.println("Giao dich da duoc duyet truoc do, khong the duyet lai");

        }else {
            System.out.println("Giao dich da bi tu choi, khong the duyet");
        }
    }
  public void deleteEnrollment(Scanner sc){
        int id = Validator.validateInteger("Nhap ma giao dich muon tu choi",sc);
        Enrollment enrollment = enrollmentService.findEnrollmentById(id);
        if (enrollment == null){
            System.out.println("Khong tim thay giao dich");
            return;
        }
        int result = enrollmentService.denyEnrollment(enrollment);
        if (result == 1){
            System.out.println("Tu choi thanh cong");
        } else if (result ==2) {
            System.out.println("Giao dich da duoc duyet roi, khong the tu choi");

        }else {
            System.out.println("Giao dich da bi tu choi truoc do, khong the tu choi lai");
        }
  }
}