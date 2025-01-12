package database;

import models.Expense;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDatabase {

    private Connection connection;

    public ExpenseDatabase(Connection connection) {
        this.connection = connection;
    }

    public boolean addExpense(Expense expense) {
        String query = "INSERT INTO Expenses (user_id, amount, category, date, description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, expense.getUserId());
            statement.setDouble(2, expense.getAmount());
            statement.setString(3, expense.getCategory());
            statement.setDate(4, Date.valueOf(expense.getDate()));  // Convertește LocalDate în Date
            statement.setString(5, expense.getDescription());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Expense> getExpensesByUserId(int userId) throws SQLException {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Citirea câmpurilor din ResultSet și conversia corectă
                int id = rs.getInt("id");
                int userIdFromDb = rs.getInt("user_id");
                double amount = rs.getDouble("amount");
                String category = rs.getString("category");
                LocalDate date = rs.getDate("date").toLocalDate(); // Conversia corectă a datei
                String description = rs.getString("description");

                // Crearea unui obiect Expense și adăugarea la listă
                expenses.add(new Expense(id, userIdFromDb, amount, category, date, description));
            }
        }
        return expenses;
    }


    public boolean updateExpense(Expense expense) {
        String query = "UPDATE Expenses SET amount = ?, category = ?, date = ?, description = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, expense.getAmount());
            statement.setString(2, expense.getCategory());
            statement.setDate(3, Date.valueOf(expense.getDate()));  // Convertește LocalDate în Date
            statement.setString(4, expense.getDescription());
            statement.setInt(5, expense.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteExpense(int id) {
        String query = "DELETE FROM Expenses WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean saveExpense(Expense expense) {
        String sql = "INSERT INTO Expenses (user_id, amount, category, date, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, expense.getUserId());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setString(3, expense.getCategory());
            pstmt.setDate(4, Date.valueOf(expense.getDate()));
            pstmt.setString(5, expense.getDescription());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
