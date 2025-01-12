package services;

import models.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseConnection;

public class ExpenseService {
    private List<Expense> expenses; // Lista cheltuielilor

    // Constructor
    public ExpenseService() {
        this.expenses = new ArrayList<>();
    }

    // Adaugă o cheltuială
    public static void addExpense(String userId, double amount, String category, String date, String description) {
        String query = "INSERT INTO Expenses (user_id, amount, category, date, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            
            stmt.setString(1, userId);
            stmt.setDouble(2, amount);
            stmt.setString(3, category);
            stmt.setString(4, date);
            stmt.setString(5, description);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cheltuială adăugată cu succes!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obține cheltuielile unui utilizator
    public List<Expense> getExpensesByUserId(int userId) {
        List<Expense> userExpenses = new ArrayList<>();
        for (Expense expense : expenses) {
            if (expense.getUserId() == userId) {
                userExpenses.add(expense);
            }
        }
        return userExpenses;
    }

    // Calculează totalul cheltuielilor pentru un utilizator
    public double getTotalExpensesByUserId(int userId) {
        return getExpensesByUserId(userId)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
