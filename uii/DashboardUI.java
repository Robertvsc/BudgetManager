package uii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseConnection;

public class DashboardUI extends JFrame {
    private JButton addIncomeButton;
    private JButton addExpenseButton;
    private JButton deleteIncomeButton;
    private JButton deleteExpenseButton;
    private JButton viewReportButton;
    private int userId; // ID-ul utilizatorului logat
    private JButton resetButton;

    public DashboardUI() {
        this.userId = userId;

        setTitle("Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        addIncomeButton = new JButton("Add Income");
        addExpenseButton = new JButton("Add Expense");
        deleteIncomeButton = new JButton("Delete Income");
        deleteExpenseButton = new JButton("Delete Expense");
        viewReportButton = new JButton("View Report");
        resetButton = new JButton("Reset Tables");

        add(addIncomeButton);
        add(addExpenseButton);
        add(deleteIncomeButton);
        add(deleteExpenseButton);
        add(viewReportButton);
        add(resetButton);
        
        // Butonul pentru adăugarea veniturilor
        addIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddIncomeDialog(DashboardUI.this, userId).setVisible(true);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTables();
            }
        });

        // Butonul pentru adăugarea cheltuielilor
        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddExpenseDialog(DashboardUI.this, userId).setVisible(true);
            }
        });

        // Butonul pentru ștergerea veniturilor
        deleteIncomeButton.addActionListener(e -> 
            new DeleteIncomeDialog(this, userId).setVisible(true)
        );

        // Butonul pentru ștergerea cheltuielilor
        deleteExpenseButton.addActionListener(e -> 
            new DeleteExpenseDialog(this, userId).setVisible(true)
        );

        // Butonul pentru vizualizarea raportului
        viewReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewReportUI(userId).setVisible(true);
            }
        });
    }

    // Fereastra de adăugare venituri
    private class AddIncomeDialog extends JDialog {
        private JTextField amountField;
        private JTextField descriptionField;
        private JButton saveButton;
        private JButton cancelButton;

        public AddIncomeDialog(JFrame parent, int userId) {
            super(parent, "Add Income", true);
            setSize(300, 200);
            setLocationRelativeTo(parent);
            setLayout(new GridLayout(4, 2));

            add(new JLabel("Amount:"));
            amountField = new JTextField();
            add(amountField);

            add(new JLabel("Description:"));
            descriptionField = new JTextField();
            add(descriptionField);

            saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addIncome();
                }
            });
            add(saveButton);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            add(cancelButton);
        }

        private void addIncome() {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO income (user_id, amount, description, date) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, userId); // ID-ul utilizatorului
                stmt.setDouble(2, Double.parseDouble(amountField.getText()));
                stmt.setString(3, descriptionField.getText()); // Adăugăm descrierea
                String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                stmt.setString(4, currentDate); // Data curentă formatată
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Income added successfully");
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding income");
            }
        }


    }

    // Fereastra de adăugare cheltuieli
    private class AddExpenseDialog extends JDialog {
        private JTextField amountField;
        private JTextField descriptionField;
        private JComboBox<String> categoryComboBox;
        private JButton saveButton;
        private JButton cancelButton;

        public AddExpenseDialog(JFrame parent, int userId) {
            super(parent, "Add Expense", true);
            setSize(300, 250);
            setLocationRelativeTo(parent);
            setLayout(new GridLayout(5, 2));

            add(new JLabel("Amount:"));
            amountField = new JTextField();
            add(amountField);

            add(new JLabel("Category:"));
            categoryComboBox = new JComboBox<>(new String[]{"Food", "Rent", "Utilities", "Entertainment"});
            add(categoryComboBox);

            add(new JLabel("Description:"));
            descriptionField = new JTextField();
            add(descriptionField);

            saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addExpense(userId);
                }
            });
            add(saveButton);

            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            add(cancelButton);
        }

        private void addExpense(int userId) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO expenses (user_id, amount, category, date, description) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, userId); // Folosește ID-ul utilizatorului logat
                stmt.setDouble(2, Double.parseDouble(amountField.getText()));
                stmt.setString(3, categoryComboBox.getSelectedItem().toString());
                String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                stmt.setString(4, currentDate);
                stmt.setString(5, descriptionField.getText());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Expense added successfully");
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding expense");
            }
        }
    }
    private class DeleteIncomeDialog extends JDialog {
        public DeleteIncomeDialog(JFrame parent, int userId) {
            super(parent, "Delete Income", true);
            setSize(300, 150);
            setLocationRelativeTo(parent);
            setLayout(new GridLayout(3, 1));

            JLabel infoLabel = new JLabel("Enter Income ID to delete:");
            JTextField idField = new JTextField();
            JButton deleteButton = new JButton("Delete");

            add(infoLabel);
            add(idField);
            add(deleteButton);

            deleteButton.addActionListener(e -> {
                int incomeId = Integer.parseInt(idField.getText());
                deleteIncome(userId, incomeId);
            });
        }

        private void deleteIncome(int userId, int incomeId) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "DELETE FROM income WHERE user_id = ? AND id = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, userId);
                stmt.setInt(2, incomeId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Income deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Income ID not found!");
                }
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting income");
            }
        }
    }
    private void resetTables() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Șterge datele din tabele
            String resetIncomeQuery = "DELETE FROM income;";
            String resetExpensesQuery = "DELETE FROM expenses;";
            String resetSequenceIncome = "DELETE FROM sqlite_sequence WHERE name='income';";
            String resetSequenceExpenses = "DELETE FROM sqlite_sequence WHERE name='expenses';";

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(resetIncomeQuery);
            stmt.executeUpdate(resetExpensesQuery);
            stmt.executeUpdate(resetSequenceIncome);
            stmt.executeUpdate(resetSequenceExpenses);

            JOptionPane.showMessageDialog(this, "Tables reset successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error resetting tables");
        }
    }

    private class DeleteExpenseDialog extends JDialog {
        public DeleteExpenseDialog(JFrame parent, int userId) {
            super(parent, "Delete Expense", true);
            setSize(300, 150);
            setLocationRelativeTo(parent);
            setLayout(new GridLayout(3, 1));

            JLabel infoLabel = new JLabel("Enter Expense ID to delete:");
            JTextField idField = new JTextField();
            JButton deleteButton = new JButton("Delete");

            add(infoLabel);
            add(idField);
            add(deleteButton);

            deleteButton.addActionListener(e -> {
                int expenseId = Integer.parseInt(idField.getText());
                deleteExpense(userId, expenseId);
            });
        }

        private void deleteExpense(int userId, int expenseId) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                String query = "DELETE FROM expenses WHERE user_id = ? AND id = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, userId);
                stmt.setInt(2, expenseId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Expense deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Expense ID not found!");
                }
                dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting expense");
            }
        }
    }
}
