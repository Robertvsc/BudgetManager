package database;
import java.sql.*;

public class DatabaseTest {
    public static void main(String[] args) {
        // URL-ul bazei de date SQLite
        String dbUrl = "jdbc:sqlite:C:\\Users\\User\\Desktop\\Proiect.db";  // înlocuiește cu calea corectă

        // Conectarea la baza de date
        try (Connection conn = DriverManager.getConnection(dbUrl)) {
            if (conn != null) {
                System.out.println("Conectare reușită la baza de date.");

                // Executarea unei interogări simple
                String query = "SELECT * FROM Users";  // Înlocuiește cu numele tabelului tău
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    // Procesarea rezultatelor
                    while (rs.next()) {
                        String username = rs.getString("username");  // Înlocuiește cu numele coloanei tale
                        String password = rs.getString("password");  // Înlocuiește cu numele coloanei tale
                        System.out.println("Username: " + username + ", Password: " + password);
                    }
                } catch (SQLException e) {
                    System.out.println("Eroare la executarea interogării: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Conectare la baza de date eșuată: " + e.getMessage());
        }
    }
    
    
}
