package com.sulongx.util.encrypy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.logging.stdout.StdOutImpl;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * @author sulongx
 * @version 1.0
 * @description TODO
 * @date 2021/12/8 14:50
 **/
public class EncryptByDES {


    /**
     * DES  ECB模式加密
     * @param key
     * @param data
     * @return
     */
    public static String encrypt(String key, String data){
        try {
            if (StringUtils.isBlank(key)) {
                key = "1qa2ws!@#$%";
            }
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secureKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, sr);
            byte[] bt = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(bt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /****
     * 获取请求签名
     * @param map
     * @return
     */
    public static String getSign(Map<String,Object> map){
        String content = map.get("app_id") + "phone.app.tet.invoice.list" + map.get("timestamp") + map.get("data");
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] byteArray = messageDigest.digest();
            StringBuilder md5StrBuff = new StringBuilder();
            for (byte b : byteArray) {
                if (Integer.toHexString(0xFF & b).length() == 1)
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & b));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & b));
            }
            return  Base64.encodeBase64String(md5StrBuff.toString().getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws ParseException {

        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2021-12-08 15:16:17"));

        Map<String,Object> data = new HashMap<String,Object>();
        data.put("plateNo", "青G55555");
        data.put("pageIndex", 1);
        data.put("pageSize", 10);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("app_id", "1468747915255137");
        map.put("sid", "2100966faa8f814e6f51b83919eb6b6a");
        map.put("method", "phone.app.tet.invoice.list");
        map.put("timestamp", "2021-12-08 15:27:42");
        map.put("data", encrypt("1468747915255137", JSON.toJSONString(data)));
        map.put("sign", getSign(map));


        System.out.println(JSON.toJSONString(map));
    }
}
