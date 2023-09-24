import java.math.BigDecimal;
import java.util.Scanner;

public class BankApp {
    private final static int MAX_ACCOUNTS = 10;

    public static Bank loadBankAccounts() {
        Bank bank = new Bank(MAX_ACCOUNTS);
        bank.addAccount("123456789", "pw789", new BigDecimal("5000.00"));
        bank.addAccount("123456788", "pw788", new BigDecimal("50000.00"));
        bank.addAccount("123456787", "pw787", new BigDecimal("10000.00"));
        bank.addAccount("123456786", "pw786", new BigDecimal("20000.00"));
        bank.addAccount("123456785", "pw785", new BigDecimal("30000.00"));
        return bank;
    }

}