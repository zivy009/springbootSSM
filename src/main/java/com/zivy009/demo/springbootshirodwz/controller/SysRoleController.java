package com.zivy009.demo.springbootshirodwz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zivy009.demo.springbootshirodwz.common.PageHandler;
import com.zivy009.demo.springbootshirodwz.common.exception.MyRuntimeException;
import com.zivy009.demo.springbootshirodwz.common.exception.MyRuntimeRightException;
import com.zivy009.demo.springbootshirodwz.common.http.RequestUtil;
import com.zivy009.demo.springbootshirodwz.controller.base.BaseController;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysRole;
import com.zivy009.demo.springbootshirodwz.service.impl.SysPermissionService;
import com.zivy009.demo.springbootshirodwz.service.impl.SysRolePermissionService;
import com.zivy009.demo.springbootshirodwz.service.impl.SysRoleService;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController<SysRoleService> {

    @Autowired
    SysRolePermissionService sysRolePermissionService;
    @Autowired
    SysPermissionService sysPermissionService;
    String viewRoot = "sysRole";

    @RequestMapping("/list")
    @RequiresPermissions("sysRole:list")
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
    @RequiresPermissions("sysRole:add")
    String addView(Model model, HttpServletRequest request) {
        model.addAttribute("model", null);
        return viewRoot + "/add";
    }

    @RequestMapping("/upd")
    @RequiresPermissions("sysRole:upd")
    String updView(Model model, HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") Integer id) {
        SysRole entity = null;
        if (id != 0) {
            entity = baseService.selectById(id);
        } else {
            throw new MyRuntimeException("");
        }
        model.addAttribute("model", entity);
        return viewRoot + "/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    String save(SysRole entity) {
        String jsonReturn = this.ajaxSuccess();
        int returnInt = 0;
        try {
            if (entity.getId() != null) {
                returnInt = baseService.update(entity);
            } else {
                returnInt = baseService.save(entity);
            }

        } catch (MyRuntimeRightException rightE) {
            jsonReturn = this.ajaxFail("警告：" + rightE.getMessage());
        } catch (Exception e) {

            e.printStackTrace();
            jsonReturn = this.ajaxFail("发生异常：" + e.getMessage());
        }
        return jsonReturn;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    String del(@RequestParam(value = "id", defaultValue = "0") Integer id) {
        String jsonReturn = this.ajaxDelSuccess();
        try {
            int returnInt = baseService.delLogical(id);

        } catch (MyRuntimeRightException rightE) {
            jsonReturn = this.ajaxFail("警告：" + rightE.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonReturn = this.ajaxFail("发生异常：" + e.getMessage());
        }
        return jsonReturn;
    }

    @RequestMapping("{anyFile}")
    public String anything(@PathVariable String anyFile) {
        // String temp= anyPath+File.separator+anyFile;
        return viewRoot + "/" + anyFile;
    }

    @RequestMapping(value = "/updPermission")
    public String updPermission(Model model, HttpServletRequest request) {
        Integer roleId = RequestUtil.getInteger(request, "id");
        model.addAttribute("permissions", sysPermissionService.obtainAll(roleId));

        return viewRoot + "/updPermission";
    }

    @RequestMapping(value = "/updPermissionDo")
    @ResponseBody
    public String updPermissionDo(HttpServletRequest request) {
        String resultStr = this.ajaxSuccess();
        try {
            Integer roleID = RequestUtil.getInteger(request, "id");
            List<Integer> permissionIDS = RequestUtil.getParameterValues(request, "permissionID");//
            System.out.println("roleId=" + roleID);

            sysRolePermissionService.updPermissionDo(roleID, permissionIDS);

        } catch (Exception e) {

            e.printStackTrace();
            this.ajaxFail("保存权限失败！！！");
        }
        return resultStr;
    }

}
