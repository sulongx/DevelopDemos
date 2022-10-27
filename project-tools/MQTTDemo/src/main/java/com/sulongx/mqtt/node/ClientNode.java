package com.sulongx.mqtt.node;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-10
 */
public class ClientNode {

    private String clientId;

    private String userName;

    private String password;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
