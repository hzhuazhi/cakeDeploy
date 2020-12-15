package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class ChannelBankPoolModel extends BasePage {
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
    private String bankCardInfo;
    private String bankCard;
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
    private String openTimeSlot;
    private String acName;
    private String bankName;
    private String accountName;
    private Integer isOk;
    private Integer isArrears;
    private Integer heartbeatStatus;
    private Integer mbUseStatus;
    private Integer bkUseStatus;
    private Integer checkStatus;
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

    public String getOpenTimeSlot() {
        return openTimeSlot;
    }

    public void setOpenTimeSlot(String openTimeSlot) {
        this.openTimeSlot = openTimeSlot;
    }

    public Integer getIsOk() {
        return isOk;
    }

    public void setIsOk(Integer isOk) {
        this.isOk = isOk;
    }

    public Integer getIsArrears() {
        return isArrears;
    }

    public void setIsArrears(Integer isArrears) {
        this.isArrears = isArrears;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
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

    public String getBankIds() {
        return bankIds;
    }

    public void setBankIds(String bankIds) {
        this.bankIds = bankIds;
    }

    public Integer getHeartbeatStatus() {
        return heartbeatStatus;
    }

    public void setHeartbeatStatus(Integer heartbeatStatus) {
        this.heartbeatStatus = heartbeatStatus;
    }

    public Integer getMbUseStatus() {
        return mbUseStatus;
    }

    public void setMbUseStatus(Integer mbUseStatus) {
        this.mbUseStatus = mbUseStatus;
    }

    public Integer getBkUseStatus() {
        return bkUseStatus;
    }

    public void setBkUseStatus(Integer bkUseStatus) {
        this.bkUseStatus = bkUseStatus;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }
}