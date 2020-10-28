package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class MerchantModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 归属的账号ID：对应表tb_hz_sys_account的主键ID，并且角色类型是卡商
     *
     * @mbggenerated
     */
    private Long accountId;

    /**
     * 名称/别名
     *
     * @mbggenerated
     */
    private String alias;

    /**
     * 总额：总共跑量的金额
     *
     * @mbggenerated
     */
    private String totalMoney;

    /**
     * 保底金额：卡商在我放至少要留有多少钱，才放量：保证金
     *
     * @mbggenerated
     */
    private String leastMoney;

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
     * 卡商类型：1我方卡商，2第三方卡商
     *
     * @mbggenerated
     */
    private Integer merchantType;

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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getLeastMoney() {
        return leastMoney;
    }

    public void setLeastMoney(String leastMoney) {
        this.leastMoney = leastMoney;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
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
}