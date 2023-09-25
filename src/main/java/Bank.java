import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

public class Bank {
    private int maxAccounts;
    private CheckingAccount[] checkingAccounts;
    private long nextAccountNumber;

    public Bank(int maxAccounts) {
        this.maxAccounts = maxAccounts;
        this.checkingAccount = new CheckingAccount[maxAccounts];
        this.nextAccountNumber = 1;
    }

    public boolean addAccount(String ssn, String pw, BigDecimal balance) {
        if (nextAccountNumber >= maxAccounts) {
            System.out.println("Bank is at maximum capacity. Cannot add more accounts.");
            return false;
        }

        String pwHash = PasswordUtils.hashPassword(pw);

        long accountNumber = nextAccountNumber++;

        CheckingAccount newAccount = new CheckingAccount(ssn, pwHash, accountNumber, balance);

        checkingAccounts[(int)accountNumber-1] = newAccount;

        return true;
    }

    public CheckingAccount getAuthorizedCheckingAccount(String ssn, String pw){
        for(CheckingAccount account : checkingAccounts){
            if(account != null && account.matchAccount(ssn, pw)){
                return account;
            }
        }
        return null;
    }

    public boolean hasAccountFor(String ssn){
        for(CheckingAccount account : checkingAccounts){
            if(account != null && account.belongsTo(ssn)){
                return true;
            }
        }
        return false;
    }
}
