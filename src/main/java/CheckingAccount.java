import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CheckingAccount {

  public static void main(String[] args){
    
  }
  private String ssn;
  private String pw;
  private long accountNo;
  private BigDecimal balance;

  public CheckingAccount(String ssn, String pw, long accountNo, BigDecimal balance){
    this.ssn = ssn;
    this.pw = pw;
    this.accountNo = accountNo;
    this.balance = balance;
  }

  public CheckingAccount(CheckingAccount newAccount) {
    this.ssn = newAccount.ssn;
    this.pw = newAccount.pw;
    this.accountNo = newAccount.accountNo;
    this.balance = newAccount.balance;
}

  public boolean withdraw(BigDecimal amount){
    if (amount.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(amount) >= 0) {
      balance = balance.subtract(amount);
      return true;
    }
      return false;
}

  public boolean deposit(BigDecimal amount){
    if (amount.compareTo(balance) <= 0) {
      balance = balance.add(amount);
    return false;
    }
    return true; 
}

  public boolean matchAccount(String ssn, String pw){
        return this.ssn.equals(ssn) && this.pwHash.equals(pw);
  }

  public long getAccountNo(){
    return accountNo;
  }

  public BigDecimal getBalance(){
    return balance;

  }

  public boolean belongsTo(String ssn){
    return this.ssn.equals(ssn);
  }

  public String getBalanceAsString(){
    return NumberFormat.getCurrencyInstance(Locale.US).format(balance);
  }
}
