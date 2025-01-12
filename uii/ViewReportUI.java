package uii;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import database.DatabaseConnection;

public class ViewReportUI extends JFrame {

    private int userId;

    public ViewReportUI(int userId) {
        this.userId = userId;
        System.out.println("Constructor ViewReportUI apelat!"); // Mesaj de debug

        setTitle("View Report");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);

        String report = generateReport();  // Apelul la generare raport
        System.out.println("Raport generat: " + report);  // Mesaj de debug
        reportArea.setText(report);

        add(new JScrollPane(reportArea), BorderLayout.CENTER);
    }

    private String generateReport() {
        StringBuilder report = new StringBuilder();
        double totalIncome = 0, totalExpense = 0;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String incomeQuery = "SELECT amount, description FROM income WHERE user_id = ?";
            PreparedStatement incomeStmt = connection.prepareStatement(incomeQuery);
            incomeStmt.setInt(1, userId);
            ResultSet incomeRs = incomeStmt.executeQuery();

            report.append("Incomes:\n");
            while (incomeRs.next()) {
                double amount = incomeRs.getDouble("amount");
                totalIncome += amount;
                report.append("Amount: ").append(amount).append(", Description: ")
                      .append(incomeRs.getString("description")).append("\n");
            }

            String expenseQuery = "SELECT amount, description FROM expenses WHERE user_id = ?";
            PreparedStatement expenseStmt = connection.prepareStatement(expenseQuery);
            expenseStmt.setInt(1, userId);
            ResultSet expenseRs = expenseStmt.executeQuery();

            report.append("\nExpenses:\n");
            while (expenseRs.next()) {
                double amount = expenseRs.getDouble("amount");
                totalExpense += amount;
                report.append("Amount: ").append(amount).append(", Description: ")
                      .append(expenseRs.getString("description")).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        report.append("\nTotal Income: ").append(totalIncome);
        report.append("\nTotal Expenses: ").append(totalExpense);
        report.append("\nNet Balance: ").append(totalIncome - totalExpense);

        return report.toString();
    }


}
