package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;
import java.util.List;

public class MerchantModel extends BasePage {

    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private long id;

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
    private long roleId;

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
     * 收益金额：加减都要
     *
     * @mbggenerated
     */
    private String profit;

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
    private int acType;

    /**
     * 总额：总共跑量的金额
     *
     * @mbggenerated
     */
    private String totalMoney;

    /**
     * 可用余额
     *
     * @mbggenerated
     */
    private String availableMoney;

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
    private int merchantType;

    /**
     * 卡商运营类型/运营性质：1 代付，2代收
     *
     * @mbggenerated
     */
    private int operateType;

    /**
     * 总收益金额
     *
     * @mbggenerated
     */
    private String totalProfit;
    /**
     * 手续比例
     *
     * @mbggenerated
     */
    private String serviceCharge;

    /**
     * 代付款类型：1手动付款，2API自动付款
     *
     * @mbggenerated
     */
    private int payType;

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
    private int useStatus;

    /**
     * 创建人ID
     *
     * @mbggenerated
     */
    private long createUserId;

    /**
     * 创建人归属角色ID
     *
     * @mbggenerated
     */
    private long createRoleId;

    /**
     * 更新人ID
     *
     * @mbggenerated
     */
    private long updateUserId;

    /**
     * 更新人归属角色ID
     *
     * @mbggenerated
     */
    private long updateRoleId;

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
    private int yn;

    /***
     * 集合数据id
     * @mbggenerated
     */
    private List<Long> merchantIdList;

    /**
     * 今日收益
     */
    private String todayProfit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
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

    public int getAcType() {
        return acType;
    }

    public void setAcType(int acType) {
        this.acType = acType;
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

    public int getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(int merchantType) {
        this.merchantType = merchantType;
    }

    public int getOperateType() {
        return operateType;
    }

    public void setOperateType(int operateType) {
        this.operateType = operateType;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(int useStatus) {
        this.useStatus = useStatus;
    }

    public long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(long createUserId) {
        this.createUserId = createUserId;
    }

    public long getCreateRoleId() {
        return createRoleId;
    }

    public void setCreateRoleId(long createRoleId) {
        this.createRoleId = createRoleId;
    }

    public long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public long getUpdateRoleId() {
        return updateRoleId;
    }

    public void setUpdateRoleId(long updateRoleId) {
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

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public List<Long> getMerchantIdList() {
        return merchantIdList;
    }

    public void setMerchantIdList(List<Long> merchantIdList) {
        this.merchantIdList = merchantIdList;
    }

    public String getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(String availableMoney) {
        this.availableMoney = availableMoney;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getTodayProfit() {
        return todayProfit;
    }

    public void setTodayProfit(String todayProfit) {
        this.todayProfit = todayProfit;
    }
}