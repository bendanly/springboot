package com.study;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by liyang on 2018/7/23.
 */
@Configuration
public class RabbitMq4Config {
    @Bean
    public Queue messageQueue() {
        return new Queue("message.queue");
    }

    @Bean
    public Queue userQueue(){
        return new Queue("user.queue");
    }

    @Bean
    public Queue returnQueue(){
        return new Queue("return.queue");
    }

    @Bean
    public Queue returnUserQueue(){
        return new Queue("return.user.queue");
    }

    ////////////////////////////////////////////////////////////////
    @Bean(name = "ack.queue")
    public Queue ackQueue(){
        return new Queue("ack.queue",true,false,true);
    }

    @Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                  @Qualifier("ack.queue") Queue queue,
                                                                  @Qualifier("consumerWithAck")ChannelAwareMessageListener channelAwareMessageListener){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueues(queue);
        container.setMessageListener(channelAwareMessageListener);
        return container;
    }
}
