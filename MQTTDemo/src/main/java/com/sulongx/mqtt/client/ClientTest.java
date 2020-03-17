package com.sulongx.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * EMQX连接客户端测试
 * @author Sulongx
 */
public class ClientTest {
    public static void main(String[] args){
        String topic        = "$admin/projectTest01/30019061682176/aaa";
        String content      = "test message";
        int qos             = 2;
        String broker       = "tcp://5m.gaojulong.com:1883";
        String clientId     = "30019061682176";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            connOpts.setUserName("admin/projectTest01");
            connOpts.setPassword("423df21cb9864886".toCharArray());

            sampleClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {

                }

                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("消息接受成功:");
                    System.out.println(s);
                    System.out.println(mqttMessage.getPayload());
                }

                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });


            sampleClient.connect(connOpts);
            System.out.println("Connected");

//            System.out.println("Publishing message: "+content);
//            MqttMessage message = new MqttMessage(content.getBytes());
//            message.setQos(qos);
//            sampleClient.publish(topic, message);
//            System.out.println("Message published");
//            sampleClient.disconnect();
//            System.out.println("Disconnected");
            //System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
}
