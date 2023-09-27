import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter SSN: ");
            String ssn = scanner.nextLine();
            System.out.print("Enter password: ");
            String pw = scanner.nextLine();
            CheckingAccount account = new CheckingAccount("6637862074333514921", "pwHashHere", 123456, BigDecimal.valueOf(5000.00));

            BankApp.displayMenu(account);
        }
    }

    public static void displayMenu(CheckingAccount account) {
        Scanner scanner = new Scanner(System.in);
        int option;
        
        while (true) {
            System.out.println("Account No. " + account.getAccountNo());
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("0. Exit");
            System.out.print("Enter Option: ");
            option = scanner.nextInt();
    
            switch (option) {
                case 1:
                System.out.println("The balance is " + NumberFormat.getCurrencyInstance(Locale.US).format(account.getBalance()));
                break;
                case 2:
                    System.out.print("Enter dollar amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    BigDecimal withdrawAmountBD = BigDecimal.valueOf(withdrawAmount);
                    if (account.withdraw(withdrawAmountBD)) {
                        System.out.println("Withdraw success.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 3:
                    System.out.print("Enter dollar amount to deposit: $");
                    BigDecimal depositAmount = scanner.nextBigDecimal();
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

        public static Bank loadBankAccounts() {
            Bank bank = new Bank(5);
            bank.addAccount("123456789", "pw789", BigDecimal.valueOf(5000.00));
            bank.addAccount("123456788", "pw788", BigDecimal.valueOf(5000.00));
            bank.addAccount("123456787", "pw787", BigDecimal.valueOf(5000.00));
            bank.addAccount("123456786", "pw786", BigDecimal.valueOf(5000.00));
            bank.addAccount("123456785", "pw785", BigDecimal.valueOf(5000.00));
            return bank;
        }
        
 

}



