package com.zivy009.demo.springbootshirodwz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations={"classpath:mykaptcha.xml"})
public class SpringbootShiroDwzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootShiroDwzApplication.class, args);
	}
}
