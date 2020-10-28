package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.util.Date;

public class OrderModel extends BasePage {
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
     * 订单号
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 订单类型：1微信转卡，2支付宝转卡，3卡转卡
     *
     * @mbggenerated
     */
    private Integer orderType;

    /**
     * 订单金额
     *
     * @mbggenerated
     */
    private String orderMoney;
    /**
     * 实际派发金额
     *
     * @mbggenerated
     */
    private String distributionMoney;

    /**
     * 收款的二维码地址：转码之后的二维码
     *
     * @mbggenerated
     */
    private String qrCode;

    /**
     * 商家订单号
     *
     * @mbggenerated
     */
    private String outTradeNo;

    /**
     * 订单状态：1初始化，2超时/失败，3有质疑，4成功
     *
     * @mbggenerated
     */
    private Integer orderStatus;

    /**
     * 失效时间
     *
     * @mbggenerated
     */
    private Date invalidTime;

    /**
     * 同步的接口地址:我方的同步地址
     *
     * @mbggenerated
     */
    private String notifyUrl;

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
     * 银行码/银行简码
     *
     * @mbggenerated
     */
    private String bankCode;

    /**
     * 归属手机号ID：对应表tb_fn_mobile_card的主键ID
     *
     * @mbggenerated
     */
    private Long mobileCardId;

    /**
     * 银行卡归属手机号
     *
     * @mbggenerated
     */
    private String phoneNum;

    /**
     * 归属卡商的账号ID：对应表tb_hz_sys_account的主键ID，并且角色类型是卡商
     *
     * @mbggenerated
     */
    private Long accountId;
    /**
     * 卡组的账号ID：对应表tb_hz_sys_account的主键ID，并且角色类型是卡商
     *
     * @mbggenerated
     */
    private Long cardSiteId;

    /**
     * 银行归属卡商名称
     *
     * @mbggenerated
     */
    private String merchantName;

    /**
     * 请求的商户ID：对应表tb_fr_channel的主键ID
     *
     * @mbggenerated
     */
    private Long channelId;

    /**
     * 商户名称
     *
     * @mbggenerated
     */
    private String channelName;

    /**
     * 是否是补单：1初始化不是补单，2是补单
     *
     * @mbggenerated
     */
    private Integer replenishType;

    /**
     * 补充数据的类型：1初始化，2补充数据失败（其它原因等..），3补充数据成功
     *
     * @mbggenerated
     */
    private Integer workType;

    /**
     * 数据说明：做解说用的
     *
     * @mbggenerated
     */
    private String dataExplain;

    /**
     * 备注
     *
     * @mbggenerated
     */
    private String remark;

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
     * 运行计算次数
     *
     * @mbggenerated
     */
    private Integer runNum;

    /**
     * 运行计算状态：0初始化，1锁定，2计算失败，3计算成功
     *
     * @mbggenerated
     */
    private Integer runStatus;

    /**
     * 发送次数
     *
     * @mbggenerated
     */
    private Integer sendNum;

    /**
     * 发送状态：0初始化，1锁定，2计算失败，3计算成功
     *
     * @mbggenerated
     */
    private Integer sendStatus;

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
     *结束时间
     *
     * @mbggenerated
     */
    private Integer endCurday;
    /**
     * 开始时间
     *
     * @mbggenerated
     */
    private Integer beginCurday;
    /**
     * 任务数
     *
     * @mbggenerated
     */
    private Integer taskNum;

    /**
     * 成功数
     *
     * @mbggenerated
     */
    private Integer successNum;
    /**
     * 订单金额
     *
     * @mbggenerated
     */
    private Integer taskMoney;
    /**
     * 放量数量成功
     *
     * @mbggenerated
     */
    private Integer numMoney;
    /**
     * 任务成功率
     *
     * @mbggenerated
     */
    private String  successRateTask;

    /**
     * 金额成功率
     *
     * @mbggenerated
     */
    private String  successRateMoney;

    /**
     * 卡商
     *
     * @mbggenerated
     */
    private String  acName;



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

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
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

    public Long getMobileCardId() {
        return mobileCardId;
    }

    public void setMobileCardId(Long mobileCardId) {
        this.mobileCardId = mobileCardId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDataExplain() {
        return dataExplain;
    }

    public void setDataExplain(String dataExplain) {
        this.dataExplain = dataExplain;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getReplenishType() {
        return replenishType;
    }

    public void setReplenishType(Integer replenishType) {
        this.replenishType = replenishType;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
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

    public Integer getRunNum() {
        return runNum;
    }

    public void setRunNum(Integer runNum) {
        this.runNum = runNum;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public Integer getSendNum() {
        return sendNum;
    }

    public void setSendNum(Integer sendNum) {
        this.sendNum = sendNum;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }

    public Integer getEndCurday() {
        return endCurday;
    }

    public void setEndCurday(Integer endCurday) {
        this.endCurday = endCurday;
    }

    public Integer getBeginCurday() {
        return beginCurday;
    }

    public void setBeginCurday(Integer beginCurday) {
        this.beginCurday = beginCurday;
    }

    public Long getCardSiteId() {
        return cardSiteId;
    }

    public void setCardSiteId(Long cardSiteId) {
        this.cardSiteId = cardSiteId;
    }

    public String getDistributionMoney() {
        return distributionMoney;
    }

    public void setDistributionMoney(String distributionMoney) {
        this.distributionMoney = distributionMoney;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(Integer taskMoney) {
        this.taskMoney = taskMoney;
    }

    public Integer getNumMoney() {
        return numMoney;
    }

    public void setNumMoney(Integer numMoney) {
        this.numMoney = numMoney;
    }

    public String getSuccessRateTask() {
        return successRateTask;
    }

    public void setSuccessRateTask(String successRateTask) {
        this.successRateTask = successRateTask;
    }

    public String getSuccessRateMoney() {
        return successRateMoney;
    }

    public void setSuccessRateMoney(String successRateMoney) {
        this.successRateMoney = successRateMoney;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }
}