package com.dgut.collegemarket.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.dgut.collegemarket.util.DateRecord;


/**
 * @author 泽恩
 *订单表
 */
@Entity
public class Orders extends DateRecord{
	User buyer;//购买者
	Goods goods;//商品
	Contact contact;//联系信息
	double price;//价格
	int quantity;//商品数量
	String note;
	boolean isPayOnline;
	int state;//状态（1表示订单刚提交，未接单；2表示已经接单，3表示已配送，4表示确认收货，交易完成，5表示已经评价， 6表示请求取消订单 ，7表示卖方同意取消，8表示卖方拒绝取消） 
	
	
	@ManyToOne(optional=false)
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	
	@ManyToOne(optional=false)
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	@ManyToOne(optional=false)
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public boolean isPayOnline() {
		return isPayOnline;
	}
	public void setPayOnline(boolean isPayOnline) {
		this.isPayOnline = isPayOnline;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}


	
}
