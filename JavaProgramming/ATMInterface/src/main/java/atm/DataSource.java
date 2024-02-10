package src.main.java.atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataSource {
    public static Connection connect() {
        String dbFile = "jdbc:sqlite:resources/bank.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbFile);
        } catch (SQLException e) {
            System.out.println("Connection to Database Failed");
            e.printStackTrace();
        }
        return connection;
    }

    public static Customer getCustomer(String email) {
        String sql = "SELECT * FROM CUSTOMERS WHERE userName = ?";
        Customer customer = null;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                customer = new Customer(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("userName"),
                        resultSet.getString("password"),
                        resultSet.getInt("account_id"));
            }
        } catch (SQLException e) {
            System.out.println("Invalid User name");
            e.printStackTrace();
        }
        return customer;
    }

    public static Account getAccount(int accountId) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        Account account = null;

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                account = new Account(resultSet.getInt("id"),
                        resultSet.getString("type"),
                        resultSet.getDouble("balance"));
            }
        } catch (SQLException e) {
            System.out.println("Invalid Account");
            e.printStackTrace();
        }
        return account;
    }

    public static void updateAccountBalance(int accountId, double balance) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, balance);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Invalid Account");
            e.printStackTrace();
        }
    }

}
