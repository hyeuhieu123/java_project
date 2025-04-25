package ra.edu.business.dao.login;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.dao.Student.StudentDAO;
import ra.edu.business.dao.Student.StudentDAOImp;
import ra.edu.business.model.Account;
import ra.edu.business.model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginDAO {
    private StudentDAO studentDao  = new StudentDAOImp();
    public Account login(String email, String password) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall(" SELECT * FROM account a LEFT JOIN student s \n" +
                    "            ON a.student_id = s.student_id \n" +
                    "            WHERE a.email = ? AND a.password = ?");
            callSt.setString(1, email);
            callSt.setString(2, password);
            ResultSet rs = callSt.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt("account_id"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                Student student = studentDao.findStudentById(rs.getInt("s.student_id"));
                account.setStudent(student);
                account.setRole(rs.getString("role"));

                return account;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (callSt != null) callSt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}