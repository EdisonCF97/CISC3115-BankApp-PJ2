import java.util.ArrayList;
import java.util.List;

public class Bank {
    private int maxAccounts;
    private List<CheckingAccount> accounts;
    private long nextAccountNumber;

    public Bank(int maxAccounts) {
        this.maxAccounts = maxAccounts;
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1;
    }

    public boolean addAccount(String ssn, String pw, BigDecimal balance) {
        if (accounts.size() >= maxAccounts) {
            System.out.println("Bank is at maximum capacity. Cannot add more accounts.");
            return false;
        }

        String pwHash = PasswordUtils.hashPassword(pw);

        long accountNumber = nextAccountNumber++;

        CheckingAccount newAccount = new CheckingAccount(ssn, pwHash, accountNumber, balance);

        accounts.add(newAccount);

        return true;
    }
}
