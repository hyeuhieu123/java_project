package ra.edu.business.service.Course;
import ra.edu.business.dao.Course.CourseDAO;
import ra.edu.business.dao.Course.CourseDAOImp;
import ra.edu.business.model.Course;
import ra.edu.utils.TableConfig;

import java.util.List;

public class CourseServiceImp implements CourseService{
    private final CourseDAO courseDao;
    private final int pageSize=5;
    public CourseServiceImp(){
        this.courseDao=new CourseDAOImp();
    }

    @Override
    public int getTotalPage() {

        return (int) (Math.ceil((double) courseDao.getTotalPage() / pageSize));
    }

    @Override
    public  TableConfig<Course> getDataPag(int page) {
        List<Course> courses = courseDao.getDataPag(page,pageSize);
        return new TableConfig<>(courses, getTotalPage());
    }


    @Override
    public boolean save(Course course) {

        return courseDao.save(course);
    }

    @Override
    public boolean update(Course course) {
        return courseDao.update(course);
    }

    @Override
    public int delete(Course course) {
        return courseDao.delete(course);

    }

    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public int getTotalPageBySearch(String name) {
        int totalItems = courseDao.getTotalPageBySearch(name);
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    @Override
    public TableConfig<Course> findCourseByName(int page, String name) {
        List<Course> courses = courseDao.findCourseByName(page, pageSize, name);
        int totalPages = getTotalPageBySearch(name);
        return new TableConfig<>(courses, totalPages);
    }

    @Override
    public TableConfig<Course> sortByNameASC(int page) {
        List<Course> courses = courseDao.sortByNameASC(page, pageSize);
        int totalPages = getTotalPage();
        return new TableConfig<>(courses, totalPages);
    }

    @Override
    public TableConfig<Course> sortByNameDESC(int page) {
        List<Course> courses = courseDao.sortByNameDESC(page, pageSize);
        int totalPages = getTotalPage();
        return new TableConfig<>(courses, totalPages);
    }

}
