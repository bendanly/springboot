package com.study;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 2018/7/21.
 * 消费者1，模拟一般消费
 */
@Service
public class Consumer {
    @JmsListener(destination = "test.queue")
    public void receiveMsg(String text){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<消费者1收到消息："+text);
    }

    @JmsListener(destination = "user.queue")
    public void receiveUser(UserVo vo){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<消费者1收到User："+vo);
    }
}
