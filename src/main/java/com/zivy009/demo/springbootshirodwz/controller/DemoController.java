package com.zivy009.demo.springbootshirodwz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zivy009.demo.springbootshirodwz.common.PageHandler;
import com.zivy009.demo.springbootshirodwz.common.http.RequestUtil;
import com.zivy009.demo.springbootshirodwz.controller.base.BaseController;
import com.zivy009.demo.springbootshirodwz.persistence.model.ZzDemo;
import com.zivy009.demo.springbootshirodwz.service.DemoService;

/**
 * @author zivy
 * @date 2017年7月10日
 * @describe
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController extends BaseController {

    @Autowired
    DemoService demoService;
    String viewRoot = "demo";

    @RequestMapping("/list")
    protected String list(Model model, HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageIndex,
            @RequestParam(value = "numPerPage", defaultValue = "5") int pageSize) {

        PageHandler page = new PageHandler(pageIndex, pageSize);
        String keyword = RequestUtil.getString(request, "keyword");
        List<Map<String, Object>> list = demoService.list(page, keyword);
       
        model.addAttribute("page", page);
        model.addAttribute("list", list);
        model.addAttribute("keyword", keyword);
        return viewRoot + "/list";
    }

    public DemoController() {
        // TODO Auto-generated constructor stub
    }

    @RequestMapping("/add")
    String addView(Model model, HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") Integer id) {
        ZzDemo zzDemo = null;
        if (id != 0) {
            zzDemo =demoService.selectById(id);
        } else {

        }
        model.addAttribute("model", zzDemo);
        return viewRoot + "/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    String save(ZzDemo entity) {
        String jsonReturn = this.ajaxSuccess();
        int returnInt=0;
        try {
            if(entity.getId()!=null){
                  returnInt=demoService.update(entity);
            }else{
                  returnInt=demoService.save(entity);
            }
            
          
        } catch (Exception e) {
          
            e.printStackTrace();
            jsonReturn =this.ajaxFail("发生异常："+e.getMessage());
        }
        return jsonReturn;
    }
    @RequestMapping(value = "/del")
    @ResponseBody
    String del( @RequestParam(value = "id", defaultValue = "0") Long id){
        String jsonReturn = this.ajaxDelSuccess();
        try {
            int returnInt=demoService.del(id);
          
        } catch (Exception e) {
            e.printStackTrace();
            jsonReturn =this.ajaxFail("发生异常："+e.getMessage());
        }
        return jsonReturn;
    }
}
