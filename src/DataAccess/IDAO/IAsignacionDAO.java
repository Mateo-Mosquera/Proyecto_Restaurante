package DataAccess.IDAO;

import java.util.List;

public interface IAsignacionDAO<T> {
    public boolean  create(T entity)        throws Exception;
    public List<T>  readAll()               throws Exception;
    public boolean  update(T entity)        throws Exception;
    public boolean  delete(Integer id)      throws Exception;
    
    public T        readBy(Integer id)      throws Exception;
    //public Interger getMaxRow() throws Exception;


}
