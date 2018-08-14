package com.study;

import org.apache.kafka.common.internals.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import java.sql.Timestamp;

/**
 * Created by liyang on 2018/8/2.
 */
@Service
public class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;


    public void sendMsg(String key, String message) {
        //kafkaTemplate.send("msg.topic",message);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("msg.topic", key, message);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //System.out.println(timestamp + ">>>>>>>>>>>>发送消息：" + message);

        //发送成功回调
        SuccessCallback<SendResult<String, String>> successCallback = new SuccessCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                //成功业务逻辑
                System.out.println(timestamp + ">>>>>>>>>>>>消息发送成功：" + result.getProducerRecord().value());
            }
        };
        //发送失败回调
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                //失败业务逻辑
                System.out.println(timestamp + ">>>>>>>>>>>>消息发送失败：" + ex.getMessage());
            }
        };
        listenableFuture.addCallback(successCallback, failureCallback);
    }

    public void sendMsgAck(String key, String message) {
        kafkaTemplate.send("ack.topic", key, message);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp + ">>>>>>>>>>>>发送待确认消息：" + message);
    }
}
