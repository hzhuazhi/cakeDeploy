package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class MobileCardModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 归属卡商ID
     *
     * @mbggenerated
     */
    private Long merchantId;

    /**
     * 手机卡名称
     *
     * @mbggenerated
     */
    private String cardName;

    /**
     * 使用了哪个归属人的名字：这张卡身份证上面的那个人办理的
     *
     * @mbggenerated
     */
    private String useName;

    /**
     * 手机号
     *
     * @mbggenerated
     */
    private String phoneNum;

    /**
     * 办理人的身份证号
     *
     * @mbggenerated
     */
    private String idCard;

    /**
     * 手机号具体与手机型号绑定
     *
     * @mbggenerated
     */
    private String bindingMobile;

    /**
     * 每月座机费
     *
     * @mbggenerated
     */
    private String cost;

    /**
     * 手机号归属省份
     *
     * @mbggenerated
     */
    private String province;

    /**
     * 手机号归属城市
     *
     * @mbggenerated
     */
    private String city;

    /**
     * 是否欠费：1未欠费，2欠费
     *
     * @mbggenerated
     */
    private Integer isArrears;

    /**
     * 心跳状态：1初始化异常，2正常
     *
     * @mbggenerated
     */
    private Integer heartbeatStatus;

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
     * 归属卡商ID
     *
     * @mbggenerated
     */
    private Long accountId;

    /**
     * 银行卡归属卡站点ID
     *
     * @mbggenerated
     */
    private Long cardSiteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBindingMobile() {
        return bindingMobile;
    }

    public void setBindingMobile(String bindingMobile) {
        this.bindingMobile = bindingMobile;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
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

    public Integer getIsArrears() {
        return isArrears;
    }

    public void setIsArrears(Integer isArrears) {
        this.isArrears = isArrears;
    }

    public Integer getHeartbeatStatus() {
        return heartbeatStatus;
    }

    public void setHeartbeatStatus(Integer heartbeatStatus) {
        this.heartbeatStatus = heartbeatStatus;
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

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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
}