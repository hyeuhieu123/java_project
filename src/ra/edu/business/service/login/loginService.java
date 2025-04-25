package ra.edu.business.service.login;

import ra.edu.business.dao.login.loginDAO;
import ra.edu.business.model.Account;
import ra.edu.business.model.Student;
import ra.edu.business.service.Student.StudentServiceImp;

public class loginService {
    private final loginDAO loginDao;

    public loginService(){
        this.loginDao = new loginDAO();
    }
    public Account login(String email, String password) {
        return loginDao.login(email,password);
    }


}
