package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

/**
 * @Description 中转站的实体属性Bean
 * @Author yoko
 * @Date 2020/12/8 16:38
 * @Version 1.0
 */
public class TransferModel extends BasePage {

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
     * 联系电话
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
     * 保底金额
     */
    private String leastMoney;

    /**
     * 余额
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
     * 收益金额：加减都要
     *
     * @mbggenerated
     */
    private String profit;

    /**
     * 手续费
     */
    private String serviceCharge;

    /**
     * 总进账
     *
     * @mbggenerated
     */
    private String totalInMoney;

    /**
     * 总转账
     */
    private String totalOutMoney;

    /**
     * 中转站后台余额
     */
    private String transferMoney;

    /**
     * 对接接口地址
     */
    private String interfaceAds;

    /**
     * 对接秘钥
     */
    private String secretKey;

    /**
     * 白名单IP：以英文逗号分割
     */
    private String whiteIp;

    /**
     * 中转站类型：1手动拉单，2API拉单
     */
    private int transferType;

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
    private String createTime;

    /**
     * 更新时间
     *
     * @mbggenerated
     */
    private String updateTime;

    /**
     * TS时间
     *
     * @mbggenerated
     */
    private String tsTime;

    /**
     * 是否有效：0有效，1无效/删除
     *
     * @mbggenerated
     */
    private int yn;

    /**
     * 中转站名称
     */
    private String transferName;

    /**
     * 角色名称
     */
    private String roleName;


    /**
     * 密码-重置密码-新
     */
    private String resetPassWd;

    /**
     * 提现密码-重置提现密码-新
     */
    private String resetWithdrawPassWd;

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

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getTotalInMoney() {
        return totalInMoney;
    }

    public void setTotalInMoney(String totalInMoney) {
        this.totalInMoney = totalInMoney;
    }

    public String getTotalOutMoney() {
        return totalOutMoney;
    }

    public void setTotalOutMoney(String totalOutMoney) {
        this.totalOutMoney = totalOutMoney;
    }

    public String getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(String transferMoney) {
        this.transferMoney = transferMoney;
    }

    public String getInterfaceAds() {
        return interfaceAds;
    }

    public void setInterfaceAds(String interfaceAds) {
        this.interfaceAds = interfaceAds;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getWhiteIp() {
        return whiteIp;
    }

    public void setWhiteIp(String whiteIp) {
        this.whiteIp = whiteIp;
    }

    public int getTransferType() {
        return transferType;
    }

    public void setTransferType(int transferType) {
        this.transferType = transferType;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTsTime() {
        return tsTime;
    }

    public void setTsTime(String tsTime) {
        this.tsTime = tsTime;
    }

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public String getTransferName() {
        return transferName;
    }

    public void setTransferName(String transferName) {
        this.transferName = transferName;
    }

    public String getResetPassWd() {
        return resetPassWd;
    }

    public void setResetPassWd(String resetPassWd) {
        this.resetPassWd = resetPassWd;
    }

    public String getResetWithdrawPassWd() {
        return resetWithdrawPassWd;
    }

    public void setResetWithdrawPassWd(String resetWithdrawPassWd) {
        this.resetWithdrawPassWd = resetWithdrawPassWd;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
