package com.sulongx.mqtt.controller;

import com.sulongx.mqtt.node.ClientNode;
import com.sulongx.mqtt.service.IMqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-10
 */
@RestController
@RequestMapping("/sulongx/iot/mqtt")
public class MqttController {

    @Autowired
    private IMqttService mqttService;



    @PostMapping("/registerNode")
    public ResponseEntity<String> registerNode(@RequestBody ClientNode node){
        mqttService.registerNode(node);
        return ResponseEntity.ok("节点注册成功!");
    }
}
