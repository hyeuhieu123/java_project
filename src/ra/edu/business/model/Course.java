package ra.edu.business.model;

import ra.edu.validate.CourseValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class Course implements IApp {
    private int id ;
    private String name;
    private int duration;
    private String instructor;
    private LocalDate create_date;

    public Course() {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }

    @Override
    public void inputData(Scanner sc) {
        this.name= CourseValidator.validateCourseName("Nhap ten khoa hoc",sc,1,20);
        this.duration=Validator.validateInteger("Nhap thoi gian",sc);
        this.instructor=Validator.validateString("Nhap giang vien",sc,1,20);

    }

    @Override
    public void displayData() {
        System.out.printf("║ %-30s ║ %-30s ║ %-30d ║ %-30s ║ %-30s ║%n",
                this.getId(),
                this.getName(),
                this.getDuration(),
                this.getInstructor(),
                this.getCreate_date());

    }
}
