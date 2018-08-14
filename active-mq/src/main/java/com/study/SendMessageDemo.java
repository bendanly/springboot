package com.study;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * Created by liyang on 2018/7/23.
 */
@Service
public class SendMessageDemo {
    @Autowired
    private Producer producer;

    public void sendMsg() throws InterruptedException {
        //普通消息
        Destination destination = new ActiveMQQueue("test.queue");

        for (int i = 0; i < 50; i++) {
            producer.sendMessage(destination, "this is a nomarl message!->" + i);
            Thread.sleep(100);
        }
        //订阅消息
        Destination destinationTopic = new ActiveMQTopic("topic.queue");
        for (int i = 0; i < 10; i++) {
            producer.sendMessage(destinationTopic, "this is a topic message->" + i);
        }
        //专门给其他应用发10个普通消息
        Destination destination4Other = new ActiveMQQueue("other.queue");

        for (int i = 0; i < 10; i++) {
            producer.sendMessage(destination4Other, "this is a nomarl message for other application!->" + i);
            Thread.sleep(100);
        }

    }
    public void sendUser(){
        //object消息
        Destination destinationUser = new ActiveMQQueue("user.queue");

        for(int i=0;i<10;i++){
            UserVo vo = new UserVo();
            vo.setUserName("user"+i);
            vo.setPassword(""+i);
            producer.sendUser(destinationUser,vo);
        }
    }
}
