package ru.kpfu.itis.app.utils;

import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */

public class Encoder {

    public static byte[] hmacSHA256(String data, byte[] key) throws Exception {
        String algorithm="HmacSHA256";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(data.getBytes("UTF8"));
    }

    public static String hexString(byte[] data) {
        return Hex.encodeHexString(data);
    }

    public static String base64String(byte[] data) {
        return (new BASE64Encoder()).encode(data);
    }
}
