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
            System.out.println("Connected to Database");
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
            System.out.println("Error on getCustomer");
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
            System.out.println("Error in getAccount");
            e.printStackTrace();
        }
        return account;
    }

    public static void updateAccount(int accountId, double balance) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, balance);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in UpdateAccount");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // Testing getCustomer
        Customer customer = getCustomer("dwhittingtoncz@mozilla.org");
        if (customer.getId() ==  1118) {
            System.out.println("Got Correct customer");
        } else {
            System.out.println("Error...Got Wrong customer");
        }

        // Testing getAccount
        Account account = getAccount(43267);
        if (customer.getAccountNo() == account.getId()) {
            System.out.println("Got Correct Account");
        } else {
            System.out.println("Error...Got Wrong Account");
        }

        // Testing updateAccount
        Account account1 = getAccount(11963);
        double balance = account1.getBalance();
        updateAccount(11963, balance - 1500);
        if (getAccount(11963).getBalance() == balance - 1500) {
            System.out.println("Successfully Updated Account");
            updateAccount(11963, balance);
        } else {
            System.out.println("Error...Updating Account");
        }


    }
}
