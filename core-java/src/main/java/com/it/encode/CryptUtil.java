package com.it.encode;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// TODO
// https://github.corp.ebay.com/CS-Platform/cs-rest-clients/blob/master/csSwiftJossClient/src/main/java/com/ebay/raptor/swift/joss/cipher/CryptUtil.java
public class CryptUtil {

    public static String encryptAndEncode(String passphrase, String clearText) throws IllegalArgumentException {
        if (passphrase == null || passphrase.length() == 0) {
            throw new IllegalArgumentException("passphrase can not null or empty");
        }

        if (clearText == null || clearText.length() == 0) {
            throw new IllegalArgumentException("clearText can not null or empty");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA");
            digest.update(passphrase.getBytes());

            SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.ENCRYPT_MODE, key);
            byte[] ciphertext = aes.doFinal(clearText.getBytes());
            return hexEncoding(ciphertext);
        } catch (Exception e) {
            return null;
        }
    }

    private static String hexEncoding(byte[] raw) throws IllegalArgumentException {
        if (raw == null) {
            throw new IllegalArgumentException("should not be null");
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < raw.length; i++) {
            int first4bit = (raw[i] & 0xF0) >> 4;
            int second4bit = (raw[i] & 0x0F);
            sb.append(convertIntToHex(first4bit)).append(convertIntToHex(second4bit));
        }
        return sb.toString();
    }
}
