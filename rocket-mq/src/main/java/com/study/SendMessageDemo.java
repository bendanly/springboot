package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 2018/8/2.
 */
@Service
public class SendMessageDemo {
    @Autowired
    private Producer producer;

    public void sendMsg(){
        for(int i=0;i<10;i++){
            producer.sendMsg("this is a rocket message!->"+i);
        }
    }
}
