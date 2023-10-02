import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
            return false;
        }
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount account) {
        userAccount = account;
    }

    public void checkBalance() {
        double balance = userAccount.getBalance();
        System.out.println("Current Balance: $" + balance);
    }

    public void deposit(double amount) {
        userAccount.deposit(amount);
    }

    public void withdraw(double amount) {
        if (userAccount.withdraw(amount)) {
            System.out.println("Please take your cash.");
        } else {
            System.out.println("Transaction failed. Please try again.");
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter initial account balance: $");
        double initialBalance = scanner.nextDouble();
        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);

        while (true) {
            System.out.println("\nATM Options:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    atm.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
