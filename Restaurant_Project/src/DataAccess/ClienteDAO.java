package DataAccess;

import java.beans.Statement;
import java.lang.classfile.constantpool.IntegerEntry;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import DataAccess.DTO.ClienteDTO;


public class ClienteDAO extends SQLiteDataHelper implements IDAO<ClienteDAO> {

    @Override
    public ClienteDAO readBy(Integer id) throws Exception {
        ClienteDTO oS = new ClienteDTO();
        String query = "SELECT IdCLiente    "
                    + " ,Nombre             "
                    + " ,Apellido           "
                    + " ,Telefono           "
                    + " Correo              "
                    + " FROM    Cliente     "
                    + " Where   Estado = 'A' AND IdCliente = " + id.toString();
        try{
            Connection conn = openConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            while(rs.next()){
                ClienteDTO s = new ClienteDTO(  rs.getInt   (1)
                                               ,rs.getString(2)
                                               ,rs.getString(3)
                                               ,rs.getInt   (4)
                                               ,rs.getString(5));
                lst.add(s);
            }
        }
        catch(SQLException e){
            throw e; //new PatExeption(e.getMessage(), getClass(), getName(), "readAll()");
        }
        return oS;
    }

    @Override
    public List<ClienteDAO> readAll() throws Exception {
        List <ClienteDTO>lst = new ArrayList<>();
        String query = "SELECT IdCLiente    "
                    + " ,Nombre             "
                    + " ,Apellido           "
                    + " ,Telefono           "
                    + " Correo              "
                    + " FROM    Cliente     "
                    + " Where   Estado = 'A'";
        try{
            Connection conn = openConnection();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(query);
            while(rs.next()){
                ClienteDTO s = new ClienteDTO(  rs.getInt   (1)
                                               ,rs.getString(2)
                                               ,rs.getString(3)
                                               ,rs.getInt   (4)
                                               ,rs.getString(5));
                lst.add(s);
            }
        }
        catch(SQLException e){
            throw e; //new PatExeption(e.getMessage(), getClass(), getName(), "readAll()");
        }
        return lst;
    }
    
    @Override
    public boolean create(ClienteDAO entity) throws Exception {
        String query = "INSERT INTO CLIENTE (Nombre) VALUES (?)";
        try{
            Connection          conn = openConnection();
            PreparedStatement   pstmt = conn.prepareStatement(query);
            pstmt.setString(1,  entity.getNombre());
            pstmt.setString(2,  entity.getApellido());
            pstmt.setInt(3,     entity.getTelefono());
            pstmt.setString(4,   entity.getCorreo());
            pstmt.executeUpdate();
            return true;
        }
        catch(SQLException e){
            throw e; //new PatExeption(e.getMessage(), getClass(), getName(), "create()";
        }
        return false;
    }

    @Override
    public boolean update(ClienteDAO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE CLIENTE SET Nombre = ?, Apellido = ?, Telefono = ?, Correo = ?";
        try{
            Connection          conn = openConnection();
            PreparedStatement   pstmt = conn.prepareStatement(query);
            pstmt.setString(1,  entity.getNombre());
            pstmt.setString(2,  entity.getApellido());
            pstmt.setInt(3,     entity.getTelefono());
            pstmt.setString(4,   entity.getCorreo());
            pstmt.executeUpdate();
            return true;
        }
        catch(SQLException e){
            throw e; //new PatExeption(e.getMessage(), getClass(), getName(), "create()";
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE CLIENTE SET Estado = ? WHERE idCliente = ?";
        try{
            Connection          conn = openConnection();
            PreparedStatement   pstmt = conn.prepareStatement(query);
            pstmt.setString(1,  "X");
            pstmt.setString(2,  id);
            pstmt.executeUpdate();
            return true;
        }
        catch(SQLException e){
            throw e; //new PatExeption(e.getMessage(), getClass(), getName(), "create()";
        }
    }

    public Integer getMaxRow() throws Exception{
        String query = "SELECT COUNT(*) TotalReg FROM CLIENTE"
                     + "WHERE Estadp = 'A' ";
    try{
            Connection          conn    = openConnection();
            Statement           stmt    = conn.createStatement();
            ResultSet               rs  = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        }
        catch(SQLException e){
            throw e; //new PatExeption(e.getMessage(), getClass(), getName(), "create()";
        }
        return 0;
    }
     
}
