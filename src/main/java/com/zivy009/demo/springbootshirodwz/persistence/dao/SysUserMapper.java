package com.zivy009.demo.springbootshirodwz.persistence.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysUser;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zivy
 * @since 2017-07-21
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
int countRow();
@Update(value={"update sys_user set password=#{md5Pwd},salt=#{salt} where id=#{id}"})
int updPwdDo(Map map);
@Select(value={"select login_name,id from sys_user limit 1"})
Map<String,Object> obtainLoginName();


}