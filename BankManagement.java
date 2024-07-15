package Medium;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Bank {
    private double balance;
    private List<String> transactionHistory;

    public Bank() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Amount Deposited: " + amount);
        } else {
            throw new IllegalArgumentException("Deposit amount need to be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Amount Withdrawn: " + amount);
        } else if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        } else {
            throw new IllegalArgumentException("Withdrawal amount need to be positive.");
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

public class BankManagament {
    public static void main(String[] args) {
        Bank account = new Bank();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nBank Account Management!!!");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amt: ");
                    double depositAmount = sc.nextDouble();
                    try {
                        account.deposit(depositAmount);
                        System.out.println("Amount Deposit successful.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter withdrawal amt: ");
                    double withdrawAmount = sc.nextDouble();
                    try {
                        account.withdraw(withdrawAmount);
                        System.out.println("Amount Withdrawal successful.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Current balance: " + account.getBalance());
                    break;
                case 4:
                    System.out.println("Transaction History:");
                    for (String transaction : account.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 5:
                    System.out.println("Thank you.Visit again!!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid, please try again.");
            }
        }
    }
}
