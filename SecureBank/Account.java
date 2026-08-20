import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

public class Account {

    private String accountNumber;
    private String customerName;
    private double balance;

    private TreeMap<LocalDateTime, Transaction> transactions =
            new TreeMap<>();

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

    public TreeMap<LocalDateTime, Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be positive."
            );
        }

        balance += amount;
    }

    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be positive."
            );
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds."
            );
        }

        balance -= amount;
    }

    public void addTransaction(Transaction transaction) {

        LocalDateTime time = transaction.getTimestamp();

        while (transactions.containsKey(time)) {
            time = time.plusNanos(1);
        }

        transactions.put(time, transaction);
    }

    public Transaction getLastTransaction() {

        if (transactions.isEmpty()) {
            return null;
        }

        return transactions.lastEntry().getValue();
    }

    public void removeLastTransaction() {

        if (!transactions.isEmpty()) {
            transactions.pollLastEntry();
        }
    }

    public void removeTransaction(String id) {

        transactions.entrySet().removeIf(
                entry -> entry.getValue()
                        .getTransactionId()
                        .equals(id)
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