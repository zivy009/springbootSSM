package com.zivy009.demo.springbootshirodwz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zivy009.demo.springbootshirodwz.common.PageHandler;
import com.zivy009.demo.springbootshirodwz.common.tools.StringUtil;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysUserMapper;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysUserRoleMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysUser;
import com.zivy009.demo.springbootshirodwz.persistence.tools.CommonMapper;
import com.zivy009.demo.springbootshirodwz.persistence.tools.other.MysqlGenerateSQL;
import com.zivy009.demo.springbootshirodwz.service.ISysUserService;
import com.zivy009.demo.springbootshirodwz.shiro.ShiroKit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Service
@Transactional
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    SysUserRoleMapper userRoleMapper;
    @Autowired
    CommonMapper commonMapper;
    String tableName = "sys_user";

    @Transactional(readOnly = true)
    public List<Map<String, Object>> list(PageHandler page, String keyword) {
        // 1.得到记录集
        Map<String, Object> map = new HashMap<String, Object>();
        MysqlGenerateSQL sql = new MysqlGenerateSQL();
        sql.SELECT("*");
        sql.FROM(tableName);
        sql.WHERE("disabled=0");
        if (!StringUtil.empty(keyword)) {
            sql.WHERE("name like #{paramMap.name}");
            map.put("name", "%" + keyword + "%");
        }
        sql.ORDER_BY("addtime desc");
        sql.limit(page.getStartRow(), page.getPageSize());

        List<Map<String, Object>> list = commonMapper.queryList(sql.getSQL(), map);

        // 2.得到总数

        Long countInt = commonMapper.queryCount(sql.getCountSQL(), map);
        page.setTotalCount(countInt.intValue());
        return list;
    }

    /**
     * 
     * 
     * @author zivy
     * @date 2017年7月17日
     * @describe
     * @param entity
     * @return
     *
     */
    public int save(SysUser entity) {
        int rInt = baseMapper.insert(entity);
        return rInt;
    }

    /**
     * 
     * 由id删除记录
     * 
     * @author zivy
     * @date 2017年7月18日
     * @describe
     * @param id
     * @return
     *
     */
    public int del(Integer id) {
        int rInt = baseMapper.deleteById(id);
        return rInt;
    }

    /**
     * 逻辑删除
     * 
     * @author zivy
     * @date 2017年8月8日
     * @describe
     * @param id
     * @return
     *
     */
    public int delLogical(Long id) {
        SysUser entity = new SysUser();
        entity.setId(id);
        entity.setDisabled(1);
        int rInt = baseMapper.updateById(entity);
        return rInt;
    }

    public SysUser selectById(Integer id) {

        return baseMapper.selectById(id);

    }

    /**
     * 修改实体
     * 
     * @author zivy
     * @date 2017年7月19日
     * @describe
     * @param entity
     * @return
     *
     */
    public int update(SysUser entity) {
        return baseMapper.updateById(entity);
    }

    public void countRow() {
        if (baseMapper == null) {
            System.out.println("aaaaaaaaaaaaaaa is null");
        } else {
            System.out.println("xxxxxxxxxxxxxx=" + baseMapper.countRow());
        }

    }

    /**
     * 修改密码
     * 
     * @author zivy
     * @date 2017年8月3日
     * @describe
     * @param id
     * @param pwd
     *
     */
    public void updPwdDo(Integer id, String pwd) {
        String salt = ShiroKit.getRandomSalt();
        String md5Pwd = ShiroKit.md5(pwd, salt);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("md5Pwd", md5Pwd);
        map.put("salt", salt);
        baseMapper.updPwdDo(map);
    }

    public void updRoleDo(Integer userId, String[] roleIds) {
        Map<String,Object> columnMap = new HashMap<String,Object>();
        columnMap.put("user_id", userId);
       // int resultInt = 
                userRoleMapper.deleteByMap(columnMap);
    //    List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        if (roleIds == null || roleIds.length < 1) { // 没有任何角色，只执行删除操作

        } else {// 先删，后增
            // for (String roleid : roleIds) {
            // Map map=new HashMap();
            // map.put("user_id", userId);
            // map.put("role_id", roleid);
            // list.add(map);
            // }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userId", userId);
            map.put("roleIds", roleIds);
            int resultInt2 = userRoleMapper.batchInsert2(map);
            System.out.println("resultInt2=" + resultInt2);
        }

    }

    public Map<String,Object> obtainLoginName() {
        SysUser sysUser=new SysUser();
        sysUser.setId(2L);
        EntityWrapper<SysUser> wrapper=new EntityWrapper<SysUser>(sysUser);
        
    
       List<Map<String,Object>> list=baseMapper.selectMaps(wrapper);
        return list.get(0);
        
    }
}
