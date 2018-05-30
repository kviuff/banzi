package com.bootdo.ordermanager.domain;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 订单表
 * 
 * @author kviuff
 * @email kviuff@163.com
 */
public class OrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// 订单总数
	private int orderCount;
	// 订单总额
	private BigDecimal orderAmount;

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getOrderCount() {

		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
}
