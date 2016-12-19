package com.dgut.collegemarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.dgut.collegemarket.util.DateRecord;

@Entity
public class Admin extends DateRecord {
	String account;
	String passwordHash;
	
	@Column(unique=true)
	public String getAccount() {
		return account;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
}
