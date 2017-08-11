package com.zivy009.demo.springbootshirodwz.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zivy009.demo.springbootshirodwz.common.exception.MyRuntimeException;
import com.zivy009.demo.springbootshirodwz.common.http.RequestUtil;
import com.zivy009.demo.springbootshirodwz.controller.base.BaseController;
import com.zivy009.demo.springbootshirodwz.service.impl.SysUserService;

/**
 * @author zivy
 * @date 2017年7月24日
 * @describe
 */
@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController<SysUserService> {
    @Autowired
    DefaultKaptcha defaultKaptcha;
    @Autowired
    DefaultWebSecurityManager defaultWebSecurityManager;
    @Autowired
    SysUserService sysUserService;

    public LoginController() {
        // TODO Auto-generated constructor stub
    }

    @RequestMapping(value = "/login")
    public String login(Model model, HttpServletRequest httpServletRequest) {

        sysUserService.countRow();
        // 判断session里是否有用户信息
        if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            return "redirect:/anon/login/timeout";
        }
        return "login";
    }

    @RequestMapping(value = "/login/timeout")
    @ResponseBody
    public String loginTimeout(HttpServletRequest httpServletRequest, HttpSession session, HttpServletResponse httpServletResponse) throws Exception {

        return this.ajaxTimeout();
    }

    /**
     * 退出系统
     * 
     * @return
     * @throws Exception
     * @since <IVersion>
     */
    @RequestMapping(value = "/logout")
    public ModelAndView logout(HttpSession session) throws Exception {

        return new ModelAndView("redirect:/anon/login");
    }

    /**
     * 登录验证
     * 
     * @author zivy
     * @date 2017年7月21日
     * @describe
     * @param request
     * @param session
     * @return
     *
     */
    @RequestMapping(value = "/loginDo")
    public String loginDo(HttpServletRequest request, HttpSession session, RedirectAttributes attr) {
        try {
            String username = RequestUtil.getString(request, "username");
            String password = RequestUtil.getString(request, "password");
            String code = RequestUtil.getString(request, "verifyCode");
            String vrifyCode = (String) session.getAttribute("vrifyCode");
            if (code == null || !code.equals(vrifyCode)) {
                if (!"a".equals(code)) {// 开发临时用
                    throw new MyRuntimeException("验证码错误");
                }
            }
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            SecurityUtils.setSecurityManager(defaultWebSecurityManager);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            System.out.println("登录成功           principal =   " + subject.getPrincipal().toString());
            return "redirect:/index";
        } catch (AuthenticationException e) {
            attr.addAttribute("errormsg", "用户或密码错误！！！");
            e.printStackTrace();
            return "redirect:/login";
        } catch (Exception e) {
            attr.addAttribute("errormsg", "验证码错误！！！");
            e.printStackTrace();
            return "redirect:/login";
        }
    }

    @RequestMapping("/login/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @RequestMapping(value = "/unauthorized")
    @ResponseBody
    protected String unauthorized() {
        System.out.println("unauthorized..............");
        return "无权访问！！！";// this.ajaxUnauthorized();

    }
}
