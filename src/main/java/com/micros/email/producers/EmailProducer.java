package com.micros.email.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.micros.email.configs.RabbitMQConfig;
import com.micros.email.dtos.EmailDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(EmailDto emailDto) throws Exception {
        System.out.println("Sending message...");
        ObjectWriter ow = new ObjectMapper().writer();
        String json = ow.writeValueAsString(emailDto);
        Message jsonMessage = MessageBuilder.withBody(json.getBytes())
            .andProperties(MessagePropertiesBuilder.newInstance().setContentType("application/json").build())
            .build();

        System.out.println(jsonMessage);
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.routingKey, jsonMessage);
    }
}
