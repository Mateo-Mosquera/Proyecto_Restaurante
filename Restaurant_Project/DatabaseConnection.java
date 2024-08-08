package Restaurant_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
private static final String URL = "jdbc:sqlite:C:/Users/Admin/OneDrive/Documentos/Proyecto_Restaurante/Restaurant Project/DataBase/Restaurante.sqlite";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }
}
