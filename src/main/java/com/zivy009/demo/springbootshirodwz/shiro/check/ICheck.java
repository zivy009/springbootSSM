package com.zivy009.demo.springbootshirodwz.shiro.check;


/**
 *  检查用接口
 */
public interface ICheck {

    /**
     * 检查指定角色
     * @param permissions
     * @return boolean
     */
    boolean check(Object[] permissions);

    /**
     * 检查全体角色
     * @return boolean
     */
    boolean checkAll();
}
