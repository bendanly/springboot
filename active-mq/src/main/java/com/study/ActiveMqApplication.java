package com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要启动actvie服务D:\apache-activemq-5.15.4\bin\win64\activemq.bat
 * 模拟发送一般消息http://localhost:8881/sendmsg
 * 模拟发送对象消息http://localhost:8881/senduser
 * active控制台http://localhost:8161
 */
@SpringBootApplication
@RestController
public class ActiveMqApplication {
	@Autowired
	private SendMessageDemo sendMessageDemo;

	public static void main(String[] args) {
		SpringApplication.run(ActiveMqApplication.class, args);
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
}
