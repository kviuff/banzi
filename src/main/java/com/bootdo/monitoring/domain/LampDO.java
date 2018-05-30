package com.bootdo.monitoring.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 灯
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-28 21:26:39
 */
public class LampDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//灯返回的id
	private String returnId;
	//控制器地址
	private String controller;
	//灯名字
	private String name;
	//灯的address
	private String address;
	//区域
	private String belongRegion;
	//楼层
	private String belongFloor;
	//用户id
	private String userId;

	/**
	 * 设置：灯返回的id
	 */
	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}
	/**
	 * 获取：灯返回的id
	 */
	public String getReturnId() {
		return returnId;
	}
	/**
	 * 设置：控制器地址
	 */
	public void setController(String controller) {
		this.controller = controller;
	}
	/**
	 * 获取：控制器地址
	 */
	public String getController() {
		return controller;
	}
	/**
	 * 设置：灯名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：灯名字
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：灯的address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：灯的address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：区域
	 */
	public void setBelongRegion(String belongRegion) {
		this.belongRegion = belongRegion;
	}
	/**
	 * 获取：区域
	 */
	public String getBelongRegion() {
		return belongRegion;
	}
	/**
	 * 设置：楼层
	 */
	public void setBelongFloor(String belongFloor) {
		this.belongFloor = belongFloor;
	}
	/**
	 * 获取：楼层
	 */
	public String getBelongFloor() {
		return belongFloor;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
}
