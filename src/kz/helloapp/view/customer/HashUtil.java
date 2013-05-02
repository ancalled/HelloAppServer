package kz.helloapp.view.customer;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashUtil {


    public static String getMD5Hash(String data) {
        try {
            byte[] bytes = data.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytes);

            return DatatypeConverter.printBase64Binary(thedigest);
//            return new String(thedigest, "UTF-8");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String generateToken() {
        Random r = new Random(System.currentTimeMillis());
        return getMD5Hash(r.nextLong() + "");
    }


    public static void main(String[] args) {

        System.out.println(generateToken());
    }
}
