import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class TestBankApp {

    private final String WITHDRAW_INPUT_TEMPLATE = "%s\n%s\n1\n2\n%d\n1\n";
    private final String WITHDRAW_SUCCESS_OUTPUT_TEMPLATE = 
                        "Enter SSN: " + 
                        "Enter password: " + 
                        "Account No. 8272966528181469941\n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: " + 
                        "The balance is %s\n" + 
                        "Account No. 8272966528181469941\n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: " + 
                        "Enter dollar amount to withdraw: $" + 
                        "Withdraw success.\n" + 
                        "Account No. 8272966528181469941\n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: " + 
                        "The balance is %s\n" + 
                        "Account No. 8272966528181469941\n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: " ;
    private final static String WITHDRAW_FAIL_OUTPUT_TEMPLATE =
                        "Enter SSN: " + 
                        "Enter password: " + 
                        "Account No. 7954889928108899335\n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: " + 
                        "The balance is %s\n" + 
                        "Account No. 7954889928108899335\n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: " + 
                        "Enter dollar amount to withdraw: $" + 
                        "Insufficient funds.\n" + 
                        "Account No. 7954889928108899335\n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: " +    
                        "The balance is %s\n" + 
                        "Account No. 7954889928108899335\n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: ";  
    private final String DEPOSIT_INPUT_TEMPLATE = "%s\n%s\n1\n3\n%d\n1\n";
    private final String DEPOSIT_SUCCESS_OUTPUT_TEMPLATE = 
                        "Enter SSN: Enter password: Account No. \n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: The balance is %s\n" + 
                        "Account No. \n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: Enter dollar amount to deposit: $Deposit success.\n" + 
                        "Account No. \n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: The balance is %s\n" + 
                        "Account No. \n" + 
                        "1. Check balance\n" + 
                        "2. Withdraw\n" + 
                        "3. Deposit\n" + 
                        "0. Exit\n" + 
                        "Enter Option: ";


    @Test
    public void testDisplayMenu()
    {
      PrintStream originalOut = System.out;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      System.setOut(new PrintStream(bos));
 
      // action
      CheckingAccount account = new CheckingAccount("123", "abc", 123, new BigDecimal(100));
      BankApp.displayMenu(account);
 
     //  System.err.println("bos.toString() = " + bos.toString());
 
      // assertion
      String expected = ("Account No. " + account.getAccountNo() + "\n" + 
                        "1. Check balance\n2. Withdraw\n3. Deposit\n0. Exit\n")
                        .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
      String actual = bos.toString().replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
      assertEquals(expected, actual);
 
 
      // undo the binding in System
      System.setOut(originalOut);
    }

    @Test
    public void testMainAccountAuthorization() {
        Bank bank = BankApp.loadBankAccounts();
        String ssn = "123456789"; 
        String pw = "pw789";
        checkMainAccountAuthorization(bank, ssn, pw);
        ssn = "123456788";
        pw = "pw788";
        checkMainAccountAuthorization(bank, ssn, pw);
        ssn = "123456787";
        pw = "pw787";
        checkMainAccountAuthorization(bank, ssn, pw);
        ssn = "123456786";
        pw = "pw786";
        checkMainAccountAuthorization(bank, ssn, pw);
        ssn = "123456785";
        pw = "pw785";
        checkMainAccountAuthorization(bank, ssn, pw);        
    }

    @Test public void testMain_1_2_1000_1_a789() {
        String ssn = "123456789"; 
        String pw = "pw789";
        String userInput = String.format(WITHDRAW_INPUT_TEMPLATE, ssn, pw, 1000);
        String expectedOutput = String.format(WITHDRAW_SUCCESS_OUTPUT_TEMPLATE, 
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("5000")),
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("4000")));
        testMainInputOutput(userInput, expectedOutput);                                
    }

    @Test public void testMain_1_2_1000_1_a788() {
        String ssn = "123456788"; 
        String pw = "pw788";
        String userInput = String.format(WITHDRAW_INPUT_TEMPLATE, ssn, pw, 1000);
        String expectedOutput = String.format(WITHDRAW_SUCCESS_OUTPUT_TEMPLATE, 
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("50000")),
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("49000")));
        testMainInputOutput(userInput, expectedOutput);                                
    }

    @Test public void testMain_1_2_10000_1_fail_a789() {
        String ssn = "123456789"; 
        String pw = "pw789";
        // String userInput = ssn + "\n" + pw + "\n" + "1\n2\n10000\n1\n";
        String userInput = String.format(WITHDRAW_INPUT_TEMPLATE, ssn, pw, 10000);
        String expectedOutput = String.format(WITHDRAW_FAIL_OUTPUT_TEMPLATE, 
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("5000")),
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("5000")));
        testMainInputOutput(userInput, expectedOutput);                             
    }

    @Test public void testMain_1_2_10000_1_fail_a787() {
        String ssn = "123456787"; 
        String pw = "pw787";
        // String userInput = ssn + "\n" + pw + "\n" + "1\n2\n10000\n1\n";
        String userInput = String.format(WITHDRAW_INPUT_TEMPLATE, ssn, pw, 10001);
        String expectedOutput = String.format(WITHDRAW_FAIL_OUTPUT_TEMPLATE, 
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("10000")),
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("10000")));
        testMainInputOutput(userInput, expectedOutput);                             
    }

    
    @Test public void testMain_1_3_500_1_a785() {
        String ssn = "123456785"; 
        String pw = "pw785";
        String userInput = String.format(DEPOSIT_INPUT_TEMPLATE, ssn, pw, 500);
        String expectedOutput = String.format(DEPOSIT_SUCCESS_OUTPUT_TEMPLATE, 
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("30000")),
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("30500")));        
        testMainInputOutput(userInput, expectedOutput);
    }

    @Test public void testMain_1_3_500_1_a786() {
        String ssn = "123456786"; 
        String pw = "pw786";
        String userInput = String.format(DEPOSIT_INPUT_TEMPLATE, ssn, pw, 10000);
        String expectedOutput = String.format(DEPOSIT_SUCCESS_OUTPUT_TEMPLATE, 
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("20000")),
            NumberFormat.getCurrencyInstance(Locale.US).format(new BigDecimal("30000")));        
        testMainInputOutput(userInput, expectedOutput);
    }

    private void checkMainAccountAuthorization(Bank bank, String ssn, String pw) {

        CheckingAccount account = bank.getAuthorizedCheckingAccount(ssn, pw);
        String userInput = ssn + "\n" + pw + "\n";
        String expectedOutput = "Enter SSN: Enter password: Account No. " + account.getAccountNo() + "\n" +
                                "1. Check balance\n" +
                                "2. Withdraw\n" + 
                                "3. Deposit\n" + 
                                "0. Exit\n" + 
                                "Enter Option: ";

        testMainInputOutput(userInput, expectedOutput);
    }

    private String removeAccountNo(String output) {
        // account no is random. so removed it from the the ouput for easy testing
        output = output.replaceAll("Account No. \\d+", "Account No. ");
        // System.err.println("=======================================\noutput=<" + output + ">");
        return output;
    }


    private void testMainInputOutput(String userInput, String expectedOutput) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
    
        InputStream originalIn = System.in;
        String input = userInput
                       .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        ByteArrayInputStream bis = new ByteArrayInputStream(input.getBytes());
        System.setIn(bis);
    
    
        // action
        try {
          BankApp.main(new String[] {});
        } catch (Exception e) {
        }
      
    
        // assertion
        String expected = expectedOutput
                          .replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        String actual = bos.toString().replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));;
        expected = removeAccountNo(expected);
        actual = removeAccountNo(actual);
        assertEquals(expected, actual);
    
    
        // undo the binding in System
        System.setOut(originalOut);   
        System.setIn(originalIn);     
       }    

    
}
