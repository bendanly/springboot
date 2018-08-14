package com.study;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by liyang on 2018/8/8.
 */
@Service
public class Consumer4Ack implements AcknowledgingMessageListener{
    @KafkaListener(topics = {"ack.topic"},containerFactory = "kafkaListenerContainerFactory2")
    public void onMessage(ConsumerRecord consumerRecord, Acknowledgment acknowledgment) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        double d = Math.random() * 10;
        System.out.println("roll->>>>>>>>>>>>>>>>" + d);
        if (d <= 5) {
            System.out.println(timestamp + "<<<<<<<<<<<模拟收到消息成功：" + consumerRecord.key() + consumerRecord.value());
            //acknowledgment.acknowledge();
        } else {
            System.out.println(timestamp + "<<<<<<<<<<<模拟收到消息失败：" + consumerRecord.key() + consumerRecord.value());
            //acknowledgment.acknowledge();

        }
    }

    @Override
    public void onMessage(Object o) {

    }
}
