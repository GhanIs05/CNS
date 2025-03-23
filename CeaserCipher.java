import java.util.Scanner;

public class CeaserCipher
{
    // Encrypts text using a shift of s
    public static StringBuffer encrypt(String text, int s)
    {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = (char)(((int)text.charAt(i) + s - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)text.charAt(i) + s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result;
    }
     public static StringBuffer decrypt(String text, int s)
    {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                // Decrypt upper-case letters by shifting backwards
                char ch = (char)(((int)text.charAt(i) - s - 65 + 26) % 26 + 65);
                result.append(ch);
            }
            else
            {
                // Decrypt lower-case letters by shifting backwards
                char ch = (char)(((int)text.charAt(i) - s - 97 + 26) % 26 + 97);
                result.append(ch);
            }
        }
        return result;
    }

    // Driver code
    public static void main(String[] args)
    {
        // Create scanner object for input
        Scanner sc = new Scanner(System.in);

        // Accepting user input for text and shift
        System.out.print("Enter the text to encrypt: ");
        String text = sc.nextLine();

        System.out.print("Enter the shift value: ");
        int s = sc.nextInt();

        // Close the scanner
        sc.close();

        // Print the results
        System.out.println("Text  : " + text);
        System.out.println("Shift : " + s);
        System.out.println("Cipher: " + encrypt(text, s));
        System.out.println("Decrypt: " + decrypt(text, s));
    }
}

