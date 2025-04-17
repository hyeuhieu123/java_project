package ra.edu.business.service.Course;

import ra.edu.business.dao.Course.CourseDAO;
import ra.edu.business.dao.Course.CourseDAOImp;
import ra.edu.business.model.Course;
import ra.edu.utils.TableConfig;

import java.util.List;

public class CourseServiceImp implements CourseService{
    private final CourseDAO courseDao;
    public CourseServiceImp(){
        this.courseDao=new CourseDAOImp();
    }

    @Override
    public int getTotalPage() {
        return courseDao.getTotalPage();
    }

    @Override
    public List<Course> getDataPag(int page) {
        return courseDao.getDataPag(page);
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
    public boolean delete(Course course) {
        return false;
    }

    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }
}
