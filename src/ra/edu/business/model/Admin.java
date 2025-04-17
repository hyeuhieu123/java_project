package ra.edu.business.model;

import ra.edu.validate.Validator;

import java.util.Scanner;

public class Admin implements IApp {
    private int id;
    private String username;
    private String password;

    public Admin() {
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public void inputData(Scanner sc) {
        this.username = Validator.validateString("nhap ten dang nhap",sc,1,20);
        this.password = Validator.validateString("nhap mat khau",sc,1,20);
    }

    @Override
    public void displayData() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
