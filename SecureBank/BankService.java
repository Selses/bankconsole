import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public class BankService {

    private HashMap<String, Account> accounts = new HashMap<>();

    private HashMap<String, List<String>> customerIndex =
            new HashMap<>();

    private int nextId = 1001;

    public String createAccount(String name) {

        String id = String.valueOf(nextId++);

        Account account = new Account(id, name, 0);

        accounts.put(id, account);

        customerIndex
                .computeIfAbsent(
                        name.toLowerCase(),
                        k -> new ArrayList<>()
                )
                .add(id);

        return id;
    }

    public void deposit(String id, double amount)
            throws AccountNotFoundException {

        Account account = getAccount(id);

        account.deposit(amount);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                "DEPOSIT",
                amount,
                null,
                id
        );

        account.addTransaction(transaction);

        System.out.println("Deposit Successful.");
    }

    public void withdraw(String id, double amount)
            throws AccountNotFoundException,
            InsufficientFundsException {

        Account account = getAccount(id);

        account.withdraw(amount);

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                "WITHDRAW",
                amount,
                id,
                null
        );

        account.addTransaction(transaction);

        System.out.println("Withdrawal Successful.");
    }

    public void transfer(String fromId, String toId, double amount)
            throws AccountNotFoundException,
            InsufficientFundsException {

        Account from = getAccount(fromId);
        Account to = getAccount(toId);

        if (fromId.equals(toId)) {
            System.out.println(
                    "Cannot transfer to same account."
            );
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (from.getBalance() < amount) {
            throw new InsufficientFundsException(
                    "Insufficient funds."
            );
        }

        double oldFromBalance = from.getBalance();
        double oldToBalance = to.getBalance();

        String transactionId =
                UUID.randomUUID().toString();

        try {

            from.withdraw(amount);
            to.deposit(amount);

            Transaction outTransaction =
                    new Transaction(
                            transactionId,
                            "TRANSFER_OUT",
                            amount,
                            fromId,
                            toId
                    );

            Transaction inTransaction =
                    new Transaction(
                            transactionId,
                            "TRANSFER_IN",
                            amount,
                            fromId,
                            toId
                    );

            from.addTransaction(outTransaction);
            to.addTransaction(inTransaction);

            System.out.println(
                    "Transfer Successful."
            );

        } catch (RuntimeException e) {

            from.setBalance(oldFromBalance);
            to.setBalance(oldToBalance);

            throw e;
        }
    }

    public void reverseLastTransaction(String id)
            throws AccountNotFoundException,
            InsufficientFundsException {

        Account account = getAccount(id);

        Transaction transaction =
                account.getLastTransaction();

        if (transaction == null) {
            System.out.println(
                    "No transaction to reverse."
            );
            return;
        }

        String type = transaction.getType();
        double amount = transaction.getAmount();

        if (type.equals("DEPOSIT")) {

            account.withdraw(amount);

        } else if (type.equals("WITHDRAW")) {

            account.deposit(amount);

        } else if (type.equals("TRANSFER_OUT")) {

            Account target =
                    getAccount(transaction.getToId());

            account.deposit(amount);
            target.withdraw(amount);

            target.removeTransaction(
                    transaction.getTransactionId()
            );

        } else if (type.equals("TRANSFER_IN")) {

            Account source =
                    getAccount(transaction.getFromId());

            account.withdraw(amount);
            source.deposit(amount);

            source.removeTransaction(
                    transaction.getTransactionId()
            );
        }

        account.removeLastTransaction();

        System.out.println(
                "Transaction Reversed Successfully."
        );
    }

    public void showCustomerAccounts(String name) {

        List<String> ids =
                customerIndex.get(
                        name.toLowerCase()
                );

        if (ids == null || ids.isEmpty()) {
            System.out.println(
                    "No accounts found."
            );
            return;
        }

        System.out.println(
                "Accounts for " + name + ":"
        );

        for (String id : ids) {
            System.out.println(
                    "Account ID : " + id
            );
        }
    }

    public void checkBalance(String id)
            throws AccountNotFoundException {

        System.out.println(getAccount(id));
    }

    public void closeAccount(String id)
            throws AccountNotFoundException {

        Account account = getAccount(id);

        accounts.remove(id);

        String name =
                account.getCustomerName().toLowerCase();

        List<String> ids =
                customerIndex.get(name);

        if (ids != null) {

            ids.remove(id);

            if (ids.isEmpty()) {
                customerIndex.remove(name);
            }
        }

        System.out.println(
                "Account Closed Successfully."
        );
    }

    public TreeMap<Integer, Account> getAccountsSortedById() {

        TreeMap<Integer, Account> sorted =
                new TreeMap<>();

        accounts.forEach(
                (id, account) ->
                        sorted.put(
                                Integer.parseInt(id),
                                account
                        )
        );

        return sorted;
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    private Account getAccount(String id)
            throws AccountNotFoundException {

        if (!accounts.containsKey(id)) {

            throw new AccountNotFoundException(
                    "Account not found."
            );
        }

        return accounts.get(id);
    }
}