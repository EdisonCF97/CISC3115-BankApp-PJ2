import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class TestCheckingAccount {
    @Test
    public void testConstructor() {
        BigDecimal expectedBalance = new BigDecimal("123.45");
        String expectedSsn = "123";
        String expectedPwHash = "abc";
        long expectedAccountNo = 123;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, expectedBalance);
        assertEquals(expectedAccountNo, account.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(account.getBalance().equals(expectedBalance));
        assertTrue(account.matchAccount(expectedSsn, expectedPwHash));
    }

    @Test
    public void testCopyConstructor() {
        BigDecimal expectedBalance = new BigDecimal("10000.99");
        String expectedSsn = "456";
        String expectedPwHash = "cde";
        long expectedAccountNo = 567;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, expectedBalance);
        CheckingAccount copy  = new CheckingAccount(account);
        assertEquals(expectedAccountNo, copy.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(copy.getBalance().equals(expectedBalance));
        assertTrue(copy.matchAccount(expectedSsn, expectedPwHash));
    }

    @Test
    public void checkDeposit() {
        BigDecimal initBalance = new BigDecimal("30000.12");
        BigDecimal expectedBalance = new BigDecimal("31234.46");
        String expectedSsn = "978";
        String expectedPwHash = "xyz";
        long expectedAccountNo = 888;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, initBalance);
        boolean result = account.deposit(new BigDecimal("1234.34"));
        assertEquals(expectedAccountNo, account.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(account.matchAccount(expectedSsn, expectedPwHash));
        assertTrue(account.getBalance().equals(expectedBalance));
        assertEquals(true, result);
    }

    @Test
    public void checkDepositZero() {
        BigDecimal expectedBalance = new BigDecimal("30000.12");
        String expectedSsn = "978";
        String expectedPwHash = "xyz";
        long expectedAccountNo = 888;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, expectedBalance);
        boolean  result = account.deposit(new BigDecimal("0.00"));
        assertEquals(expectedAccountNo, account.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(account.matchAccount(expectedSsn, expectedPwHash));
        assertTrue(account.getBalance().equals(expectedBalance));
        assertEquals(false, result);
    }

    @Test
    public void checkDepositNegative() {
        BigDecimal expectedBalance = new BigDecimal("30000.12");
        String expectedSsn = "978";
        String expectedPwHash = "xyz";
        long expectedAccountNo = 888;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, expectedBalance);
        boolean  result = account.deposit(new BigDecimal("-12345.00"));
        assertEquals(expectedAccountNo, account.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(account.matchAccount(expectedSsn, expectedPwHash));
        assertTrue(account.getBalance().equals(expectedBalance));
        assertEquals(false, result);
    }

    @Test
    public void testWithdraw() {
        BigDecimal initBalance = new BigDecimal("41234.89");
        BigDecimal expectedBalance = new BigDecimal("30000.00");
        String expectedSsn = "978";
        String expectedPwHash = "xyz";
        long expectedAccountNo = 888;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, initBalance);
        boolean result = account.withdraw(new BigDecimal("11234.89"));
        assertEquals(expectedAccountNo, account.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(account.matchAccount(expectedSsn, expectedPwHash));
        assertTrue(account.getBalance().equals(expectedBalance));   
        assertEquals(true, result);
    }

    @Test
    public void testWithdrawZero() {
        BigDecimal expectedBalance = new BigDecimal("41234.89");
        String expectedSsn = "978";
        String expectedPwHash = "xyz";
        long expectedAccountNo = 888;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, expectedBalance);
        boolean result = account.withdraw(new BigDecimal("0.00"));
        assertEquals(expectedAccountNo, account.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(account.matchAccount(expectedSsn, expectedPwHash));
        assertTrue(account.getBalance().equals(expectedBalance));   
        assertEquals(false, result);
    }

    @Test
    public void testWithdrawNegative() {
        BigDecimal expectedBalance = new BigDecimal("41234.89");
        String expectedSsn = "978";
        String expectedPwHash = "xyz";
        long expectedAccountNo = 888;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, expectedBalance);
        boolean result = account.withdraw(new BigDecimal("-12345.00"));
        assertEquals(expectedAccountNo, account.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(account.matchAccount(expectedSsn, expectedPwHash));
        assertTrue(account.getBalance().equals(expectedBalance));   
        assertEquals(false, result);
    }

    @Test
    public void testWithdrawTooMuch() {
        BigDecimal expectedBalance = new BigDecimal("11234.89");
        String expectedSsn = "888";
        String expectedPwHash = "hgi";
        long expectedAccountNo = 666;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, expectedBalance);
        boolean result = account.withdraw(expectedBalance.add(new BigDecimal("0.01")));
        assertEquals(expectedAccountNo, account.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(account.matchAccount(expectedSsn, expectedPwHash));
        assertTrue(account.getBalance().equals(expectedBalance));   
        assertEquals(false, result);
    }


    @Test
    public void testBalanceAsString() {
        BigDecimal expectedBalance = new BigDecimal("4567.89");
        String expectedSsn = "456789";
        String expectedPwHash = "xyzabc";
        long expectedAccountNo = 888111;
        CheckingAccount account = new CheckingAccount(expectedSsn, expectedPwHash, expectedAccountNo, expectedBalance);
        assertEquals(expectedAccountNo, account.getAccountNo());
        assertTrue(account.belongsTo(expectedSsn));
        assertTrue(account.matchAccount(expectedSsn, expectedPwHash));
        assertTrue(account.getBalance().equals(expectedBalance));  
        assertEquals("$4,567.89", account.getBalanceAsString());

        boolean result = account.deposit(new BigDecimal("1000000.00"));
        assertEquals(true, result);
        assertEquals("$1,004,567.89", account.getBalanceAsString());

        result = account.withdraw(new BigDecimal("1004000.00"));
        assertEquals(true, result);
        assertEquals("$567.89", account.getBalanceAsString());

        result = account.withdraw(new BigDecimal("0.89"));
        assertEquals(true, result);
        assertEquals("$567.00", account.getBalanceAsString());
    }

}
