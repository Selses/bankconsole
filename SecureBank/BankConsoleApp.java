import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BankConsoleApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankService bank = new BankService();

        StatementsService statements =
                new StatementsService(bank);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "yyyy-MM-dd HH:mm"
                );

        while (true) {

            System.out.println(
                    "\n===== SECURE BANK ====="
            );

            System.out.println(
                    "1. Create Account"
            );

            System.out.println(
                    "2. Deposit"
            );

            System.out.println(
                    "3. Withdraw"
            );

            System.out.println(
                    "4. Check Balance"
            );

            System.out.println(
                    "5. Close Account"
            );

            System.out.println(
                    "6. Transfer"
            );

            System.out.println(
                    "7. Reverse Last Transaction"
            );

            System.out.println(
                    "8. Find Customer Accounts"
            );

            System.out.println(
                    "9. Accounts Sorted by ID"
            );

            System.out.println(
                    "10. Accounts Sorted by Balance"
            );

            System.out.println(
                    "11. Date Range Statement"
            );

            System.out.println(
                    "12. Ceiling Account ID"
            );

            System.out.println(
                    "13. Transaction History"
            );

            System.out.println(
                    "14. Exit"
            );

            System.out.print(
                    "Enter Choice : "
            );

            int choice = sc.nextInt();

            try {

                switch (choice) {

                    case 1:

                        sc.nextLine();

                        System.out.print(
                                "Customer Name : "
                        );

                        String name =
                                sc.nextLine();

                        String id =
                                bank.createAccount(name);

                        System.out.println(
                                "Account Created."
                        );

                        System.out.println(
                                "Account ID : " + id
                        );

                        break;

                    case 2:

                        System.out.print(
                                "Account ID : "
                        );

                        id = sc.next();

                        System.out.print(
                                "Amount : "
                        );

                        double amount =
                                sc.nextDouble();

                        bank.deposit(id, amount);

                        break;

                    case 3:

                        System.out.print(
                                "Account ID : "
                        );

                        id = sc.next();

                        System.out.print(
                                "Amount : "
                        );

                        amount =
                                sc.nextDouble();

                        bank.withdraw(
                                id,
                                amount
                        );

                        break;

                    case 4:

                        System.out.print(
                                "Account ID : "
                        );

                        id = sc.next();

                        bank.checkBalance(id);

                        break;

                    case 5:

                        System.out.print(
                                "Account ID : "
                        );

                        id = sc.next();

                        bank.closeAccount(id);

                        break;

                    case 6:

                        System.out.print(
                                "From Account ID : "
                        );

                        String fromId =
                                sc.next();

                        System.out.print(
                                "To Account ID : "
                        );

                        String toId =
                                sc.next();

                        System.out.print(
                                "Amount : "
                        );

                        amount =
                                sc.nextDouble();

                        bank.transfer(
                                fromId,
                                toId,
                                amount
                        );

                        break;

                    case 7:

                        System.out.print(
                                "Account ID : "
                        );

                        id = sc.next();

                        bank.reverseLastTransaction(
                                id
                        );

                        break;

                    case 8:

                        sc.nextLine();

                        System.out.print(
                                "Customer Name : "
                        );

                        name =
                                sc.nextLine();

                        bank.showCustomerAccounts(
                                name
                        );

                        break;

                    case 9:

                        statements.showAccountsById();

                        break;

                    case 10:

                        statements.showAccountsByBalance();

                        break;

                    case 11:

                        System.out.print(
                                "Account ID : "
                        );

                        id = sc.next();

                        sc.nextLine();

                        System.out.print(
                                "Start Date (yyyy-MM-dd HH:mm): "
                        );

                        String startText =
                                sc.nextLine();

                        System.out.print(
                                "End Date (yyyy-MM-dd HH:mm): "
                        );

                        String endText =
                                sc.nextLine();

                        LocalDateTime start =
                                LocalDateTime.parse(
                                        startText,
                                        formatter
                                );

                        LocalDateTime end =
                                LocalDateTime.parse(
                                        endText,
                                        formatter
                                );

                        statements.showStatement(
                                id,
                                start,
                                end
                        );

                        break;

                    case 12:

                        System.out.print(
                                "Enter Account ID : "
                        );

                        int searchId =
                                sc.nextInt();

                        Integer result =
                                statements
                                        .findCeilingAccountId(
                                                searchId
                                        );

                        if (result == null) {

                            System.out.println(
                                    "No account found."
                            );

                        } else {

                            System.out.println(
                                    "Ceiling Account ID : "
                                    + result
                            );
                        }

                        break;

                    case 13:

                        System.out.print(
                                "Account ID : "
                        );

                        id = sc.next();

                        Account account =
                                bank.getAccounts()
                                        .get(id);

                        if (account == null) {

                            System.out.println(
                                    "Account not found."
                            );

                        } else {

                            account.getTransactions()
                                    .forEach(
                                            (time,
                                             transaction) ->
                                                    System.out.println(
                                                            time +
                                                            " | " +
                                                            transaction
                                                    )
                                    );
                        }

                        break;

                    case 14:

                        System.out.println(
                                "Thank You."
                        );

                        sc.close();

                        return;

                    default:

                        System.out.println(
                                "Invalid Choice."
                        );
                }

            } catch (
                    AccountNotFoundException |
                    InsufficientFundsException e
            ) {

                System.out.println(
                        e.getMessage()
                );

            } catch (
                    IllegalArgumentException e
            ) {

                System.out.println(
                        "Invalid input: " +
                        e.getMessage()
                );
            }
        }
    }
}