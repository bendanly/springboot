package com.study;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * Created by liyang on 2018/7/21.
 * 消费者2，除了消费以外，还会反馈消息
 */
@Service
public class Consumer2 {
    @JmsListener(destination = "test.queue")
    @SendTo("return.queue")
    //注意和consumer对比差异，如果是双向消息，需要以String返回
    public String receiveQueue(String text) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<消费者2收到消息：" + text);
        return "消费者2已收到消息：" + text;
    }

    @JmsListener(destination = "user.queue")
    @SendTo("return.user.queue")
    public UserVo receiveUser(UserVo vo) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<消费者2收到User：" + vo);
        return vo;
    }
}
