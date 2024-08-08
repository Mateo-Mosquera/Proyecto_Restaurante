package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                     + "WHERE Cedula = ?";
                     
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, cedula);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClientesDTO(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                    );
                }
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
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ClientesDTO cliente = new ClientesDTO(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getString(5)
                );
                lst.add(cliente);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;
    }

    @Override
    public boolean create(ClientesDTO cliente) throws Exception {
        String query = "INSERT INTO Clientes (Cedula, Nombre, Apellido, Telefono, Correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, cliente.getCedula());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getApellido());
            pstmt.setInt(4, cliente.getTelefono());
            pstmt.setString(5, cliente.getCorreo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(ClientesDTO cliente) throws Exception {
        String query = "UPDATE Clientes SET Nombre = ?, Apellido = ?, Telefono = ?, Correo = ? WHERE Cedula = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setInt(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getCorreo());
            pstmt.setString(5, cliente.getCedula());
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
            pstmt.setString(1, cedula);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public boolean exists(String cedula) {
        String query = "SELECT COUNT(*) FROM Clientes WHERE Cedula = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, cedula);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia del cliente: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return false;
        }
    }

    public int getRowCount() throws Exception {
        String query = "SELECT COUNT(*) FROM Clientes";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "getRowCount()");
        }
        return 0;
    }
}
