package com.bootdo.monitoring.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 套餐表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-28 22:39:37
 */
public class SetMealDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户id
	private Long userId;
	//套餐名称
	private String mealName;
	//类型 0 流量模式 1时间模式
	private Integer type;
	//流量最大值1
	private String flow1;
	//流量最大值1
	private String flow2;
	//流量最大值1
	private String flow3;
	//流量最大值1
	private String flow4;
	//流量最大值5
	private String flow5;
	//保养周期
	private String cycle;
	//备用
	private String prop1;
	//备用字段
	private String porp2;
	//备用字段
	private String prop3;
	//备用字段
	private String prop4;
	//备用字段
	private String prop5;

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
	 * 设置：用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：套餐名称
	 */
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	/**
	 * 获取：套餐名称
	 */
	public String getMealName() {
		return mealName;
	}
	/**
	 * 设置：类型 0 流量模式 1时间模式
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型 0 流量模式 1时间模式
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：流量最大值1
	 */
	public void setFlow1(String flow1) {
		this.flow1 = flow1;
	}
	/**
	 * 获取：流量最大值1
	 */
	public String getFlow1() {
		return flow1;
	}
	/**
	 * 设置：流量最大值1
	 */
	public void setFlow2(String flow2) {
		this.flow2 = flow2;
	}
	/**
	 * 获取：流量最大值1
	 */
	public String getFlow2() {
		return flow2;
	}
	/**
	 * 设置：流量最大值1
	 */
	public void setFlow3(String flow3) {
		this.flow3 = flow3;
	}
	/**
	 * 获取：流量最大值1
	 */
	public String getFlow3() {
		return flow3;
	}
	/**
	 * 设置：流量最大值1
	 */
	public void setFlow4(String flow4) {
		this.flow4 = flow4;
	}
	/**
	 * 获取：流量最大值1
	 */
	public String getFlow4() {
		return flow4;
	}
	/**
	 * 设置：流量最大值5
	 */
	public void setFlow5(String flow5) {
		this.flow5 = flow5;
	}
	/**
	 * 获取：流量最大值5
	 */
	public String getFlow5() {
		return flow5;
	}
	/**
	 * 设置：保养周期
	 */
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	/**
	 * 获取：保养周期
	 */
	public String getCycle() {
		return cycle;
	}
	/**
	 * 设置：备用
	 */
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	/**
	 * 获取：备用
	 */
	public String getProp1() {
		return prop1;
	}
	/**
	 * 设置：备用字段
	 */
	public void setPorp2(String porp2) {
		this.porp2 = porp2;
	}
	/**
	 * 获取：备用字段
	 */
	public String getPorp2() {
		return porp2;
	}
	/**
	 * 设置：备用字段
	 */
	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}
	/**
	 * 获取：备用字段
	 */
	public String getProp3() {
		return prop3;
	}
	/**
	 * 设置：备用字段
	 */
	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}
	/**
	 * 获取：备用字段
	 */
	public String getProp4() {
		return prop4;
	}
	/**
	 * 设置：备用字段
	 */
	public void setProp5(String prop5) {
		this.prop5 = prop5;
	}
	/**
	 * 获取：备用字段
	 */
	public String getProp5() {
		return prop5;
	}
}
