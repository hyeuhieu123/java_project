package ra.edu.business.dao.Course;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Course;

public interface CourseDAO extends AppDAO<Course> {
    Course findCourseById(int id);
    public boolean isNameExists(String name);
}
