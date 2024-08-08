package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.GerenteDTO;

public class GerenteDAO extends SQLiteDataHelper implements IDAO<GerenteDTO> {

    @Override
    public GerenteDTO readBy(Integer id) throws Exception {
        GerenteDTO gerente = null;
        String query = "SELECT IDGerente, IDRestaurante, Nombre, Correo FROM Gerente WHERE IDGerente = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    gerente = new GerenteDTO(
                        rs.getInt("IDGerente"),
                        rs.getInt("IDRestaurante"),
                        rs.getString("Nombre"),
                        rs.getString("Correo")
                    );
                }
            }
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }

        return gerente;
    }

    @Override
    public List<GerenteDTO> readAll() throws Exception {
        List<GerenteDTO> lst = new ArrayList<>();
        String query = "SELECT IDGerente, IDRestaurante, Nombre, Correo FROM Gerente";

        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                GerenteDTO gerente = new GerenteDTO(
                    rs.getInt("IDGerente"),
                    rs.getInt("IDRestaurante"),
                    rs.getString("Nombre"),
                    rs.getString("Correo")
                );
                lst.add(gerente);
            }
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }

        return lst;
    }

    @Override
    public boolean create(GerenteDTO gerente) throws Exception {
        String query = "INSERT INTO Gerente (IDRestaurante, Nombre, Correo) VALUES (?, ?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, gerente.getIDRestaurante());
            pstmt.setString(2, gerente.getNombre());
            pstmt.setString(3, gerente.getCorreo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }
    }

    @Override
    public boolean update(GerenteDTO gerente) throws Exception {
        String query = "UPDATE Gerente SET IDRestaurante = ?, Nombre = ?, Correo = ? WHERE IDGerente = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, gerente.getIDRestaurante());
            pstmt.setString(2, gerente.getNombre());
            pstmt.setString(3, gerente.getCorreo());
            pstmt.setInt(4, gerente.getIDGerente());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "DELETE FROM Gerente WHERE IDGerente = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e; // Considerar manejar la excepción de manera más específica
        }
    }
}
