import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class StatementsService {

    private BankService bank;

    public StatementsService(BankService bank) {
        this.bank = bank;
    }

    public void showAccountsById() {

        TreeMap<Integer, Account> accounts =
                bank.getAccountsSortedById();

        System.out.println(
                "\n===== ACCOUNTS BY ID ====="
        );

        accounts.forEach(
                (id, account) ->
                        System.out.println(
                                id + " - " +
                                account.getCustomerName() +
                                " - Rs." +
                                account.getBalance()
                        )
        );
    }

    public void showAccountsByBalance() {

        TreeMap<Integer, Account> accounts =
                bank.getAccountsSortedById();

        TreeMap<Account, Integer> sorted =
                new TreeMap<>(
                        Comparator
                                .comparingDouble(
                                        Account::getBalance
                                )
                                .thenComparing(
                                        Account::getAccountNumber
                                )
                );

        accounts.forEach(
                (id, account) ->
                        sorted.put(account, id)
        );

        System.out.println(
                "\n===== ACCOUNTS BY BALANCE ====="
        );

        sorted.forEach(
                (account, id) ->
                        System.out.println(
                                id + " - " +
                                account.getCustomerName() +
                                " - Rs." +
                                account.getBalance()
                        )
        );
    }

    public void showStatement(
            String accountId,
            LocalDateTime start,
            LocalDateTime end)
            throws AccountNotFoundException {

        Account account =
                bank.getAccounts().get(accountId);

        if (account == null) {
            throw new AccountNotFoundException(
                    "Account not found."
            );
        }

        TreeMap<LocalDateTime, Transaction>
                transactions =
                account.getTransactions();

        Map<LocalDateTime, Transaction> result =
                transactions.subMap(
                        start,
                        true,
                        end,
                        true
                );

        System.out.println(
                "\n===== DATE RANGE STATEMENT ====="
        );

        if (result.isEmpty()) {
            System.out.println(
                    "No transactions in this date range."
            );
            return;
        }

        result.forEach(
                (time, transaction) ->
                        System.out.println(
                                time + " | " +
                                transaction.getType() +
                                " | Rs." +
                                transaction.getAmount()
                        )
        );
    }

    public Integer findCeilingAccountId(int id) {

        TreeMap<Integer, Account> accounts =
                bank.getAccountsSortedById();

        return accounts.ceilingKey(id);
    }
}