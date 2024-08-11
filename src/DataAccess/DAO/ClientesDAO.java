/*---------------------------------------------------\
|  Autor: Mateo Mosquera                             |
|  DAO: Creacion de los DAO para Clientes            |
\---------------------------------------------------*/

package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.ClientesDTO;
import DataAccess.DataHelper.SQLiteDataHelper;
import DataAccess.IDAO.IGestionDAO;
import Framework.MsgException;

public class ClientesDAO extends SQLiteDataHelper implements IGestionDAO<ClientesDTO> {
    private static final String DB_URL = "jdbc:sqlite:C:\\\\Users\\\\Dario\\\\Desktop\\\\Proyecto_Restaurante_Mateo\\\\Restaurant_Project_Mat\\\\DataBase\\\\Restaurante.sqlite";

    /**
     * Método para leer los datos de un cliente en la base de datos utilizando su cédula.
     * 
     * @param cedula La cédula del cliente que se quiere buscar.
     * @return Un objeto ClientesDTO con los datos del cliente.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */
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

    /**
     * Método para leer todos los clientes almacenados en la base de datos.
     * 
     * @return Una lista de objetos ClientesDTO con los datos de todos los clientes.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */
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

    /**
     * Método para crear un nuevo cliente en la base de datos.
     * 
     * @param cliente Un objeto ClientesDTO con los datos del cliente a crear.
     * @return true si el cliente fue creado exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la inserción en la base de datos.
     */
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

    /**
     * Método para actualizar los datos de un cliente en la base de datos.
     * 
     * @param cliente Un objeto ClientesDTO con los nuevos datos del cliente.
     * @return true si el cliente fue actualizado exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la actualización en la base de datos.
     */
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

    /**
     * Método para eliminar un cliente de la base de datos utilizando su cédula.
     * 
     * @param cedula La cédula del cliente que se desea eliminar.
     * @return true si el cliente fue eliminado exitosamente, false en caso contrario.
     * @throws Exception Si ocurre un error durante la eliminación en la base de datos.
     */
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

    /**
     * Método para verificar si un cliente existe en la base de datos utilizando su cédula.
     * 
     * @param cedula La cédula del cliente que se desea verificar.
     * @return true si el cliente existe, false en caso contrario.
     */
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

    /**
     * Método para obtener el número total de registros en la tabla Clientes.
     * 
     * @return El número total de clientes en la base de datos.
     * @throws Exception Si ocurre un error durante la consulta a la base de datos.
     */
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
