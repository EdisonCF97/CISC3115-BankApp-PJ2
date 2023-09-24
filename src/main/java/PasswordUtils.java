
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
  public static String getPasswordHash(String pw) {
    MessageDigest msgDigest;
    try {
      msgDigest = MessageDigest.getInstance("SHA-256");
      msgDigest.update(pw.getBytes());
      String pwHash = new String(msgDigest.digest());
      return pwHash;      
    } catch (NoSuchAlgorithmException e) {
      return null;
    }
  }
}