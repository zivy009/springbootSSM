package com.zivy009.demo.springbootshirodwz.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zivy
 * @since 2017-07-21
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> implements Serializable  {

    private static final long serialVersionUID = 1L;

    /**
     * 用户表
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	@TableField("login_name")
	private String loginName;
    /**
     * 真实姓名
     */
	private String name;
	private String password;
    /**
     *  加密参数
     */
	private String salt;
	private Date addtime;
    /**
     * 1禁用；0否
     */
	private Integer disabled;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
