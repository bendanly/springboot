package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 2018/8/3.
 */
@Service
public class SendMessageDemo {
    @Autowired
    private  Producer producer;

    public void sendMsg() throws InterruptedException {
        for(int i=0;i<10;i++){
            producer.sendMsg(i%5+"","this is a kafka message!->"+i);
            Thread.sleep(100);
        }
    }

    public void sendMsgAck() throws InterruptedException {
        for(int i=0;i<10;i++){
            producer.sendMsgAck(i%5+"","this is a kafka message for ack!->"+i);
            Thread.sleep(100);
        }
    }
}
