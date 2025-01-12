package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DatabaseConnection;

public class UserService {
    
    public boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return true; // Autentificare reușită
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Autentificare eșuată
    }

	public boolean validateCredentials(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}
