package com.sulongx.mqtt.service;

import com.sulongx.mqtt.node.ClientNode;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-10
 */
public interface IMqttService {

    /**
     * 注册节点
     * @param node
     */
    void registerNode(ClientNode node);
}
