package src.main.java.atm;

import java.util.Scanner;
import javax.security.auth.login.LoginException;
import src.main.java.atm.exceptions.AmountException;

import static src.main.java.atm.DataSource.getCustomer;


public class Menu {
    private Scanner scanner;

    private Customer authenticateUser() {
        System.out.print("Please enter user name(email): ");
        String userName = scanner.next();
        System.out.print("Please enter password: ");
        String password = scanner.next();
        Customer customer = null;
        try {
            customer = Authenticator.logIn(userName, password);
        } catch (LoginException e) {
            System.out.println("There was an error " + e.getMessage());
        }
        return customer;
    }

    private void showMenu(Customer customer, Account account) {
        int selection = 0;

        while (selection != 5 && customer.isAuthenticated()) {
            System.out.println("---------------------------------------");
            System.out.println("\t1: Check balance\n\t2: Deposit\n\t3: Withdraw\n\t4: Transfer to other customer\n\t5: Quit");
            System.out.println("---------------------------------------");
            System.out.print("Please select what do you like to do from the menu: ");

            selection = scanner.nextInt();
            double amount = 0;

            switch (selection) {
                case 1:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.println("How much would you like to deposit: ");
                    amount = scanner.nextDouble();
                    try {
                        account.deposit(amount);
                    } catch (AmountException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please try again.");
                    }
                    break;
                case 3:
                    System.out.print("How much would you like to withdraw: ");
                    amount = scanner.nextDouble();
                    try {
                        account.withdraw(amount);
                    } catch (AmountException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please try again.");
                    }
                    break;
                case 4:
                    System.out.print("Please enter the account email you want to transfer to: ");
                    String recipientEmail = scanner.next();
                    Customer recipientCustomer = getCustomer(recipientEmail);
                    if (recipientCustomer.equals(null)) {
                        System.out.println("The account number you entered is Invalid!");
                        break;
                    }
                    System.out.print("Please enter amount of transfer: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Are you sure you want to transfer " + transferAmount + " to " + recipientEmail +" Y/N: ");
                    String confirmation = scanner.next();
                    if (confirmation.equals("y") || confirmation.equals("Y")) {
                        try {
                            account.transferToAccount(recipientCustomer.getAccountNo(), transferAmount);
                            System.out.println("You have successfully transferred " + transferAmount + " to " + recipientEmail +
                                    ". Your current balance is " + account.getBalance());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            System.out.println("Please try again.");
                        }
                    }
                    break;
                case 5:
                    Authenticator.logOut(customer);
                    System.out.println("Thanks for banking with Awash International bank!");
                    break;
                default:
                    System.out.println("Invalid option. please try again");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Awash International Bank ATM Machine!");
        Menu menu = new Menu();
        menu.scanner = new Scanner(System.in);
        Customer customer = menu.authenticateUser();
        if (customer != null) {
            Account account = DataSource.getAccount(customer.getAccountNo());
            menu.showMenu(customer, account);
        }
        menu.scanner.close();
    }



}
