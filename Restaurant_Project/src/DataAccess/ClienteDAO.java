package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.ClienteDTO;

public class ClienteDAO extends SQLiteDataHelper implements IDAO<ClienteDTO> {

    @Override
    public ClienteDTO readBy(Integer id) throws Exception {
        ClienteDTO cliente = null;
        String query = "SELECT IDCliente, Nombre, Apellido, Telefono, Correo FROM Cliente WHERE IDCliente = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteDTO(
                        rs.getInt("IDCliente"),
                        rs.getString("Nombre"),
                        rs.getString("Apellido"),
                        rs.getInt("Telefono"),
                        rs.getString("Correo")
                    );
                }
            }
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }

        return cliente;
    }

    @Override
    public List<ClienteDTO> readAll() throws Exception {
        List<ClienteDTO> lst = new ArrayList<>();
        String query = "SELECT IDCliente, Nombre, Apellido, Telefono, Correo FROM Cliente";

        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO(
                    rs.getInt("IDCliente"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getInt("Telefono"),
                    rs.getString("Correo")
                );
                lst.add(cliente);
            }
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }

        return lst;
    }

    @Override
    public boolean create(ClienteDTO cliente) throws Exception {
        String query = "INSERT INTO Cliente (Nombre, Apellido, Telefono, Correo) VALUES (?, ?, ?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setInt(3, cliente.getTelefono());
            pstmt.setString(4, cliente.getCorreo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }
    }

    @Override
    public boolean update(ClienteDTO cliente) throws Exception {
        String query = "UPDATE Cliente SET Nombre = ?, Apellido = ?, Telefono = ?, Correo = ? WHERE IDCliente = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, cliente.getNombre());
                pstmt.setString(2, cliente.getApellido());
                pstmt.setInt(3, cliente.getTelefono());
                pstmt.setString(4, cliente.getCorreo());
                pstmt.setInt(5, cliente.getIdCliente());
                pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "DELETE FROM Cliente WHERE IDCliente = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }
    }

    public int getRowCount() throws Exception {
        String query = "SELECT COUNT(*) FROM Cliente";
        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }
        return 0;
    }
}
