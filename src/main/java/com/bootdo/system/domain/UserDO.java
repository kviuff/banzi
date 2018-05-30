package com.bootdo.system.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UserDO implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    private Long userId;
    // 用户名
    private String username;
    // 用户真实姓名
    private String name;
    // 密码
    private String password;
    // 部门
    private Long deptId;
    private String deptName;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;
    // 状态 0:禁用，1:正常
    private Integer status;
    // 创建用户id
    private Long userIdCreate;
    // 创建用户账号
    private String userNameCreate;
    // 创建时间
    private Date gmtCreate;
    // 修改时间
    private Date gmtModified;
    //角色
    private List<Long> roleIds;
    //性别
    private Long sex;
    //出身日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    //图片ID
    private Long picId;
    //现居住地
    private String liveAddress;
    //爱好
    private String hobby;
    //省份
    private String province;
    //所在城市
    private String city;
    //所在地区
    private String district;
    //公司名称
    private String companyName;
    //位置
    private String area;
    //'负责人'
    private String companyUser;
    //'公司电话'
    private String companyMobile;
    //负责人电话
    private String personPhone;
    //客户等级  A级 B级 C级
    private String customerLevel;
    //客户类型
    private String customerType;
    //负责经销商
    private String responsibleForPhone;
    //附件路径，多个以，号分隔
    private String attachmentMore;
    //省id
    private String provinceId;
    //市id
    private String cityId;
    //县
    private String county;
    //县id
    private String countyId;
    //详细地址
    private String detailedAddress;
    //账号类型:0客户  1:经销商 2:销售 3:客服 4:总监
    private String accountnumberType;
    //职位
    private String position;
    //售前类型
    private String preSaleType;
    //售后类型
    private String customerServiceType;
    // 名字模糊查询
    private String sname;
    //订单总数
    private String ordercount;
    //订单总额
    private BigDecimal ordertotalprice;
    // 密码明文
    private String sign;
    //经销商id
    private Long agencyId;
    //总监id
    private Long chiefId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyUser() {
        return companyUser;
    }

    public void setCompanyUser(String companyUser) {
        this.companyUser = companyUser;
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getResponsibleForPhone() {
        return responsibleForPhone;
    }

    public void setResponsibleForPhone(String responsibleForPhone) {
        this.responsibleForPhone = responsibleForPhone;
    }

    public String getAttachmentMore() {
        return attachmentMore;
    }

    public void setAttachmentMore(String attachmentMore) {
        this.attachmentMore = attachmentMore;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getAccountnumberType() {
        return accountnumberType;
    }

    public void setAccountnumberType(String accountnumberType) {
        this.accountnumberType = accountnumberType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPreSaleType() {
        return preSaleType;
    }

    public void setPreSaleType(String preSaleType) {
        this.preSaleType = preSaleType;
    }

    public String getCustomerServiceType() {
        return customerServiceType;
    }

    public void setCustomerServiceType(String customerServiceType) {
        this.customerServiceType = customerServiceType;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(String ordercount) {
        this.ordercount = ordercount;
    }

    public BigDecimal getOrdertotalprice() {
        return ordertotalprice;
    }

    public void setOrdertotalprice(BigDecimal ordertotalprice) {
        this.ordertotalprice = ordertotalprice;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public Long getChiefId() {
        return chiefId;
    }

    public void setChiefId(Long chiefId) {
        this.chiefId = chiefId;
    }

    public String getUserNameCreate() {
        return userNameCreate;
    }

    public void setUserNameCreate(String userNameCreate) {
        this.userNameCreate = userNameCreate;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", roleIds=" + roleIds +
                ", sex=" + sex +
                ", birth=" + birth +
                ", picId=" + picId +
                ", liveAddress='" + liveAddress + '\'' +
                ", hobby='" + hobby + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", companyName='" + companyName + '\'' +
                ", area='" + area + '\'' +
                ", companyUser='" + companyUser + '\'' +
                ", companyMobile='" + companyMobile + '\'' +
                ", personPhone='" + personPhone + '\'' +
                ", customerLevel='" + customerLevel + '\'' +
                ", customerType='" + customerType + '\'' +
                ", responsibleForPhone='" + responsibleForPhone + '\'' +
                ", attachmentMore='" + attachmentMore + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", county='" + county + '\'' +
                ", countyId='" + countyId + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", accountnumberType='" + accountnumberType + '\'' +
                ", position='" + position + '\'' +
                ", preSaleType='" + preSaleType + '\'' +
                ", customerServiceType='" + customerServiceType + '\'' +
                '}';
    }
}
