package ra.edu.business.service.Student;

import ra.edu.business.dao.Student.StudentDAO;
import ra.edu.business.dao.Student.StudentDAOImp;
import ra.edu.business.dao.login.loginDAO;
import ra.edu.business.model.Account;
import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.Student;
import ra.edu.utils.TableConfig;

import java.util.List;

public class    StudentServiceImp implements StudentService {
    private final StudentDAO studentDao;
    public static Student currentStudent;
    private final int pageSize = 5;

    public StudentServiceImp() {
        this.studentDao = new StudentDAOImp();
    }


    @Override
    public int getTotalPage() {
        return (int) (Math.ceil((double) studentDao.getTotalPage() / pageSize));

    }

    @Override
    public TableConfig<Student> getDataPag(int page) {
        List<Student> students = studentDao.getDataPag(page, pageSize);
        int totalPages = getTotalPage();

        return new TableConfig<>(students, totalPages);
    }

    @Override
    public boolean save(Student student) {
        return studentDao.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public int delete(Student student) {
        return studentDao.delete(student);
    }

    @Override
    public Student findStudentById(int id) {
        return studentDao.findStudentById(id);
    }

    public List<Student> studentInEnrollment() {
        return studentDao.studentInEnrollment();
    }

    @Override
    public TableConfig<Student> findByName(int page, String name) {
        List<Student> students = studentDao.findByName(page, pageSize, name);
        return new TableConfig<>(students, getTotalPageBySearchName(name));
    }


    @Override
    public TableConfig<Student> findByEmail(int page, String email) {
        List<Student> students = studentDao.findByEmail(page, pageSize, email);
        return new TableConfig<>(students, getTotalPageBySearchName(email));
    }

    @Override
    public int getTotalPageBySearchName(String name) {
        int totalItems = studentDao.getTotalPageBySearchName(name);
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    @Override
    public int getTotalPageBySearchEmail(String name) {
        int totalItems = studentDao.getTotalStudentsByEmail(name);
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    @Override
    public int getTotalStudents() {
        return studentDao.getTotalStudents();
    }

    @Override
    public TableConfig<Student> sortByNameASC(int page) {
        List<Student> students = studentDao.sortByNameASC(page, pageSize);
        int totalItems = studentDao.getTotalStudents();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        return new TableConfig<>(students, totalPages);
    }

    @Override
    public TableConfig<Student> sortByNameDESC(int page) {
        List<Student> students = studentDao.sortByNameDESC(page, pageSize);
        int totalItems = studentDao.getTotalStudents();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        return new TableConfig<>(students, totalPages);
    }

    public int assignCourse(Student student, Course course) {
        return studentDao.assignCourse(student, course);
    }

    @Override
    public TableConfig<Enrollment> currentStudentEnrollmentPaging(int page, Student student) {
        List<Enrollment> enrollments = studentDao.currentStudentEnrollment(page, pageSize, student);
        int totalItems = studentDao.totalRegisteredCourse(student);
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        return new TableConfig<>(enrollments, totalPages);
    }
@Override
    public int CancleEnrollment(Student student ,int id ){
        return studentDao.CancleEnrollment(student,id);
    }
   public  boolean changePassword(String password,Student student){
        return studentDao.changePassword(password,student);
    }

}