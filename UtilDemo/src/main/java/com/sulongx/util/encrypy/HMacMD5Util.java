package com.sulongx.util.encrypy;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author sulongx
 * @version 1.0
 * @description HMAC-MD5签名算法
 * @date 2022/2/21 10:28
 **/
public class HMacMD5Util {

    private static final String[] chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7" , "8", "9", "A", "B", "C", "D", "E", "F"};

    public static String getHmacMd5Str(String secret, String data){
        String result = null;
        try {
            byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] hmacMd5bytes = getHMacMD5Bytes(secretBytes, dataBytes);
            StringBuilder md5StrBuff = new StringBuilder();
            for(int i = 0; i < hmacMd5bytes.length; i ++){
                if(Integer.toHexString(0xFF & hmacMd5bytes[i]).length() == 1){
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & hmacMd5bytes[i]));
                }else {
                    md5StrBuff.append(Integer.toHexString(0xFF & hmacMd5bytes[i]));
                }
            }
            result = md5StrBuff.toString().toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param secret
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static byte[] getHMacMD5Bytes(byte[] secret, byte[] data) throws NoSuchAlgorithmException {
        int length = 64;
        byte[] ipad = new byte[length];
        byte[] opad = new byte[length];
        for(int i = 0; i < length; i ++){
            ipad[i] = 0x36;
            opad[i] = 0x5c;
        }
        byte[] actualKey = secret;
        byte[] keyArr = new byte[length];

        //如果密钥长度大于64,则使用哈希算法,计算其摘要
        if(secret.length > length){
            actualKey = md5(secret);
        }
        for(int i = 0; i < actualKey.length; i ++){
            keyArr[i] = actualKey[i];
        }
        //密钥长度不足64位的,用0补齐
        if(actualKey.length < length){
            for(int i = actualKey.length; i < keyArr.length; i ++){
                keyArr[i] = 0x00;
            }
        }
        //密钥和ipad进行异或运算
        byte[] kIpadXorResult = new byte[length];
        for(int i = 0; i < length; i ++){
            kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
        }

        //将待加密数据追加到后面
        byte[] firstAppendResult = new byte[kIpadXorResult.length + data.length];
        for(int i = 0; i < kIpadXorResult.length; i ++){
            firstAppendResult[i] = kIpadXorResult[i];
        }
        for(int i = 0; i < data.length; i ++){
            firstAppendResult[i + keyArr.length] = data[i];
        }

        //使用哈希算法获取上一步计算结果的摘要
        byte[] firstHashResult = md5(firstAppendResult);

        //密钥和opad进行异或运算
        byte[] kOpadXorResult = new byte[length];
        for(int i = 0; i < length; i ++){
            kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
        }

        //将第一次计算结果摘要追加到后面
        byte[] secondAppendResult = new byte[kOpadXorResult.length + firstHashResult.length];
        for(int i = 0; i < kOpadXorResult.length; i  ++){
            secondAppendResult[i] = kOpadXorResult[i];
        }
        for(int i = 0; i < firstHashResult.length; i ++){
            secondAppendResult[i + keyArr.length] = firstHashResult[i];
        }

        //对第二次计算结果数据进行哈希运算后返回
        return md5(secondAppendResult);
    }

    public static String secretGenerator(int length){
        StringBuilder secretBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for(int i = 0; i < length; i ++){
            secretBuilder.append(chars[random.nextInt(chars.length)]);
        }
        return secretBuilder.toString();
    }

    private static byte[] md5(byte[] str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str);
        return md5.digest();
    }

}
