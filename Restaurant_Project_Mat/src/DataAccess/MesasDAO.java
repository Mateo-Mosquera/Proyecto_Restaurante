package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.MesasDTO;
import Framework.MsgException;

public class MesasDAO extends SQLiteDataHelper implements IAsignacionDAO<MesasDTO> {

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
                                 rs.getInt   (1)
                                ,rs.getString(2)
                    );
                }
            } catch (SQLException e) {
                throw new MsgException(e.getMessage(), getClass().getName(), "readBy()");
            }
            
            return mesa;
        }
        
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
                        rs.getInt   (1)
                       ,rs.getString(2)
           );
                lst.add(mesa);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;
    }

    @Override
    public boolean create(MesasDTO Mesas) throws Exception {
        String query = "INSERT INTO Mesas (Capacidad, Estado) VALUES (?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt    (1, Mesas.getCapacidad());
            pstmt.setString (2, Mesas.getEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(MesasDTO Mesas) throws Exception {
        String query = "UPDATE Mesas SET Capacidad = ?, Estado = ? WHERE IDMesa = ?";

        try{
             Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt (1, Mesas.getCapacidad());
                pstmt.setString (2, Mesas.getEstado());
                pstmt.setInt    (3, Mesas.getIdMesa());
                pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "update()");
        }
    }

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