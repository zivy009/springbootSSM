package com.zivy009.demo.springbootshirodwz.persistence.tools.other;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author zivy
 * @date 2017年7月13日
 * @describe
 */
public class GenerateSQL {
    public final int AND = 1;
    public final int OR = 2;
    String table = new String();
    StringBuilder whereBuilder = new StringBuilder();
    StringBuilder limitBuilder = new StringBuilder();
    StringBuilder orderByBuilder = new StringBuilder();

    public GenerateSQL(String table) {
        this.table = table;
    }

//    public void where(String where) {
//        whereAND(where);
//    }

    public void whereLike(String Field, String value, int AND_OR) {
        if (AND_OR == OR) {
            whereAND(" " + Field + " like '%" + TransactSQLInjection(value) + "%' ");
        } else {
            whereOR(" " + Field + " like '%" + TransactSQLInjection(value) + "%' ");
        }
    }

    public void whereLike(String Field, String value) {
        whereLike(Field, value, AND);
    }

    public void whereAND(String where) {
        if (whereBuilder.length() > 0) {
            whereBuilder.append(" and ");
        }
        whereBuilder.append(where);
    }
/**
 * 谨慎使用，
 * 
 *@author zivy
 *@date 2017年7月17日
 *@describe
 *@param where
 *
 */
    public void whereOR(String where) {
        if (whereBuilder.length() > 0) {
            whereBuilder.append(" or ");
        }
        whereBuilder.append(where);
    }

    public void limit(Integer m, Integer n) {
        limitBuilder.append(" limit ").append(m).append(",").append(n);
    }
    public void ord(String field) {
        ord(field,true);
    }
    public void ord(String field, boolean isDESC) {
        if (orderByBuilder.length() >0 ) {
            orderByBuilder.append(" , ");
           // orderByBuilder.append(" order by ");
        }  
        orderByBuilder.append(field);
        if(isDESC){
            orderByBuilder.append(" desc "); 
        }
    }

    /**
     * 获得拼接成的sql语句
     * 
     * @author zivy
     * @date 2017年7月13日
     * @describe
     * @return
     *
     */
    public String getSql() {
        StringBuilder generateStringBuilder = new StringBuilder();
        generateStringBuilder.append("select * from ").append(table);
        if (whereBuilder.length() > 0) {
          
            generateStringBuilder.append(" where ").append(whereBuilder.toString());
        }
        if(orderByBuilder.length() >0 ){
            generateStringBuilder.append(" order by ");
            generateStringBuilder.append(orderByBuilder.toString());
        }
        if (limitBuilder.length() > 0) {
            generateStringBuilder.append(limitBuilder.toString());
        }
        return generateStringBuilder.toString();
    }

    /**
     * 获得总行数
     * 
     * @author zivy
     * @date 2017年7月13日
     * @describe
     * @return
     *
     */
    public String getCountSql() {
        StringBuilder generateStringBuilder = new StringBuilder();
        generateStringBuilder.append("select count(*) c from ").append(table);
        if (whereBuilder.length() > 0) {
            generateStringBuilder.append(" where ").append(whereBuilder.toString());
        }

        return generateStringBuilder.toString();
    }
    
    /**
     * 防止sql注入
     * 
     *@author zivy
     *@date 2017年7月17日
     *@describe
     *@param str
     *@return
     *
     */
    public static String TransactSQLInjection(String str)
    {
          return str.replaceAll("([';]+|(--)+)", " ");
 

    }
}
