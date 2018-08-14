package com.study;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;

/**
 * Created by liyang on 2018/7/27.
 */
@Service
public class ConsumerWithAck implements ChannelAwareMessageListener {
    private double l;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        double l = Math.random() * 10;
        System.out.println("#############roll############"+l);
        if (l < 5) {
            //消息处理成功
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用2消费者2收到消息并处理消息成功：" + new String(message.getBody(), Charset.defaultCharset().name()));
        } else {
            //消息处理失败
            System.out.println("模拟业务耗时！10S 处理消息"+new String(message.getBody(), Charset.defaultCharset().name()));
            Thread.sleep(10000);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<应用2消费者2收到消息并处理消息失败：" + new String(message.getBody(), Charset.defaultCharset().name()));
        }
    }
}
