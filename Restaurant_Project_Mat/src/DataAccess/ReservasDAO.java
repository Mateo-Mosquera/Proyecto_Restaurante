package DataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.ReservasDTO;
import Framework.MsgException;

public class ReservasDAO extends SQLiteDataHelper implements IGestionDAO<ReservasDTO> {

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
                     + "FROM Reservas)      ";

        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                ReservasDTO reservas = new ReservasDTO(
                      rs.getInt(1)
                    , rs.getString(2)
                    , rs.getInt(3)
                    , rs.getDate(4)
                    , rs.getTime(5)
                    , rs.getString(6)
                    , rs.getDouble(7)
                );
                lst.add(reservas);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;
    }

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