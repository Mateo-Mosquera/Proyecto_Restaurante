package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.PersonalDTO;
import Framework.MsgException;

public class PersonalDAO extends SQLiteDataHelper implements IGestionDAO<PersonalDTO> {

    @Override
    public PersonalDTO readBy(String cedula) throws Exception {
        PersonalDTO personal = new PersonalDTO();
        String query = "SELECT ID           "
                     + ",Cedula             "
                     + ",Nombre             "
                     + ",Apellido           "
                     + ",Telefono           "
                     + ",Correo             "
                     + "FROM (              "
                     + "SELECT ROW_NUMBER() OVER (ORDER BY IDPersonal) AS ID"
                     + ",Cedula             "
                     + ",Nombre             "
                     + ",Apellido           "
                     + ",Telefono           "
                     + ",Correo             "
                     + "FROM Personal) sub  "
                     + "WHERE Cedula = " + cedula;
                     
                     try (Connection conn = openConnection();
                     Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                         
                         while (rs.next()) {
                             personal = new PersonalDTO(
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
            
            return personal;
        }
        
        @Override
        public List<PersonalDTO> readAll() throws Exception {
            List<PersonalDTO> lst = new ArrayList<>();
            String query = "SELECT ID       "
                     + ",Cedula             "
                     + ",Nombre             "
                     + ",Apellido           "
                     + ",Telefono           "
                     + ",Correo             "
                     + "FROM (              "
                     + "SELECT ROW_NUMBER() OVER (ORDER BY IDPersonal) AS ID"
                     + ",Cedula             "
                     + ",Nombre             "
                     + ",Apellido           "
                     + ",Telefono           "
                     + ",Correo             "
                     + "FROM Personal       ";

        try (Connection conn = openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                PersonalDTO personal = new PersonalDTO(
                    rs.getString   (1)
                   ,rs.getString(2)
                   ,rs.getString(3)
                   ,rs.getInt   (4)
                   ,rs.getString(5)
                );
                lst.add(personal);
            }
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "readAll()");
        }

        return lst;
    }

    @Override
    public boolean create(PersonalDTO personal) throws Exception {
        String query = "INSERT INTO Personal (Cedula, Nombre, Apellido, Telefono, Correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString (1, personal.getCedula());
            pstmt.setString (2, personal.getNombre());
            pstmt.setString (3, personal.getApellido());
            pstmt.setInt    (4, personal.getTelefono());
            pstmt.setString (5, personal.getCorreo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(PersonalDTO personal) throws Exception {
        String query = "UPDATE Personal SET Nombre = ?, Apellido = ?, Telefono = ?, Correo = ? WHERE Cedula = ?";

        try{
             Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString (1, personal.getNombre());
                pstmt.setString (2, personal.getApellido());
                pstmt.setInt    (3, personal.getTelefono());
                pstmt.setString (4, personal.getCorreo());
                pstmt.setString (5, personal.getCedula());
                pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new MsgException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(String cedula) throws Exception {
        String query = "DELETE FROM Personal WHERE Cedula = ?";

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
        String query = "SELECT COUNT(*) FROM Personal";
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