package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;
import java.util.List;

public class ChannelBankModel extends BasePage {
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
     * 添加银行卡id
     *
     * @mbggenerated
     */
    private String bankIds;

    /**
     * 商户ID：对应表tb_fr_channel的主键ID
     *
     * @mbggenerated
     */
    private Long channelId;

    /**
     * 卡商银行卡主键ID：对应表tb_fr_bank的主键ID
     *
     * @mbggenerated
     */
    private Long bankId;

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

    /**
     * 卡的信息
     *
     * @mbggenerated
     */
    private String  bankCardInfo;

    /**
     * 银行卡号
     *
     * @mbggenerated
     */
    private String  bankCard;
    /**
     * 银行名称
     *
     * @mbggenerated
     */
    private String  bankName;
    /**
     * 开户人
     *
     * @mbggenerated
     */
    private String  accountName;

    /**
     * 银行卡id集合
     *
     * @mbggenerated
     */
    private List<Long> bankIdList;

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

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
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

    public String getBankCardInfo() {
        return bankCardInfo;
    }

    public void setBankCardInfo(String bankCardInfo) {
        this.bankCardInfo = bankCardInfo;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public List<Long> getBankIdList() {
        return bankIdList;
    }

    public void setBankIdList(List<Long> bankIdList) {
        this.bankIdList = bankIdList;
    }

    public String getBankIds() {
        return bankIds;
    }

    public void setBankIds(String bankIds) {
        this.bankIds = bankIds;
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
}