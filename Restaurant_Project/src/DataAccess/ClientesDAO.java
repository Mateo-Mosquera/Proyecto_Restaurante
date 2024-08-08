package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.ClientesDTO;
import Framework.MsgException;

public class ClientesDAO extends SQLiteDataHelper implements IGestionDAO<ClientesDTO> {

    @Override
    public ClientesDTO readBy(String cedula) throws Exception {
        ClientesDTO cliente = new ClientesDTO();
        String query = "SELECT ID           "
                     + ",Cedula             "
                     + ",Nombre             "
                     + ",Apellido           "
                     + ",Telefono           "
                     + ",Correo             "
                     + "FROM (              "
                     + "SELECT ROW_NUMBER() OVER (ORDER BY IDCliente) AS ID"
                     + ",Cedula             "
                     + ",Nombre             "
                     + ",Apellido           "
                     + ",Telefono           "
                     + ",Correo             "
                     + "FROM Clientes) sub  "
                     + "WHERE Cedula = " + cedula;
                     
                     try (Connection conn = openConnection();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                         
                         while (rs.next()) {
                             cliente = new ClientesDTO(
                                 rs.getString   (1)
                                ,rs.getString(2)
                                ,rs.getString(3)
                                ,rs.getInt   (4)
                                ,rs.getString(5)
                    );
                }
            } catch (SQLException e) {
                throw new MsgException(e.getMessage(), getClass().getName(), "readBy()");
            }
            
            return cliente;
        }
        
        @Override
        public List<ClientesDTO> readAll() throws Exception {
            List<ClientesDTO> lst = new ArrayList<>();
            String query = "SELECT ID       "
                     + ",Cedula             "
                     + ",Nombre             "
                     + ",Apellido           "
                     + ",Telefono           "
                     + ",Correo             "
                     + "FROM (              "
                     + "SELECT ROW_NUMBER() OVER (ORDER BY IDCliente) AS ID"
                     + ",Cedula             "
                     + ",Nombre             "
                     + ",Apellido           "
                     + ",Telefono           "
                     + ",Correo             "
                     + "FROM Clientes)      ";

        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ClientesDTO clientes = new ClientesDTO(
                    rs.getString   (1)
                   ,rs.getString(3)
                   ,rs.getString(4)
                   ,rs.getInt   (5)
                   ,rs.getString(6)
                );
                lst.add(clientes);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;
    }

    @Override
    public boolean create(ClientesDTO clientes) throws Exception {
        String query = "INSERT INTO Clientes (Cedula, Nombre, Apellido, Telefono, Correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString (1, clientes.getCedula());
            pstmt.setString (2, clientes.getNombre());
            pstmt.setString (3, clientes.getApellido());
            pstmt.setInt    (4, clientes.getTelefono());
            pstmt.setString (5, clientes.getCorreo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(ClientesDTO clientes) throws Exception {
        String query = "UPDATE Clientes SET Nombre = ?, Apellido = ?, Telefono = ?, Correo = ? WHERE Cedula = ?";

        try{
             Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString (1, clientes.getNombre());
                pstmt.setString (2, clientes.getApellido());
                pstmt.setInt    (3, clientes.getTelefono());
                pstmt.setString (4, clientes.getCorreo());
                pstmt.setString (5, clientes.getCedula());
                pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(String cedula) throws Exception {
        String query = "DELETE FROM Clientes WHERE Cedula = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString (1, cedula);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public int getRowCount() throws Exception {
        String query = "SELECT COUNT(*) FROM Clientes";
        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }
            catch (SQLException e) {
                throw new MsgException(e.getMessage(), getClass().getName(), "getRowCount()");
            }
        return 0;
    }
}

