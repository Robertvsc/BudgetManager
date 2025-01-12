package services;

public class BudgetService {
    private ExpenseService expenseService;
    private IncomeService incomeService;

    // Constructor
    public BudgetService(ExpenseService expenseService, IncomeService incomeService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    // Calculează economiile pentru un utilizator
    public double calculateSavings(int userId) {
        double totalIncome = incomeService.getTotalIncomeByUserId(userId);
        double totalExpenses = expenseService.getTotalExpensesByUserId(userId);
        return totalIncome - totalExpenses;
    }
}
