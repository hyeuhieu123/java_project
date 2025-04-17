package ra.edu.business.dao;

import ra.edu.utils.TableConfig;

import java.util.List;

public interface AppDAO<T> {
    public int getTotalPage() ;
    public List<T> getDataPag(int page) ;
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);

}
