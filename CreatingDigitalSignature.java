import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Scanner;
import java.io.*;
public class CreatingDigitalSignature {
 public static void main(String args[]) throws Exception {
 Scanner sc = new Scanner(System.in);
 System.out.println("Enter some text");
 String msg = sc.nextLine();

 KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

 keyPairGen.initialize(2048);

 KeyPair pair = keyPairGen.generateKeyPair();

 PrivateKey privKey = pair.getPrivate();
 PublicKey pubKey = pair.getPublic();
 System.out.println(privKey);
 System.out.println(pubKey);

 Signature sign = Signature.getInstance("SHA256withDSA");

 sign.initSign(privKey);
 byte[] bytes = "msg".getBytes();

 sign.update(bytes);

 byte[] signature = sign.sign();
 for(int i=0;i<signature.length;i++)
 System.out.print(" "+signature[i]);
System.out.println();

 sign.initVerify(pair.getPublic());
 sign.update(bytes);

 boolean bool = sign.verify(signature);

 if(bool) {
 System.out.println("Signature verified");
 } else {
 System.out.println("Signature failed");
 }
 }
}
