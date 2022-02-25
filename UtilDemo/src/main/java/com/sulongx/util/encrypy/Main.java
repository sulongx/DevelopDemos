package com.sulongx.util.encrypy;


import com.alibaba.fastjson.JSONObject;
import com.sulongx.util.http.HttpClientUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author sulongx
 * @version 1.0
 * @description TODO
 * @date 2022/2/21 14:12
 **/
public class Main {
    public static void main(String[] args) {
        aes128Test("67a3E0im0Wvh9skI", "E777EB0711FAC89D8D5B7A24C2642B78", "x8V737f2BPu1iDCV");
    }

    static void aes128Test(String secret, String hKey, String iv){
        String dataSecret = secret != null && !"".equals(secret) ? secret : AES128Util.secretGenerator();
        String sigSecret = hKey != null && !"".equals(hKey) ? hKey : HMacMD5Util.secretGenerator(32);
        String dataIv = iv != null && !"".equals(iv) ? iv : AES128Util.secretGenerator();

        System.out.println("数据密钥: " + dataSecret);
        System.out.println("签名密钥: " + sigSecret);
        System.out.println("数据向量: " + dataIv);

        String data = "{\n" +
                " \"operator_id\": \"MT123456\",\n" +
                " \"order_id\": \"20211215173527123\",\n" +
                " \"park_id\": \"PL-2106044059110862\",\n" +
                " \"plate\": \"京A55553\",\n" +
                " \"start_time\": \"2022-02-24 15:43:55\",\n" +
                " \"end_time\": \"2022-02-24 15:47:55\",\n" +
                " \"fee\": 100.5, // 非必须\n" +
                " \"station_id\": \"asd4wqe52asdga5sad\"\n" +
                "}";

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("OperatorID", "MT123456");
        dataMap.put("TimeStamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        dataMap.put("Seq", "0001");
        //数据加密
        String encryptData = AES128Util.encrypt(data, dataSecret, dataIv);
        dataMap.put("Data", encryptData);
        //签名
        String sig = HMacMD5Util.getHmacMd5Str(sigSecret, dataMap.get("OperatorID") + dataMap.put("Data", encryptData) + dataMap.get("TimeStamp") + dataMap.get("Seq"));
        dataMap.put("Sig", sig);

        System.out.println("加密后的数据: " + JSONObject.toJSONString(dataMap));

//        long time = System.currentTimeMillis();
//        //验证签名
//        String sigCheck = HMacMD5Util.getHmacMd5Str(sigSecret, dataMap.get("operator_id") + encryptData + dataMap.get("timestamp") + dataMap.get("seq"));
//        if(sig.equals(sigCheck)){
//            System.out.println("签名验证成功");
//            //解密后的数据
//            String decryptData = AES128Util.decrypt(dataMap.put("data", encryptData), dataSecret, dataIv);
//            System.out.println("解密后的数据: " + decryptData);
//        }else {
//            System.out.println("签名验证失败");
//        }
//        System.out.println("解密耗时: " + (System.currentTimeMillis() - time) + " ms");

        //发送数据
//        JSONObject jsonObject = HttpClientUtils.invokePostMethodWithMap("http://127.0.0.1:6082/ee/uploadSwappingInfo", dataMap);
//        System.out.println(jsonObject);
    }
}
