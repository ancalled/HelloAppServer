package kz.helloapp.test;


import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.security.*;

public class DSTest {

    public static void main(String[] args) throws Exception {
        /*
         * Generate an ECDSA signature
         */

        /*
         * Generate a key pair
         */

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        keyGen.initialize(256, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();

        /*
         * Create a Signature object and initialize it with the private key
         */

        Signature dsa = Signature.getInstance("SHA1withECDSA");

        dsa.initSign(priv);

        String str = "This is string to sign";
        byte[] strByte = str.getBytes("UTF-8");
        dsa.update(strByte);

        /*
         * Now that all the data to be signed has been read in, generate a
         * signature for it
         */

        byte[] realSig = dsa.sign();
        String base64 = new BASE64Encoder().encode(realSig);
//        System.out.println("Signature:\n" + new BigInteger(1, realSig).toString(16));

        System.out.println("base64 = " + base64);

        QRGeneratorTest.generateToFile(base64, "./out/test.png");
    }
}
