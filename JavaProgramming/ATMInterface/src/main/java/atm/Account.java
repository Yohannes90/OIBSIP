package src.main.java.atm;

import src.main.java.atm.exceptions.AmountException;

import static src.main.java.atm.DataSource.*;


public class Account {
    private int id;
    private String type;
    private double balance;

    public Account(int id, String type, double balance) {
        setId(id);
        setType(type);
        setBalance(balance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) throws AmountException{
        if (amount < 50) {
            throw new AmountException("Minimum deposit is 50");
        } else {
            double newBalance = balance + amount;
            setBalance(newBalance);
            updateAccountBalance(id, newBalance);
        }
    }

    public void withdraw(double amount) throws AmountException {
        if (amount < 10) {
            throw new AmountException("The minimum withdraw amount is 10.00");
        } else if (amount > balance) {
            throw new AmountException("You do not have sufficient funds for this withdraw!");
        } else {
            double newBalance = balance - amount;
            setBalance(newBalance);
            updateAccountBalance(id, newBalance);
        }
    }

    public void transferToAccount(int recipientAccount, double amount) throws AmountException {
        if (amount < 10) {
            throw new AmountException("The minimum withdraw amount is 10.00");
        } else if (amount > balance) {
            throw new AmountException("You do not have sufficient funds for this withdraw!");
        } else {
            double senderBalance = balance - amount;
            double receiverBalance = getAccount(recipientAccount).getBalance() + amount;
            System.out.println(" before recipient " + getAccount(recipientAccount).getBalance());
            System.out.println("before sender " + getBalance());
            setBalance(senderBalance);
            getAccount(recipientAccount).setBalance(receiverBalance);
            updateAccountBalance(id, senderBalance);
            updateAccountBalance(recipientAccount, receiverBalance);
            System.out.println("recipient " + getAccount(recipientAccount).getBalance());
            System.out.println("sender " + getBalance());
        }
    }
}
