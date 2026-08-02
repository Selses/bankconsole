import java.util.HashMap;

public class BankService {

    private HashMap<Integer, Account> accounts = new HashMap<>();
    private int nextId = 1001;

    // Create Account
    public int createAccount(String name) {

        Account account = new Account(nextId, name);
        accounts.put(nextId, account);

        return nextId++;
    }

    // Deposit
    public void deposit(int id, double amount)
            throws AccountNotFoundException {

        if (!accounts.containsKey(id))
            throw new AccountNotFoundException("Account not found.");

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        accounts.get(id).deposit(amount);
        System.out.println("Deposit Successful.");
    }

    // Withdraw
    public void withdraw(int id, double amount)
            throws AccountNotFoundException,
            InsufficientFundsException {

        if (!accounts.containsKey(id))
            throw new AccountNotFoundException("Account not found.");

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        Account account = accounts.get(id);

        if (amount > account.getBalance())
            throw new InsufficientFundsException("Insufficient Balance.");

        account.withdraw(amount);

        System.out.println("Withdrawal Successful.");
    }

    // Balance
    public void checkBalance(int id)
            throws AccountNotFoundException {

        if (!accounts.containsKey(id))
            throw new AccountNotFoundException("Account not found.");

        System.out.println(accounts.get(id));
    }

    // Close Account
    public void closeAccount(int id)
            throws AccountNotFoundException {

        if (!accounts.containsKey(id))
            throw new AccountNotFoundException("Account not found.");

        accounts.remove(id);

        System.out.println("Account Closed Successfully.");
    }

}