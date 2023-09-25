import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.Bank;

public class BankApp{

    public static void main(String[] args) {
        Bank bank = loadBankAccounts();
        addLoop(bank);
    }

    public static void displayMenu(BankAccount account){
        System.out.println("1. Check balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("0. Exit");
    }

    public static void addLoop(){
        try (Scanner scanner = new Scanner(System.in)) {
            int option;
            BankAccount account = new BankAccount("6637862074333514921", 5000.00);

            while (true) {
                displayMenu(account);
                System.out.print("Enter Option: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("The balance is $" + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter dollar amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        if (account.withdraw(withdrawAmount)) {
                            System.out.println("Withdraw success.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter dollar amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        System.out.println("Deposit success.");
                        break;
                    case 0:
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
    public static Bank loadBankAccounts() {
        Bank bank = new Bank(5);
        bank.addAccount("123456789", "pw789", new BigDecimal("5000.00"));
        bank.addAccount("123456788", "pw788", new BigDecimal("50000.00"));
        bank.addAccount("123456787", "pw787", new BigDecimal("10000.00"));
        bank.addAccount("123456786", "pw786", new BigDecimal("20000.00"));
        bank.addAccount("123456785", "pw785", new BigDecimal("30000.00"));
        return bank;
        }
}

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        balance += amount;
    }

}


