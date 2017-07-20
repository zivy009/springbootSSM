package com.zivy009.demo.springbootshirodwz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author zivy
 * @date 2017年7月12日
 * @describe
 */
@Configuration

public class ApplicationConfig {
    @Autowired
    private Environment env;

    public ApplicationConfig() {
        // TODO Auto-generated constructor stub
    }

  
}
