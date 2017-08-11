package com.zivy009.demo.springbootshirodwz.persistence.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysRole;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zivy
 * @since 2017-07-21
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 非用户录入可以用String。拼接id字符窜
     * 
     *@author zivy
     *@date 2017年7月31日
     *@describe
     *@param userid
     *@return
     *
     */
    @Select(value = "select * from sys_role where disabled=0 and id in (${roleIds})")
    public List<SysRole> obtainBy(@Param("roleIds") String roleIds);
    
    @Select(value="select code from sys_role where  disabled=0 and  id in (${roleIds})")
    public Set<String> obtainCodeBy(@Param("roleIds") String roleIds);
    
    @Select(value="select id,name from sys_role where disabled=0")
    public List<Map<String,Object>> obtainNames();
}