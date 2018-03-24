package com.it.encode;


/**
 * TODO: unit test
 * 
 * @author ke.wang@hpe.com
 */
public class B64EncodeUtil {

    // 需要JDK1.7
    // encode func
    public static String encode(String s) {
        if (s == null)
            return null;
        return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
    }

    // 将 BASE64 编码的字符串 s 进行解码 解密
    public static String decode(String s) {
        if (s == null)
            return null;
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    public static String encodeToString(Object ming) {
        return B64EncodeUtil.encode(B64EncodeUtil.encode(B64EncodeUtil.encode((String) ming)));
    }

    public static String decodeToString(String an) {
        return B64EncodeUtil.decode(B64EncodeUtil.decode(B64EncodeUtil.decode(an)));
    }

    public static void main(String[] args) {
        String a = encodeToString("111k_jkljlj[l].txt".toString());
        System.out.println(a);// 加密
        System.out.println(decodeToString(a));// 解密
    }
}
