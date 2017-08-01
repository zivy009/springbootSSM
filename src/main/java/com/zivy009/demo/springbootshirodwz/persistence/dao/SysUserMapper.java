package com.zivy009.demo.springbootshirodwz.persistence.dao;

import com.zivy009.demo.springbootshirodwz.persistence.model.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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
}