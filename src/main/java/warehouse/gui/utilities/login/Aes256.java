package warehouse.gui.utilities.login;

import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.logging.Level;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.experimental.UtilityClass;
import warehouse.ErrorLogger;

/**
 * It takes a string, decrypts it, and returns the decrypted string.
 * This code was retrieved from
 * <a href="https://howtodoinjava.com/java/java-security/aes-256-encryption-decryption/">
 *   Java AES-256 Encryption and Decryption
 * </a>.
 *
 * @author Lokesh Gupta
 * @since 2022-01-25
 */
@UtilityClass
public class Aes256 {
  // The key used to encrypt and decrypt the password.
  private final String key = "bXlfc3VwZXJfc2VjcmV0X2tleQ==";
  // A salt.
  private final String salt = "c3NzaGhoaGhoaGhoaGghISEh";

  /**
   * It takes a string, decrypts it, and returns the decrypted string.
   *
   * @param strToDecrypt The string to decrypt
   * @return The decrypted string
   */
  public String decrypt(String strToDecrypt) {
    try {
      // The initialization vector.
      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      // Creating an initialization vector.
      IvParameterSpec ivspec = new IvParameterSpec(iv);
      // Creating a secret key factory.
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
      // Creating a key specification.
      KeySpec spec = new PBEKeySpec(
          new String(Base64.getDecoder().decode(key)).toCharArray(),
          new String(Base64.getDecoder().decode(salt)).getBytes(),
          65536,
          256
      );
      // Creating a secret key, a cipher, and initializing the cipher.
      SecretKey tmp = factory.generateSecret(spec);
      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
      // Decrypting the string.
      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    // Catching any exception that occurs and logging it.
    } catch (Exception e) {
      ErrorLogger.LOGGER.log(Level.SEVERE, String.format("Error while decrypting: %s", e));
    }
    // Returning null if an exception occurs.
    return null;
  }
}