package ra.edu.business.service.Enrollment;

import ra.edu.business.dao.Enrollment.EnrollmentDAO;
import ra.edu.business.dao.Enrollment.EnrollmentDAOImp;
import ra.edu.business.model.Enrollment;
import ra.edu.utils.TableConfig;

import java.util.List;

public class EnrollmentServiceImp implements EnrollmentSERVICE {
    private final int pageSize = 5;
    private EnrollmentDAO enrollmentDAO;
    public EnrollmentServiceImp(){
        enrollmentDAO = new EnrollmentDAOImp();
    }
    @Override
    public int getTotalPage() {
        int totalPage = enrollmentDAO.getTotalPage();
        return (int) Math.ceil((double)totalPage/pageSize);
    }

    @Override
    public TableConfig<Enrollment> getDataPag(int page) {

        List<Enrollment> enrollments = enrollmentDAO.getDataPag(page, pageSize);

        return new TableConfig<>(enrollments, getTotalPage());
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
        return enrollmentDAO.findEnrollmentById(id);
    }

    @Override
    public int confirmEnrollment(Enrollment enrollment) {
        return enrollmentDAO.confirmEnrollment(enrollment);
    }
    @Override
    public int denyEnrollment(Enrollment enrollment) {
        return enrollmentDAO.denyEnrollment(enrollment);
    }
}
