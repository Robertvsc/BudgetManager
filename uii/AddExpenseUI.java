package uii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpenseUI extends JFrame {

    private JTextField amountField, categoryField, descriptionField;
    private String username;

    public AddExpenseUI(String username) {
        this.username = username;
        
        setTitle("Add Expense");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Category:"));
        categoryField = new JTextField();
        add(categoryField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        JButton submitButton = new JButton("Add Expense");
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryField.getText();
                String description = descriptionField.getText();
                DatabaseUtils.addExpense(username, amount, category, description); // Apel funcție pentru adăugare
                JOptionPane.showMessageDialog(AddExpenseUI.this, "Expense added successfully.");
                dispose();
            }
        });
    }
}
