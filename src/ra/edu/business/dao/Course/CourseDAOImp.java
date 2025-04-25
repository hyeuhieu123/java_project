package ra.edu.business.dao.Course;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Course;
import ra.edu.utils.TableConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImp implements CourseDAO {


    @Override
    public int getTotalPage() {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("SELECT COUNT(id) as total FROM course");
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
    public List<Course> getDataPag(int page,int pageSize) {
        List<Course> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from course limit ? offset ?");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, (page - 1) * pageSize);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_date(rs.getDate("create_date").toLocalDate());
                list.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public boolean save(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("INSERT INTO course (name, duration, instructor) VALUES ( ?, ?, ?)");
            callSt.setString(1, course.getName());
            callSt.setInt(2, course.getDuration());
            callSt.setString(3, course.getInstructor());

            callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("UPDATE course SET name = ?, duration = ?, instructor = ?, create_date = ? WHERE id = ?");
            callSt.setString(1, course.getName());
            callSt.setInt(2, course.getDuration());
            callSt.setString(3, course.getInstructor());
            callSt.setDate(4, Date.valueOf(course.getCreate_date()));
            callSt.setInt(5, course.getId());
            callSt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Course course) {
        Connection conn = null;
        CallableStatement callSt= null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call delete_course(?,?)");
            callSt.setInt(1, course.getId());
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            return callSt.getInt(2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course findCourseById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Course course = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from course where id = ?");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_date(rs.getDate("create_date").toLocalDate());
            }
            return course;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getTotalPageBySearch(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("SELECT COUNT(id) as total FROM course WHERE name LIKE CONCAT('%', ?, '%')");
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
    public List<Course> findCourseByName(int page,int pageSize, String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> list = new ArrayList<>();
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from course where name like concat('%',?,'%') limit ? offset ?");
            callSt.setString(1, name);
            callSt.setInt(2, pageSize);
            callSt.setInt(3, (page - 1) * pageSize);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_date(rs.getDate("create_date").toLocalDate());
               list.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public boolean isNameExists(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from course where name = ?");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> sortByNameASC(int page, int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from course   order by name asc  limit ? offset ?");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, (page - 1) * pageSize);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_date(rs.getDate("create_date").toLocalDate());
                list.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public List<Course> sortByNameDESC(int page, int pageSize) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> list = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from course   order by name desc  limit ? offset ?");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, (page - 1) * pageSize);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_date(rs.getDate("create_date").toLocalDate());
                list.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
