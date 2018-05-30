package com.bootdo.monitoring.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 灯控制器表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-11 18:21:25
 */
public class LightCtrDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户id
	private Long userId;
	//用户名
	private String userName;
	//控制器id
	private String controllerId;
	//控制器地址
	private String controllerAddr;
	//灯账号
	private String lanerusername;
	//灯密码
	private String lanerpassword;
	//备用字段
	private String prop1;
	//备用字段
	private String prop2;
	//备用字段
	private String prop3;
	//备用字段
	private String prop4;
	//备用字段
	private String prop5;
	//临时数量
	private Long num;

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
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：控制器id
	 */
	public void setControllerId(String controllerId) {
		this.controllerId = controllerId;
	}
	/**
	 * 获取：控制器id
	 */
	public String getControllerId() {
		return controllerId;
	}
	/**
	 * 设置：控制器地址
	 */
	public void setControllerAddr(String controllerAddr) {
		this.controllerAddr = controllerAddr;
	}
	/**
	 * 获取：控制器地址
	 */
	public String getControllerAddr() {
		return controllerAddr;
	}
	/**
	 * 设置：灯账号
	 */
	public void setLanerusername(String lanerusername) {
		this.lanerusername = lanerusername;
	}
	/**
	 * 获取：灯账号
	 */
	public String getLanerusername() {
		return lanerusername;
	}
	/**
	 * 设置：灯密码
	 */
	public void setLanerpassword(String lanerpassword) {
		this.lanerpassword = lanerpassword;
	}
	/**
	 * 获取：灯密码
	 */
	public String getLanerpassword() {
		return lanerpassword;
	}
	/**
	 * 设置：备用字段
	 */
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	/**
	 * 获取：备用字段
	 */
	public String getProp1() {
		return prop1;
	}
	/**
	 * 设置：备用字段
	 */
	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}
	/**
	 * 获取：备用字段
	 */
	public String getProp2() {
		return prop2;
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

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}
}
