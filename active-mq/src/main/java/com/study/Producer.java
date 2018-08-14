package com.study;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * Created by liyang on 2018/7/21.
 * 生产者，除了发出消息外，还会接收反馈消息
 */
@Service
public class Producer {
    @Resource
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destinationName, String message) {
        System.out.println(">>>>>>>>>>>>>发送消息:" + message);
        jmsTemplate.convertAndSend(destinationName, message);
    }

    public void sendUser(Destination destinationName,UserVo vo){
        System.out.println(">>>>>>>>>>>>>发送User:" + vo);
        jmsTemplate.convertAndSend(destinationName,vo);
    }

    @JmsListener(destination = "return.queue")
    public  void receiveMsg(String text) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<收到回复消息："+text);
    }

    @JmsListener(destination = "return.user.queue")
    public  void receiveUser(UserVo vo) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<收到回复User："+vo);
    }

    @JmsListener(destination = "other.return.queue")
    public void receiveOtherQueue(String text){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<收到回复消息："+text);
    }
}
