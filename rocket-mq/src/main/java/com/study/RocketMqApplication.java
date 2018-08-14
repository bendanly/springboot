package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RocketMqApplication {

	@Autowired
	private SendMessageDemo sendMessageDemo;

	public static void main(String[] args) {
		SpringApplication.run(RocketMqApplication.class, args);
	}

	@RequestMapping(value = "/sendmsg")
	public String sendMsg(){
		sendMessageDemo.sendMsg();
		return "sendMsg";
	}
}
