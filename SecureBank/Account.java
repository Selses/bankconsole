public class Account {
    private int id;
    private String customerName;
    private double balance;

    public Account(int id, String customerName) {
        this.id = id;
        this.customerName = customerName;
        this.balance = 0;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public String toString() {
        return "Account ID : " + id +
                "\nCustomer : " + customerName +
                "\nBalance : ₹" + balance;
    }
}