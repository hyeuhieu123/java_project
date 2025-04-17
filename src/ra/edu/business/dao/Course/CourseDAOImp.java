package ra.edu.business.dao.Course;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Course;
import ra.edu.utils.TableConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImp implements CourseDAO {
    private final int PAGE_SIZE = 5;

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
                return (int) Math.ceil((double) total / PAGE_SIZE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Course> getDataPag(int page) {
        List<Course> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from course limit ? offset ?");
            callSt.setInt(1, PAGE_SIZE);
            callSt.setInt(2, (page - 1) * PAGE_SIZE);
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
            callSt = conn.prepareCall("INSERT INTO course (name, duration, instructor, create_date) VALUES (?, ?, ?, ?)");
            callSt.setString(1, course.getName());
            callSt.setInt(2, course.getDuration());
            callSt.setString(3, course.getInstructor());
            callSt.setDate(4, Date.valueOf(course.getCreate_date()));
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
    public boolean delete(Course course) {
        return false;
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
}
