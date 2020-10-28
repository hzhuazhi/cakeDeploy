package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;
import java.util.List;

public class BankStrategyModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 归属银行卡ID：对应表tb_fr_bank的主键ID
     *
     * @mbggenerated
     */
    private Long bankId;

    /**
     * 别名
     *
     * @mbggenerated
     */
    private String alias;

    /**
     * 优先级：就是填入数字，数字越小的优先级越靠前
     *
     * @mbggenerated
     */
    private Integer priority;

    /**
     * 放量时间段：支持多个时间段，以#号分割
     *
     * @mbggenerated
     */
    private String openTimeSlot;

    /**
     * 微信日收款限量金额
     *
     * @mbggenerated
     */
    private String wxInDayMoney;

    /**
     * 微信月收款限量金额
     *
     * @mbggenerated
     */
    private String wxInMonthMoney;

    /**
     * 微信日收款限量次数
     *
     * @mbggenerated
     */
    private Integer wxInDayNum;

    /**
     * 支付宝日收款限量金额
     *
     * @mbggenerated
     */
    private String zfbInDayMoney;

    /**
     * 支付宝月收款限量金额
     *
     * @mbggenerated
     */
    private String zfbInMonthMoney;

    /**
     * 支付宝日收款限量次数
     *
     * @mbggenerated
     */
    private Integer zfbInDayNum;

    /**
     * 卡日收款限量金额
     *
     * @mbggenerated
     */
    private String cardInDayMoney;

    /**
     * 卡月收款限量金额
     *
     * @mbggenerated
     */
    private String cardInMonthMoney;

    /**
     * 卡日收款限量次数
     *
     * @mbggenerated
     */
    private Integer cardInDayNum;

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


    private Long accountId;
    private String bankCard;
    private String bankName;
    private String accountName;
    private List<Long> bankIdList;

    /**
     * 要修改的id
     *
     * @mbggenerated
     */
    private String ids;

    /**
     * 要修改的id
     *
     * @mbggenerated
     */
    private List<Long>  idList;


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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getOpenTimeSlot() {
        return openTimeSlot;
    }

    public void setOpenTimeSlot(String openTimeSlot) {
        this.openTimeSlot = openTimeSlot;
    }

    public String getWxInDayMoney() {
        return wxInDayMoney;
    }

    public void setWxInDayMoney(String wxInDayMoney) {
        this.wxInDayMoney = wxInDayMoney;
    }

    public String getWxInMonthMoney() {
        return wxInMonthMoney;
    }

    public void setWxInMonthMoney(String wxInMonthMoney) {
        this.wxInMonthMoney = wxInMonthMoney;
    }

    public Integer getWxInDayNum() {
        return wxInDayNum;
    }

    public void setWxInDayNum(Integer wxInDayNum) {
        this.wxInDayNum = wxInDayNum;
    }

    public String getZfbInDayMoney() {
        return zfbInDayMoney;
    }

    public void setZfbInDayMoney(String zfbInDayMoney) {
        this.zfbInDayMoney = zfbInDayMoney;
    }

    public String getZfbInMonthMoney() {
        return zfbInMonthMoney;
    }

    public void setZfbInMonthMoney(String zfbInMonthMoney) {
        this.zfbInMonthMoney = zfbInMonthMoney;
    }

    public Integer getZfbInDayNum() {
        return zfbInDayNum;
    }

    public void setZfbInDayNum(Integer zfbInDayNum) {
        this.zfbInDayNum = zfbInDayNum;
    }

    public String getCardInDayMoney() {
        return cardInDayMoney;
    }

    public void setCardInDayMoney(String cardInDayMoney) {
        this.cardInDayMoney = cardInDayMoney;
    }

    public String getCardInMonthMoney() {
        return cardInMonthMoney;
    }

    public void setCardInMonthMoney(String cardInMonthMoney) {
        this.cardInMonthMoney = cardInMonthMoney;
    }

    public Integer getCardInDayNum() {
        return cardInDayNum;
    }

    public void setCardInDayNum(Integer cardInDayNum) {
        this.cardInDayNum = cardInDayNum;
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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<Long> getBankIdList() {
        return bankIdList;
    }

    public void setBankIdList(List<Long> bankIdList) {
        this.bankIdList = bankIdList;
    }


    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}