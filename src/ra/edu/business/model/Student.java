package ra.edu.business.model;

import ra.edu.validate.StudentValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Student implements IApp {
    private int id;
    private String name;
    private LocalDate dob;
    private String email;
    private String phone;
    private boolean sex;
    private LocalDate create_at;


    public Student() {
    }

    public Student(int id ,String name, LocalDate dob, String email,  boolean sex,String phone, LocalDate create_at) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.create_at = create_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    @Override
    public void inputData(Scanner sc) {
        name = Validator.validateString("Nhap ten", sc, 1, 100);
        dob = Validator.validateDate("Nhap ngay sinh", sc);
        email= StudentValidator.validateStudentEmail("Nhap email",sc);
        sex = Validator.validateBoolean("Nhap gioi tinh(true:nam | false:nu)", sc);
        phone = Validator.validatePhone("Nhap so dien thoai", sc);
    }

    @Override
    public void displayData() {
        System.out.printf("║%-20d║%-20s║%-20s║%-30s║%-20s║%-20s║%-20s║\n",
                this.getId(),
                this.getName(),
                this.getDob(),
                this.getEmail(),
                this.isSex() ? "Nam" : "Nu",
                this.getPhone(),
                this.getCreate_at().toString());

    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", create_at=" + create_at +
                '}';
    }
}
