package com.bootdo.monitoring.vo;

import java.io.Serializable;


/**
 * 曲线图 超类
 * 
 * @author dn
 * @email nan_du@126.com
 * @date 2018-02-11 16:38:03
 */
public class EchartVo implements Serializable {
	private static final long serialVersionUID = 1L;

	//查询日期
	private String day;
	//机器编码
	private String monitorId;
	//原水
	private String raw;
	//净水
	private String purification;
	//pm
	private String pm;
	//小时
	private int hour;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getPurification() {
		return purification;
	}

	public void setPurification(String purification) {
		this.purification = purification;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getMonitorId() {
		return monitorId;
	}

	public void setMonitorId(String monitorId) {
		this.monitorId = monitorId;
	}
}
