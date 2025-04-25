package ra.edu.business.dao.statistict;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.dao.Enrollment.EnrollmentDAO;
import ra.edu.business.dao.Enrollment.EnrollmentDAOImp;
import ra.edu.business.model.Enrollment;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StatististDAOImp implements StatistictDAO {
    private EnrollmentDAO enrollmentDao = new EnrollmentDAOImp();
    @Override
    public int totalEachCourse() {
        Connection conn = null;
        CallableStatement callSt=null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select count(sub.total) as total from(select  count(e.student_id) as total from enrollment e join course c on e.course_id = c.id join student s on s.student_id = e.student_id where e.status ='CONFIRM'group by  c.name ) as sub");
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
    public int getTotalStudent() {
        Connection conn = null;
        PreparedStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareStatement("SELECT COUNT(student_id) as total FROM student");
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
    public int getTotalCourse() {
        Connection conn = null;
        PreparedStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareStatement("SELECT COUNT(id) as total FROM course");
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
    public Map<String, Integer> totalStudentEachCourse(int page,int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        Map<String,Integer> map = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall(" select c.name as name, count(e.student_id) as total from enrollment e join course c on e.course_id = c.id join student s on s.student_id = e.student_id where e.status ='CONFIRM'group by  c.name limit ? offset ?");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, (page - 1) * pageSize);
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            map = new HashMap<>();
            while (rs.next()) {
                String courseName = rs.getString("name");
                int totalStudents = rs.getInt("total");
                 map.put(courseName, totalStudents);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    @Override
    public Map<String, Integer> findTop5PopularCourses() {
        Connection conn = null;
        CallableStatement callSt = null;
        Map<String,Integer> map = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall(" select c.name as name, count(e.student_id) as total from enrollment e join course c on e.course_id = c.id join student s on s.student_id = e.student_id where e.status ='CONFIRM'group by c.name order by count(e.student_id) desc limit 5");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            map = new LinkedHashMap<>();
            while (rs.next()) {
                String courseName = rs.getString("name");
                int totalStudents = rs.getInt("total");
                map.put(courseName, totalStudents);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    @Override
    public Map<String, Integer> courseAbove10Student() {
       Connection conn = null;
         CallableStatement callSt = null;
          Map<String,Integer> map = null;
          try {
                conn = ConnectionDB.openConnection();
                callSt = conn.prepareCall(" select c.name as name, count(e.student_id) as total from enrollment e join course c on e.course_id = c.id join student s on s.student_id = e.student_id where e.status ='CONFIRM'group by c.name having count(e.student_id) > 10");
                callSt.execute();
                ResultSet rs = callSt.getResultSet();
                map = new LinkedHashMap<>();
                while (rs.next()) {
                 String courseName = rs.getString("name");
                 int totalStudents = rs.getInt("total");
                 map.put(courseName, totalStudents);
                }
          } catch (SQLException e) {
                throw new RuntimeException(e);
          }

          return map;



    }
}
