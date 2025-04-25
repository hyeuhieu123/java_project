package ra.edu.business.service;

import ra.edu.business.model.Course;
import ra.edu.utils.TableConfig;

import java.util.List;

public interface AppService <T>{
    public int getTotalPage() ;
    public TableConfig<T> getDataPag(int page) ;
    boolean save(T t);
    boolean update(T t);
    int delete(T t);

}
