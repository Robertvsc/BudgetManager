package models;

import java.util.List;

public class Budget {
    private double totalIncome; // Venitul total
    private double totalExpenses; // Cheltuielile totale

    // Constructor
    public Budget(double totalIncome, double totalExpenses) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
    }

    // Getteri și setteri
    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    // Metodă pentru calcularea economiilor
    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    // Suprascrierea metodei toString
    @Override
    public String toString() {
        return "Budget{" +
                "totalIncome=" + totalIncome +
                ", totalExpenses=" + totalExpenses +
                ", savings=" + calculateSavings() +
                '}';
    }
}
