package ra.edu.business.service.Student;

import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.Student;
import ra.edu.business.service.AppService;
import ra.edu.utils.TableConfig;

import java.util.List;

public interface StudentService extends AppService<Student> {
        Student findStudentById(int id);
        List<Student> studentInEnrollment();
       TableConfig<Student> findByName(int page, String name);
        TableConfig<Student> findByEmail(int page, String name);
        int getTotalPageBySearchName(String name);
        int getTotalPageBySearchEmail(String name);
    int getTotalStudents();
    TableConfig<Student> sortByNameASC(int page);
    TableConfig<Student> sortByNameDESC(int page);

    public int assignCourse(Student student, Course course);
    TableConfig<Enrollment> currentStudentEnrollmentPaging(int page, Student student);

    public int CancleEnrollment(Student student ,int id );
    boolean changePassword(String password,Student student);
}
