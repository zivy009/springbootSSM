package com.zivy009.demo.springbootshirodwz.persistence.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysUserRole;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author zivy
 * @since 2017-07-21
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    @Select(value = { "select role_id from sys_user_role where user_id=#{user_id}" })
    public Set<Integer> obtainRoldidBy(Long userId);

    public int batchInsert2(Map map);
    
}