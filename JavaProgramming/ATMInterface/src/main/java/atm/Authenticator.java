package src.main.java.atm;

import javax.security.auth.login.LoginException;
import static src.main.java.atm.DataSource.getCustomer;


public class Authenticator {
    public static Customer logIn(String email, String password) throws LoginException {
        Customer customer = getCustomer(email);
        if (customer.equals(null)) {
            throw new LoginException("Invalid user email! Please try again.");
        } else if (password.equals(customer.getPassword())) {
            customer.setAuthenticated(true);
            return customer;
        }
        throw new LoginException("Incorrect password");
    }

    public static void logOut(Customer customer) {
        customer.setAuthenticated(false);
    }
}
