package com.zivy009.demo.springbootshirodwz.persistence.tools;
/**
* @author zivy
* @date 2017年7月12日
* @describe
*/

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.zivy009.demo.springbootshirodwz.persistence.tools.other.CommonProvider;

/**
 * 1、得到sql 语句 用GenerateSqle.java 2、传入执行
 * 
 * @author hongcai
 *
 */

public interface CommonMapper {

    @SelectProvider(type = CommonProvider.class, method = "sqlByParam")
    List<Map<String, Object>> queryList(@Param("sql") String sql, @Param("paramMap") Map<String, Object> map);

    @SelectProvider(type = CommonProvider.class, method = "sqlByParam")
    <T> T queryCount(@Param("sql") String sql, @Param("paramMap") Map<String, Object> map);

    @SelectProvider(type = CommonProvider.class, method = "sql")
    Map<String, Object> queryOneRow(String sql);

    @SelectProvider(type = CommonProvider.class, method = "sql")
    <T> T query(String sql, Class<T> t);

    @InsertProvider(type = CommonProvider.class, method = "sql")
    int insert(String sql);

    @UpdateProvider(type = CommonProvider.class, method = "sql")
    int update(@Param("sql") String sql);

    @DeleteProvider(type = CommonProvider.class, method = "sql")
    int delete(@Param("sql") String sql);

}