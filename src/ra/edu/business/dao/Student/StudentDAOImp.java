package ra.edu.business.dao.Student;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Student;
import ra.edu.utils.TableConfig;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImp implements StudentDAO{


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
        Connection conn = null;
        CallableStatement callSt=null;
        Student student = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from student where email = ?") ;
            callSt.setString(1,email);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                student = new Student();
                student.setId(rs.getInt("id"));
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
}
