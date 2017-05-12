package restful.service;


import java.io.Serializable;
import java.util.List;

public interface CRUDService<T> {

    T save(T entity);

    T getById(Serializable id);

    List<T> getAll();

    void delete(Serializable id);

}
