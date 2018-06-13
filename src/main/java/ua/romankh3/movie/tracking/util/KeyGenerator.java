package ua.romankh3.movie.tracking.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyGenerator {

    public static String generateSecretKey() throws Exception {
        Integer KEY_LENGTH = 60;
        int keySize = (int)((double) KEY_LENGTH * 6.0D / 8.0D + 0.5D) * 8;

        javax.crypto.KeyGenerator keyGen;
        try {
            String algorithm = "HmacSHA256";
            keyGen = javax.crypto.KeyGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e);
        }

        keyGen.init(keySize);
        SecretKey secretKey = keyGen.generateKey();
        byte[] key = secretKey.getEncoded();
        return Base64.encodeBase64URLSafeString(key).substring(0, KEY_LENGTH);
    }
}
