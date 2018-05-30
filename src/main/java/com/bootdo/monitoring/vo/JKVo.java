package com.bootdo.monitoring.vo;

import java.io.Serializable;


/**
 * 监控页面 超类
 * 
 * @author dn
 * @email nan_du@126.com
 * @date 2018-02-11 16:38:03
 */
public class JKVo implements Serializable {
	private static final long serialVersionUID = 1L;

	//用户id
	private String userId;
	//用户
	private String userName;
	//异常信息
	private String abnormalCondition;
	//机器类型:1-新风 2-饮水机
	private String machineType;
	//所属区域
	private String belongRegion;
	//异常机器数量
	private String reportNum;


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

	public String getAbnormalCondition() {
		return abnormalCondition;
	}

	public void setAbnormalCondition(String abnormalCondition) {
		this.abnormalCondition = abnormalCondition;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getBelongRegion() {
		return belongRegion;
	}

	public void setBelongRegion(String belongRegion) {
		this.belongRegion = belongRegion;
	}

	public String getReportNum() {
		return reportNum;
	}

	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}
}
