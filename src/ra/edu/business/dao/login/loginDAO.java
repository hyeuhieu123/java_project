package ra.edu.business.dao.login;

import ra.edu.business.config.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginDAO {
    public boolean loginAdmin(String username, String password) {
        Connection conn = null;
        CallableStatement callSt= null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from admin where username = ? and password = ?");
            callSt.setString(1, username);
            callSt.setString(2, password);
            ResultSet rs = callSt.executeQuery();

            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public boolean loginStudent(String email,String password){
        Connection conn = null;
        CallableStatement callSt= null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("select * from student where email = ? and password = ?");
            callSt.setString(1, email);
            callSt.setString(2, password);
            ResultSet rs = callSt.executeQuery();

            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}
