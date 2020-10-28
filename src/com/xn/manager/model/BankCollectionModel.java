package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class BankCollectionModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 归属银行卡ID：对应表tb_fn_bank的主键ID
     *
     * @mbggenerated
     */
    private Long bankId;

    /**
     * 订单号：这里订单号可能为空值，因为不一定匹配到订单
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 收款金额
     *
     * @mbggenerated
     */
    private String money;

    /**
     * 创建日期：存的日期格式20160530
     *
     * @mbggenerated
     */
    private Integer curday;

    /**
     * 创建所属小时：24小时制
     *
     * @mbggenerated
     */
    private Integer curhour;

    /**
     * 创建所属分钟：60分钟制
     *
     * @mbggenerated
     */
    private Integer curminute;

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



    /**
     * 银行名称
     *
     * @mbggenerated
     */
    private String bankName;
    /**
     * 银行卡
     *
     * @mbggenerated
     */
    private String bankCard;
    /**
     * 开户人
     *
     * @mbggenerated
     */
    private String accountName;
    /**
     * 卡商id
     *
     * @mbggenerated
     */
    private Long accountId;
    /**
     * 卡站点id
     *
     * @mbggenerated
     */
    private Long cardSiteId;

    /**
     * 卡商昵称
     *
     * @mbggenerated
     */
    private String accountIdName;

    /**
     * 卡站点昵称
     *
     * @mbggenerated
     */
    private String cardSiteIdName;

    /**
     * 总额度收款金额
     *
     * @mbggenerated
     */
    private String countMoney;
    /**
     * 开始时间
     *
     * @mbggenerated
     */
    private String beginTime;
    /**
     * 关闭时间
     *
     * @mbggenerated
     */
    private String endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getCurday() {
        return curday;
    }

    public void setCurday(Integer curday) {
        this.curday = curday;
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

    public Integer getCurhour() {
        return curhour;
    }

    public void setCurhour(Integer curhour) {
        this.curhour = curhour;
    }

    public Integer getCurminute() {
        return curminute;
    }

    public void setCurminute(Integer curminute) {
        this.curminute = curminute;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCardSiteId() {
        return cardSiteId;
    }

    public void setCardSiteId(Long cardSiteId) {
        this.cardSiteId = cardSiteId;
    }

    public String getAccountIdName() {
        return accountIdName;
    }

    public void setAccountIdName(String accountIdName) {
        this.accountIdName = accountIdName;
    }

    public String getCardSiteIdName() {
        return cardSiteIdName;
    }

    public void setCardSiteIdName(String cardSiteIdName) {
        this.cardSiteIdName = cardSiteIdName;
    }

    public String getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(String countMoney) {
        this.countMoney = countMoney;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}