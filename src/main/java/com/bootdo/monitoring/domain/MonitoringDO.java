package com.bootdo.monitoring.domain;

import java.io.Serializable;



/**
 * 机器表
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-02-10 21:38:03
 */
public class MonitoringDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户id
	private String userId;
	//用户
	private String userName;
	//异区域
	private String anomalousRegion;
	//客户id
	private String customerId;
	//客户名称
	private String customerName;
	//异常信息
	private String abnormalCondition;
	//机器类型:1-新风 2-饮水机  3-灯 4老新风机
	private String machineType;
	//添加时间
	private Long createTime;
	//修改时间
	private Long updateTime;
	//异常时间
	private Long abnormalTime;
	//机器编号
	private String monitorCode;
	//机器型号
	private String monitorModeCode;
	//所属区域
	private String belongRegion;
	//所属楼层
	private String belongFloor;
	//累计流量
	private String cumulativeFlow;
	//时长
	private String length;
	//参数
	private String paramJson;
	//状态：1-开机  2-关机 对于新风状态值说明：0 关机  1开机 对于水的状态值说明 0开机 1关机
	private String status;
	//档位:1 2 3
	private String stall;
	//备用字段1 维修状态 0未报修 已经维修结束回访 1已报修未派单 2已派单维修中
	private String prop1;
	//备用字段2 报修时间
	private String prop2;
	//备用字段3 派单时间
	private String prop3;
	//备用字段4 报修日志表id
	private String prop4;
	//备用字段5 新风机安装时间
	private String prop5;
	//备用字段6  新风机 到期维护时间
	private String prop6;
	//备用字段7  新风机保养间隔周期
	private String prop7;
	//备用字段8  套餐表id
	private String prop8;
	//备用字段9
	private String prop9;
	//备用字段10  IP地址
	private String prop10;
	// 品牌
	private String brand;
	// 机器名称
	private String monitorname;
	//查询字段 保养剩余天数
	private Integer searchTime;
	//查询字段 如果值是1 则查询异常记录
	private String searchType;
	// 用户名
	private String lanerUserName;
	// 密码
	private String lanerPassword;
	// 控制器地址
	private String contollerAddr;
	// 智能灯的区id
	private String returnid;
	// 定时关机时间
	private String closeTime;
	//滤芯剩余
	private String flow1;

	private String flow2;

	private String flow3;

	private String flow4;

	private String flow5;

	//参数
	private String paramJson1;


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
	/**
	 * 设置：异区域
	 */
	public void setAnomalousRegion(String anomalousRegion) {
		this.anomalousRegion = anomalousRegion;
	}
	/**
	 * 获取：异区域
	 */
	public String getAnomalousRegion() {
		return anomalousRegion;
	}
	/**
	 * 设置：客户id
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取：客户id
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * 设置：客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：客户名称
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：异常信息
	 */
	public void setAbnormalCondition(String abnormalCondition) {
		this.abnormalCondition = abnormalCondition;
	}
	/**
	 * 获取：异常信息
	 */
	public String getAbnormalCondition() {
		return abnormalCondition;
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
	 * 设置：异常时间
	 */
	public void setAbnormalTime(Long abnormalTime) {
		this.abnormalTime = abnormalTime;
	}
	/**
	 * 获取：异常时间
	 */
	public Long getAbnormalTime() {
		return abnormalTime;
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
	 * 设置：累计流量
	 */
	public void setCumulativeFlow(String cumulativeFlow) {
		this.cumulativeFlow = cumulativeFlow;
	}
	/**
	 * 获取：累计流量
	 */
	public String getCumulativeFlow() {
		return cumulativeFlow;
	}
	/**
	 * 设置：时长
	 */
	public void setLength(String length) {
		this.length = length;
	}
	/**
	 * 获取：时长
	 */
	public String getLength() {
		return length;
	}
	/**
	 * 设置：参数
	 */
	public void setParamJson(String paramJson) {
		this.paramJson = paramJson;
	}
	/**
	 * 获取：参数
	 */
	public String getParamJson() {
		return paramJson;
	}
	/**
	 * 设置：状态：1-开机  2-关机
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1-开机  2-关机
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：档位:1 2 3
	 */
	public void setStall(String stall) {
		this.stall = stall;
	}
	/**
	 * 获取：档位:1 2 3
	 */
	public String getStall() {
		return stall;
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

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public Integer getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Integer searchTime) {
		this.searchTime = searchTime;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMonitorname() {
		return monitorname;
	}

	public void setMonitorname(String monitorname) {
		this.monitorname = monitorname;
	}

	public String getLanerUserName() {
		return lanerUserName;
	}

	public void setLanerUserName(String lanerUserName) {
		this.lanerUserName = lanerUserName;
	}

	public String getLanerPassword() {
		return lanerPassword;
	}

	public void setLanerPassword(String lanerPassword) {
		this.lanerPassword = lanerPassword;
	}

	public String getContollerAddr() {
		return contollerAddr;
	}

	public void setContollerAddr(String contollerAddr) {
		this.contollerAddr = contollerAddr;
	}

	public String getReturnid() {
		return returnid;
	}

	public void setReturnid(String returnid) {
		this.returnid = returnid;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getFlow1() {
		return flow1;
	}

	public void setFlow1(String flow1) {
		this.flow1 = flow1;
	}

	public String getFlow4() {
		return flow4;
	}

	public void setFlow4(String flow4) {
		this.flow4 = flow4;
	}

	public String getFlow2() {
		return flow2;
	}

	public void setFlow2(String flow2) {
		this.flow2 = flow2;
	}

	public String getFlow3() {
		return flow3;
	}

	public void setFlow3(String flow3) {
		this.flow3 = flow3;
	}

	public String getFlow5() {
		return flow5;
	}

	public void setFlow5(String flow5) {
		this.flow5 = flow5;
	}

	public String getParamJson1() {
		return paramJson1;
	}

	public void setParamJson1(String paramJson1) {
		this.paramJson1 = paramJson1;
	}
}
