package ra.edu.business.service.Enrollment;

import ra.edu.business.model.Enrollment;
import ra.edu.business.service.AppService;

public interface EnrollmentSERVICE extends AppService<Enrollment> {
    Enrollment findEnrollmentById(int id);
    int confirmEnrollment(Enrollment enrollment);
    int denyEnrollment(Enrollment enrollment);
}
