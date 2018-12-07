package com.zivy009.demo.springbootshirodwz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.zivy009.demo.springbootshirodwz.test.MyWebSocket;

@SpringBootApplication
@ImportResource(locations={"classpath:mykaptcha.xml"})
public class SpringbootShiroDwzApplication {

	public static void main(String[] args) {
	    final ApplicationContext applicationContext=SpringApplication.run(SpringbootShiroDwzApplication.class, args);
		//MyWebSocket.setApplicationContext(applicationContext);
		System.out.println("springboot main...................start");
	}
}
