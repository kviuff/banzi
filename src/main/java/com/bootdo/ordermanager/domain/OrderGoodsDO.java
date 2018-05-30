package com.bootdo.ordermanager.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 订单产品
 * 
 * @author kviuff
 * @email kviuff@163.com
 */
public class OrderGoodsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//订单编号
	private String orderSn;
	//产品名称
	private String goodsName;
	//产品型号
	private String goosSn;
	//产品单价
	private BigDecimal goodsPrice;
	//销售数量
	private String goodsNum;
	//品牌
	private String brand;
	//产品总额
	private String tatalPrice;
	//添加时间
	private Long createTime;
	//修改时间
	private Long updateTime;
	//保存数据库用户ID
	private String prop1;
	// 储存备注
	private String prop2;
	//备用字段3
	private String prop3;
	//备用字段4
	private String prop4;
	//备用字段5
	private String prop5;
	//备用字段6
	private String prop6;
	//备用字段7
	private String prop7;
	//备用字段8
	private String prop8;
	//备用字段9
	private String prop9;
	//备用字段10
	private String prop10;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderSn() {
		return orderSn;
	}
	/**
	 * 设置：产品名称
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * 设置：产品型号
	 */
	public void setGoosSn(String goosSn) {
		this.goosSn = goosSn;
	}
	/**
	 * 获取：产品型号
	 */
	public String getGoosSn() {
		return goosSn;
	}
	/**
	 * 设置：产品单价
	 */
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	/**
	 * 获取：产品单价
	 */
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	/**
	 * 设置：销售数量
	 */
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	/**
	 * 获取：销售数量
	 */
	public String getGoodsNum() {
		return goodsNum;
	}
	/**
	 * 设置：品牌
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * 获取：品牌
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * 设置：产品总额
	 */
	public void setTatalPrice(String tatalPrice) {
		this.tatalPrice = tatalPrice;
	}
	/**
	 * 获取：产品总额
	 */
	public String getTatalPrice() {
		return tatalPrice;
	}
	/**
	 * 设置：添加时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Long getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Long getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：备用字段1
	 */
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	/**
	 * 获取：备用字段1
	 */
	public String getProp1() {
		return prop1;
	}
	/**
	 * 设置：备用字段2
	 */
	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}
	/**
	 * 获取：备用字段2
	 */
	public String getProp2() {
		return prop2;
	}
	/**
	 * 设置：备用字段3
	 */
	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}
	/**
	 * 获取：备用字段3
	 */
	public String getProp3() {
		return prop3;
	}
	/**
	 * 设置：备用字段4
	 */
	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}
	/**
	 * 获取：备用字段4
	 */
	public String getProp4() {
		return prop4;
	}
	/**
	 * 设置：备用字段5
	 */
	public void setProp5(String prop5) {
		this.prop5 = prop5;
	}
	/**
	 * 获取：备用字段5
	 */
	public String getProp5() {
		return prop5;
	}
	/**
	 * 设置：备用字段6
	 */
	public void setProp6(String prop6) {
		this.prop6 = prop6;
	}
	/**
	 * 获取：备用字段6
	 */
	public String getProp6() {
		return prop6;
	}
	/**
	 * 设置：备用字段7
	 */
	public void setProp7(String prop7) {
		this.prop7 = prop7;
	}
	/**
	 * 获取：备用字段7
	 */
	public String getProp7() {
		return prop7;
	}
	/**
	 * 设置：备用字段8
	 */
	public void setProp8(String prop8) {
		this.prop8 = prop8;
	}
	/**
	 * 获取：备用字段8
	 */
	public String getProp8() {
		return prop8;
	}
	/**
	 * 设置：备用字段9
	 */
	public void setProp9(String prop9) {
		this.prop9 = prop9;
	}
	/**
	 * 获取：备用字段9
	 */
	public String getProp9() {
		return prop9;
	}
	/**
	 * 设置：备用字段10
	 */
	public void setProp10(String prop10) {
		this.prop10 = prop10;
	}
	/**
	 * 获取：备用字段10
	 */
	public String getProp10() {
		return prop10;
	}
}
