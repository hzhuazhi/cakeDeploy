package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @Description 卡商审核补单申请的实体属性Bean
 * @Author yoko
 * @Date 2020/10/12 18:44
 * @Version 1.0
 */
public class MerchantReplenishModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 2233224301100L;

    public MerchantReplenishModel(){

    }

    /**
     * 主键ID
     */
    private long id;

    /**
     * 关联ID：对应支付平台要更新的补单申请的主键ID
     */
    private long linkId;

    /**
     * 归属卡商的账号ID：对应表tb_hz_sys_account的主键ID，并且角色类型是卡商
     */
    private long accountId;

    /**
     * 银行卡归属卡站点ID：对应表tb_hz_sys_account的主键ID，并且角色是卡站点
     */
    private long cardSiteId;

    /**
     * 请求的商户ID：对应表tb_fr_channel的主键ID
     */
    private long channelId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 参数名称：商家订单号；支付平台订单号
     */
    private String outTradeNo;

    /**
     * 订单金额
     */
    private String orderMoney;

    /**
     * 实际派单金额
     */
    private String distributionMoney;

    /**
     * 渠道金额
     */
    private String channelMoney;

    /**
     * 渠道上传的图片
     */
    private String pictureAds;

    /**
     * 审核金额：上游给出的金额；审核反馈的金额
     */
    private String checkMoney;

    /**
     * 审核上传的图片：上游给出的图片；审核反馈的图片
     */
    private String checkPictureAds;

    /**
     * 审核状态（上游反馈）：1初始化，2审核失败，3审核成功
     */
    private int checkStatus;

    /**
     * 审核失败缘由，审核失败的原因
     */
    private String checkInfo;

    /**
     * 处理状态：1初始化/未处理，2已处理
     */
    private int handleType;

    /**
     * 处理人：对应登录后台的账号ID
     */
    private long handlePeople;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否有效：0有效，1无效/删除
     */
    private int yn;

    /**
     * 卡商名称
     */
    private String merchantName;

    /**
     * 卡站点名称
     */
    private String cardSiteName;

    /**
     * 渠道/商户名称
     */
    private String channelName;

    /**
     * 处理人名称
     */
    private String handlePeopleName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getCardSiteId() {
        return cardSiteId;
    }

    public void setCardSiteId(long cardSiteId) {
        this.cardSiteId = cardSiteId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getChannelMoney() {
        return channelMoney;
    }

    public void setChannelMoney(String channelMoney) {
        this.channelMoney = channelMoney;
    }

    public String getPictureAds() {
        return pictureAds;
    }

    public void setPictureAds(String pictureAds) {
        this.pictureAds = pictureAds;
    }

    public String getCheckMoney() {
        return checkMoney;
    }

    public void setCheckMoney(String checkMoney) {
        this.checkMoney = checkMoney;
    }

    public String getCheckPictureAds() {
        return checkPictureAds;
    }

    public void setCheckPictureAds(String checkPictureAds) {
        this.checkPictureAds = checkPictureAds;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public int getHandleType() {
        return handleType;
    }

    public void setHandleType(int handleType) {
        this.handleType = handleType;
    }

    public long getHandlePeople() {
        return handlePeople;
    }

    public void setHandlePeople(long handlePeople) {
        this.handlePeople = handlePeople;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public int getYn() {
        return yn;
    }

    public void setYn(int yn) {
        this.yn = yn;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCardSiteName() {
        return cardSiteName;
    }

    public void setCardSiteName(String cardSiteName) {
        this.cardSiteName = cardSiteName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getHandlePeopleName() {
        return handlePeopleName;
    }

    public void setHandlePeopleName(String handlePeopleName) {
        this.handlePeopleName = handlePeopleName;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getDistributionMoney() {
        return distributionMoney;
    }

    public void setDistributionMoney(String distributionMoney) {
        this.distributionMoney = distributionMoney;
    }
}
