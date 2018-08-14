package com.study;

import org.apache.kafka.common.network.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaMqApplication {
	@Autowired
	private SendMessageDemo sendMessageDemo;

	public static void main(String[] args) {
		SpringApplication.run(KafkaMqApplication.class, args);
	}

	@RequestMapping(value = "/sendmsg")
	public String sendMsg() throws InterruptedException {
		sendMessageDemo.sendMsg();
		return "sendMsg";
	}

	@RequestMapping(value = "/sendmsgack")
	public String sendMsgAck() throws InterruptedException {
		sendMessageDemo.sendMsgAck();
		return "sendMsgAck";
	}
}
