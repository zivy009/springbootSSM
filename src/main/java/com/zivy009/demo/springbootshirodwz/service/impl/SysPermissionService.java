package com.zivy009.demo.springbootshirodwz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zivy009.demo.springbootshirodwz.common.PageHandler;
import com.zivy009.demo.springbootshirodwz.common.exception.MyRuntimeRightException;
import com.zivy009.demo.springbootshirodwz.common.tools.StringUtil;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysPermissionMapper;
import com.zivy009.demo.springbootshirodwz.persistence.dao.SysRolePermissionMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.SysPermission;
import com.zivy009.demo.springbootshirodwz.persistence.tools.CommonMapper;
import com.zivy009.demo.springbootshirodwz.persistence.tools.other.MysqlGenerateSQL;
import com.zivy009.demo.springbootshirodwz.service.ISysPermissionService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zivy
 * @since 2017-07-25
 */
@Service
public class SysPermissionService extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    String tableName = "sys_permission";
    @Autowired
    CommonMapper commonMapper;
    @Autowired
    SysRolePermissionMapper sysRolePermissionMapper;

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
    public int save(SysPermission entity) {

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

        List<String> b = baseMapper.obtainSon(id);

        if (b != null && b.size() > 0) {
            throw new MyRuntimeRightException("它下面有子元素：" + b.toString() + "。 请不要删！");
        }
        int rInt = baseMapper.deleteById(id);
        return rInt;
    }

    public SysPermission selectById(Integer id) {
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
    public int update(SysPermission entity) {
        return baseMapper.updateById(entity);
    }

    /**
     * 
     * 获得所有的父数据 id-name
     * 
     * @author zivy
     * @date 2017年8月2日
     * @describe
     * @return
     *
     */
    public Object obtainAllParent() {

        return baseMapper.obtainAllParent();

    }

    /**
     * 获得这个人的，所有权限，包括没选中的。
     * 
     * @author zivy
     * @date 2017年8月11日
     * @describe
     * @param roleId
     * @return
     *
     */
    public Map<String, Map<String, Object>> obtainAll(Integer roleId) {
        Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();
        Map paramMap = new HashMap();
        paramMap.put("disabled", 0);
        List<SysPermission> list = baseMapper.selectByMap(paramMap);
        // 自己的角色
        Set<Integer> permissionSelf = sysRolePermissionMapper.obtainPermissionSelf(roleId);
        // 1. 获得父数据
        for (SysPermission sysPermission : list) {
            if (sysPermission.getParentId() == 0) {
                Map tempMap = new HashMap();
                tempMap.put("name", sysPermission.getName());
                resultMap.put(sysPermission.getId().toString(), tempMap);
            }
        }
        // 2.获得父的子数据
        for (String parentIdStr : resultMap.keySet()) {
            List<SysPermission> tempList = new ArrayList<SysPermission>();
            for (SysPermission sysPermission : list) {
                if (sysPermission.getParentId() == Integer.parseInt(parentIdStr)) {
                    if (permissionSelf == null) {
                        sysPermission.setChecked(false);
                    } else {
                        if (permissionSelf.contains(sysPermission.getId())) {
                            sysPermission.setChecked(true);
                        } else {
                            sysPermission.setChecked(false);
                        }
                    }
                    tempList.add(sysPermission);
                }
            }
            resultMap.get(parentIdStr).put("data", tempList);

        }
        return resultMap;

    }
}
