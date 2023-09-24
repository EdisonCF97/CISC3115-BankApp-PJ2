import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TestBank{
    @Test
    public void testAddAccountNew() {
        Bank bank = new Bank(3);
        boolean result = bank.addAccount("123", "abc", new BigDecimal("100"));
        assertTrue(result);
    }

    @Test
    public void testAddAccountExistingNonExisting() {
        Bank bank = new Bank(3);
        boolean result = bank.addAccount("123", "abc", new BigDecimal("100"));
        assertTrue(result);

        // existing
        result = bank.addAccount("123", "abc", new BigDecimal("200"));
        assertFalse(result);

        // existing
        result = bank.addAccount("123", "cde", new BigDecimal("200"));
        assertFalse(result);

        // new
        result = bank.addAccount("456", "abc", new BigDecimal("100"));
        assertTrue(result);

        // existing
        result = bank.addAccount("456", "cde", new BigDecimal("200"));
        assertFalse(result);
    }

    @Test
    public void testAddAccountToCapacity() {
        // capacity: 0 
        Bank bank = new Bank(0);
        boolean result = bank.addAccount("123", "abc", new BigDecimal("100"));
        assertFalse(result);


        // capacity: 1
        bank = new Bank(1);
        result = bank.addAccount("123", "abc", new BigDecimal("100"));
        assertTrue(result);

        result = bank.addAccount("321", "cde", new BigDecimal("100"));
        assertFalse(result);

        // capcity: 2
        bank = new Bank(2);
        result = bank.addAccount("123", "abc", new BigDecimal("100"));
        assertTrue(result);

        result = bank.addAccount("321", "cde", new BigDecimal("100"));
        assertTrue(result);
        
        result = bank.addAccount("456", "cde", new BigDecimal("100"));
        assertFalse(result);


        // capacity: 3
        bank = new Bank(3);
        result = bank.addAccount("123", "abc", new BigDecimal("100"));
        assertTrue(result);

        result = bank.addAccount("321", "cde", new BigDecimal("100"));
        assertTrue(result);

        result = bank.addAccount("789", "cde", new BigDecimal("100"));
        assertTrue(result);
        
        result = bank.addAccount("456", "cde", new BigDecimal("100"));
        assertFalse(result);

        // capacity: 100
        bank = new Bank(100);
        Random rng = new Random();
        for (int i=0; i<100; i++) {
            String ssn = null;
            do {
                ssn  = Integer.toString(rng.nextInt(Integer.MAX_VALUE));
            } while(bank.hasAccountFor(ssn));
            result = bank.addAccount(ssn, "abc", new BigDecimal("100"));
            assertTrue(result);            
        }

        String ssn = null;
        do {
            ssn  = Integer.toString(rng.nextInt(Integer.MAX_VALUE));
        } while(bank.hasAccountFor(ssn));
        result = bank.addAccount(ssn, "abc", new BigDecimal("100"));
        assertFalse(result);  
    }

    @Test
    public void testGetAuthorizedCheckingAccount() {
        // capacity: 3
        Bank bank = new Bank(3);
        boolean result = bank.addAccount("123", "abc", new BigDecimal("100"));
        assertTrue(result);

        result = bank.addAccount("321", "cde", new BigDecimal("200"));
        assertTrue(result);

        result = bank.addAccount("789", "cde", new BigDecimal("300"));
        assertTrue(result);

        CheckingAccount account = bank.getAuthorizedCheckingAccount("123", "abc");
        assertNotNull(account);
        assertTrue(account.belongsTo("123"));
        assertFalse(account.matchAccount("123", "abc"));
        assertTrue(account.matchAccount("123", PasswordUtils.getPasswordHash("abc")));
        assertEquals(new BigDecimal("100"), account.getBalance());

        account = bank.getAuthorizedCheckingAccount("321", "cde");
        assertNotNull(account);
        assertTrue(account.belongsTo("321"));
        assertFalse(account.matchAccount("321", "cde"));
        assertTrue(account.matchAccount("321", PasswordUtils.getPasswordHash("cde")));
        assertEquals(new BigDecimal("200"), account.getBalance());     
        
        account = bank.getAuthorizedCheckingAccount("789", "cde");
        assertNotNull(account);
        assertTrue(account.belongsTo("789"));
        assertFalse(account.matchAccount("789", "cde"));
        assertTrue(account.matchAccount("789", PasswordUtils.getPasswordHash("cde")));
        assertEquals(new BigDecimal("300"), account.getBalance());     

        account = bank.getAuthorizedCheckingAccount("999", "cde");
        assertNull(account);
    }

    @Test
    public void testHasAccountFor() {
        // capacity: 3
        Bank bank = new Bank(3);
        boolean result = bank.addAccount("123", "abc", new BigDecimal("100"));
        assertTrue(result);

        result = bank.addAccount("321", "cde", new BigDecimal("200"));
        assertTrue(result);

        result = bank.addAccount("789", "cde", new BigDecimal("300"));
        assertTrue(result);

        assertTrue(bank.hasAccountFor("123"));
        assertTrue(bank.hasAccountFor("321"));
        assertTrue(bank.hasAccountFor("789"));
        assertFalse(bank.hasAccountFor("999"));
    }

    @Test
    public void testAccountNoGeneration() {
        checkAccountNoGeneration(100);
        Random rng = new Random();
        for (int i=0; i<3; i++) {
            int maxAccounts = rng.nextInt(100) + 100;
            checkAccountNoGeneration(maxAccounts);
        }
    }

    private void checkAccountNoGeneration(int maxAccounts) {
        // capacity: maxAccounts
        Bank bank = new Bank(maxAccounts);
        Random rng = new Random();
        class AccountSecret {
            public String ssn;
            public String pw;
            public AccountSecret(String ssn, String pw) {
                this.ssn = ssn;
                this.pw = pw;
            }
        };
        AccountSecret[] secrets = new AccountSecret[maxAccounts];
        for (int i=0; i<maxAccounts; i++) {
            String ssn = null;
            do {
                ssn  = Integer.toString(rng.nextInt(Integer.MAX_VALUE));
            } while(bank.hasAccountFor(ssn));
            boolean result = bank.addAccount(ssn, "abc", new BigDecimal("100"));
            assertTrue(result);  
            secrets[i] = new AccountSecret(ssn, "abc");    
        }
        Set<Long> accountNoSet = new HashSet<Long>();
        for (int i=0; i<maxAccounts; i++) {
            CheckingAccount account = bank.getAuthorizedCheckingAccount(secrets[i].ssn, secrets[i].pw);
            assertFalse(accountNoSet.contains(account.getAccountNo()));
            accountNoSet.add(account.getAccountNo());
        } 
    }
}
