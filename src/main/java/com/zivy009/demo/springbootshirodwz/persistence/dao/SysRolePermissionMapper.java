package com.zivy009.demo.springbootshirodwz.persistence.dao;

import com.zivy009.demo.springbootshirodwz.persistence.model.SysRolePermission;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 角色授权 Mapper 接口
 * </p>
 *
 * @author zivy
 * @since 2017-07-21
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    void updPermissionDo(Integer roleID, String[] permissionIDS);

    @Select(value = { "delete from sys_role_permission where role_id=#{roleID}" })
    void delByRoleId(Integer roleID);

    void insertAll(@Param("roleID") Integer roleID, @Param("permissionIDS") List<Integer> permissionIDS);

    @Select(value = { "select permission_id from sys_role_permission where role_id=#{roleID}" })
    Set<Integer> obtainPermissionSelf(Integer roleId);

}