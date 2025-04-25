package ra.edu.business.service.Statistict;

import ra.edu.business.model.Enrollment;
import ra.edu.utils.TableConfig;

import java.util.List;
import java.util.Map;

public interface StatistictSERVICE {
    int totalEachCourse();
    int getTotalStudent();
    int getTotalCourse();
    TableConfig<String> totalStudentEachCourse(int page);
    Map<String,Integer> findTop5PopularCourses();
    Map<String,Integer> courseAbove10Student();
}
