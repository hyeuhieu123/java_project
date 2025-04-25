package ra.edu.business.dao.Course;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Course;

import java.util.List;

public interface CourseDAO extends AppDAO<Course> {
    Course findCourseById(int id);
    List<Course> findCourseByName(int page, int pageSize, String name);
    int getTotalPageBySearch(String name);
    public boolean isNameExists(String name);
    List<Course> sortByNameASC(int page, int pageSize);
    List<Course> sortByNameDESC(int page, int pageSize);



}
