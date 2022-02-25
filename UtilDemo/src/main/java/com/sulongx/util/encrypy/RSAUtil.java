package com.sulongx.util.encrypy;

import com.alibaba.fastjson.JSON;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * RSA公钥/私钥/签名工具包
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * @author BadBoyHH
 * @Date 2019-03-20
 * @version 1.0
 */
public class RSAUtil {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /** */
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** */
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * BASE64解密
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decode(key);
    }

    /**
     * BASE64加密
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64.encode(key);
    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param content 已加密数据
     * @param privateKey 私钥(BASE64编码)
     */
    public static String sign(String content, String privateKey) throws Exception {
        byte[] keyBytes = decryptBASE64(privateKey);
        byte[] data = content.getBytes(StandardCharsets.UTF_8);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param content 源数据
     * @param privateKey 私钥(BASE64编码)
     */
    public static String encryptByPrivateKey(String content, String privateKey)
        throws Exception {
        byte[] keyBytes = decryptBASE64(privateKey);
        byte[] data = content.getBytes(StandardCharsets.UTF_8);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptBASE64(encryptedData);
    }

    /** */
    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * @return
     * @throws Exception
     */
    public static boolean verify(String content, String publicKey, String sign)
        throws Exception {
        byte[] keyBytes = decryptBASE64(publicKey);
        byte[] data = content.getBytes(StandardCharsets.UTF_8);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(decryptBASE64(sign));
    }

    /** */
    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String content, String publicKey)
        throws Exception {
        byte[] keyBytes = decryptBASE64(publicKey);
        byte[] encryptedData = decryptBASE64(content);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        out.close();
        return out.toString("utf-8");
    }



    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqfYuBdqREjvaK+dYFdUK2NQDwg65vi6xFTSjpUAstUIohLcBCqhRkFsHQuTGzTXvkamRzkLXqYsuIl+DawUoNVO9ZIRYUS9+tADMtOlp4w/UTyrVUnBPGL+d4sa1mTVFbBwCrOJ4GU7CNGOkkl25zlhqXQo5mEUrzFi3CwUwdZQIDAQAB";
    private static String primaryKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKp9i4F2pESO9or51gV1QrY1APCDrm+LrEVNKOlQCy1QiiEtwEKqFGQWwdC5MbNNe+RqZHOQtepiy4iX4NrBSg1U71khFhRL360AMy06WnjD9RPKtVScE8Yv53ixrWZNUVsHAKs4ngZTsI0Y6SSXbnOWGpdCjmYRSvMWLcLBTB1lAgMBAAECgYANcmN7bZEdOy1k3rmrqoj9eAa/8ZLjh1+qA6DzbfXDuDN81G/ykhmTn1vgC4tI+haIeH+9wa8ncm1dM6Vve4lMjJGIvXHPu1/t8VUOLLVllPKX44+7pq1jltPVT5p8c96zY6nv6/BO4i1SGLCcY5GROVRmuhSB46u6WFsyymGwgQJBAPJhNKVz/bTpED05EvWSYLN7hWvSvdARnYZiaeB78paiPsXVTZYyeyTUhbzNf74cPIpPTobk21lNxUMUiXM2JMUCQQC0Einh4Igkxemc8OpKJtKpTa8mMlC/LeCKpOVw+WLCEUITC2R5phedZDY1/MkLJYDYTjplbQXIM/Q4v5++4eAhAkBOrdNDKiXfRhCpbw+Ce+O8H4qt95j6I924s9Wes12KHFy4J4eqC/UBfaN0KFkhQ1U7qIbbLwstMBuqrM35gkfpAkEAj8RAtBp0GAf7cFXYeMNuRcXEJRbe6QRT1d25Ye16L79fpZze51D4Yaz0qz3btY6HxCsQ7JGSoDj6r6QuglQMgQJALD5eeDIwJc+Lx27jcCaBzjxIwPaJYRLwVRV1qGZ56jQlHj5yVxTnhVyw2e26hI13d7Q+HZBuW4IOWDrXwstGaw==";


    public static void main(String[] args) {
        try {
            String data = null;
            //车辆签约信息下发
            //data = departurePayInfo();
            //车辆账单信息查询
            //data = fee();
            //账单支付扣费结果通知
            data = payResult();


            Map<String, Object> dataMap = new HashMap<>();
            String cipher = RSAUtil.encryptByPrivateKey(data, primaryKey);
            String sign = RSAUtil.sign(cipher, primaryKey);
            dataMap.put("cipher", cipher);
            dataMap.put("sign", sign);
            dataMap.put("requestId", 123456L);
            dataMap.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            System.out.println(JSON.toJSONString(dataMap));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }


    private static String departurePayInfo(){
        Map<String, Object> data = new HashMap<>();
        data.put("plateNo", "豫U88888");
        data.put("plateColor", 1);
        data.put("parkingCode", "01273");
        data.put("noSense", 0);
        data.put("moneyLimit", 100);
        data.put("departurePay", 1);

        return JSON.toJSONString(data);
    }


    private static String fee(){
        Map<String, Object> data = new HashMap<>();
        data.put("plateNo", "豫U88888");
        data.put("plateColor", 1);
        data.put("parkingCode", "01273");

        return JSON.toJSONString(data);
    }

    private static String payResult(){
        Map<String, Object> data = new HashMap<>();
        data.put("plateNo", "豫U88888");
        data.put("billID", "1321564165");
        data.put("billTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        data.put("shouldPay", 10);
        data.put("payStatus", 1);
        data.put("actualPay", 10);
        data.put("discountAmount", 0);
        data.put("dealTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        data.put("orderCode", "4165464646");
        data.put("paidType", 3);
        data.put("payType", 101);

        return JSON.toJSONString(data);
    }
}
