package com.it.patterns.simpleFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 * KeyGenerator apply to simple factory pattern
 * 
 * @since 18.03.26
 */
public class DESEncrypt {

    // //加密算法类型，可设置为DES、DESede、AES等字符串
    private static final String cipherType = "DESede";

    // 设置密钥长度
    private static int keyLength = 112;
    private static Cipher cp = null;
    private static SecretKey key = null;
    static {
        try {
        KeyGenerator kenGen = KeyGenerator.getInstance(cipherType);
        // init key generator
        kenGen.init(keyLength);

            // 生成密钥
            key = kenGen.generateKey();
        byte[] keyBytes = key.getEncoded();
            System.out.println("generated key is");
            for (int i = 0; i < keyBytes.length; i++) {
                System.out.print(keyBytes[i] + ",");
            }
            System.out.println();
            // 创建密码器
            cp = Cipher.getInstance(cipherType);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            System.err.println(e);
        }
    }

    public static byte[] encode(String str) {
        System.out.println();
        System.out.println("start encoding...");
        String encodedStr = null;
        byte[] encodedBytes = null;
        try {

            // init cipher
            cp.init(Cipher.ENCRYPT_MODE, key);

            // get bytes of to be encoding String
            byte[] strBytes = str.getBytes("utf-8");

            // encode
            encodedBytes = cp.doFinal(strBytes);
            System.out.println("encodedBytes is ");
            for (int i = 0; i < encodedBytes.length; i++) {
                System.out.print(encodedBytes[i] + ",");
            }
            System.out.println();
            encodedStr = new String(encodedBytes, Charset.forName("UTF-8"));
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("String encoded,which is " + encodedStr);
        return encodedBytes;

    }

    public static String decode(byte[] code) {
        System.out.println("to be decoded String.");
        String decodeStr = null;
        try {
            cp.init(Cipher.DECRYPT_MODE, key);
            byte[] decodebytes = cp.doFinal(code);
            System.out.println("解密后的字符串对应的字节码是：");
            for (int i = 0; i < decodebytes.length; i++) {
                System.out.print(decodebytes[i] + ",");
            }
            System.out.println();
            decodeStr = new String(decodebytes);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {

            e.printStackTrace();
        }
        System.out.println("decoded String,it is " + decodeStr);
        return decodeStr;
    }

    public static void main(String[] args) {
        DESEncrypt.decode(DESEncrypt.encode("Andy wang"));
    }
}
