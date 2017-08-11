package com.zivy009.demo.springbootshirodwz.service.impl;

import com.zivy009.demo.springbootshirodwz.persistence.model.SysRolePermission;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysRolePermissionMapper;
import com.zivy009.demo.springbootshirodwz.service.ISysRolePermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色授权 服务实现类
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Service
@Transactional
public class SysRolePermissionService extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;
    
    public void updPermissionDo(Integer roleID, List<Integer> permissionIDS) {
       //1. 删除角色
        sysRolePermissionMapper.delByRoleId(roleID);
       //2.添加角色
        sysRolePermissionMapper.insertAll(roleID,permissionIDS);
    }
	
}
