package com.zivy009.demo.springbootshirodwz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zivy009.demo.springbootshirodwz.controller.base.BaseController;
import com.zivy009.demo.springbootshirodwz.service.impl.SysUserRoleService;

/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Controller
@RequestMapping("/sysUserRole")
public class SysUserRoleController extends BaseController<SysUserRoleService>{
	
}
