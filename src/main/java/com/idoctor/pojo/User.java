package com.idoctor.pojo;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class User implements Serializable {
	
	private int id;
	private String name;
	
	@JsonIgnore
	private String password;
	private Integer age;
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss a", locale="zh", timezone="GMT+8")
	private String  birthday;
	
	@JsonInclude(Include.NON_NULL)
	private String descation;

	public User(){}
    public User(int id, String userName, String password) {
    	this.id=id;
    	this.name=userName;
    	this.password=password;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
                + ", age=" + age  + ", birthday=" + birthday+ "]";
	}

	
}
