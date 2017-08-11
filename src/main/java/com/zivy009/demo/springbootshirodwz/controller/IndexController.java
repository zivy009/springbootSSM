package com.zivy009.demo.springbootshirodwz.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zivy009.demo.springbootshirodwz.controller.base.BaseController;
import com.zivy009.demo.springbootshirodwz.service.impl.SysUserService;

/**
 * @author zivy
 * @date 2017年7月10日
 * @describe
 */
@Controller
public class IndexController extends BaseController<SysUserService> {

    public IndexController() {
        // TODO Auto-generated constructor stub
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("sysuser", subject.getPrincipal());
        return "index";
    }

 
  

}
