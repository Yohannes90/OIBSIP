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
        try{
            connection = DriverManager.getConnection(dbFile);
            System.out.println("Connected to Database");
        } catch(SQLException e) {
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
        } catch(SQLException e) {
            System.out.println("Error on getCustomer");
            e.printStackTrace();
        }
        return customer;
    }


    public static void main(String[] args) {
        connect();
        Customer customer = getCustomer("dwhittingtoncz@mozilla.org");
        if (customer.getId() ==  1118) {
            System.out.println("Got Correct customer");
        } else {
            System.out.println("Error...Got Wrong customer");

        }

    }
}
