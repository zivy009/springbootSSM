package com.zivy009.demo.springbootshirodwz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author zivy
 * @date 2017年7月12日
 * @describe
 */
@Configuration
public class ApplicationConfig {
    @Autowired
    private Environment env;

//    @Bean
//    public ViewResolver viewResolver() {
//        FreeMarkerViewResolver bean = new FreeMarkerViewResolver();
//        bean.setOrder(0);
//        bean.setViewClass(FreeMarkerView.class);
//        bean.setPrefix("/");
//        bean.setSuffix(".ftl");
//        bean.setRequestContextAttribute("ctx");
//        bean.setContentType("text/html;charset=UTF-8");
//       // cacheComponent.registerCachingViewResolverList(bean);
//        return bean;
//    }
  
}
