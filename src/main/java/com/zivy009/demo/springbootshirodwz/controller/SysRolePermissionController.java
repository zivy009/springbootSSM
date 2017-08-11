package com.zivy009.demo.springbootshirodwz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zivy009.demo.springbootshirodwz.controller.base.BaseController;
import com.zivy009.demo.springbootshirodwz.service.impl.SysRolePermissionService;

/**
 * <p>
 * 角色授权 前端控制器
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Controller
@RequestMapping("/sysRolePermission")
public class SysRolePermissionController extends BaseController<SysRolePermissionService>{
	
}
