package com.zivy009.demo.springbootshirodwz.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zivy009.demo.springbootshirodwz.common.PageHandler;
import com.zivy009.demo.springbootshirodwz.common.tools.StringUtil;
import com.zivy009.demo.springbootshirodwz.persistence.dao.ZzDemoMapper;
import com.zivy009.demo.springbootshirodwz.persistence.model.ZzDemo;
import com.zivy009.demo.springbootshirodwz.persistence.tools.CommonMapper;
import com.zivy009.demo.springbootshirodwz.persistence.tools.GenerateSQL;

/**
 * @author zivy
 * @date 2017年7月10日
 * @describe
 */
@Service
@Transactional
public class DemoService {

    @Autowired
    CommonMapper commonMapper;
    @Autowired
    ZzDemoMapper selfMapper;

    public DemoService() {
        // TODO Auto-generated constructor stub
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> list(PageHandler page, String keyword) {
        // 1.得到记录集
        GenerateSQL generateSQL = new GenerateSQL("zz_demo");
        if (!StringUtil.empty(keyword)) {
            generateSQL.whereLike("name", keyword);
        }
        generateSQL.ord("addtime");
        generateSQL.limit(page.getStartRow(), page.getPageSize());

        List<Map<String, Object>> list = commonMapper.queryList(generateSQL.getSql());

        //// EntityWrapper<ZzDemo> ew = new EntityWrapper<ZzDemo>();
        //// ew.setEntity(new ZzDemo());
        //// ew.where("user_name={0}", "'zhangsan'")
        //// .orderBy("dd").orderBy("d1,d2");
        //// System.out.println(ew.getSqlSegment());
        // Wrapper<ZzDemo> wrapper=Condition.create().like("name",
        //// keyword).orderBy("addtime",false);
        //
        // System.out.println("getSqlSegment="+wrapper.getSqlSegment());
        // System.out.println("getSqlSelect="+wrapper.getSqlSelect());
        // 2.得到总数

        Long countInt = commonMapper.query(generateSQL.getCountSql(), Long.class);
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
        int rInt = selfMapper.insert(entity);
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
        int rInt = selfMapper.deleteById(id);
        return rInt;
    }

    public void test() {
        Map<String, Object> map = commonMapper.queryOneRow("select * from zz_demo limit 1");
        System.out.println("******************** map =" + map.toString());
    }

    public ZzDemo selectById(Integer id) {

        return selfMapper.selectById(id);

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

        return selfMapper.updateById(entity);

    }

}
