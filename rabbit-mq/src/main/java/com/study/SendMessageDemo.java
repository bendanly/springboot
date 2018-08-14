package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 2018/7/23.
 */
@Service
public class SendMessageDemo {
    @Autowired
    private Producer producer;

    public void sendMsg() throws InterruptedException {
        for(int i =0;i<10;i++){
            producer.sendMsg("message.queue","this is a rabbitmq message!->"+i);
            Thread.sleep(100);
        }
    }

    public void sendUser() throws InterruptedException {
        for(int i =0;i<10;i++){
            UserVo vo = new UserVo();
            vo.setUserName("name"+i);
            vo.setPassword(""+i);
            producer.sendObject("user.queue",vo);
            Thread.sleep(100);
        }
    }

    /**
     * 这里只模拟发送topic.a的消息
     * @throws InterruptedException
     */
    public void sendTopic() throws InterruptedException {
        for(int i=0;i<10;i++){
            producer.sendTopic("topic.a","this is a rabbitmq topic messagea!->"+i);
            Thread.sleep(100);
        }
    }

    public void sendFanout() throws InterruptedException {
        for(int i =0;i<1;i++){
            producer.sendFanout("this is a rabbitmq fanout message!->"+i);
            Thread.sleep(100);
        }
    }

    public void sendMsgForAck() throws InterruptedException {
        for(int i =0;i<10;i++){
            producer.sendMsgForAck("ack.queue","this is a rabbitmq message for ack!->"+i);
            Thread.sleep(100);
        }
    }
}
