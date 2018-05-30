package com.bootdo.maintainManger.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 维修记录表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-09 18:55:50
 */
public class RepairLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//维修记录id
	private Integer repairId;
	//用户id
	private String userId;
	//用户账号
	private String userName;
	//维修机器id
	private Long monitoringId;
	//机器编号
	private String monitorCode;
	//机器型号
	private String monitorModeCode;
	//所属区域
	private String belongRegion;
	//所属楼层
	private String belongFloor;
	//简述故障
	private String failureCause;
	//维修时间
	private String maintainTime;
	//报修时间
	private String repairTime;
	//回访情况
	private String returnVisit;
	//派单时间
	private String dispatchTime;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;
	//备用字段1 客户满意度
	private String prop1;
	//备用字段2 更换配
	private String prop2;
	//备用字段3 钱数
	private String prop3;
	//备用字段4 设备类型
	private String prop4;
	//备用字段5
	private String prop5;
	//备用字段6
	private String prop6;
	//维修数量 临时字段
	private String maintainNum;

	/**
	 * 设置：维修记录id
	 */
	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}
	/**
	 * 获取：维修记录id
	 */
	public Integer getRepairId() {
		return repairId;
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
	 * 设置：用户账号
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户账号
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：维修机器id
	 */
	public void setMonitoringId(Long monitoringId) {
		this.monitoringId = monitoringId;
	}
	/**
	 * 获取：维修机器id
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
	 * 设置：简述故障
	 */
	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}
	/**
	 * 获取：简述故障
	 */
	public String getFailureCause() {
		return failureCause;
	}
	/**
	 * 设置：维修时间
	 */
	public void setMaintainTime(String maintainTime) {
		this.maintainTime = maintainTime;
	}
	/**
	 * 获取：维修时间
	 */
	public String getMaintainTime() {
		return maintainTime;
	}
	/**
	 * 设置：报修时间
	 */
	public void setRepairTime(String repairTime) {
		this.repairTime = repairTime;
	}
	/**
	 * 获取：报修时间
	 */
	public String getRepairTime() {
		return repairTime;
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
	 * 设置：派单时间
	 */
	public void setDispatchTime(String dispatchTime) {
		this.dispatchTime = dispatchTime;
	}
	/**
	 * 获取：派单时间
	 */
	public String getDispatchTime() {
		return dispatchTime;
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

	public String getMaintainNum() {
		return maintainNum;
	}

	public void setMaintainNum(String maintainNum) {
		this.maintainNum = maintainNum;
	}
}
