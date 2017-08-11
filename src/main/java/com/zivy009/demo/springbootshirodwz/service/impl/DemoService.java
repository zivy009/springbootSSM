package com.zivy009.demo.springbootshirodwz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zivy009.demo.springbootshirodwz.common.PageHandler;
import com.zivy009.demo.springbootshirodwz.common.tools.StringUtil;
import com.zivy009.demo.springbootshirodwz.persistence.dao.ZzDemoMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.ZzDemo;
import com.zivy009.demo.springbootshirodwz.persistence.tools.CommonMapper;
import com.zivy009.demo.springbootshirodwz.persistence.tools.other.MysqlGenerateSQL;

/**
 * @author zivy
 * @date 2017年7月10日
 * @describe
 */
@Service
@Transactional
public class DemoService extends ServiceImpl<ZzDemoMapper, ZzDemo> {

    @Autowired
    CommonMapper commonMapper;

    String tableName = "zz_demo";

    @Transactional(readOnly = true)
    public List<Map<String, Object>> list(PageHandler page, String keyword) {
        // 1.得到记录集
        Map<String,Object> map = new HashMap<String,Object>();
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
 
        List<Map<String, Object>> list =commonMapper.queryList(sql.toString(),map);
 
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
    public int save(ZzDemo entity) {
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
    public int del(Long id) {
        int rInt = baseMapper.deleteById(id);
        return rInt;
    }

    /**
     * 
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
        ZzDemo entity = new ZzDemo();
        entity.setId(id);
        entity.setDisabled(1);
        int rInt = baseMapper.updateById(entity);
        return rInt;
    }

    public ZzDemo selectById(Integer id) {

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
    public int update(ZzDemo entity) {
        return baseMapper.updateById(entity);
    }

}
