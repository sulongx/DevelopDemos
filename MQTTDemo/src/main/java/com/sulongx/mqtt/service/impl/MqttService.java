package com.sulongx.mqtt.service.impl;

import com.sulongx.mqtt.client.MyMqttClient;
import com.sulongx.mqtt.node.ClientNode;
import com.sulongx.mqtt.service.IMqttService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-10
 */
@Service
public class MqttService implements IMqttService {

    @Autowired
    private MyMqttClient myMqttClient;

    @Override
    public void registerNode(ClientNode node) {
        MqttClient mqttClient = myMqttClient.connect(node.getClientId(), node.getUserName(), node.getPassword());
        //监听主题
        try {
            mqttClient.subscribe(new String[]{"$admin/projectTest01/general/aaa"},new int[]{1});
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
