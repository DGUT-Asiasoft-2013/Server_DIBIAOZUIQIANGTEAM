package com.dgut.collegemarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dgut.collegemarket.util.DateRecord;

@Entity

public class User extends DateRecord{
	String account;
	String passwordHash;
	String name;//昵称
	String avatar;//头像
	String email;//邮箱地址
	int xp;//经验值
	double coin;//金币
	
	

	@Column(unique=true)
	public String getAccount() {
		return account;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public String getName() {
		return name;
	}
	public String getAvatar() {
		return avatar;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getXp() {
		return xp;
	}
	public void setXp(int xp) {
		this.xp = xp;
	}
	public double getCoin() {
		return coin;
	}
	public void setCoin(double coin) {
		this.coin = coin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
