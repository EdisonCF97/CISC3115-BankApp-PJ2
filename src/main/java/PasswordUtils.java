import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    public static void main(String[] args){
        
    }

    public static String getPasswordHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hashString = new StringBuilder();

            for (byte b : hashBytes) {
                hashString.append(String.format("%02x", b));
            }

            return hashString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}