package models;

import java.time.LocalDate;
import java.util.Objects;

public class Expense {
	 private int id;
	    private int userId;
	    private double amount;
	    private String category;
	    private LocalDate date;
	    private String description;

	    // Constructor complet
	 // Constructor cu 5 parametri (fără descriere)
	 // Constructor cu 6 parametri (inclusiv descrierea)
	    public Expense(int id, int userId, double amount, String category, LocalDate date, String description) {
	        this.id = id;
	        this.userId = userId;
	        this.amount = amount;
	        this.category = category;
	        this.date = date;
	        this.description = description; // Adaugă descrierea
	    }


	    // Getters și Setters pentru fiecare câmp
	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public void setCategory(String category) {
	        this.category = category;
	    }

	    public LocalDate getDate() {
	        return date;
	    }

	    public void setDate(LocalDate date) {
	        this.date = date;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

    // Suprascrierea metodei toString
    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", date=" + date +
                '}';
    }

    // Suprascrierea metodelor equals și hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return id == expense.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
