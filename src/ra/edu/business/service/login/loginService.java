package ra.edu.business.service.login;

import ra.edu.business.dao.login.loginDAO;

public class loginService {
    private final loginDAO loginDao;
    public loginService(){
        this.loginDao = new loginDAO();
    }
    public boolean loginAdmin(String username, String password) {

        return loginDao.loginAdmin(username,password);
    }

    public boolean loginStudent(String email, String password) {
        return loginDao.loginStudent(email,password);
    }
}
