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

    public boolean addAccount(String ssn, String pw, BigDecimal balance) {
        if (numAccounts >= maxAccounts) {
            System.out.println("Bank is at maximum capacity. Cannot add more accounts.");
            return false;
        }
        
        if(hasAccountFor(ssn)) {
            return false;
        }
    
            
        long accountNumber = numAccounts + 1;

        CheckingAccount newAccount = new CheckingAccount(ssn, PasswordUtils.getPasswordHash(pw), accountNumber, balance);

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
        CheckingAccount account = bank.getAuthorizedCheckingAccount("123", "abc");
            if (account != null) {
                assertNotNull(account);
                assertTrue(account.belongsTo("123"));
                assertFalse(account.matchAccount("123", "abc"));
                assertTrue(account.matchAccount("123", PasswordUtils.getPasswordHash("abc")));
                assertEquals(new BigDecimal("100"), account.getBalance());
            } else {
                    System.out.println("No authorized account found for the provided credentials.");
        }

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
