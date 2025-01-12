package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:C:\\Users\\User\\Desktop\\Proiect.db";  // Modifică calea în funcție de locația fișierului tău

    public static Connection getConnection() throws SQLException {
        try {
            // Încarcă driverul SQLite
            Class.forName("org.sqlite.JDBC");
            // Returnează conexiunea la baza de date SQLite
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found", e);
        }
    }
}
