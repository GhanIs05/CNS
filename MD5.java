import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// Java program to calculate MD5 hash value
public class MD5 {
public static String getMd5(String input)
{
try {
MessageDigest md = MessageDigest.getInstance("MD5");

byte[] messageDigest = md.digest(input.getBytes());
BigInteger no = new BigInteger(1, messageDigest);
String hashtext = no.toString(16);
while (hashtext.length() < 32) {
hashtext = "0" + hashtext;
}
return hashtext;
}
catch (NoSuchAlgorithmException e) {
throw new RuntimeException(e);
}
}
public static void main(String args[]) throws NoSuchAlgorithmException
{
String s = "VVIT";
System.out.println("Your HashCode Generated by MD5 is: " + getMd5(s));
}
}
