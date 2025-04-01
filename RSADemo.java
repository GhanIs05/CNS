import java.math.BigInteger;
import java.security.SecureRandom;

public class RSADemo {

    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    RSADemo(int N) {
        BigInteger p = BigInteger.probablePrime(N / 2, random);
        BigInteger q = BigInteger.probablePrime(N / 2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));
        
        System.out.println("Prime p = " + p);
        System.out.println("Prime q = " + q);
        System.out.println("Phi = " + phi);
        
        modulus = p.multiply(q); // N = p * q
        publicKey = new BigInteger("65537"); 
        privateKey = publicKey.modInverse(phi); 
        
        if (privateKey == null) {
            throw new IllegalArgumentException("The chosen public key and phi are not coprime.");
        }
    }

    BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    public String toString() {
        return "Public Key = " + publicKey + "\n" +
               "Private Key = " + privateKey + "\n" +
               "Modulus = " + modulus;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java RSADemo <key_size_in_bits>");
            return;
        }

        int N = Integer.parseInt(args[0]); // Key size in bits
        RSADemo key = new RSADemo(N);
        System.out.println(key);

        BigInteger message = new BigInteger("12345");


        BigInteger encrypted = key.encrypt(message);

        BigInteger decrypted = key.decrypt(encrypted);

        System.out.println("Message = " + message);
        System.out.println("Encrypted = " + encrypted);
        System.out.println("Decrypted = " + decrypted);
    }
}

