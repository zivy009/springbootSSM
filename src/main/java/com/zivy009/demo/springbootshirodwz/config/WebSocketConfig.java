package com.zivy009.demo.springbootshirodwz.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zivy009.demo.springbootshirodwz.test.MyWebSocket;

/**
 * @author zivy
 * @date 2017年11月13日
 * @describe
 */
@Configuration
public class WebSocketConfig {

    public WebSocketConfig() {

    }

    @Bean
    public MyWebSocket getMyWebSocket(ApplicationContext context) {
        MyWebSocket.setApplicationContext(context);
        return new MyWebSocket();
    }

}
