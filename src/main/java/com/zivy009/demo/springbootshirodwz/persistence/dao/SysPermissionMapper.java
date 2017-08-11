package com.zivy009.demo.springbootshirodwz.persistence.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysPermission;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zivy
 * @since 2017-07-21
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    @Select(value = { "select code from sys_permission p , sys_role_permission rp    where  disabled=0 and  rp.role_id in (${roleIds}) and p.id=rp.permission_id" })
    public Set<String> obtainPermissionCodeBy(@Param("roleIds") String roleIds);

    @Select(value={"select id,name from sys_permission p where parent_id=0 and disabled=0"})
    public List<Map<String,String>> obtainAllParent();
    @Select(value={"select name from sys_permission p where parent_id=#{id}"})
    public List<String> obtainSon(Integer id);
}