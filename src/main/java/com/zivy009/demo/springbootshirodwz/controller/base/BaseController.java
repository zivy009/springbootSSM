package com.zivy009.demo.springbootshirodwz.controller.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.zivy009.demo.springbootshirodwz.service.impl.SysUserService;

/**
 * @author zivy
 * @date 2017年7月17日
 * @describe
 */
public class BaseController<S> {
    @Autowired
    protected  S baseService;
    
    public BaseController() {
        // TODO Auto-generated constructor stub
    }

    protected String ajaxSuccess(String rel) {
        return ajaxSuccessforCurpage("操作成功", rel);
    }

    protected String ajaxSuccess() {
        return ajaxSuccessforCurpage("操作成功", null);
    }
    protected String ajaxTimeout() {
        JSONObject obj = new JSONObject();
        obj.put("statusCode", 301);
        obj.put("message", "登录过期");
       // obj.put("forwardUrl", "/login");
        return obj.toString();
    }
    protected String ajaxUnauthorized() {
        JSONObject obj = new JSONObject();
        obj.put("statusCode", 200);
        obj.put("message", "无权访问");
       // obj.put("forwardUrl", "/login");
        return obj.toString();
    }
    /**
     * 
     * 
     * @author zivy
     * @date 2017年7月18日
     * @describe
     * @param message
     *            提示消息
     * @param navTabId
     *            重新导入这个tab
     * @return
     *
     */
    protected String ajaxSuccessforCurpage(String message, String navTabId) {
        JSONObject obj = new JSONObject();
        obj.put("statusCode", 200);
        obj.put("message", message);
        if (navTabId != null) { // 是null 时，刷新当前页

            obj.put("navTabId", navTabId); // // 这个也是重载页面用，是navTabID
        }
        // obj.put("rel", rel); // 这个也是重载页面用，是navTabID
        obj.put("callbackType", "closeCurrent");
        obj.put("forwardUrl", "");
        // obj.put("confirmMsg", confirmMsg);

        return obj.toString();
    }

    protected String ajaxFail(String msg) {
        JSONObject obj = new JSONObject();
        obj.put("statusCode", 300);
        obj.put("message", msg);
        obj.put("forwardUrl", "");
        return obj.toString();
    }
    protected String ajaxDelFail(String msg) {
        JSONObject obj = new JSONObject();
        obj.put("statusCode", 300);
        obj.put("message", msg);
        obj.put("forwardUrl", "");
        return obj.toString();
    }
    protected String ajaxDelSuccess() {
        JSONObject obj = new JSONObject();
        obj.put("statusCode", 200);
        obj.put("message", "操作成功");
        obj.put("forwardUrl", "");
        return obj.toString();
    }

  
}
