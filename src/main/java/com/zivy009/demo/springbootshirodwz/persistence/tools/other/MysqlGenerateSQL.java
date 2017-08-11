package com.zivy009.demo.springbootshirodwz.persistence.tools.other;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author zivy
 * @date 2017年8月8日
 * @describe
 */
public class MysqlGenerateSQL extends SQL {
    StringBuilder limitBuilder = new StringBuilder();
    private SQL countSQL = new SQL();

    /**
     * 不适合DISTINCT
     * 
     * @author zivy
     * @date 2017年8月8日
     * @describe
     * @return
     *
     */
    public String getCountSQL() {

        return countSQL.toString();
    }

    @Override
    public SQL SELECT(String columns) {
        countSQL.SELECT("count(*) c");
        return super.SELECT(columns);

    }

    @Override
    public SQL SELECT(String... columns) {
        countSQL.SELECT("count(*) c");
        return super.SELECT(columns);

    }

    @Override
    public SQL FROM(String table) {
        countSQL.FROM(table);
        return super.FROM(table);

    }

    @Override
    public SQL FROM(String... tables) {
        countSQL.FROM(tables);
        return super.FROM(tables);

    }

    @Override
    public SQL JOIN(String join) {
        countSQL.JOIN(join);
        return super.JOIN(join);

    }

    @Override
    public SQL JOIN(String... joins) {
        countSQL.JOIN(joins);
        return super.JOIN(joins);

    }

    @Override
    public SQL INNER_JOIN(String join) {
        countSQL.INNER_JOIN(join);
        return super.INNER_JOIN(join);

    }

    @Override
    public SQL INNER_JOIN(String... joins) {
        countSQL.INNER_JOIN(joins);
        return super.INNER_JOIN(joins);

    }

    @Override
    public SQL LEFT_OUTER_JOIN(String join) {
        countSQL.LEFT_OUTER_JOIN(join);
        return super.LEFT_OUTER_JOIN(join);

    }

    @Override
    public SQL LEFT_OUTER_JOIN(String... joins) {
        countSQL.LEFT_OUTER_JOIN(joins);
        return super.LEFT_OUTER_JOIN(joins);

    }

    @Override
    public SQL RIGHT_OUTER_JOIN(String join) {
        countSQL.RIGHT_OUTER_JOIN(join);
        return super.RIGHT_OUTER_JOIN(join);

    }

    @Override
    public SQL RIGHT_OUTER_JOIN(String... joins) {
        countSQL.RIGHT_OUTER_JOIN(joins);
        return super.RIGHT_OUTER_JOIN(joins);

    }

    @Override
    public SQL OUTER_JOIN(String join) {
        countSQL.OUTER_JOIN(join);
        return super.OUTER_JOIN(join);

    }

    @Override
    public SQL OUTER_JOIN(String... joins) {
        countSQL.OUTER_JOIN(joins);
        return super.OUTER_JOIN(joins);

    }

    @Override
    public SQL WHERE(String conditions) {
        countSQL.WHERE(conditions);
        return super.WHERE(conditions);

    }

    @Override
    public SQL WHERE(String... conditions) {
        countSQL.WHERE(conditions);
        return super.WHERE(conditions);

    }

    @Override
    public SQL OR() {
        countSQL.OR();
        return super.OR();

    }

    @Override
    public SQL AND() {
        countSQL.AND();
        return super.AND();

    }

    @Override
    public SQL GROUP_BY(String columns) {
        countSQL.GROUP_BY(columns);
        return super.GROUP_BY(columns);

    }

    @Override
    public SQL GROUP_BY(String... columns) {
        countSQL.GROUP_BY(columns);
        return super.GROUP_BY(columns);

    }

    @Override
    public SQL HAVING(String conditions) {
        countSQL.HAVING(conditions);
        return super.HAVING(conditions);

    }

    @Override
    public SQL HAVING(String... conditions) {
        countSQL.HAVING(conditions);
        return super.HAVING(conditions);

    }

    public void limit(Integer offset, Integer rows) {
        if (offset != null) {
            limitBuilder.append(" limit ").append(offset);
            if (rows != null) {
                limitBuilder.append(",").append(rows);
            }
        }

    }

    /**
     * 
     * 
     * @author zivy
     * @date 2017年8月8日
     * @describe
     * @return
     *
     */
    public String getSQL() {
        if (limitBuilder.length() > 0) {
            return super.toString() + limitBuilder.toString();
        } else {
            return super.toString();
        }
    }

    @Override
    public String toString() {
        return getSQL();
    }

}
