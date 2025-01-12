package database;

import models.Income;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeDatabase {

    private Connection connection;

    public IncomeDatabase(Connection connection) {
        this.connection = connection;
    }

    public boolean addIncome(Income income) {
        String query = "INSERT INTO Income (user_id, amount, description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, income.getUserId());
            statement.setDouble(2, income.getAmount());
            statement.setString(3, income.getDescription());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Income> getIncomeByUserId(int userId) {
        List<Income> incomes = new ArrayList<>();
        String query = "SELECT * FROM Income WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                incomes.add(new Income(rs.getInt("id"), rs.getInt("user_id"), rs.getDouble("amount"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomes;
    }

    public boolean updateIncome(Income income) {
        String query = "UPDATE Income SET amount = ?, description = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, income.getAmount());
            statement.setString(2, income.getDescription());
            statement.setInt(3, income.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteIncome(int id) {
        String query = "DELETE FROM Income WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
