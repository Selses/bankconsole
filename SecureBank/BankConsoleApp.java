import java.util.Scanner;

public class BankConsoleApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();

        while (true) {

            System.out.println("\n===== SECURE BANK =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Close Account");
            System.out.println("6. Transfer");
            System.out.println("7. Reverse Last Transaction");
            System.out.println("8. Find Customer Accounts");
            System.out.println("9. Transaction History");
            System.out.println("10. Exit");

            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();

            try {

                switch (choice) {

                    case 1:

                        sc.nextLine();

                        System.out.print("Customer Name : ");
                        String name = sc.nextLine();

                        String id = bank.createAccount(name);

                        System.out.println("Account Created.");
                        System.out.println("Account ID : " + id);

                        break;

                    case 2:

                        System.out.print("Account ID : ");
                        id = sc.next();

                        System.out.print("Amount : ");
                        double amount = sc.nextDouble();

                        bank.deposit(id, amount);

                        break;

                    case 3:

                        System.out.print("Account ID : ");
                        id = sc.next();

                        System.out.print("Amount : ");
                        amount = sc.nextDouble();

                        bank.withdraw(id, amount);

                        break;

                    case 4:

                        System.out.print("Account ID : ");
                        id = sc.next();

                        bank.checkBalance(id);

                        break;

                    case 5:

                        System.out.print("Account ID : ");
                        id = sc.next();

                        bank.closeAccount(id);

                        break;

                    case 6:

                        System.out.print("From Account ID : ");
                        String fromId = sc.next();

                        System.out.print("To Account ID : ");
                        String toId = sc.next();

                        System.out.print("Amount : ");
                        amount = sc.nextDouble();

                        bank.transfer(fromId, toId, amount);

                        break;

                    case 7:

                        System.out.print("Account ID : ");
                        id = sc.next();

                        bank.reverseLastTransaction(id);

                        break;

                    case 8:

                        sc.nextLine();

                        System.out.print("Customer Name : ");
                        name = sc.nextLine();

                        bank.showCustomerAccounts(name);

                        break;

                    case 9:

                        System.out.print("Account ID : ");
                        id = sc.next();

                        bank.showTransactions(id);

                        break;

                    case 10:

                        System.out.println("Thank You.");
                        sc.close();
                        return;

                    default:

                        System.out.println("Invalid Choice.");
                }

            } catch (AccountNotFoundException |
                     InsufficientFundsException e) {

                System.out.println(e.getMessage());

            } catch (IllegalArgumentException e) {

                System.out.println(e.getMessage());
            }
        }
    }
}