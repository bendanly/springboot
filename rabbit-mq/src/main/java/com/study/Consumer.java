package com.study;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by liyang on 2018/7/23.
 */
@Service
public class Consumer {
    @RabbitListener(queues = "message.queue")
    @SendTo("return.queue")
    public String receiveMsg(String msg) throws IOException {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用1消费者1收到消息："+msg);
        return "应用1消费者1收到消息：" + msg;
    }
    @RabbitListener(queues = "user.queue")
    @SendTo("return.user.queue")
    public UserVo receiveObject(UserVo userVo){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用1消费者1收到Object："+userVo);
        return userVo;
    }

    @RabbitListener(queues = "topic.a")
    @SendTo("return.queue")
    public String receiveTopic(String msg){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用1消费者1收到topic.a："+msg);
        return "应用1消费者1收到topic.a：" + msg;
    }

    @RabbitListener(queues = "topic.b")
    @SendTo("return.queue")
    public String receiveTopic2(String msg){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用1消费者1收到topic.b："+msg);
        return "应用1消费者1收到topic.b：" + msg;
    }

    @RabbitListener(queues = "fanout.a")
    @SendTo("return.queue")
    public String receiveTopic3(String msg){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用1消费者1收到fanout.a："+msg);
        return "应用1消费者1收到fanout.a：" + msg;
    }

    @RabbitListener(queues = "fanout.b")
    @SendTo("return.queue")
    public String receiveTopic4(String msg){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用1消费者1收到fanout.b："+msg);
        return "应用1消费者1收到fanout.b：" + msg;
    }

}
