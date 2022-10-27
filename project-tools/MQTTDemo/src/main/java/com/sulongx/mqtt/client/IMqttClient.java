package com.sulongx.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-03-17
 * @description
 */
public interface IMqttClient {
    /**
     * 发布消息内容体到指定主题
     * @param msg 消息内容体
     * @param topic 主题
     */
    void publishMsg(String msg,String topic) throws MqttException;
}
