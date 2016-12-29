package com.dgut.collegemarket.util;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class CreateDateRecord extends BaseEntity {
	Date createDate;
	
	@Column(updatable=false)
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