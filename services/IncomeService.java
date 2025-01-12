package services;

import models.Income;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeService {
    private List<Income> incomes; // Lista veniturilor

    // Constructor
    public IncomeService() {
        this.incomes = new ArrayList<>();
    }

    // Adaugă un venit
    public void addIncome(int userId, double amount, String description, LocalDate date) {
        Income newIncome = new Income(incomes.size() + 1, userId, amount, description);
        incomes.add(newIncome);
    }

    // Obține veniturile unui utilizator
    public List<Income> getIncomesByUserId(int userId) {
        List<Income> userIncomes = new ArrayList<>();
        for (Income income : incomes) {
            if (income.getUserId() == userId) {
                userIncomes.add(income);
            }
        }
        return userIncomes;
    }

    // Calculează totalul veniturilor pentru un utilizator
    public double getTotalIncomeByUserId(int userId) {
        return getIncomesByUserId(userId)
                .stream()
                .mapToDouble(Income::getAmount)
                .sum();
    }
}
