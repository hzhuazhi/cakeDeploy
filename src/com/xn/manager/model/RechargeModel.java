package com.xn.manager.model;

import com.xn.common.page.BasePage;

import java.io.Serializable;

/**
 * @Description 卡商充值的实体属性Bean
 * @Author yoko
 * @Date 2020/9/28 18:04
 * @Version 1.0
 */
public class RechargeModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 2233223301101L;

    public RechargeModel(){

    }


    /**
     * 主键ID
     */
    private long id;

    /**
     * 归属的账号ID：对应表tb_hz_sys_account的主键ID，并且角色类型是卡商
     */
    private long accountId;

    /**
     * 银行卡归属卡站点ID：对应表tb_hz_sys_account的主键ID，并且角色是卡站点
     */
    private long cardSiteId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单类型：1预付款订单，2平台发起订单，3下发订单
     */
    private int orderType;

    /**
     * 下发表的订单号：对应表tb_fr_issue的order_no；也可以把它称之为关联订单号
     */
    private String issueOrderNo;

    /**
     * 订单金额
     */
    private String orderMoney;

    /**
     * 订单状态：1初始化，2超时/失败/审核驳回，3成功
     */
    private int orderStatus;

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
     * 银行卡转账图片凭证
     */
    private String pictureAds;

    /**
     * 操作状态：1初始化，2系统放弃，3手动放弃，4锁定
     */
    private int operateStatus;

    /**
     * 是否需要数据同步：1不需要同步，2需要同步
     */
    private int isSynchro;

    /**
     * 审核状态：1初始化，2审核收款失败，3审核收款成功
     */
    private int checkStatus;

    /**
     * 审核失败缘由，审核失败的原因
     */
    private String checkInfo;

    /**
     * 锁定人：具体点击锁定的人的账号
     */
    private long lockAccountId;

    /**
     * 系统运行自动放弃的时间：订单分配完毕之后，订单类型是：下发分配订单，如果卡商在超过这个时间没有进行放弃或者锁定这样的操作，则自动修改成放弃。
     */
    private String invalidTime;

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
     * 运行计算状态：：0初始化，1锁定，2计算失败，3计算成功
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
     * 失效金额运算次数
     */
    private int invalidNum;

    /**
     * 失效金额运算状态：0初始化，1锁定，2计算失败，3计算成功
     */
    private int invalidStatus;

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

    private int curdayStart;
    private int curdayEnd;

    /**
     * SQL查询条件
     */
    private String invalidTimeStr;

    /**
     * SQL-查询
     */
    private String operateStatusStr;

    private int limitNum;

    /**
     * SQL-条件
     */
    private int whereOperateStatus;

    private int operateStatusStart;
    private int operateStatusEnd;

    /**
     * 卡商名称
     */
    private String merchantName;

    private String cardSiteName;

    private String lockAccountName;

    private String checkStatusStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getIssueOrderNo() {
        return issueOrderNo;
    }

    public void setIssueOrderNo(String issueOrderNo) {
        this.issueOrderNo = issueOrderNo;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getPictureAds() {
        return pictureAds;
    }

    public void setPictureAds(String pictureAds) {
        this.pictureAds = pictureAds;
    }

    public int getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(int operateStatus) {
        this.operateStatus = operateStatus;
    }

    public int getIsSynchro() {
        return isSynchro;
    }

    public void setIsSynchro(int isSynchro) {
        this.isSynchro = isSynchro;
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

    public long getLockAccountId() {
        return lockAccountId;
    }

    public void setLockAccountId(long lockAccountId) {
        this.lockAccountId = lockAccountId;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
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

    public int getInvalidNum() {
        return invalidNum;
    }

    public void setInvalidNum(int invalidNum) {
        this.invalidNum = invalidNum;
    }

    public int getInvalidStatus() {
        return invalidStatus;
    }

    public void setInvalidStatus(int invalidStatus) {
        this.invalidStatus = invalidStatus;
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

    public String getInvalidTimeStr() {
        return invalidTimeStr;
    }

    public void setInvalidTimeStr(String invalidTimeStr) {
        this.invalidTimeStr = invalidTimeStr;
    }

    public String getOperateStatusStr() {
        return operateStatusStr;
    }

    public void setOperateStatusStr(String operateStatusStr) {
        this.operateStatusStr = operateStatusStr;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public int getWhereOperateStatus() {
        return whereOperateStatus;
    }

    public void setWhereOperateStatus(int whereOperateStatus) {
        this.whereOperateStatus = whereOperateStatus;
    }

    public int getOperateStatusStart() {
        return operateStatusStart;
    }

    public void setOperateStatusStart(int operateStatusStart) {
        this.operateStatusStart = operateStatusStart;
    }

    public int getOperateStatusEnd() {
        return operateStatusEnd;
    }

    public void setOperateStatusEnd(int operateStatusEnd) {
        this.operateStatusEnd = operateStatusEnd;
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

    public String getLockAccountName() {
        return lockAccountName;
    }

    public void setLockAccountName(String lockAccountName) {
        this.lockAccountName = lockAccountName;
    }

    public String getCheckStatusStr() {
        return checkStatusStr;
    }

    public void setCheckStatusStr(String checkStatusStr) {
        this.checkStatusStr = checkStatusStr;
    }
}
