package com.zivy009.demo.springbootshirodwz.persistence.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.ZzDemo;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author zivy
 * @since 2017-07-13
 */
@Mapper
public interface ZzDemoMapper extends BaseMapper<ZzDemo> {
    @Insert("insert into user(name) values(#{name})")
    int insertInjector(ZzDemo entity);
}