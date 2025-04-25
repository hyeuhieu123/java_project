package ra.edu.business.dao;

import ra.edu.utils.TableConfig;

import java.util.List;

public interface AppDAO<T> {
    public int getTotalPage() ;
    public List<T> getDataPag(int page,int pageSize ) ;
    boolean save(T t);
    boolean update(T t);
    int delete(T t);

}
