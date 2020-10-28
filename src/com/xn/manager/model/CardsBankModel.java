package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class CardsBankModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 手机号
     *
     * @mbggenerated
     */
    private String phoneNum;

    /**
     * 归属卡商ID：对应表tb_hz_sys_account的主键ID，并且角色是卡商
     *
     * @mbggenerated
     */
    private Long merchantId;

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
     * 补充状态：1、初始化  2、完成 
     *
     * @mbggenerated
     */
    private Integer supplyType;

    /**
     * 测试状态：1、初始化 2是发送过测试 3、技术未获取到数据，请重新发送 4、测试成功
     *
     * @mbggenerated
     */
    private Integer isTest;

    /**
     * 测试金额
     *
     * @mbggenerated
     */
    private String testMoney;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

    public Integer getSupplyType() {
        return supplyType;
    }

    public void setSupplyType(Integer supplyType) {
        this.supplyType = supplyType;
    }

    public Integer getIsTest() {
        return isTest;
    }

    public void setIsTest(Integer isTest) {
        this.isTest = isTest;
    }

    public String getTestMoney() {
        return testMoney;
    }

    public void setTestMoney(String testMoney) {
        this.testMoney = testMoney;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}