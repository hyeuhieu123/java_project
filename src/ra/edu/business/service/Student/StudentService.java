package ra.edu.business.service.Student;

import ra.edu.business.model.Student;
import ra.edu.business.service.AppService;

public interface StudentService extends AppService<Student> {
    Student findStudentByEmail(String email);

}
