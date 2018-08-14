package com.study;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 2018/7/21.
 */
@Service
public class Subscriber {
    @JmsListener(destination = "topic.queue", containerFactory = "jmsTopicListenerCF")
    public void subcriber(String text) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<订阅者收到消息：" + text);
    }
}
