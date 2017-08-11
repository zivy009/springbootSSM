package com.zivy009.demo.springbootshirodwz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zivy009.demo.springbootshirodwz.common.PageHandler;
import com.zivy009.demo.springbootshirodwz.common.constants.MyCodeOfException;
import com.zivy009.demo.springbootshirodwz.common.exception.MyRuntimeException;
import com.zivy009.demo.springbootshirodwz.common.exception.MyRuntimeRightException;
import com.zivy009.demo.springbootshirodwz.common.http.RequestUtil;
import com.zivy009.demo.springbootshirodwz.controller.base.BaseController;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysPermission;
import com.zivy009.demo.springbootshirodwz.service.impl.SysPermissionService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Controller
@RequestMapping("/sysPermission")
public class SysPermissionController extends BaseController<SysPermissionService> {

   
    String viewRoot = "syspermission";

    @RequestMapping("/list")
    @RequiresPermissions("syspermission:list")
    protected String list(Model model, HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageIndex,
            @RequestParam(value = "numPerPage", defaultValue = "5") int pageSize) {

        PageHandler page = new PageHandler(pageIndex, pageSize);
        String keyword = RequestUtil.getString(request, "keyword");
        List<Map<String, Object>> list = baseService.list(page, keyword);

        model.addAttribute("page", page);
        model.addAttribute("list", list);
        model.addAttribute("keyword", keyword);
        return viewRoot + "/list";
    }

    @RequestMapping("/add")
    String addView(Model model, HttpServletRequest request) {
        model.addAttribute("parent", baseService.obtainAllParent());
        model.addAttribute("model", null);
        return viewRoot + "/add";
    }

    @RequestMapping("/upd")
    String updView(Model model, HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") Integer id) {
        SysPermission entity = null;
        if (id != 0) {
            entity = baseService.selectById(id);
            if(entity.getParentId()==0){// 只有一层父
                model.addAttribute("parent", null);
            }else{
                model.addAttribute("parent", baseService.obtainAllParent());
            }
            
        } else {
            throw new MyRuntimeException(MyCodeOfException.PARAMETER_NULL.des);
        }
        model.addAttribute("model", entity);
        return viewRoot + "/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    @RequiresPermissions(value={"syspermission:add","syspermission:upd"},logical=Logical.OR)
    String save(SysPermission entity) {
        String jsonReturn = this.ajaxSuccess();
        int returnInt = 0;
        try {
            if (entity.getId() != null) {
                returnInt = baseService.update(entity);
            } else {
                returnInt = baseService.save(entity);
            }

        }catch(MyRuntimeRightException rightE){
            jsonReturn = this.ajaxFail("警告：" + rightE.getMessage());
        } catch (Exception e) {

            e.printStackTrace();
            jsonReturn = this.ajaxFail("发生异常：" + e.getMessage());
        }
        return jsonReturn;
    }

    @RequestMapping(value = "/del")
    @RequiresPermissions(value={"syspermission:del"})
    @ResponseBody
    String del(@RequestParam(value = "id", defaultValue = "0") Integer id) {
        String jsonReturn = this.ajaxDelSuccess();
        try {
            int returnInt = baseService.del(id);

        }catch(MyRuntimeRightException rightE){
            jsonReturn = this.ajaxFail("警告：" + rightE.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            jsonReturn = this.ajaxFail("发生异常：" + e.getMessage());
        }
        return jsonReturn;
    }

}
