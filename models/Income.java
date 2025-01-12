package models;

import java.time.LocalDate;
import java.util.Objects;

public class Income {
    private int id; // ID-ul unic al venitului
    private int userId; // ID-ul utilizatorului care a înregistrat venitul
    private double amount; // Suma venitului
    private String description; // Descrierea venitului (ex: Salariu, Bonus)
    private LocalDate date; // Data venitului

    // Constructor
 // Constructor cu 4 parametri (inclusiv descrierea)
    public Income(int id, int userId, double amount, String description) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.description = description; // Adaugă descrierea
    }


    // Getteri și setteri
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Suprascrierea metodei toString
    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    // Suprascrierea metodelor equals și hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return id == income.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
