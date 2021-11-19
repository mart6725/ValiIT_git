package ee.bcs.valiit.tasks;

public class bankCustomer {

    private int accountNumber;
    private String customerName;
    boolean locked;
    private int balance;
//
//    public bankCustomer(int accountNumber, String customerName, boolean isLocked, int balance) {
//        this.accountNumber = accountNumber;
//        this.customerName = customerName;
//        this.isLocked = isLocked;
//        this.balance = balance;
//    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
