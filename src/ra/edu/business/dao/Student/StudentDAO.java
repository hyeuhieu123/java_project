package ra.edu.business.dao.Student;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.Student;

import java.util.List;

public interface StudentDAO extends AppDAO<Student> {
    Student findStudentById(int id );
     List<Student> studentInEnrollment();
     List<Student> findByName(int page, int pageSize, String name);
     List<Student> findByEmail(int page, int pageSize, String name);
    int getTotalPageBySearchName(String name);
    int getTotalStudentsByEmail(String email);
    int getTotalStudents();
    List<Student> sortByNameASC(int page, int pageSize);
    List<Student> sortByNameDESC(int page, int pageSize);

    ///
    public int assignCourse(Student student, Course course);
    public List<Enrollment> currentStudentEnrollment(int page,int pageSize,Student student);
    int totalRegisteredCourse(Student student);

    List<Enrollment> choiceCancleEnrollment(Student student);

    public int CancleEnrollment(Student student ,int id );
    boolean changePassword(String password,Student student);

    public boolean isEmailExists(String email);
}
