package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开始菜单里，启动rabbit服务
 * rabbit控制台http://localhost:15672
 * 模拟发送一般消息http://localhost:8883/sendmsg
 * 模拟发送对象消息http://localhost:8883/senduser
 * 模拟通过exchange的topic模式，发送多队列消息http://localhost:8883/sendtopic
 * 模拟通过exchange的fanout模式，实现所有队列发送消息http://localhost:8883/sendfanout
 * 模拟消费者消费确认模式，如果有消费者消息处理失败，会重新发送消息http://localhost:8883/sendmsgack
 */
@SpringBootApplication
@RestController
public class RabbitMqApplication {
	@Autowired
	private SendMessageDemo sendMessageDemo;

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqApplication.class, args);
	}

	@RequestMapping(value = "/sendmsg")
	public String sendMsg() throws InterruptedException {
		sendMessageDemo.sendMsg();
		return "sendMsg";
	}

	@RequestMapping(value = "/senduser")
	public String sendUser() throws InterruptedException {
		sendMessageDemo.sendUser();
		return "sendUser";
	}

	@RequestMapping(value = "/sendtopic")
	public String sendTopic() throws InterruptedException {
		sendMessageDemo.sendTopic();
		return "sendTopic";
	}

	@RequestMapping(value = "/sendfanout")
	public String sendFanout() throws InterruptedException {
		sendMessageDemo.sendFanout();
		return "sendFanout";
	}

	@RequestMapping(value = "/sendmsgack")
	public String sendMsgForAck() throws InterruptedException {
		sendMessageDemo.sendMsgForAck();
		return "sendMsgForAck";
	}
}
