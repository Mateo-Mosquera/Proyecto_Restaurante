package DataAccess;

import java.sql.Connection;
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
                     + "FROM Reservas) sub  "
                     + "WHERE Cedula = " + cedula;
                     
                     try (Connection conn = openConnection();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                         
                         while (rs.next()) {
                            reservas = new ReservasDTO(
                                 rs.getInt      (1)
                                ,rs.getString   (2)
                                ,rs.getInt      (3)
                                ,rs.getDate     (4)
                                ,rs.getTime     (5)
                                ,rs.getString   (6)
                    );
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
                         rs.getInt      (1)
                        ,rs.getString   (2)
                        ,rs.getInt      (3)
                        ,rs.getDate     (4)
                        ,rs.getTime     (5)
                        ,rs.getString   (6)
            );
                lst.add(reservas);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;
    }

    @Override
    public boolean create(ReservasDTO Reservas) throws Exception {
        String query = "INSERT INTO Reservas (IDReserva, Cedula, IDMesa, Fecha, Hora, Estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt    (1, Reservas.getIdReserva());
            pstmt.setString (2, Reservas.getCedula());
            pstmt.setInt    (3, Reservas.getIdMesa());
            pstmt.setDate    (4, Reservas.getFecha());
            pstmt.setTime   (5, Reservas.getHora());
            pstmt.setString   (6, Reservas.getEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(ReservasDTO Reservas) throws Exception {
        String query = "UPDATE Reservas SET Cedula = ?, IDMesa = ?, Fecha = ?, Hora = ?, Estado = ? WHERE IDReserva = ?";

        try{
             Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             pstmt.setInt    (1, Reservas.getIdReserva());
             pstmt.setString (2, Reservas.getCedula());
             pstmt.setInt    (3, Reservas.getIdMesa());
             pstmt.setDate    (4, Reservas.getFecha());
             pstmt.setTime   (5, Reservas.getHora());
             pstmt.setString   (6, Reservas.getEstado());
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

            pstmt.setString (1, cedula);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public int getRowCount() throws Exception {
        String query = "SELECT COUNT(*) FROM Reservas";
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