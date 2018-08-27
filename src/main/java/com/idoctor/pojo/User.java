package com.idoctor.pojo;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class User implements Serializable {
	
	private int id;
	private String name;
	private String username;
	@JsonIgnore
	private String password;
	private String phone;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
	private String  birthday;

	@JsonInclude(Include.NON_NULL)
	private String descation;



	private int roleid;//角色id

	private boolean rememberMe;


	public User(){}
    public User(int id, String userName, String password) {
    	this.id=id;
    	this.username=userName;
    	this.password=password;
    }


	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getDescation() {
		return descation;
	}
	public void setDescation(String descation) {
		this.descation = descation;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
                + ", username=" + username+ ", phone=" + phone  + ", birthday=" + birthday+ "]";
	}

	
}
