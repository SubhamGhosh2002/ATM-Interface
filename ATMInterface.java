import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static List<BankAccount> accountDatabase = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM!");

        // Create some example accounts and add them to the database
        accountDatabase.add(new BankAccount("123456", "Soumyadip Paul", 100000));
        accountDatabase.add(new BankAccount("987654", "Avishek Mondal", 150000));

        while (true) {
            System.out.print("Enter your account number (or 'exit' to quit): ");
            String accountNumber = scanner.nextLine();

            if (accountNumber.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the ATM!");
                System.exit(0);
            }

            BankAccount userAccount = findAccount(accountNumber);

            if (userAccount != null) {
                System.out.println("Welcome, " + userAccount.getAccountHolderName() + "!");
                performOperations(userAccount);
            } else {
                System.out.println("Account not found. Please enter a valid account number.");
            }
        }
    }

    private static BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accountDatabase) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    private static void performOperations(BankAccount account) {
        while (true) {
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    checkBalance(account);
                    break;
                case 2:
                    deposit(account);
                    break;
                case 3:
                    withdraw(account);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void checkBalance(BankAccount account) {
        System.out.println("Your account balance: Rs " + account.getAccountBalance());
    }

    private static void deposit(BankAccount account) {
        System.out.print("Enter the amount to deposit: Rs ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        account.deposit(amount);
    }

    private static void withdraw(BankAccount account) {
        System.out.print("Enter the amount to withdraw: Rs ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        account.withdraw(amount);
    }

    private static class BankAccount {
        private String accountNumber;
        private String accountHolderName;
        private double accountBalance;

        public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.accountBalance = initialBalance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getAccountHolderName() {
            return accountHolderName;
        }

        public double getAccountBalance() {
            return accountBalance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                accountBalance += amount;
                System.out.println("Deposit successful. New balance: Rs " + accountBalance);
            } else {
                System.out.println("Invalid amount for deposit.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= accountBalance) {
                accountBalance -= amount;
                System.out.println("Withdrawal successful. New balance: Rs " + accountBalance);
            } else {
                System.out.println("Invalid amount for withdrawal or insufficient balance.");
            }
        }
    }
}
