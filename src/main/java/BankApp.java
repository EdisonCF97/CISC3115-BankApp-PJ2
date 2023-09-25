import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {
        accountNumber();
    }

        public static void displayMenu(){
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("0. Exit");
        }

        public static void accountNumber(){
            Scanner scanner = new Scanner(System.in);
            int option;
            BankAccount account = new BankAccount("6637862074333514921", 5000.00);
        
        while (true) {
            displayMenu();
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



    

