package ra.edu.business.service.Statistict;

import ra.edu.business.dao.statistict.StatistictDAO;
import ra.edu.business.dao.statistict.StatististDAOImp;
import ra.edu.business.model.Enrollment;
import ra.edu.utils.TableConfig;

import java.util.List;
import java.util.Map;

public class StatistictServiceImp implements StatistictSERVICE{
    final int pageSize = 5;
    private StatistictDAO statistictDAO;
    public StatistictServiceImp(){
        statistictDAO = new StatististDAOImp();
    }

    @Override
    public int totalEachCourse() {
        return  (int) Math.ceil((double) statistictDAO.totalEachCourse() / pageSize);
    }

    @Override
    public int getTotalStudent() {
        return statistictDAO.getTotalStudent();
    }

    @Override
    public int getTotalCourse() {
        return statistictDAO.getTotalCourse();
    }

    @Override
    public TableConfig<String> totalStudentEachCourse(int page) {
        Map<String,Integer> enrollments = statistictDAO.totalStudentEachCourse(page, pageSize);
        return new TableConfig<>(enrollments, totalEachCourse());
    }

    @Override
    public Map<String,Integer> findTop5PopularCourses() {

        return statistictDAO.findTop5PopularCourses();
    }

    @Override
    public Map<String,Integer> courseAbove10Student() {
        return statistictDAO.courseAbove10Student();
    }
}
