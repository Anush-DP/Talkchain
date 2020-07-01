import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class StringUtil {
    /* Applies Sha256 to a string and returns a hash. */
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            /* Applies sha256 to our input */
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(0xff & elem);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* Counts number of leading zeroes in hash and returns it */
    public static int countZeroes(String hash) {
        int count = 0;
        for (char c : hash.toCharArray()) {
            if (c != '0') break;
            count++;
        }
        return count;
    }
}