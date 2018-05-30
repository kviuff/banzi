package com.bootdo.maintainManger.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 保养记录表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-11 17:00:54
 */
public class KeepLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//保养记录表id
	private Long keepId;
	//用户id
	private String userId;
	//用户
	private String userName;
	//机器id
	private Long monitoringId;
	//机器编号
	private String monitorCode;
	//机器型号
	private String monitorModeCode;
	//所属区域
	private String belongRegion;
	//所属楼层
	private String belongFloor;
	//流量情况
	private String cumulativeFlow;
	//机器类型:1-新风 2-饮水机
	private String machineType;
	//保养时间
	private String keepTime;
	//到期维护时间
	private String endKeepTime;
	//回访情况
	private String returnVisit;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//备注字段 客户满意度
	private String prop1;
	//备注字段 更换配件
	private String prop2;
	//备注字段 钱数
	private String prop3;
	//备注字段 维护机器数量
	private String prop4;
	//备注字段
	private String prop5;

	private Long[] ids;

	/**
	 * 设置：保养记录表id
	 */
	public void setKeepId(Long keepId) {
		this.keepId = keepId;
	}
	/**
	 * 获取：保养记录表id
	 */
	public Long getKeepId() {
		return keepId;
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
	/**
	 * 设置：机器id
	 */
	public void setMonitoringId(Long monitoringId) {
		this.monitoringId = monitoringId;
	}
	/**
	 * 获取：机器id
	 */
	public Long getMonitoringId() {
		return monitoringId;
	}
	/**
	 * 设置：机器编号
	 */
	public void setMonitorCode(String monitorCode) {
		this.monitorCode = monitorCode;
	}
	/**
	 * 获取：机器编号
	 */
	public String getMonitorCode() {
		return monitorCode;
	}
	/**
	 * 设置：机器型号
	 */
	public void setMonitorModeCode(String monitorModeCode) {
		this.monitorModeCode = monitorModeCode;
	}
	/**
	 * 获取：机器型号
	 */
	public String getMonitorModeCode() {
		return monitorModeCode;
	}
	/**
	 * 设置：所属区域
	 */
	public void setBelongRegion(String belongRegion) {
		this.belongRegion = belongRegion;
	}
	/**
	 * 获取：所属区域
	 */
	public String getBelongRegion() {
		return belongRegion;
	}
	/**
	 * 设置：所属楼层
	 */
	public void setBelongFloor(String belongFloor) {
		this.belongFloor = belongFloor;
	}
	/**
	 * 获取：所属楼层
	 */
	public String getBelongFloor() {
		return belongFloor;
	}
	/**
	 * 设置：流量情况
	 */
	public void setCumulativeFlow(String cumulativeFlow) {
		this.cumulativeFlow = cumulativeFlow;
	}
	/**
	 * 获取：流量情况
	 */
	public String getCumulativeFlow() {
		return cumulativeFlow;
	}
	/**
	 * 设置：机器类型:1-新风 2-饮水机
	 */
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	/**
	 * 获取：机器类型:1-新风 2-饮水机
	 */
	public String getMachineType() {
		return machineType;
	}
	/**
	 * 设置：保养时间
	 */
	public void setKeepTime(String keepTime) {
		this.keepTime = keepTime;
	}
	/**
	 * 获取：保养时间
	 */
	public String getKeepTime() {
		return keepTime;
	}
	/**
	 * 设置：到期维护时间
	 */
	public void setEndKeepTime(String endKeepTime) {
		this.endKeepTime = endKeepTime;
	}
	/**
	 * 获取：到期维护时间
	 */
	public String getEndKeepTime() {
		return endKeepTime;
	}
	/**
	 * 设置：回访情况
	 */
	public void setReturnVisit(String returnVisit) {
		this.returnVisit = returnVisit;
	}
	/**
	 * 获取：回访情况
	 */
	public String getReturnVisit() {
		return returnVisit;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：备注字段
	 */
	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}
	/**
	 * 获取：备注字段
	 */
	public String getProp1() {
		return prop1;
	}
	/**
	 * 设置：备注字段
	 */
	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}
	/**
	 * 获取：备注字段
	 */
	public String getProp2() {
		return prop2;
	}
	/**
	 * 设置：备注字段
	 */
	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}
	/**
	 * 获取：备注字段
	 */
	public String getProp3() {
		return prop3;
	}
	/**
	 * 设置：备注字段
	 */
	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}
	/**
	 * 获取：备注字段
	 */
	public String getProp4() {
		return prop4;
	}
	/**
	 * 设置：备注字段
	 */
	public void setProp5(String prop5) {
		this.prop5 = prop5;
	}
	/**
	 * 获取：备注字段
	 */
	public String getProp5() {
		return prop5;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
}
