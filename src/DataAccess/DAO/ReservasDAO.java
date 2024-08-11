package DataAccess.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.ReservasDTO;
import DataAccess.DataHelper.SQLiteDataHelper;
import DataAccess.IDAO.IGestionDAO;
import Framework.MsgException;

public class ReservasDAO extends SQLiteDataHelper implements IGestionDAO<ReservasDTO> {

    /**
     * Método para leer los datos de una reserva específica en la base de datos utilizando la cédula.
     * 
     * @param cedula La cédula asociada a la reserva que se quiere buscar.
     * @return Un objeto ReservasDTO con los datos de la reserva.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */
    @Override
    public ReservasDTO readBy(String cedula) throws Exception {
        ReservasDTO reservas = new ReservasDTO();
        String query = "SELECT ID       "
                        +",IDReserva    "
                        +",Cedula       "
                        +",IDMesa       "
                        +",Fecha        "
                        +",Hora         "
                        +",Estado       "
                        +"FROM (SELECT ROW_NUMBER() OVER (ORDER BY IDReserva) AS ID"
                        +",IDReserva    "
                        +",Cedula       "
                        +",IDMesa       "
                        +",Fecha        "
                        +",Hora         "
                        +",Estado       "
                        +"FROM Reservas) sub "
                        +"WHERE Cedula = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, cedula);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    reservas = new ReservasDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getTime(5),
                        rs.getString(6),
                        rs.getDouble(7)
                    );
                }
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return reservas;
    }

    /**
     * Método para leer todos los registros de reservas almacenados en la base de datos.
     * 
     * @return Una lista de objetos ReservasDTO con los datos de todas las reservas.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */
    @Override
    public List<ReservasDTO> readAll() throws Exception {
        List<ReservasDTO> lst = new ArrayList<>();
        String query = "SELECT ID           "
                     + ",IDReserva          "
                     + ",Cedula             "
                     + ",IDMesa             "
                     + ",Fecha              "
                     + ",Hora               "
                     + ",Estado             "
                     + "FROM (              "
                     + "SELECT ROW_NUMBER() OVER (ORDER BY IDReserva) AS ID"
                     + ",IDReserva          "
                     + ",Cedula             "
                     + ",IDMesa             "
                     + ",Fecha              "
                     + ",Hora               "
                     + ",Estado             "
                     + "FROM Reservas)";

        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ReservasDTO reservas = new ReservasDTO(
                      rs.getInt(1),
                      rs.getString(2),
                      rs.getInt(3),
                      rs.getDate(4),
                      rs.getTime(5),
                      rs.getString(6),
                      rs.getDouble(7)
                );
                lst.add(reservas);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;
    }

    /**
     * Método para crear un nuevo registro de reserva en la base de datos.
     * 
     * @param reservas Un objeto ReservasDTO con los datos de la reserva a crear.
     * @return true si la reserva fue creada exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la inserción en la base de datos.
     */
    @Override
    public boolean create(ReservasDTO reservas) throws Exception {
        String query = "INSERT INTO Reservas (IDReserva, Cedula, IDMesa, Fecha, Hora, Estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, reservas.getIdReserva());
            pstmt.setString(2, reservas.getCedula());
            pstmt.setInt(3, reservas.getIdMesa());
            pstmt.setDate(4, reservas.getFecha());
            pstmt.setTime(5, reservas.getHora());
            pstmt.setString(6, reservas.getEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    /**
     * Método para actualizar los datos de una reserva en la base de datos.
     * 
     * @param reservas Un objeto ReservasDTO con los nuevos datos de la reserva.
     * @return true si la reserva fue actualizada exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la actualización en la base de datos.
     */
    @Override
    public boolean update(ReservasDTO reservas) throws Exception {
        String query = "UPDATE Reservas SET Cedula = ?, IDMesa = ?, Fecha = ?, Hora = ?, Estado = ? WHERE IDReserva = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, reservas.getCedula());
            pstmt.setInt(2, reservas.getIdMesa());
            pstmt.setDate(3, reservas.getFecha());
            pstmt.setTime(4, reservas.getHora());
            pstmt.setString(5, reservas.getEstado());
            pstmt.setInt(6, reservas.getIdReserva());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    /**
     * Método para eliminar una reserva de la base de datos utilizando la cédula.
     * 
     * @param cedula La cédula asociada a la reserva que se desea eliminar.
     * @return true si la reserva fue eliminada exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la eliminación en la base de datos.
     */
    @Override
    public boolean delete(String cedula) throws Exception {
        String query = "DELETE FROM Reservas WHERE Cedula = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, cedula);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    /**
     * Método para verificar si una reserva existe en la base de datos utilizando su ID.
     * 
     * @param idReserva El ID de la reserva a verificar.
     * @return true si la reserva existe, false en caso contrario.
     */
    public boolean exists(Integer idReserva) {
        String query = "SELECT COUNT(*) FROM Reservas WHERE IDReserva = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idReserva);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar la existencia de la reserva: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para leer los datos de una reserva específica en la base de datos utilizando su ID.
     * 
     * @param idReserva El ID de la reserva que se quiere buscar.
     * @return Un objeto ReservasDTO con los datos de la reserva.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */
    public ReservasDTO readBy(Integer idReserva) throws Exception {
        ReservasDTO reserva = null;
        String query = "SELECT IDReserva, Cedula, IDMesa, Fecha, Hora, Estado FROM Reservas WHERE IDReserva = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idReserva);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    reserva = new ReservasDTO(
                        rs.getInt("IDReserva"),
                        rs.getString("Cedula"),
                        rs.getInt("IDMesa"),
                        rs.getDate("Fecha"),
                        rs.getTime("Hora"),
                        rs.getString("Estado"),
                        rs.getDouble("Monto")
                    );
                }
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readBy(Integer)");
        }
        return reserva;
    }
}
