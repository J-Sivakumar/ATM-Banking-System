import java.sql.*;

public class BankService {
    private Connection connection;

    public BankService() {
        try {
            // Modern JDBC automatically loads driver
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/atm_db?useSSL=false&serverTimezone=UTC",
                    "root",       // your MySQL username
                    "adminroot"   // your MySQL password
            );
            if (connection != null) {
                System.out.println("Database connected successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    public BankAccount login(String accountNumber, String pin) {
        if (connection == null) return null;
        try {
            String query = "SELECT * FROM accounts WHERE account_number=? AND pin=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, accountNumber);
            ps.setString(2, pin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new BankAccount(
                        rs.getString("account_number"),
                        rs.getString("account_holder"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBalance(BankAccount account) {
        if (connection == null) return;
        try {
            String query = "UPDATE accounts SET balance=? WHERE account_number=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1, account.getBalance());
            ps.setString(2, account.getAccountNumber());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(BankAccount account, String type, double amount) {
        if (connection == null) return;
        try {
            String query = "INSERT INTO transactions (account_number, type, amount) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account.getAccountNumber());
            ps.setString(2, type);
            ps.setDouble(3, amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showTransactions(BankAccount account) {
        if (connection == null) return;
        try {
            String query = "SELECT * FROM transactions WHERE account_number=? ORDER BY transaction_time DESC";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account.getAccountNumber());
            ResultSet rs = ps.executeQuery();
            System.out.println("Transaction History for account " + account.getAccountNumber() + ":");
            while (rs.next()) {
                System.out.println(rs.getTimestamp("transaction_time") + " - " +
                        rs.getString("type") + ": " + rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
