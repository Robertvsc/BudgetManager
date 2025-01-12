package database;


import java.sql.Connection;
import java.sql.Statement;


public class DatabaseInitializer {
    public static void initializeDatabase() {
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS Users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL
            );
        """;

        String createExpensesTable = """
            CREATE TABLE IF NOT EXISTS Expenses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER NOT NULL,
                amount REAL NOT NULL,
                category TEXT NOT NULL,
                date DATE NOT NULL,
                FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
            );
        """;

        String createIncomeTable = """
            CREATE TABLE IF NOT EXISTS Income (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER NOT NULL,
                amount REAL NOT NULL,
                description TEXT,
                date DATE NOT NULL,
                FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
            );
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createUsersTable);
            statement.execute(createExpensesTable);
            statement.execute(createIncomeTable);
            System.out.println("Tabelele au fost create sau sunt deja existente.");
        } catch (Exception e) {
            System.err.println("Eroare la inițializarea bazei de date: " + e.getMessage());
        }
    }

	public static void main(String[] args) {
    initializeDatabase();
}
}

