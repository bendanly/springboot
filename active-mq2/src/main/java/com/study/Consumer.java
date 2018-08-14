package com.study;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 2018/7/23.
 */
@Service
public class Consumer {

    @JmsListener(destination = "test.queue")
    @SendTo("return.queue")
    public String receiveQueue(String msg){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用2消费者1收到消息："+msg);
        return "应用2消费者1已收到消息："+msg;
    }

    @JmsListener(destination = "other.queue")
    @SendTo("return.queue")
    public String receiveOtherQueue(String msg){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用2消费者1收到消息："+msg);
        return "应用2消费者1已收到消息："+msg;
    }

    @JmsListener(destination = "user.queue")
    public void receiveUser(UserVo vo){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用2消费者1收到User："+vo);
    }
}
