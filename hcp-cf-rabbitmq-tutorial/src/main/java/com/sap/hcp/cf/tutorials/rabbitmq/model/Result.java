package com.sap.hcp.cf.tutorials.rabbitmq.model;

import java.util.List;

public class Result {

    private String status;

    private List<RabbitMessage> rabbitMessages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RabbitMessage> getRabbitMessages() {
        return rabbitMessages;
    }

    public void setRabbitMessages(List<RabbitMessage> rabbitMessages) {
        this.rabbitMessages = rabbitMessages;
    }

}
