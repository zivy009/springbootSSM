package com.zivy009.demo.springbootshirodwz.persistence.model;

import java.util.Date;
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
@TableName("schema_version")
public class SchemaVersion extends Model<SchemaVersion> {

    private static final long serialVersionUID = 1L;

	@TableField("version_rank")
	private Integer versionRank;
	@TableField("installed_rank")
	private Integer installedRank;
	private String version;
	private String description;
	private String type;
	private String script;
	private Integer checksum;
	@TableField("installed_by")
	private String installedBy;
	@TableField("installed_on")
	private Date installedOn;
	@TableField("execution_time")
	private Integer executionTime;
	private Integer success;


	public Integer getVersionRank() {
		return versionRank;
	}

	public void setVersionRank(Integer versionRank) {
		this.versionRank = versionRank;
	}

	public Integer getInstalledRank() {
		return installedRank;
	}

	public void setInstalledRank(Integer installedRank) {
		this.installedRank = installedRank;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Integer getChecksum() {
		return checksum;
	}

	public void setChecksum(Integer checksum) {
		this.checksum = checksum;
	}

	public String getInstalledBy() {
		return installedBy;
	}

	public void setInstalledBy(String installedBy) {
		this.installedBy = installedBy;
	}

	public Date getInstalledOn() {
		return installedOn;
	}

	public void setInstalledOn(Date installedOn) {
		this.installedOn = installedOn;
	}

	public Integer getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Integer executionTime) {
		this.executionTime = executionTime;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	@Override
	protected Serializable pkVal() {
		return this.version;
	}

}
