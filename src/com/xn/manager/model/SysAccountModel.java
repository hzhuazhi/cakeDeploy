package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class SysAccountModel extends BasePage {
    /**
     * 主键ID：用户ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 账号
     *
     * @mbggenerated
     */
    private String accountNum;

    /**
     * 密码
     *
     * @mbggenerated
     */
    private String passWd;

    /**
     * 所属角色ID
     *
     * @mbggenerated
     */
    private Long roleId;

    /**
     * 账号昵称
     *
     * @mbggenerated
     */
    private String acName;

    /**
     * 账号联系人
     *
     * @mbggenerated
     */
    private String acContacts;

    /**
     * 联系电话
     *
     * @mbggenerated
     */
    private String acPhone;

    /**
     * 类型:目前没用到
     *
     * @mbggenerated
     */
    private Integer acType;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 是否启用：0初始化属于暂停状态，1表示暂停使用，2正常状态
     *
     * @mbggenerated
     */
    private Integer isEnable;

    /**
     * 创建人
     *
     * @mbggenerated
     */
    private Long createUser;

    /**
     * 更新人
     *
     * @mbggenerated
     */
    private Long updateUser;

    /**
     * 创建人的角色ID
     *
     * @mbggenerated
     */
    private Long createRole;

    /**
     * 更新人的角色ID
     *
     * @mbggenerated
     */
    private Long updateRole;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * TS时间戳
     *
     * @mbggenerated
     */
    private Date tstime;

    /**
     * 是否有效：0初始化，1失效/删除
     *
     * @mbggenerated
     */
    private Integer yn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcContacts() {
        return acContacts;
    }

    public void setAcContacts(String acContacts) {
        this.acContacts = acContacts;
    }

    public String getAcPhone() {
        return acPhone;
    }

    public void setAcPhone(String acPhone) {
        this.acPhone = acPhone;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Long getCreateRole() {
        return createRole;
    }

    public void setCreateRole(Long createRole) {
        this.createRole = createRole;
    }

    public Long getUpdateRole() {
        return updateRole;
    }

    public void setUpdateRole(Long updateRole) {
        this.updateRole = updateRole;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getTstime() {
        return tstime;
    }

    public void setTstime(Date tstime) {
        this.tstime = tstime;
    }

    public Integer getAcType() {
        return acType;
    }

    public void setAcType(Integer acType) {
        this.acType = acType;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}