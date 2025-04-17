package ra.edu.business.service.Course;

import ra.edu.business.model.Course;
import ra.edu.business.service.AppService;

public interface CourseService extends AppService<Course> {
    Course findCourseById(int id);

}
