package com.study;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * Created by liyang on 2018/7/24.
 */
@EnableJms
@Configuration
public class ActiveMQ4Config {

    @Bean(name = "jmsTopicListenerCF")
    public DefaultJmsListenerContainerFactory jmsTopicListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1");
        factory.setPubSubDomain(true);
        return factory;
    }


    ///////////////////////////////////////////////////////////////////////////////
    //下列几个方法会覆盖默认的消息工厂对象，注释掉也可以运行，但是是用默认工厂。
    //重点
    @Bean
    public RedeliveryPolicy redeliveryPolicy(){
        RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
        //是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        //重发次数,默认为6次   这里设置为10次
        redeliveryPolicy.setMaximumRedeliveries(10);
        //重发时间间隔,默认为1秒
        redeliveryPolicy.setInitialRedeliveryDelay(1);
        //第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        redeliveryPolicy.setBackOffMultiplier(2);
        //是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        //设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
        return redeliveryPolicy;
    }
    @Bean
    public ActiveMQConnectionFactory factory(@Value("${spring.activemq.broker-url}")String url,RedeliveryPolicy redeliveryPolicy){
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", url);
        factory.setRedeliveryPolicy(redeliveryPolicy);
        factory.setTrustAllPackages(true);//这里和application.properties的配置效果相同
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory factory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDeliveryMode(2);//设置持久化，1 非持久， 2 持久化
        jmsTemplate.setConnectionFactory(factory);

        /**
         SESSION_TRANSACTED = 0  事物提交并确认
         AUTO_ACKNOWLEDGE = 1    自动确认
         CLIENT_ACKNOWLEDGE = 2    客户端手动确认
         DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
         INDIVIDUAL_ACKNOWLEDGE = 4    单条消息确认 activemq 独有
         */
        jmsTemplate.setSessionAcknowledgeMode(4);//消息确认模式
        return jmsTemplate;
    }

    @Bean("jmsListener")
    public DefaultJmsListenerContainerFactory listener(ActiveMQConnectionFactory factory){
        DefaultJmsListenerContainerFactory listener = new DefaultJmsListenerContainerFactory();
        listener.setConnectionFactory(factory);
        listener.setConcurrency("1-10");//设置连接数
        listener.setRecoveryInterval(1000L);//重连间隔时间
        listener.setSessionAcknowledgeMode(4);
        return listener;
    }

}
