package com.study;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 2018/7/23.
 */
@Service
public class Producer {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void sendMsg(String queueName,String msg)  {
        amqpTemplate.convertAndSend(queueName, msg);
        System.out.println(">>>>>>>>>>>>>发送消息:" + msg);
   }

   public void sendObject(String queueName,Object object){
       amqpTemplate.convertAndSend(queueName,object);
       System.out.println(">>>>>>>>>>>>>发送Object:" + object);
   }

   public void sendTopic(String queueName,String msg){
       amqpTemplate.convertAndSend("exchange",queueName,msg);
       System.out.println(">>>>>>>>>>>>>exchange发送消息:" + msg);
   }

   public void sendFanout(String msg){
       amqpTemplate.convertAndSend("fanoutExchange","1",msg);//参数2队列将被忽略
       System.out.println(">>>>>>>>>>>>>fanout发送广播消息:" + msg);
   }

    public void sendMsgForAck(String queueName,String msg){
        amqpTemplate.convertAndSend(queueName,msg);
        System.out.println(">>>>>>>>>>>>>发送待答复消息:" + msg);
    }

   @RabbitListener(queues = "return.queue")
    public void reciveMsgReturn(String msg){
       System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<收到回复消息："+msg);
   }

    @RabbitListener(queues = "return.user.queue")
    public void reciveObjectReturn(UserVo vo){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<收到回复Object："+vo);
    }
}
