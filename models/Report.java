package models;

import java.util.List;

public class Report {
    private List<Expense> expenses; // Lista cheltuielilor
    private List<Income> incomes; // Lista veniturilor

    // Constructor
    public Report(List<Expense> expenses, List<Income> incomes) {
        this.expenses = expenses;
        this.incomes = incomes;
    }

    // Metodă pentru generarea unui raport sumar
    public String generateSummary() {
        double totalExpenses = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double totalIncome = incomes.stream().mapToDouble(Income::getAmount).sum();
        return "Summary Report:\n" +
                "Total Expenses: " + totalExpenses + "\n" +
                "Total Income: " + totalIncome + "\n" +
                "Savings: " + (totalIncome - totalExpenses);
    }
    
}
