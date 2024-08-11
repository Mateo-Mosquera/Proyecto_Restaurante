package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.MesasDTO;
import DataAccess.DataHelper.SQLiteDataHelper;
import DataAccess.IDAO.IAsignacionDAO;
import Framework.MsgException;

public class MesasDAO extends SQLiteDataHelper implements IAsignacionDAO<MesasDTO> {

    /**
     * Método para leer los datos de una mesa específica en la base de datos utilizando su ID.
     * 
     * @param id El ID de la mesa que se quiere buscar.
     * @return Un objeto MesasDTO con los datos de la mesa.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */
    @Override
    public MesasDTO readBy(Integer id) throws Exception {
        MesasDTO mesa = new MesasDTO();
        String query = "SELECT ID           "
                     + ",Capacidad          "
                     + ",Estado             "
                     + "FROM (              "
                     + "SELECT ROW_NUMBER() OVER (ORDER BY IDMesa) AS ID"
                     + ",Capacidad          "
                     + ",Estado             "
                     + "FROM Mesas) sub  "
                     + "WHERE IDMesa = " + id.toString();
                     
        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
                         
            while (rs.next()) {
                mesa = new MesasDTO(
                    rs.getInt   (1),
                    rs.getString(2)
                );
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readBy()");
        }
            
        return mesa;
    }
        
    /**
     * Método para leer todos los registros de mesas almacenados en la base de datos.
     * 
     * @return Una lista de objetos MesasDTO con los datos de todas las mesas.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */
    @Override
    public List<MesasDTO> readAll() throws Exception {
        List<MesasDTO> lst = new ArrayList<>();
        String query = "SELECT ID           "
                     + ",Capacidad          "
                     + ",Estado             "
                     + "FROM (              "
                     + "SELECT ROW_NUMBER() OVER (ORDER BY IDMesa) AS ID"
                     + ",Capacidad          "
                     + ",Estado             "
                     + "FROM Mesas)";

        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                MesasDTO mesa = new MesasDTO(
                    rs.getInt   (1),
                    rs.getString(2)
                );
                lst.add(mesa);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;
    }

    /**
     * Método para crear un nuevo registro de mesa en la base de datos.
     * 
     * @param mesa Un objeto MesasDTO con los datos de la mesa a crear.
     * @return true si la mesa fue creada exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la inserción en la base de datos.
     */
    @Override
    public boolean create(MesasDTO mesa) throws Exception {
        String query = "INSERT INTO Mesas (Capacidad, Estado) VALUES (?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt    (1, mesa.getCapacidad());
            pstmt.setString (2, mesa.getEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    /**
     * Método para actualizar los datos de una mesa en la base de datos.
     * 
     * @param mesa Un objeto MesasDTO con los nuevos datos de la mesa.
     * @return true si la mesa fue actualizada exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la actualización en la base de datos.
     */
    @Override
    public boolean update(MesasDTO mesa) throws Exception {
        String query = "UPDATE Mesas SET Capacidad = ?, Estado = ? WHERE IDMesa = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt (1, mesa.getCapacidad());
            pstmt.setString (2, mesa.getEstado());
            pstmt.setInt    (3, mesa.getIdMesa());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    /**
     * Método para eliminar una mesa de la base de datos utilizando su ID.
     * 
     * @param id El ID de la mesa que se desea eliminar.
     * @return true si la mesa fue eliminada exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la eliminación en la base de datos.
     */
    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "DELETE FROM Mesas WHERE IDMesa = ?";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt (1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    /**
     * Método para obtener el número total de registros en la tabla Mesas.
     * 
     * @return El número total de mesas en la base de datos.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */
    public int getRowCount() throws Exception {
        String query = "SELECT COUNT(*) FROM Mesas";
        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "getRowCount()");
        }
        return 0;
    }
}
