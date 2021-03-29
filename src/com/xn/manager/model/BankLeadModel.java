package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.List;

/**
 * @Description 主卡：原始卡卡信息的实体属性Bean
 * @Author yoko
 * @Date 2021/2/26 17:01
 * @Version 1.0
 */
public class BankLeadModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private long id;

    /**
     * 别名
     *
     * @mbggenerated
     */
    private String alias;

    /**
     * 卡商id
     *
     * @mbggenerated
     */
    private long accountId;

    /**
     * 归属手机号ID：对应表tb_fn_mobile_card的主键ID
     *
     * @mbggenerated
     */
    private long mobileCardId;


    /**
     * 手机号
     *
     * @mbggenerated
     */
    private String phoneNum;
    private String accountNum;

    /**
     * 银行卡归属类型：对应表tb_fr_bank_type的主键ID
     *
     * @mbggenerated
     */
    private long bankTypeId;


    /**
     * 卡站点id
     *
     * @mbggenerated
     */
    private long cardSiteId;
    /**
     * 卡站点id
     *
     * @mbggenerated
     */
    private long merchantSiteId;

    /**
     * 银行名称/归属开户行
     *
     * @mbggenerated
     */
    private String bankName;

    /**
     * 银行卡账号/银行卡号
     *
     * @mbggenerated
     */
    private String bankCard;

    /**
     * 银行支行/支行名称
     *
     * @mbggenerated
     */
    private String subbranchName;

    /**
     * 开户名
     *
     * @mbggenerated
     */
    private String accountName;

    /**
     * 银行码
     *
     * @mbggenerated
     */
    private String bankCode;

    /**
     * 开户地省份/所在省份
     *
     * @mbggenerated
     */
    private String province;

    /**
     * 开户地城市/所在城市
     *
     * @mbggenerated
     */
    private String city;

    /**
     * 回调对照凭证短信来源:例如招商银行的是95555
     *
     * @mbggenerated
     */
    private String smsNum;

    /**
     * 回调对照凭证银行卡尾号：收到短信后，短信内容尾号xxxx收款
     *
     * @mbggenerated
     */
    private String lastNum;

    /**
     * 银行卡余额
     */
    private String balance;

    /**
     * 收款日限金额
     *
     * @mbggenerated
     */
    private String inDayMoney;

    /**
     * 收款月限金额
     *
     * @mbggenerated
     */
    private String inMonthMoney;

    /**
     * 转账日限金额
     *
     * @mbggenerated
     */
    private String outDayMoney;

    /**
     * 转账月限金额
     *
     * @mbggenerated
     */
    private String outMonthMoney;

    /**
     * 归属手机卡是否欠费：1未欠费，2欠费
     *
     * @mbggenerated
     */
    private int isArrears;

    /**
     * 检测状态：1初始化正常，2不正常
     *
     * @mbggenerated
     */
    private int checkStatus;

    /**
     * 数据说明：检测被限制的原因:task跑日月总限制，如果被限制，连续给出订单失败会填充被限制的原因
     *
     * @mbggenerated
     */
    private String dataExplain;

    /**
     * 是否测试通过：1未通过，2通过；收到银行卡短信，并且解析短信模板配置正确
     *
     * @mbggenerated
     */
    private int isOk;

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
     * 是否有效：0有效，1无效/删除
     *
     * @mbggenerated
     */
    private long merchantId;

    /**
     * bankStrategy 放量表已有的id
     *
     * @mbggenerated
     */
    private List<Long> bankStrategyList;

    /**
     * 卡商
     */
    private String merchantName;


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

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getMobileCardId() {
        return mobileCardId;
    }

    public void setMobileCardId(long mobileCardId) {
        this.mobileCardId = mobileCardId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public long getBankTypeId() {
        return bankTypeId;
    }

    public void setBankTypeId(long bankTypeId) {
        this.bankTypeId = bankTypeId;
    }

    public long getCardSiteId() {
        return cardSiteId;
    }

    public void setCardSiteId(long cardSiteId) {
        this.cardSiteId = cardSiteId;
    }

    public long getMerchantSiteId() {
        return merchantSiteId;
    }

    public void setMerchantSiteId(long merchantSiteId) {
        this.merchantSiteId = merchantSiteId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getSubbranchName() {
        return subbranchName;
    }

    public void setSubbranchName(String subbranchName) {
        this.subbranchName = subbranchName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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

    public String getSmsNum() {
        return smsNum;
    }

    public void setSmsNum(String smsNum) {
        this.smsNum = smsNum;
    }

    public String getLastNum() {
        return lastNum;
    }

    public void setLastNum(String lastNum) {
        this.lastNum = lastNum;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getInDayMoney() {
        return inDayMoney;
    }

    public void setInDayMoney(String inDayMoney) {
        this.inDayMoney = inDayMoney;
    }

    public String getInMonthMoney() {
        return inMonthMoney;
    }

    public void setInMonthMoney(String inMonthMoney) {
        this.inMonthMoney = inMonthMoney;
    }

    public String getOutDayMoney() {
        return outDayMoney;
    }

    public void setOutDayMoney(String outDayMoney) {
        this.outDayMoney = outDayMoney;
    }

    public String getOutMonthMoney() {
        return outMonthMoney;
    }

    public void setOutMonthMoney(String outMonthMoney) {
        this.outMonthMoney = outMonthMoney;
    }

    public int getIsArrears() {
        return isArrears;
    }

    public void setIsArrears(int isArrears) {
        this.isArrears = isArrears;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getDataExplain() {
        return dataExplain;
    }

    public void setDataExplain(String dataExplain) {
        this.dataExplain = dataExplain;
    }

    public int getIsOk() {
        return isOk;
    }

    public void setIsOk(int isOk) {
        this.isOk = isOk;
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

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public List<Long> getBankStrategyList() {
        return bankStrategyList;
    }

    public void setBankStrategyList(List<Long> bankStrategyList) {
        this.bankStrategyList = bankStrategyList;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
