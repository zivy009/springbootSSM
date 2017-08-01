package com.zivy009.demo.springbootshirodwz.service.impl;

import com.zivy009.demo.springbootshirodwz.persistence.model.SysUser;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysUserMapper;
import com.zivy009.demo.springbootshirodwz.service.ISysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
@Autowired
SysUserMapper sysUserMapper;
    public void countRow(){
        if(sysUserMapper==null){
            System.out.println("aaaaaaaaaaaaaaa is null");
        }else{
            System.out.println("xxxxxxxxxxxxxx="+sysUserMapper.countRow());
        }
       
	}
}
