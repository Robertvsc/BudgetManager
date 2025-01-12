package uii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddIncomeUI extends JFrame {

    private JTextField amountField, descriptionField;
    private String username;

    public AddIncomeUI(String username) {
        this.username = username;
        
        setTitle("Add Income");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        JButton submitButton = new JButton("Add Income");
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                String description = descriptionField.getText();
                DatabaseUtils.addIncome(username, amount, description); // Apel funcție pentru adăugare
                JOptionPane.showMessageDialog(AddIncomeUI.this, "Income added successfully.");
                dispose();
            }
        });
    }
}
