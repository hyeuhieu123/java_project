package ra.edu.business.dao.Enrollment;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Enrollment;

public interface EnrollmentDAO extends AppDAO<Enrollment> {
    Enrollment findEnrollmentById(int id);
    int confirmEnrollment(Enrollment enrollment);
    int denyEnrollment(Enrollment enrollment);
}
