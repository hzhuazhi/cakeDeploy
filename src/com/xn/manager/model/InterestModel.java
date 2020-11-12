package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class InterestModel  extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 名称/别名
     *
     * @mbggenerated
     */
    private String alias;

    /**
     * 账号
     *
     * @mbggenerated
     */
    private String accountNum;
    /**
     * 可用余额
     *
     * @mbggenerated
     */
    private String availableMoney;

    /**
     * 登录密码
     *
     * @mbggenerated
     */
    private String passWd;

    /**
     * 提款密码
     *
     * @mbggenerated
     */
    private String withdrawPassWd;

    /**
     * 所属角色ID
     *
     * @mbggenerated
     */
    private Long roleId;

    /**
     * 账号名称
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
     * 账号类型
     *
     * @mbggenerated
     */
    private Integer acType;

    /**
     * 总额：总共跑量的金额
     *
     * @mbggenerated
     */
    private String totalMoney;

    /**
     * 余额：跑量的金额累加，渠道提现的金额扣减
     *
     * @mbggenerated
     */
    private String balance;

    /**
     * 锁定金额
     *
     * @mbggenerated
     */
    private String lockMoney;

    /**
     * 总收益金额
     *
     * @mbggenerated
     */
    private String totalProfit;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * 使用状态:1初始化有效正常使用，2无效暂停使用
     *
     * @mbggenerated
     */
    private Integer useStatus;

    /**
     * 创建人ID
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * 创建人归属角色ID
     *
     * @mbggenerated
     */
    private Long createRoleId;

    /**
     * 更新人ID
     *
     * @mbggenerated
     */
    private Long updateUserId;

    /**
     * 更新人归属角色ID
     *
     * @mbggenerated
     */
    private Long updateRoleId;

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
     * TS时间
     *
     * @mbggenerated
     */
    private Date tsTime;

    /**
     * 是否有效：0有效，1无效/删除
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public String getWithdrawPassWd() {
        return withdrawPassWd;
    }

    public void setWithdrawPassWd(String withdrawPassWd) {
        this.withdrawPassWd = withdrawPassWd;
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



    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLockMoney() {
        return lockMoney;
    }

    public void setLockMoney(String lockMoney) {
        this.lockMoney = lockMoney;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getCreateRoleId() {
        return createRoleId;
    }

    public void setCreateRoleId(Long createRoleId) {
        this.createRoleId = createRoleId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Long getUpdateRoleId() {
        return updateRoleId;
    }

    public void setUpdateRoleId(Long updateRoleId) {
        this.updateRoleId = updateRoleId;
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

    public Date getTsTime() {
        return tsTime;
    }

    public void setTsTime(Date tsTime) {
        this.tsTime = tsTime;
    }

    public Integer getAcType() {
        return acType;
    }

    public void setAcType(Integer acType) {
        this.acType = acType;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public String getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(String availableMoney) {
        this.availableMoney = availableMoney;
    }
}