package ra.edu.business.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Enrollment implements IApp {
    private int id;
    private Student student;
    private Course course;
    private LocalDateTime registered_at;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(LocalDateTime registered_at) {
        this.registered_at = registered_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {

    }

    @Override
    public void displayData() {
        System.out.printf("║ %-30d ║ %-30s ║ %-30s ║ %-30s ║ %-30s ║%n",
                id,
                student.getName(),
                course.getName(),
                registered_at.toString(),
                status);
    }

}
