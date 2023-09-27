import java.math.BigDecimal;
import java.util.ArrayList;

public class Bank {
    private int maxAccounts;
    private int numAccounts;
    private CheckingAccount[] checkingAccounts;

    public Bank(int maxAccounts) {
        this.maxAccounts = maxAccounts;
        this.numAccounts = 0;
        this.checkingAccounts = new CheckingAccount[maxAccounts];
    }

    public boolean addAccount(String ssn, String pwHash, BigDecimal balance) {
        if (numAccounts >= maxAccounts) {
            System.out.println("Bank is at maximum capacity. Cannot add more accounts.");
            return false;
        }
        
        if (hasAccountFor.belongsTo(ssn)) {
            return true;

        }else{
            return false;
        }
        
        }
    
            
        long accountNumber = numAccounts + 1;

        CheckingAccount newAccount = new CheckingAccount(ssn, pwHash, accountNumber, balance);

        checkingAccounts[numAccounts] = newAccount;
        numAccounts++;

        return true;
    }

    public CheckingAccount getAuthorizedCheckingAccount(String ssn, String pw) {
        for (int i = 0; i < numAccounts; i++) {
            if (checkingAccounts[i].matchAccount(ssn, pw)) {
                return checkingAccounts[i];
            }
        }
        return null;
    }

    public boolean hasAccountFor(String ssn) {
        for (int i = 0; i < numAccounts; i++) {
            if (checkingAccounts[i].belongsTo(ssn)) {
                return true;
            }
        }
        return false;
    }
}
