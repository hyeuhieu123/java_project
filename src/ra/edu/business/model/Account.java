package ra.edu.business.model;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class Account {
    private int id;
    private String email;
    private String password;
    private String role;
    private Student student;
    public void inputData(Scanner sc){
        this.email = Validator.validateString("Nhap email dang nhap",sc,1,30);
        this.password = Validator.validateString("Nhap mat khau",sc,1,30);


    }
    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public  Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", student=" + student +
                '}';
    }
}
