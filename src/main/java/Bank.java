import java.math.BigDecimal;

public class Bank {

    public static void main(String[] args){
        
    }
    private int maxAccounts;
    private int numAccounts;
    private CheckingAccount[] checkingAccounts;

    public Bank (int maxAccounts){
        this.maxAccounts = maxAccounts;
        this.numAccounts = 0;
        this.checkingAccounts = new CheckingAccount[maxAccounts];
    }

    public boolean addAccount(String ssn, String pw, BigDecimal balance){
        if (numAccounts < maxAccounts) {
            checkingAccounts[maxAccounts] = new CheckingAccount(ssn, pw, maxAccounts, balance);
            numAccounts++;
            return true;
        } 
        return false;
    }

    public CheckingAccount getAuthorizedCheckingAccount(String ssn, String pw){
        for (CheckingAccount account : checkingAccounts) {
            if (account != null && account.matchAccount(ssn, pw)){
                return account;
            }
        }
        return null;
    }

    public boolean hasAccountFor(String ssn){
        for (CheckingAccount account : checkingAccounts) {
            if (account != null && account.belongsTo(ssn)) {
                return true;
            }
        }
        return false;
    }

}

