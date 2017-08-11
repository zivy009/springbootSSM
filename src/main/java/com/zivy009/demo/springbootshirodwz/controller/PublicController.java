package com.zivy009.demo.springbootshirodwz.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zivy009.demo.springbootshirodwz.controller.base.BaseController;
import com.zivy009.demo.springbootshirodwz.service.impl.SysUserService;
import com.zivy009.demo.springbootshirodwz.test.TestMap;

/**
 * @author zivy
 * @date 2017年7月6日
 * @describe
 */
@Controller
public class PublicController extends BaseController<SysUserService> {
   

    public PublicController() {
        // TODO Auto-generated constructor stub
    }

   
   
}
