package ra.edu.business.service;

import ra.edu.utils.TableConfig;

import java.util.List;

public interface AppService <T>{
    public int getTotalPage() ;
    public List<T> getDataPag(int page) ;
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);

}
