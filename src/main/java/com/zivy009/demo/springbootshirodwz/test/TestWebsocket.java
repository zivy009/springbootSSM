package com.zivy009.demo.springbootshirodwz.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zivy009.demo.springbootshirodwz.service.impl.SysUserService;

/**
 * @author zivy
 * @date 2017年11月10日
 * 
 * @describe
 */
@Controller
@RequestMapping("/")
public class TestWebsocket {
    @Autowired
    SysUserService sysUserService;
    public TestWebsocket() {
        // TODO Auto-generated constructor stub
    }

    @RequestMapping("/websocket")
    public String websocket() {
        
        return "test/testWebsocket";
    }
    @RequestMapping("/t4")
    @ResponseBody
    public String t(){
        sysUserService.countRow();
        return "ok";
    }
}
