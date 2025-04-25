package ra.edu.business.dao.Enrollment;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.dao.Course.CourseDAO;
import ra.edu.business.dao.Course.CourseDAOImp;
import ra.edu.business.dao.Student.StudentDAO;
import ra.edu.business.dao.Student.StudentDAOImp;
import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImp implements EnrollmentDAO{
    private StudentDAO studentDao = new StudentDAOImp();
    private CourseDAO courseDao = new CourseDAOImp();
    @Override
    public int getTotalPage() {
        Connection conn = null;
        CallableStatement callSt=null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select count(id) as total  from enrollment");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()){
                int total = rs.getInt("total");
                return total;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Enrollment> getDataPag(int page, int pageSize) {
        Connection conn = null;
        CallableStatement callSt=null;
        List<Enrollment> list = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from enrollment order by status limit ? offset ?");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, (page-1)*pageSize);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            list = new ArrayList<>();
            while (rs.next()){
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                Student student = studentDao.findStudentById(rs.getInt("student_id"));
                enrollment.setStudent(student);
                Course course = courseDao.findCourseById(rs.getInt("course_id"));
                enrollment.setCourse(course);
                enrollment.setRegistered_at(rs.getTimestamp("registered_at").toLocalDateTime());
                enrollment.setStatus(rs.getString("status"));
                list.add(enrollment);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean save(Enrollment enrollment) {
        return false;
    }

    @Override
    public boolean update(Enrollment enrollment) {
        return false;
    }

    @Override
    public int delete(Enrollment enrollment) {
        return 0;
    }

    @Override
    public Enrollment findEnrollmentById(int id) {
        Connection conn = null;
        CallableStatement callSt=null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from enrollment where id = ? ");
            callSt.setInt(1, id);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()){
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                Student student = studentDao.findStudentById(rs.getInt("student_id"));
                enrollment.setStudent(student);
                Course course = courseDao.findCourseById(rs.getInt("course_id"));
                enrollment.setCourse(course);
                enrollment.setRegistered_at(rs.getTimestamp("registered_at").toLocalDateTime());

                enrollment.setStatus(rs.getString("status"));
                return enrollment;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public int confirmEnrollment(Enrollment enrollment) {
        Connection conn = null;
        CallableStatement callSt=null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call confirm_enrollment(?,?)");
            callSt.setInt(1,enrollment.getId());
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            int result = callSt.getInt(2);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int denyEnrollment(Enrollment enrollment) {
        Connection conn = null;
        CallableStatement callSt=null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call deny_enrollment(?,?)");
            callSt.setInt(1,enrollment.getId());
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            int result = callSt.getInt(2);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
