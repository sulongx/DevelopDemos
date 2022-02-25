package com.sulongx.util.encrypy;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * @author sulongx
 * @version 1.0
 * @description AES 128位加解密
 * @date 2022/2/21 10:02
 **/
public class AES128Util {

    /***
     * 加密
     * @param data 加密内容
     * @param secret 密钥
     * @param dataIv 数据向量
     * @return
     */
    public static String encrypt(String data, String secret, String dataIv){
        String result = null;
        try {
            if(secret == null || "".equals(secret) || secret.length() != 16){
                return null;
            }
            byte[] raw = secret.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            //算法/模式/补码方式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(dataIv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            byte[] encrypted = cipher.doFinal(data.getBytes());
            result = new BASE64Encoder().encode(encrypted);
            result = result.replaceAll("\r", "").replaceAll("\n", "");
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String decrypt(String data, String secret, String dataIv){
        String result = null;
        try {
            if(secret == null || "".equals(secret) || secret.length() != 16){
                return null;
            }
            byte[] raw = secret.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            //算法/模式/补码方式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(dataIv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(data);
            byte[] decrypted = cipher.doFinal(decodeBuffer);
            result = new String(decrypted, StandardCharsets.UTF_8);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static String secretGenerator(){
        StringBuilder secretBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for(int i = 0; i < 16; i ++){
            int type = random.nextInt(3);
            switch (type){
                case 0:
                    secretBuilder.append(random.nextInt(10));
                    break;
                case 1:
                    secretBuilder.append((char) (random.nextInt(25) + 65));
                    break;
                case 2:
                    secretBuilder.append((char) (random.nextInt(25) + 97));
                    break;
            }
        }
        return secretBuilder.toString();
    }

}
