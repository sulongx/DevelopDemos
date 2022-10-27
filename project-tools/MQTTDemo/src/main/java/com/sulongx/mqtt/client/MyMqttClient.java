package com.sulongx.mqtt.client;

import com.sulongx.mqtt.monitor.CallBackHandle;
import com.sulongx.mqtt.node.ClientNode;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-10
 */
@Component
public class MyMqttClient {

    @Value("${mqtt.broker}")
    private String broker;

    public  MqttClient connect(String clientId,String userName,String password){
        MqttClient sampleClient = null;
        try {
            sampleClient = new MqttClient(broker, clientId, new MemoryPersistence());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            //设置回掉函数
            sampleClient.setCallback(new CallBackHandle(sampleClient));
            sampleClient.connect(connOpts);
        }catch (MqttException e){
            e.printStackTrace();
        }
        return sampleClient;
    }
}
