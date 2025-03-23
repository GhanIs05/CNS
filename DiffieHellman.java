import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;
public class DiffieHellman {
    private static final SecureRandom random = new SecureRandom();
    private static BigInteger generatePrivateKey(BigInteger p) {
        return new BigInteger(p.bitLength() - 1, random);
    }
    private static BigInteger generatePublicKey(BigInteger g, BigInteger privateKey, BigInteger p) {
        return g.modPow(privateKey, p);
    }
    private static BigInteger computeSharedSecret(BigInteger otherPublicKey, BigInteger privateKey, BigInteger p) {
        return otherPublicKey.modPow(privateKey, p);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a large prime number p: ");
        BigInteger p = new BigInteger(scanner.nextLine());
        System.out.print("Enter the generator g (primitive root modulo p): ");
        BigInteger g = new BigInteger(scanner.nextLine());
        BigInteger privateKeyA = generatePrivateKey(p);
        BigInteger publicKeyA = generatePublicKey(g, privateKeyA, p);
        BigInteger privateKeyB = generatePrivateKey(p);
        BigInteger publicKeyB = generatePublicKey(g, privateKeyB, p);
        System.out.println("\nPublic Key A: " + publicKeyA);
        System.out.println("Public Key B: " + publicKeyB);
        BigInteger sharedSecretA = computeSharedSecret(publicKeyB, privateKeyA, p);
        BigInteger sharedSecretB = computeSharedSecret(publicKeyA, privateKeyB, p);
        System.out.println("\nShared Secret computed by A: " + sharedSecretA);
        System.out.println("Shared Secret computed by B: " + sharedSecretB);
        if (sharedSecretA.equals(sharedSecretB)) {
            System.out.println("The shared secrets match. The key exchange was successful!");
        } else {
            System.out.println("The shared secrets do not match. Something went wrong!");
        }
        scanner.close();
    }
}

