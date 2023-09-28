import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class BankApp{

    public static void main(String[] args) {
           
            /*CheckingAccount account = new CheckingAccount("6637862074333514921", "pwHashHere", 123456, BigDecimal.valueOf(5000.00));
            
            BankApp.displayMenu(account);
       
    }
    
        public static void displayMenu(CheckingAccount account) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter SSN: ");
            String ssn = scanner.nextLine();
            System.out.print("Enter password: ");
            String pw = scanner.nextLine();
            int option;*/
            Bank bank = loadBankAccounts();
            Scanner scanner = new Scanner(System.in);



                
        /*CheckingAccount account = new CheckingAccount("6637862074333514921", "pwHashHere", 123456, BigDecimal.valueOf(5000.00));
  */    
                System.out.print("Enter SSN: ");
                String ssn = scanner.nextLine();
                System.out.print("Enter password: ");
                String pw = scanner.nextLine();

                CheckingAccount account = bank.getAuthorizedCheckingAccount(ssn, pw);

                appLoop(account, scanner);

            }

                
        public static void displayMenu(CheckingAccount account){           
            System.out.println("Account No. " + account.getAccountNo());
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("0. Exit");
        }
        
        public static void appLoop(CheckingAccount account) {
            Scanner scanner = new Scanne(System.in);
            int option; 
            
            
        while (true) {
            displayMenu(account);
            System.out.print("Enter Option: ");
            option = scanner.nextInt();
    
            switch (option) {
                case 1:
                System.out.printf("The balance is %s\n ",account.getBalanceAsString());
                break;
                case 2:
                    System.out.print("Enter dollar amount to withdraw: $");
                    BigDecimal withdrawAmount = scanner.nextBigDecimal();
                    if (account.withdraw(withdrawAmount)) {
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
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }  

    public static Bank loadBankAccounts() {
            Bank bank = new Bank(5);
            bank.addAccount("123456789", "pw789", BigDecimal.valueOf(5000.00));
            bank.addAccount("123456788", "pw788", BigDecimal.valueOf(50000.00));
            bank.addAccount("123456787", "pw787", BigDecimal.valueOf(10000.00));
            bank.addAccount("123456786", "pw786", BigDecimal.valueOf(20000.00));
            bank.addAccount("123456785", "pw785", BigDecimal.valueOf(30000.00));
            return bank;
        }   
}



