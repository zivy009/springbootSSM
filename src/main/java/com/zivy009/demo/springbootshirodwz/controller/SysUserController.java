package com.zivy009.demo.springbootshirodwz.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.zivy009.demo.springbootshirodwz.common.PageHandler;
import com.zivy009.demo.springbootshirodwz.common.exception.MyRuntimeException;
import com.zivy009.demo.springbootshirodwz.common.exception.MyRuntimeRightException;
import com.zivy009.demo.springbootshirodwz.common.http.RequestUtil;
import com.zivy009.demo.springbootshirodwz.controller.base.BaseController;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysUser;
import com.zivy009.demo.springbootshirodwz.service.impl.SysRoleService;
import com.zivy009.demo.springbootshirodwz.service.impl.SysUserService;
import com.zivy009.demo.springbootshirodwz.shiro.ShiroKit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Controller
@RequestMapping("/sysuser")
public class SysUserController extends BaseController<SysUserService> {

    @Autowired
    SysRoleService roleService;

    String viewRoot = "sysuser";

    @RequestMapping("/list")
    @RequiresPermissions("sysuser:list")
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
    @RequiresPermissions("sysuser:add")
    String addView(Model model, HttpServletRequest request) {
        model.addAttribute("model", null);
        return viewRoot + "/add";
    }

    @RequestMapping("/updPwd")
    @RequiresPermissions("sysuser:upd")
    String updPwdView() {

        return viewRoot + "/updPwd";
    }

    @RequestMapping("/updRole")
    @RequiresPermissions("sysuser:upd")
    String updRoleView(Model model, HttpServletRequest request) {
        Long id = RequestUtil.getLong(request, "id");
        if (id == null) {
            throw new MyRuntimeException("参数空！！！");
        }
        // 1.获得所以角色；rolesSelf
        List<Map<String, Object>> roleAll = roleService.obtainNames();

        // 2. 获得这个用户的当前角色；
        Set<Integer> rolesSelf = roleService.obtainRolesSelf(id);
        for (Map<String, Object> map : roleAll) {
            Integer roleid = Integer.parseInt(map.get("id").toString());
            if (rolesSelf.contains(roleid)) {
                map.put("checked", true);
            } else {
                map.put("checked", false);
            }
        }
        model.addAttribute("roleAll", roleAll);
        // 3. 获得用户名
        model.addAttribute("sysUser", baseService.selectById(id));
        return viewRoot + "/updRole";
    }

    @RequestMapping(value = "/updRoleDo")
    @ResponseBody
    String updRoleDo(HttpServletRequest request) {
        
        String jsonReturn = this.ajaxSuccess();
        
        try {
            Integer userId = RequestUtil.getInteger(request, "id");
            String[] roleIds = request.getParameterValues("roleIds");
            baseService.updRoleDo(userId,roleIds);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            jsonReturn=this.ajaxFail("操作失败。"+e.getMessage());
        }finally {
            return jsonReturn;
        }
        
    }

    @RequestMapping("/upd")
    @RequiresPermissions("sysuser:upd")
    String updView(Model model, HttpServletRequest request, @RequestParam(value = "id", defaultValue = "0") Integer id) {
        SysUser entity = null;
        if (id != 0) {
            entity = baseService.selectById(id);

        } else {
            throw new MyRuntimeException("");
        }
        model.addAttribute("model", entity);
        return viewRoot + "/add";
    }

    @RequestMapping("/updPwdDo")
    @RequiresPermissions("sysuser:upd")
    @ResponseBody
    String updPwdDo(HttpServletRequest request) {
        String jsonReturn = this.ajaxSuccess();
        Integer id = RequestUtil.getInteger(request, "id");
        String pwd = RequestUtil.getString(request, "password");
        baseService.updPwdDo(id, pwd);
        return jsonReturn;
    }

    @RequestMapping("/updPwdSelfDo")
    @RequiresUser
    @ResponseBody
    String updPwdSelfDo(HttpServletRequest request) {
        String jsonReturn = this.ajaxSuccess();
        Integer id = RequestUtil.getInteger(request, "id");
        String pwd = RequestUtil.getString(request, "password");
        baseService.updPwdDo(id, pwd);
        return jsonReturn;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    String save(SysUser entity) {
        String jsonReturn = this.ajaxSuccess();
        int returnInt = 0;
        try {
            if (entity.getId() != null) {
                returnInt = baseService.update(entity);
            } else {
                String salt = ShiroKit.getRandomSalt(4);
                entity.setPassword(ShiroKit.md5("123456", salt));
                entity.setSalt(salt);

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

    @RequestMapping(value = "/infoselfDo")
    @ResponseBody
    @RequiresUser
    String infoselfDo(SysUser entity) {
        String jsonReturn = this.ajaxSuccess();
        int returnInt = 0;
        try {
            if (entity.getId() != null) {
                returnInt = baseService.update(entity);
                SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
                sysUser.setName(entity.getName());
            } else {
                new MyRuntimeRightException("id 空，修改失败请联系管理员！！");
            }

        } catch (MyRuntimeRightException rightE) {
            jsonReturn = this.ajaxFail("警告：" + rightE.getMessage());
        } catch (Exception e) {

            e.printStackTrace();
            jsonReturn = this.ajaxFail("发生异常：" + e.getMessage());
        }
        return jsonReturn;
    }

    /**
     * 改成逻辑删除
     * 
     * @author zivy
     * @date 2017年8月4日
     * @describe
     * @param id
     * @return
     *
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    String del(@RequestParam(value = "id", defaultValue = "0") Long id) {
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

    @RequestMapping(value = "/infoself")
    @RequiresUser
    String infoself(Model model) {
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("sysuser", subject.getPrincipal());
        return viewRoot + "/infoself";
    }

    @RequestMapping("{anyFile}")
    public String anything(@PathVariable String anyFile) {
        // String temp= anyPath+File.separator+anyFile;
        return viewRoot + "/" + anyFile;
    }
}
