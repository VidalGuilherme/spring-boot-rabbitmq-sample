package com.micros.email.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micros.email.consumers.EmailConsumer;
import com.micros.email.dtos.EmailDto;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static String topicExchangeName = "micros-email-exchange";

    public static String queueName = "micros-email";

    public static String routingKey = "micros.email.send";

    @Bean
    Queue queue(){
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with ("micros.email.*");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setConnectionFactory(connectionFactory);
        listenerAdapter.setMessageConverter(messageConverter());
        container.setMessageListener(listenerAdapter);

        container.setQueueNames(queueName);

        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(EmailConsumer consumer) {
        return new MessageListenerAdapter(consumer, "receiveMessage");
    }

    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setDefaultType(EmailDto.class);
        Jackson2JsonMessageConverter jsonConverter =  new Jackson2JsonMessageConverter(objectMapper);
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }

}
