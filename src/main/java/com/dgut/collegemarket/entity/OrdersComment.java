package com.dgut.collegemarket.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.dgut.collegemarket.util.DateRecord;


/**
 * @author æ∞»Ÿ
 *∂©µ•∆¿¬€
 */
@Entity
public class OrdersComment extends DateRecord{
	
	Orders orders;
	String comments;
	
	@ManyToOne(optional=false)
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
