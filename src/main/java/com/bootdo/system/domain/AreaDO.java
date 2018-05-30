package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 地区表
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-03-07 15:15:28
 */
public class AreaDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//索引ID
	private String areaId;
	//地区名称
	private String areaName;
	//地区父ID
	private String areaParentId;
	//排序
	private Integer areaSort;
	//地区深度，从1开始
	private Integer areaDeep;
	//是否删除0:未删除;1:已删除
	private Integer isDel;
	//序号
	private String seqNum;
	//创建时间
	private Long createTime;
	//修改时间
	private Long updateTime;

	/**
	 * 设置：索引ID
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	/**
	 * 获取：索引ID
	 */
	public String getAreaId() {
		return areaId;
	}
	/**
	 * 设置：地区名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：地区名称
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * 设置：地区父ID
	 */
	public void setAreaParentId(String areaParentId) {
		this.areaParentId = areaParentId;
	}
	/**
	 * 获取：地区父ID
	 */
	public String getAreaParentId() {
		return areaParentId;
	}
	/**
	 * 设置：排序
	 */
	public void setAreaSort(Integer areaSort) {
		this.areaSort = areaSort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getAreaSort() {
		return areaSort;
	}
	/**
	 * 设置：地区深度，从1开始
	 */
	public void setAreaDeep(Integer areaDeep) {
		this.areaDeep = areaDeep;
	}
	/**
	 * 获取：地区深度，从1开始
	 */
	public Integer getAreaDeep() {
		return areaDeep;
	}
	/**
	 * 设置：是否删除0:未删除;1:已删除
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	/**
	 * 获取：是否删除0:未删除;1:已删除
	 */
	public Integer getIsDel() {
		return isDel;
	}
	/**
	 * 设置：序号
	 */
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	/**
	 * 获取：序号
	 */
	public String getSeqNum() {
		return seqNum;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
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
}
