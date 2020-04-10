package com.sulongx.mqtt.monitor;

import com.sulongx.mqtt.client.MyMqttClient;
import org.eclipse.paho.client.mqttv3.*;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-10
 */
public class CallBackHandle implements MqttCallback {

    private MqttClient mqttClient;

    public CallBackHandle(MqttClient mqttClient){
        this.mqttClient = mqttClient;
    }

    public void connectionLost(Throwable throwable) {
        System.out.println("断开连接了");
    }

    public void messageArrived(String s, MqttMessage mqttMessage){
        System.out.println("--------------------------------------------------");
        System.out.println(s +  "  发来消息，" + "接受到消息：" + mqttMessage.toString());

        try {
            MqttMessage publicMessage = new MqttMessage();
            publicMessage.setPayload("你是吊毛".getBytes());
            publicMessage.setQos(1);
            mqttClient.publish("$admin/projectTest01/general/bbb",publicMessage);
            System.out.println("消息转发: " + "你是吊毛");
        } catch (MqttException e) {
            e.printStackTrace();
        }


        System.out.println("---------------------------------------------------");
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("消息发送成功");
    }
}
