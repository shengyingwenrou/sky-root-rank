/*
 * Copyright (c) 2018 magicwifi.com.cn
 */
package cn.com.admin.user.entity.base;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class BaseSysUser implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String mobile;
    private String imageUri;
    private Integer rank;
    private Date lastLoginTime;
    private String lastLoginIp;
    private Integer loginCount;
    private Boolean disabled;
    private String remark;
    private Date createTime;


	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}

	public void setUsername(String value) {
		this.username = value;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public String getPassword() {
		return this.password;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getEmail() {
		return this.email;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	public void setMobile(String value) {
		this.mobile = value;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setImageUri(String value) {
		this.imageUri = value;
	}

	public String getImageUri() {
		return this.imageUri;
	}

	public void setRank(Integer value) {
		this.rank = value;
	}

	public Integer getRank() {
		return this.rank;
	}

	public void setLastLoginTime(Date value) {
		this.lastLoginTime = value;
	}

	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginIp(String value) {
		this.lastLoginIp = value;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLoginCount(Integer value) {
		this.loginCount = value;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setDisabled(Boolean value) {
		this.disabled = value;
	}

	public Boolean getDisabled() {
		return this.disabled;
	}

	public void setRemark(String value) {
		this.remark = value;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}
}
