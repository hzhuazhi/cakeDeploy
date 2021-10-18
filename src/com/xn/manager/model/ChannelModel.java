package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class ChannelModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 商户名称/别名
     *
     * @mbggenerated
     */
    private String alias;

    /**
     * 商户秘钥
     *
     * @mbggenerated
     */
    private String secretKey;

    /**
     * 银行卡绑定类型：1无需绑定银行卡，2需要绑定银行卡
     *
     * @mbggenerated
     */
    private Integer bankBindingType;

    /**
     * 渠道类型：1代收，2大包，3代付
     */
    private Integer channelType;

    /**
     * 代收金额订单范围：每单代收的金额范围以“-”进行分割的
     */
    private String inMoneyRange;

    /**
     * 代付金额订单范围：每单代付的金额范围以“-”进行分割的
     */
    private String outMoneyRange;

    /**
     * 出码后银行卡金额的锁定时间
     */
    private Integer moneyLockTime;

    /**
     * 出码后订单的支付时间
     */
    private Integer invalidTimeNum;

    /**
     * 代付方式：0初始化，1手动转账，2API转账
     */
    private Integer replacePayType;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;
    /**
     * merchantid 字符串
     *
     * @mbggenerated
     */
    private String merchantStr;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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

    public Integer getBankBindingType() {
        return bankBindingType;
    }

    public void setBankBindingType(Integer bankBindingType) {
        this.bankBindingType = bankBindingType;
    }

    public String getMerchantStr() {
        return merchantStr;
    }

    public void setMerchantStr(String merchantStr) {
        this.merchantStr = merchantStr;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public String getInMoneyRange() {
        return inMoneyRange;
    }

    public void setInMoneyRange(String inMoneyRange) {
        this.inMoneyRange = inMoneyRange;
    }

    public String getOutMoneyRange() {
        return outMoneyRange;
    }

    public void setOutMoneyRange(String outMoneyRange) {
        this.outMoneyRange = outMoneyRange;
    }

    public Integer getMoneyLockTime() {
        return moneyLockTime;
    }

    public void setMoneyLockTime(Integer moneyLockTime) {
        this.moneyLockTime = moneyLockTime;
    }

    public Integer getInvalidTimeNum() {
        return invalidTimeNum;
    }

    public void setInvalidTimeNum(Integer invalidTimeNum) {
        this.invalidTimeNum = invalidTimeNum;
    }

    public Integer getReplacePayType() {
        return replacePayType;
    }

    public void setReplacePayType(Integer replacePayType) {
        this.replacePayType = replacePayType;
    }
}