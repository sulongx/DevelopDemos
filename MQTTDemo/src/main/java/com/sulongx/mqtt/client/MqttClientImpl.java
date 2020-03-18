package com.sulongx.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-03-17
 * @description
 */
public class MqttClientImpl implements IMqttClient {

    private MqttAsyncClient mqttAsyncClient;


    private void connect(String url,String userName,String password,String clientId) throws MqttException {
        mqttAsyncClient = new MqttAsyncClient(url,clientId);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(userName);
        connOpts.setPassword(password.toCharArray());
        mqttAsyncClient.connect();
    }


    public void publishMsg(String msg, String topic) throws MqttException {
        MqttMessage message = new MqttMessage(topic.getBytes());
        mqttAsyncClient.publish(topic, message);
    }


}
