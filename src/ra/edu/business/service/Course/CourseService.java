package ra.edu.business.service.Course;

import ra.edu.business.model.Course;
import ra.edu.business.service.AppService;
import ra.edu.utils.TableConfig;

import java.util.List;

public interface CourseService extends AppService<Course> {
    Course findCourseById(int id);
    int getTotalPageBySearch(String name);
    TableConfig<Course> findCourseByName(int page , String name);
    TableConfig<Course> sortByNameASC(int page);
    TableConfig<Course> sortByNameDESC(int page);


}
