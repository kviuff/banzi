package com.bootdo.monitoring.domain;

import com.bootdo.lamp.vo.LampVo;
import net.sf.json.JSONArray;

import java.io.Serializable;
import java.util.List;


/**
 * 灯控制区域
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-11 18:21:25
 */
public class LightCtrVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//区域id
	private String id;
	//区名字
	private String name;
	//区域下item数组
	private List<LampDO> ChannelList;
	//机器编码(控制器id)
	private String monitorCode;
	//控制器地址
	private String controllerAddr;
	//灯账号
	private String lanerusername;
	//灯密码
	private String lanerpassword;

	private String userId;

	private String userName;




	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LampDO> getChannelList() {
		return ChannelList;
	}

	public void setChannelList(List<LampDO> channelList) {
		ChannelList = channelList;
	}

	public String getMonitorCode() {
		return monitorCode;
	}

	public void setMonitorCode(String monitorCode) {
		this.monitorCode = monitorCode;
	}

	public String getControllerAddr() {
		return controllerAddr;
	}

	public void setControllerAddr(String controllerAddr) {
		this.controllerAddr = controllerAddr;
	}

	public String getLanerusername() {
		return lanerusername;
	}

	public void setLanerusername(String lanerusername) {
		this.lanerusername = lanerusername;
	}

	public String getLanerpassword() {
		return lanerpassword;
	}

	public void setLanerpassword(String lanerpassword) {
		this.lanerpassword = lanerpassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
