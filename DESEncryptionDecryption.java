import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESEncryptionDecryption {
    public static void main(String[] args) throws Exception {
        System.out.print("Enter Text: ");
        Scanner sc = new Scanner(System.in);
        String textToEncrypt = sc.nextLine();
        System.out.println("Original Text: " + textToEncrypt);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56); 
        SecretKey secretKey = keyGenerator.generateKey();

        String encryptedText = encrypt(textToEncrypt, secretKey);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }
    
}
