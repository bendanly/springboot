package com.study;


import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by liyang on 2018/7/23.
 */
@Configuration
public class RabbitMq4Config {
    @Bean
    public Queue messageQueue() {
        return new Queue("message.queue");
    }

    @Bean
    public Queue userQueue(){
        return new Queue("user.queue");
    }

    @Bean
    public Queue returnMessageQueue(){
        return new Queue("return.queue");
    }

    @Bean
    public Queue returnUserQueue(){
        Queue q = new Queue("test",true,false,false);
        return new Queue("return.user.queue");
    }



    /////////////////////////////////配置交换机完成多消息队列转发/////////////////////////////////////////
    @Bean(name="messageA")
    public Queue queueMessageA() {
        return new Queue("topic.a");
    }

    @Bean(name="messageB")
    public Queue queueMessageB() {
        return new Queue("topic.b");
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }

    /////////////////////////////Topic转发模式，当exchange交换机匹配，消息会发到所有匹配的队列中，且只会被消费一次////////////////
    //如果发的是topic.a则绑定的两个队列都会收到
    //如果发的是topic.b则第二个绑定的队列会受到，第一个不匹配，不会收到
    @Bean
    Binding bindingExchangeMessageA(@Qualifier("messageA") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.a");
    }

    @Bean
    Binding bindingExchangeMessageB(@Qualifier("messageB") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");//*表示一个词,#表示零个或多个词
    }


    /////////////////////////////////////////////////////////////////////////
    @Bean(name="messageC")
    public Queue queueMessageC() {
        return new Queue("fanout.a");
    }

    @Bean(name="messageD")
    public Queue queueMessageD() {
        return new Queue("fanout.b");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");//配置广播路由器
    }
    ///////////////////////////广播消息Fanout Exchange形式，所有绑定的队列都会收到消息，不论是否匹配，且只会被消费一次////////////
    ///////////////////////////注意是广播到各个队列里，一个队列多个消费者的话，仍然只会有一个消费者得到消息///////////////////////

    @Bean
    Binding bindingExchangeMessageA2(@Qualifier("messageC") Queue queueMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeMessageB2(@Qualifier("messageD") Queue queueMessages, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessages).to(fanoutExchange);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    @Bean(name = "ack.queue")
    public Queue ackQueue(){
        return new Queue("ack.queue",true,false,true);
    }

    /**
     * 简单消息监听器。绑定了ack.queue队列和consumerWithAck消费者
     * @param connectionFactory
     * @param queue
     * @param channelAwareMessageListener
     * @return
     */
    @Bean
    SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                  @Qualifier("ack.queue") Queue queue,
                                                                  @Qualifier("consumerWithAck")ChannelAwareMessageListener channelAwareMessageListener){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueues(queue);
        container.setMessageListener(channelAwareMessageListener);
        return container;
    }
}
