package com.dgut.collegemarket.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.dgut.collegemarket.util.CreateDateRecord;
import com.dgut.collegemarket.util.DateRecord;


/**
 * @author 泽恩
 *私信消息表
 */
@Entity
public class Message extends CreateDateRecord{
	
	User sender;//发送者
	User receiver;//接收者
	String content;//文字
	String picture;//图片地址
	int type;//图文类型判断 1为图 0为文
	
	@ManyToOne(optional=false)
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	@ManyToOne(optional=false)
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	
}
