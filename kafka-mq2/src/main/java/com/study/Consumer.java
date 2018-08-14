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
    /**
     * 注意配置文件，这里用的分组是test2，和另一个工程test区分开来，这样两边的消费者都会同时消费消息。如果分组一样，只有一个消费者能消费消息。且后加入的消费者霸占消息，排斥现前加入的消费者
     * @param record
     */
    @KafkaListener(topics = {"msg.topic"})
    public void listen(ConsumerRecord<?, ?> record) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp+"<<<<<<<<<<<应用2收到消息："+record.key()+record.value());
    }
}
