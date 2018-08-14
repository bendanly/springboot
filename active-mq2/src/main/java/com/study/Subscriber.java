package com.study;

import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import javax.jms.ConnectionFactory;

/**
 * Created by liyang on 2018/7/23.
 */
@Service
public class Subscriber {
    @JmsListener(destination = "topic.queue",containerFactory = "jmsTopicListenerCF")
    public void subcriber(String text){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用2订阅者收到消息："+text);
    }

}
