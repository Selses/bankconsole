import java.util.Scanner;

public class BankConsoleApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankService bank = new BankService();

        while (true) {

            System.out.println("\n===== SECURE BANK =====");
            System.out.println("1.Create Account");
            System.out.println("2.Deposit");
            System.out.println("3.Withdraw");
            System.out.println("4.Check Balance");
            System.out.println("5.Close Account");
            System.out.println("6.Exit");

            System.out.print("Enter Choice : ");
            int choice = sc.nextInt();

            try {

                switch (choice) {

                    case 1:

                        sc.nextLine();

                        System.out.print("Customer Name : ");
                        String name = sc.nextLine();

                        int id = bank.createAccount(name);

                        System.out.println("Account Created.");
                        System.out.println("Account ID : " + id);

                        break;

                    case 2:

                        System.out.print("Account ID : ");
                        id = sc.nextInt();

                        System.out.print("Amount : ");
                        double amount = sc.nextDouble();

                        bank.deposit(id, amount);

                        break;

                    case 3:

                        System.out.print("Account ID : ");
                        id = sc.nextInt();

                        System.out.print("Amount : ");
                        amount = sc.nextDouble();

                        bank.withdraw(id, amount);

                        break;

                    case 4:

                        System.out.print("Account ID : ");
                        id = sc.nextInt();

                        bank.checkBalance(id);

                        break;

                    case 5:

                        System.out.print("Account ID : ");
                        id = sc.nextInt();

                        bank.closeAccount(id);

                        break;

                    case 6:

                        System.out.println("Thank You.");
                        sc.close();
                        System.exit(0);

                    default:

                        System.out.println("Invalid Choice.");

                }

            } catch (AccountNotFoundException |
                     InsufficientFundsException e) {

                System.out.println(e.getMessage());

            }

        }

    }

}