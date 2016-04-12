package com.sap.hcp.cf.tutorials.rabbitmq;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sap.hcp.cf.tutorials.rabbitmq.model.RabbitMessage;
import com.sap.hcp.cf.tutorials.rabbitmq.model.Result;

@Controller
@RequestMapping("/")
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private RabbitTemplate template;

    private static final String QUEUE_NAME = "amqp-test";
    private static final String MESSAGE = "rabbitmq-test";

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Result onRootAccess() {

        RabbitMessage rabbitMessage = new RabbitMessage();

        log.info("Sending message: " + MESSAGE);
        template.convertAndSend(QUEUE_NAME, MESSAGE);
        rabbitMessage.setProducedMessage(MESSAGE);

        log.info("Receiving message: ");
        String msg = (String) template.receiveAndConvert(QUEUE_NAME);
        rabbitMessage.setConsumedMessage(msg);

        Result result = new Result();
        result.setStatus("Successfully connected to rabbit and produced a message and consumed the same message");
        List<RabbitMessage> rabbitMessages = new ArrayList<RabbitMessage>();
        rabbitMessages.add(rabbitMessage);
        result.setRabbitMessages(rabbitMessages);

        return result;
    }
}