package ra.edu.business.service.Student;

import ra.edu.business.dao.Student.StudentDAO;
import ra.edu.business.dao.Student.StudentDAOImp;
import ra.edu.business.dao.login.loginDAO;
import ra.edu.business.model.Student;
import ra.edu.utils.TableConfig;

import java.util.List;

public class StudentServiceImp implements StudentService {
    private final StudentDAO studentDao;
    public StudentServiceImp(){
        this.studentDao = new StudentDAOImp();
    }


    @Override
    public int getTotalPage() {
        return 0;
    }

    @Override
    public List<Student> getDataPag(int page) {
        return List.of();
    }

    @Override
    public boolean save(Student student) {
        return false;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }
    @Override
    public Student findStudentByEmail(String email) {
        return studentDao.findStudentByEmail(email);
    }
}
