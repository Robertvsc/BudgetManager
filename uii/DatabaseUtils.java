package uii;

import java.sql.*;

import database.DatabaseConnection;

public class DatabaseUtils {

    public static boolean authenticate(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addExpense(String username, double amount, String category, String description) {
        String query = "INSERT INTO Expenses (user_id, amount, category, description) VALUES ((SELECT id FROM Users WHERE username = ?), ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setDouble(2, amount);
            stmt.setString(3, category);
            stmt.setString(4, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addIncome(String username, double amount, String description) {
        String query = "INSERT INTO Income (user_id, amount, description) VALUES ((SELECT id FROM Users WHERE username = ?), ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setDouble(2, amount);
            stmt.setString(3, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteIncome(int userId, int incomeId) {
        String query = "DELETE FROM income WHERE id = ? AND user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, incomeId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteExpense(int userId, int expenseId) {
        String query = "DELETE FROM expenses WHERE id = ? AND user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, expenseId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getReport(String username) {
        StringBuilder report = new StringBuilder();
        String expenseQuery = "SELECT * FROM Expenses WHERE user_id = (SELECT id FROM Users WHERE username = ?)";
        String incomeQuery = "SELECT * FROM Income WHERE user_id = (SELECT id FROM Users WHERE username = ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement expenseStmt = connection.prepareStatement(expenseQuery);
             PreparedStatement incomeStmt = connection.prepareStatement(incomeQuery)) {
            
            expenseStmt.setString(1, username);
            incomeStmt.setString(1, username);

            ResultSet expenseResult = expenseStmt.executeQuery();
            ResultSet incomeResult = incomeStmt.executeQuery();

            report.append("Expenses:\n");
            while (expenseResult.next()) {
                report.append("Amount: ").append(expenseResult.getDouble("amount"))
                        .append(", Category: ").append(expenseResult.getString("category"))
                        .append(", Description: ").append(expenseResult.getString("description"))
                        .append("\n");
            }

            report.append("\nIncome:\n");
            while (incomeResult.next()) {
                report.append("Amount: ").append(incomeResult.getDouble("amount"))
                        .append(", Description: ").append(incomeResult.getString("description"))
                        .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report.toString();
    }
}
