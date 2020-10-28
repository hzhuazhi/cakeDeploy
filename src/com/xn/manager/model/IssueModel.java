package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @Description 下发的实体属性Bean
 * @Author yoko
 * @Date 2020/9/27 14:56
 * @Version 1.0
 */
public class IssueModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 2233223301100L;

    public IssueModel(){

    }

    /**
     * 主键ID
     */
    private long id;


    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 支付平台订单号：下游上报的订单号
     */
    private String outTradeNo;

    /**
     * 订单金额
     */
    private String orderMoney;

    /**
     * 银行名称/归属开户行
     */
    private String bankName;

    /**
     * 银行卡账号/银行卡号
     */
    private String bankCard;

    /**
     * 开户名
     */
    private String accountName;

    /**
     * 订单状态：1初始化，2超时/失败/审核驳回，3成功
     */
    private int orderStatus;

    /**
     * 充值记录银行卡转账图片凭证
     */
    private String pictureAds;


    /**
     * 我方银行卡信息备注：假如归属类型：我方/平台，填写我方银行卡的信息
     */
    private String myBankInfo;

    /**
     * 订单分配归属类型：1归属卡商，2归属平台
     */
    private int ascriptionType;

    /**
     * 是否已分配完毕归属：1初始化/未分配，2已分配
     */
    private int isDistribution;

    /**
     * 是否已归集完毕：1初始化/未归集完毕，2已归集完毕；此状态：是归属类型属于平台方，平台方需要向卡商发布充值订单，发布完毕，如果卡商都已经充值完毕到我方卡，则修改此状态，修改成归集完毕的状态
     */
    private int isComplete;

    /**
     * 审核状态：1初始化，2审核收款失败，3审核收款成功
     */
    private int checkStatus;

    /**
     *审核失败缘由，审核失败的原因
     */
    private String checkInfo;

    /**
     * 数据说明：做解说用的
     */
    private String dataExplain;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建日期：存的日期格式20160530
     */
    private int curday;

    /**
     * 创建所属小时：24小时制
     */
    private int curhour;

    /**
     * 创建所属分钟：60分钟制
     */
    private int curminute;

    /**
     *运行计算次数
     */
    private int runNum;

    /**
     * 运行计算状态：0初始化，1锁定，2计算失败，3计算成功
     */
    private int runStatus;

    /**
     *发送次数
     */
    private int sendNum;

    /**
     * 发送状态：0初始化，1锁定，2计算失败，3计算成功
     */
    private int sendStatus;


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
     * SQL查询条件
     */
    private int whereCheckStatus;

    private int curdayStart;
    private int curdayEnd;

    private String checkStatusStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPictureAds() {
        return pictureAds;
    }

    public void setPictureAds(String pictureAds) {
        this.pictureAds = pictureAds;
    }

    public String getMyBankInfo() {
        return myBankInfo;
    }

    public void setMyBankInfo(String myBankInfo) {
        this.myBankInfo = myBankInfo;
    }

    public int getAscriptionType() {
        return ascriptionType;
    }

    public void setAscriptionType(int ascriptionType) {
        this.ascriptionType = ascriptionType;
    }

    public int getIsDistribution() {
        return isDistribution;
    }

    public void setIsDistribution(int isDistribution) {
        this.isDistribution = isDistribution;
    }

    public int getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(int isComplete) {
        this.isComplete = isComplete;
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

    public int getCurday() {
        return curday;
    }

    public void setCurday(int curday) {
        this.curday = curday;
    }

    public int getCurhour() {
        return curhour;
    }

    public void setCurhour(int curhour) {
        this.curhour = curhour;
    }

    public int getCurminute() {
        return curminute;
    }

    public void setCurminute(int curminute) {
        this.curminute = curminute;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public int getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(int runStatus) {
        this.runStatus = runStatus;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
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

    public int getWhereCheckStatus() {
        return whereCheckStatus;
    }

    public void setWhereCheckStatus(int whereCheckStatus) {
        this.whereCheckStatus = whereCheckStatus;
    }


    public int getCurdayStart() {
        return curdayStart;
    }

    public void setCurdayStart(int curdayStart) {
        this.curdayStart = curdayStart;
    }

    public int getCurdayEnd() {
        return curdayEnd;
    }

    public void setCurdayEnd(int curdayEnd) {
        this.curdayEnd = curdayEnd;
    }

    public String getCheckStatusStr() {
        return checkStatusStr;
    }

    public void setCheckStatusStr(String checkStatusStr) {
        this.checkStatusStr = checkStatusStr;
    }
}
