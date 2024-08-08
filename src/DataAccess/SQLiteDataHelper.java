package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDataHelper {
    private static final String URL = "jdbc:sqlite:../DataBase/Restaurante.sqlite";

    protected Connection openConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found.", e);
        }
        
        return DriverManager.getConnection(URL);
    }
}
