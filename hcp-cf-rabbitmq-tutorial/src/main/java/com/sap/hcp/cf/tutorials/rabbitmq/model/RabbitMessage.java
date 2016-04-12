package com.sap.hcp.cf.tutorials.rabbitmq.model;

public class RabbitMessage {

    private String producedMessage;
    private String consumedMessage;
    
    public String getProducedMessage() {
        return producedMessage;
    }
    public void setProducedMessage(String producedMessage) {
        this.producedMessage = producedMessage;
    }
    public String getConsumedMessage() {
        return consumedMessage;
    }
    public void setConsumedMessage(String consumedMessage) {
        this.consumedMessage = consumedMessage;
    }


}
