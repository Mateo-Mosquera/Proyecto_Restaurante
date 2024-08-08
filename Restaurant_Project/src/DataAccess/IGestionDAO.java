package DataAccess;

import java.util.List;

public interface IGestionDAO<T> {
    public boolean  create(T entity)        throws Exception;
    public List<T>  readAll()               throws Exception;
    public boolean  update(T entity)        throws Exception;
    public boolean  delete(String cedula)      throws Exception;
    
    public T        readBy(String cedula)      throws Exception;
    //public Interger getMaxRow() throws Exception;


}
