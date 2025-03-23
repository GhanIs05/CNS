import java.math.BigInteger;
import java.security.SecureRandom;

public class RSADemo {

    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    // Constructor to generate RSA keys of size N bits
    RSADemo(int N) {
        BigInteger p = BigInteger.probablePrime(N / 2, random);
        BigInteger q = BigInteger.probablePrime(N / 2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
        
        // Print the primes and phi
        System.out.println("Prime p = " + p);
        System.out.println("Prime q = " + q);
        System.out.println("Phi = " + phi);
        
        modulus = p.multiply(q); // N = p * q
        publicKey = new BigInteger("65537"); // Common public exponent
        privateKey = publicKey.modInverse(phi); // Calculate private key
        
        // Check if privateKey is valid
        if (privateKey == null) {
            throw new IllegalArgumentException("The chosen public key and phi are not coprime.");
        }
    }

    // Encrypt the message with the public key
    BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    // Decrypt the message with the private key
    BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    // String representation of the RSA keys and modulus
    public String toString() {
        return "Public Key = " + publicKey + "\n" +
               "Private Key = " + privateKey + "\n" +
               "Modulus = " + modulus;
    }

    public static void main(String[] args) {
        // Ensure the user has passed the key size
        if (args.length != 1) {
            System.out.println("Usage: java RSADemo <key_size_in_bits>");
            return;
        }

        int N = Integer.parseInt(args[0]); // Key size in bits
        RSADemo key = new RSADemo(N);
        System.out.println(key);

        // Create a random message (for simplicity, using a small number)
        BigInteger message = new BigInteger("12345");

        // Alternatively, you can use a string message like this:
        // String s = "test";
        // BigInteger message = new BigInteger(s.getBytes());

        // Encrypt the message
        BigInteger encrypted = key.encrypt(message);

        // Decrypt the message
        BigInteger decrypted = key.decrypt(encrypted);

        // Display results
        System.out.println("Message = " + message);
        System.out.println("Encrypted = " + encrypted);
        System.out.println("Decrypted = " + decrypted);
    }
}

