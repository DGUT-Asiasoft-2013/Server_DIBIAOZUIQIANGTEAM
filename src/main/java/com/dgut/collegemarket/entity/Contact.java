package com.dgut.collegemarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.dgut.collegemarket.util.DateRecord;

@Entity

public class Contact extends DateRecord{
	User user;
	String name;//姓名
	String phone;//电话
	String school;//学校
	String sushe;//宿舍
	
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSushe() {
		return sushe;
	}
	public void setSushe(String sushe) {
		this.sushe = sushe;
	}


	

	
}
