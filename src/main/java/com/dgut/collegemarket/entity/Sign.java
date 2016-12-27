package com.dgut.collegemarket.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.dgut.collegemarket.util.BaseEntity;




/**
 * @author 泽恩
 *签到表
 */
@Entity
public class Sign extends BaseEntity{

	User user;

	Date createDate;//订阅时间
	int  xp;
 

	@ManyToOne(optional=false)
	public User getUser() {
		return user;
		
		
		
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@PrePersist
	void onPrePersist(){
		createDate = new Date();
	}

}
