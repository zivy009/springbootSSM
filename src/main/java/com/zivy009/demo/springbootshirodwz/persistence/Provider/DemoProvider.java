package com.zivy009.demo.springbootshirodwz.persistence.Provider;

import java.util.Map;

import com.zivy009.demo.springbootshirodwz.common.tools.StringUtil;

/**
 * @author zivy
 * @date 2017年7月12日
 * @describe
 */
public class DemoProvider {

    public DemoProvider() {
        // TODO Auto-generated constructor stub
    }

//    public String list(Map<String, Object> args) {
//        StringBuilder sqlBd = new StringBuilder();
//        sqlBd.append("select * from zz_demo ");
//        if (!StringUtil.empty(args.get("keyword"))) {
//            sqlBd.append(" where name like '%" + args.get("keyword") + "%'");
//        }
//        sqlBd.append(" limit "+args.get("m")+","+args.get("n"));
//        return sqlBd.toString();
//
//    }
    public String select(String sql){
        return sql;
    }
}
