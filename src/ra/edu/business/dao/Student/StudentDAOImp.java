package ra.edu.business.dao.Student;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.dao.Course.CourseDAO;
import ra.edu.business.dao.Course.CourseDAOImp;
import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.Student;
import ra.edu.utils.TableConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO{
    private CourseDAO courseDao= new CourseDAOImp();

    @Override
    public int getTotalPage() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("SELECT COUNT(student_id) as total FROM student");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                int total = rs.getInt("total");
                return total;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Student> getDataPag(int page,int pageSize) {
        Connection conn = null;
        CallableStatement callSt= null;
        List<Student> list = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from student limit ? offset ?");
            callSt.setInt(1,pageSize);
            callSt.setInt(2,(page-1)*pageSize);
            ResultSet rs = callSt.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                list.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt =conn.prepareCall("{call insert_student_and_account(?,?,?,?,?)}");
            callSt.setString(1,student.getName());
            callSt.setString(2,student.getDob().toString());
            callSt.setString(3,student.getEmail());
            callSt.setBoolean(4,student.isSex());
            callSt.setString(5,student.getPhone());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt=null;
        try{
            conn = ConnectionDB.openConnection();
            callSt= conn.prepareCall("{call update_student(?,?,?,?,?,?)} ");
            callSt.setInt(1,student.getId());
            callSt.setString(2,student.getName());
            callSt.setString(3,student.getDob().toString());
            callSt.setString(4,student.getEmail());
            callSt.setBoolean(5,student.isSex());
            callSt.setString(6,student.getPhone());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    public List<Student> studentInEnrollment(){
        Connection conn = null;
        CallableStatement callSt= null;
        List<Student> list = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from student where student_id  in (select student_id from enrollment)");
            ResultSet rs = callSt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()){
                Student student= new Student();
                student.setId(rs.getInt("student_id"));
                list.add(student);
            }
            return list;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findByName(int page, int pageSize, String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> list = new ArrayList<>();
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from student where name like concat('%',?,'%') limit ? offset ?");
            callSt.setString(1, name);
            callSt.setInt(2, pageSize);
            callSt.setInt(3, (page - 1) * pageSize);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
               Student student = new Student();
               student.setId(rs.getInt("student_id"));
               student.setName(rs.getString("name"));
               student.setDob(rs.getDate("dob").toLocalDate());
               student.setEmail(rs.getString("email"));
               student.setSex(rs.getBoolean("sex"));
               student.setPhone((rs.getString("phone")));
               student.setCreate_at(rs.getDate("create_at").toLocalDate());
               list.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Student> findByEmail(int page, int pageSize, String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> list = new ArrayList<>();
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from student where email like concat('%',?,'%') limit ? offset ?");
            callSt.setString(1, email);
            callSt.setInt(2, pageSize);
            callSt.setInt(3, (page - 1) * pageSize);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone((rs.getString("phone")));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                list.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public int getTotalPageBySearchName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("SELECT COUNT(student_id) as total FROM student WHERE name LIKE CONCAT('%', ?, '%')");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int getTotalStudentsByEmail(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("SELECT COUNT(student_id) as total FROM student WHERE email LIKE CONCAT('%', ?, '%')");
            callSt.setString(1, email);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int delete(Student student) {
        Connection conn = null;
        CallableStatement callSt= null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_student_account(?)}");
            callSt.setInt(1,student.getId());
            callSt.execute();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public Student findStudentById(int id) {
        Connection conn = null;
        CallableStatement callSt=null;
        Student student = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from student where student_id = ?") ;
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
            }
            return student;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int getTotalStudents() {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall("SELECT COUNT(student_id) as total FROM student")) {

            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    @Override
    public List<Student> sortByNameASC(int page, int pageSize) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(
                     "SELECT * FROM student ORDER BY name ASC LIMIT ? OFFSET ?")) {

            cs.setInt(1, pageSize);
            cs.setInt(2, (page - 1) * pageSize);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone((rs.getString("phone")));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                students.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public List<Student> sortByNameDESC(int page, int pageSize) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(
                     "SELECT * FROM student ORDER BY name DESC LIMIT ? OFFSET ?")) {

            cs.setInt(1, pageSize);
            cs.setInt(2, (page - 1) * pageSize);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone((rs.getString("phone")));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                students.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public int assignCourse(Student student, Course course) {
        Connection conn  = null;
        CallableStatement callSt=null;
        try {
            conn =ConnectionDB.openConnection();
            callSt=conn.prepareCall("{call assign_course(?,?,?)}");
            callSt.setInt(1,student.getId());
            callSt.setInt(2,course.getId());
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            int result = callSt.getInt(3);
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Enrollment> currentStudentEnrollment(int page, int pageSize, Student student) {
        Connection conn = null;
        CallableStatement callSt=null;
        List<Enrollment> enrollments = new ArrayList<>();
        try{
            conn = ConnectionDB.openConnection();
            callSt=conn.prepareCall("select * from enrollment where student_id =?   limit ? offset ?");
            callSt.setInt(1,student.getId());
            callSt.setInt(2,pageSize);
            callSt.setInt(3,(page-1)*pageSize);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Enrollment enrollment=new Enrollment();
                enrollment.setId(rs.getInt("id"));
                Course course = courseDao.findCourseById(rs.getInt("course_id"));
                enrollment.setCourse(course);

                enrollment.setRegistered_at(rs.getTimestamp("registered_at").toLocalDateTime());

                enrollment.setStatus(rs.getString("status"));
                enrollment.setStudent(student);
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enrollments;
    }

    @Override
    public int totalRegisteredCourse(Student student) {
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall("SELECT COUNT(student_id) as total from enrollment where student_id =?")) {
            cs.setInt(1,student.getId());
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;

    }

    @Override
    public List<Enrollment> choiceCancleEnrollment(Student student) {
        Connection conn = null;
        CallableStatement callSt=null;
        List<Enrollment> enrollments = new ArrayList<>();
        try{
            conn = ConnectionDB.openConnection();
            callSt=conn.prepareCall("select * from enrollment where student_id =? and status !='CONFIRM'");
            callSt.setInt(1,student.getId());
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                Enrollment enrollment=new Enrollment();
                enrollment.setId(rs.getInt("id"));
                Course course = courseDao.findCourseById(rs.getInt("course_id"));
                enrollment.setCourse(course);
                enrollment.setRegistered_at(rs.getTimestamp("registered_at").toLocalDateTime());

                enrollment.setStatus(rs.getString("status"));
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enrollments;

    }
    @Override
   public  int CancleEnrollment(Student student,int id ){
        Connection conn = null;
        CallableStatement callSt=null;

        try{
            conn = ConnectionDB.openConnection();
            callSt=conn.prepareCall("{call cancle_enrollment(?,?,?)} " );
            callSt.setInt(1,student.getId());
            callSt.setInt(2,id);
            callSt.registerOutParameter(3,Types.INTEGER);

            callSt.execute();



            return  callSt.getInt(3);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean changePassword(String password,Student student) {
        Connection conn = null;
        CallableStatement callSt=null;
                try{
                    conn = ConnectionDB.openConnection();
                    callSt=conn.prepareCall("update  account set password = ? where student_id = ?");
                    callSt.setString(1,password);
                    callSt.setInt(2,student.getId());
                    callSt.execute();
                    return true;

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
    }
    public boolean isEmailExists(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from account where email = ?");
            callSt.setString(1, email);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
