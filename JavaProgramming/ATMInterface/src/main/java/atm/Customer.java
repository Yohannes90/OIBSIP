package src.main.java.atm;


public class Customer {
    private int id;
    private String name;
    private String email;
    private String password;
    private int accountNo;
    private boolean authenticated;

    public Customer(int id, String name, String email, String password, int accountNo) {
        setId(id);
        setName(name);
        setEmail(email);
        setPassword(password);
        setAccountNo(accountNo);
        setAuthenticated(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
