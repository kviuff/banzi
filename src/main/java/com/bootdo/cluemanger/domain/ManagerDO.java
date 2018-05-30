package com.bootdo.cluemanger.domain;

import com.bootdo.common.utils.DateUtils;

import java.io.Serializable;



/**
 * 线索管理
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-03-19 19:37:26
 */
public class ManagerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//公司名称
	private String companyName;
	//负责人
	private String userName;
	//负责人电话
	private String personCharge;
	//对接人
	private String personPicup;
	//单位地址
	private String companyAddress;
	//拜访时间，拜访详情
	private String timeDetail;
	//添加时间
	private Long createTime;
	//修改时间
	private Long updateTime;
	//添加ID
	private String prop1;
	//添加用户名
	private String prop2;
	//备用字段
	private String prop3;
	//备用字段4
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
	// 拜访时间
	private String visitTime;
	// 拜访详情
	private String visitDetail;
	// 创建时间字符串，页面使用
	private String createTimeStr;
	// 拜访标题
	private String visitTitle;

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
	 * 设置：公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取：公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置：负责人
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：负责人
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：负责人电话
	 */
	public void setPersonCharge(String personCharge) {
		this.personCharge = personCharge;
	}
	/**
	 * 获取：负责人电话
	 */
	public String getPersonCharge() {
		return personCharge;
	}
	/**
	 * 设置：对接人
	 */
	public void setPersonPicup(String personPicup) {
		this.personPicup = personPicup;
	}
	/**
	 * 获取：对接人
	 */
	public String getPersonPicup() {
		return personPicup;
	}
	/**
	 * 设置：单位地址
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	/**
	 * 获取：单位地址
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}
	/**
	 * 设置：拜访时间，拜访详情
	 */
	public void setTimeDetail(String timeDetail) {
		this.timeDetail = timeDetail;
	}
	/**
	 * 获取：拜访时间，拜访详情
	 */
	public String getTimeDetail() {
		return timeDetail;
	}
	/**
	 * 设置：添加时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
		if (null != createTime) {
			createTimeStr = DateUtils.formatLongToStr(createTime);
		}
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

	public String getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitDetail() {
		return visitDetail;
	}

	public void setVisitDetail(String visitDetail) {
		this.visitDetail = visitDetail;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getVisitTitle() {
		return visitTitle;
	}

	public void setVisitTitle(String visitTitle) {
		this.visitTitle = visitTitle;
	}
}
