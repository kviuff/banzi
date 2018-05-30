package com.bootdo.monitoring.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 机器表历史记录表
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-02-10 21:38:03
 */
public class HistoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//机器id
	private String monitorId;
	//机器编号
	private String monitorCode;
	//机器型号
	private String monitorModeCode;
	//机器数据详情
	private String monitorDetailJson;
	//添加时间
	private Long createTime;
	//修改时间
	private Long updateTime;
	//时长
	private String prop1;
	//原水
	private String prop2;
	//净水
	private String prop3;
	//pm2.5
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
	 * 设置：机器id
	 */
	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}
	/**
	 * 获取：机器id
	 */
	public String getMonitorId() {
		return monitorId;
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
	 * 设置：机器数据详情
	 */
	public void setMonitorDetailJson(String monitorDetailJson) {
		this.monitorDetailJson = monitorDetailJson;
	}
	/**
	 * 获取：机器数据详情
	 */
	public String getMonitorDetailJson() {
		return monitorDetailJson;
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
