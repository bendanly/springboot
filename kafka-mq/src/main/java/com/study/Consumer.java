package com.study;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Created by liyang on 2018/8/2.
 */
@Service
public class Consumer {
    @KafkaListener(topics = {"msg.topic"})
    public void listen(ConsumerRecord<?, ?> record) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp+"<<<<<<<<<<<收到消息："+record.key()+record.value());
    }
}
