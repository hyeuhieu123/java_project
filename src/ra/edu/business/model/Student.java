package ra.edu.business.model;

import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Student implements IApp {
    private int id;
    private String name;
    private LocalDate dob;
    private String email;
    private String password;
    private String phone;
    private boolean sex;
    private LocalDate create_at;


    public Student() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    }
    public void inputDataLogin(Scanner sc){
        this.email= Validator.validateEmail("nhap email",sc);
        this.password=Validator.validateString("nhap mat khau",sc,1,20);

    }

    @Override
    public void displayData() {

    }
}
