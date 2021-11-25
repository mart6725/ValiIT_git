package ee.bcs.valiit.tasks;

public class BankAccounts {

    private String name;
    private int accountNumber;
    private int clientId;
    boolean locked;
    private int balance;
//
//    public bankCustomer(int accountNumber, String customerName, boolean isLocked, int balance) {
//        this.accountNumber = accountNumber;
//        this.customerName = customerName;
//        this.isLocked = isLocked;
//        this.balance = balance;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
