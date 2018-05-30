package com.bootdo.monitoring.vo;

import java.io.Serializable;


/**
 * 机器表 超类
 * 
 * @author dn
 * @email nan_du@163.com
 * @date 2018-02-10 21:38:03
 */
public class MonitoringVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户id
	private String userId;
	//用户
	private String userName;
	//机器总数量
	private String allNum;
	//开机机器数量
	private String openNum;
	//关闭机器数量
	private String closeNum;



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
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：用户
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户
	 */
	public String getUserName() {
		return userName;
	}


	public String getAllNum() {
		return allNum;
	}

	public void setAllNum(String allNum) {
		this.allNum = allNum;
	}

	public String getOpenNum() {
		return openNum;
	}

	public void setOpenNum(String openNum) {
		this.openNum = openNum;
	}

	public String getCloseNum() {
		return closeNum;
	}

	public void setCloseNum(String closeNum) {
		this.closeNum = closeNum;
	}
}
