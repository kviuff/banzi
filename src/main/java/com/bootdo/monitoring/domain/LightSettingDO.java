package com.bootdo.monitoring.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 灯光显示参数
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-18 19:54:59
 */
public class LightSettingDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long userId;
	//教室照度
	private String classroomIllumination;
	//教室照度均匀度
	private String classroomUniformity;
	//黑板照度
	private String blackboardIllumination;
	//黑板照度均匀度
	private String blackboardUniformity;
	//眩光
	private String glare;
	//显色指数
	private String renderingIndex;
	//色温
	private String colorTemperature;
	//备用
	private String prop;

	/**
	 * 设置：
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：教室照度
	 */
	public void setClassroomIllumination(String classroomIllumination) {
		this.classroomIllumination = classroomIllumination;
	}
	/**
	 * 获取：教室照度
	 */
	public String getClassroomIllumination() {
		return classroomIllumination;
	}
	/**
	 * 设置：教室照度均匀度
	 */
	public void setClassroomUniformity(String classroomUniformity) {
		this.classroomUniformity = classroomUniformity;
	}
	/**
	 * 获取：教室照度均匀度
	 */
	public String getClassroomUniformity() {
		return classroomUniformity;
	}
	/**
	 * 设置：黑板照度
	 */
	public void setBlackboardIllumination(String blackboardIllumination) {
		this.blackboardIllumination = blackboardIllumination;
	}
	/**
	 * 获取：黑板照度
	 */
	public String getBlackboardIllumination() {
		return blackboardIllumination;
	}
	/**
	 * 设置：黑板照度均匀度
	 */
	public void setBlackboardUniformity(String blackboardUniformity) {
		this.blackboardUniformity = blackboardUniformity;
	}
	/**
	 * 获取：黑板照度均匀度
	 */
	public String getBlackboardUniformity() {
		return blackboardUniformity;
	}
	/**
	 * 设置：眩光
	 */
	public void setGlare(String glare) {
		this.glare = glare;
	}
	/**
	 * 获取：眩光
	 */
	public String getGlare() {
		return glare;
	}
	/**
	 * 设置：显色指数
	 */
	public void setRenderingIndex(String renderingIndex) {
		this.renderingIndex = renderingIndex;
	}
	/**
	 * 获取：显色指数
	 */
	public String getRenderingIndex() {
		return renderingIndex;
	}
	/**
	 * 设置：色温
	 */
	public void setColorTemperature(String colorTemperature) {
		this.colorTemperature = colorTemperature;
	}
	/**
	 * 获取：色温
	 */
	public String getColorTemperature() {
		return colorTemperature;
	}
	/**
	 * 设置：备用
	 */
	public void setProp(String prop) {
		this.prop = prop;
	}
	/**
	 * 获取：备用
	 */
	public String getProp() {
		return prop;
	}
}
