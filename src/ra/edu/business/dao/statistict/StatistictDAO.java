package ra.edu.business.dao.statistict;

import ra.edu.business.model.Enrollment;

import java.util.List;
import java.util.Map;

public interface StatistictDAO {
    int totalEachCourse();
    int getTotalStudent();
    int getTotalCourse();
    Map<String, Integer> totalStudentEachCourse(int page,int pageSize);
    Map<String, Integer> findTop5PopularCourses();
    Map<String, Integer> courseAbove10Student();
}
