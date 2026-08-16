import java.util.ArrayList;
import java.util.List;

public class Account {

    private String accountNumber;
    private String customerName;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNumber, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        balance += amount;
    }

    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds.");
        }

        balance -= amount;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Transaction getLastTransaction() {
        if (transactions.isEmpty()) {
            return null;
        }

        return transactions.get(transactions.size() - 1);
    }

    public void removeLastTransaction() {
        if (!transactions.isEmpty()) {
            transactions.remove(transactions.size() - 1);
        }
    }

    public void removeTransaction(String id) {
        transactions.removeIf(
            t -> t.getTransactionId().equals(id)
        );
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "Account ID : " + accountNumber +
                "\nCustomer : " + customerName +
                "\nBalance : Rs." + balance;
    }
}