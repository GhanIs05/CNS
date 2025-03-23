 import java.util.Scanner;

public class OTP {

    public static String stringEncryption(String text, String key) {
        String cipherText = "";
        int cipher[] = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            cipher[i] = text.charAt(i) - 'A' + key.charAt(i) - 'A';
        }

        for (int i = 0; i < key.length(); i++) {
            if (cipher[i] > 25) {
                cipher[i] = cipher[i] - 26;
            }
        }

        for (int i = 0; i < key.length(); i++) {
            int x = cipher[i] + 'A';
            cipherText += (char)x;
        }

        return cipherText;
    }

    public static String stringDecryption(String s, String key) {
        String plainText = "";
        int plain[] = new int[key.length()];

        for (int i = 0; i < key.length(); i++) {
            plain[i] = s.charAt(i) - 'A' - (key.charAt(i) - 'A');
        }

        for (int i = 0; i < key.length(); i++) {
            if (plain[i] < 0) {
                plain[i] = plain[i] + 26;
            }
        }

        for (int i = 0; i < key.length(); i++) {
            int x = plain[i] + 'A';
            plainText += (char)x;
        }

        return plainText;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the plain text: ");
        String plainText = sc.nextLine();

        System.out.print("Enter the key: ");
        String key = sc.nextLine();

        sc.close();

        String encryptedText = stringEncryption(plainText.toUpperCase(), key.toUpperCase());

        System.out.println("Cipher Text - " + encryptedText);

        System.out.println("Message - " + stringDecryption(encryptedText, key.toUpperCase()));
    }
}

