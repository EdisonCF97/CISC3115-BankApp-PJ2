import java.math.BigDecimal;
import java.util.Scanner;

public class BankApp {
    
    public static void main(String[] args){
        addLoop();
    }
        public static void displayMenu(){
            System.out.println("1. Check balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("0. Exit");
            System.out.print("Enter Option: ");
        }

        public static void addLoop(){
        Bank bank = loadBankAccounts();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Enter SSN: ");
        String ssn = sc.nextLine();
        System.out.printf("Enter password: ");
        String password = sc.nextLine();  
            
        CheckingAccount account = bank.getAuthorizedCheckingAccount(ssn, password);

        if(account != null){
            System.out.printf("Account No. " , account.getAccountNo());
        }
            while(true){
                displayMenu();
                System.out.print("Enter Option: ");
                int option = sc.nextInt();

        switch(option){
            case 1:
            if(option ==1){
                System.out.println("The balance is $" + account.getBalance());
                break;
            }
            case 2:
            if(option == 2){
                System.out.print("Enter dollar amount to withdraw: $");
                BigDecimal withdrawAmount = sc.nextBigDecimal();
                if (account.withdraw(withdrawAmount)) {
                    System.out.println("Withdraw success.");
                } else {
                    System.out.println("Insufficient funds.");
                }
                break;
            }
            case 3:
            if(option == 3){
                System.out.print("Enter dollar amount to deposit: $");
                BigDecimal depositAmount = sc.nextBigDecimal();
                account.deposit(depositAmount);
                System.out.println("Deposit success.");
                break;
            }
            case 0:
            if(option == 0){
                System.exit(0);
            }
            default:
                System.out.println("Invalid option. Please try again.");
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


    

