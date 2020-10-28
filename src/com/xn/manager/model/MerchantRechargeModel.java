package com.xn.manager.model;

import com.xn.common.page.BasePage;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

public class MerchantRechargeModel extends BasePage {
    /**
     * 自增主键ID
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 归属的账号ID：对应表tb_hz_sys_account的主键ID，并且角色类型是卡商
     *
     * @mbggenerated
     */
    private Long accountId;

    /**
     * 银行卡归属卡站点ID：对应表tb_hz_sys_account的主键ID，并且角色是卡站点
     *
     * @mbggenerated
     */
    private Long cardSiteId;

    /**
     * 订单号
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * 订单类型：1预付款订单，2平台发起订单，3下发订单
     *
     * @mbggenerated
     */
    private Integer orderType;

    /**
     * 下发表的订单号：对应表tb_fr_issue的order_no；也可以把它称之为关联订单号
     *
     * @mbggenerated
     */
    private String issueOrderNo;

    /**
     * 订单金额
     *
     * @mbggenerated
     */
    private String orderMoney;

    /**
     * 订单状态：1初始化，2超时/失败/审核驳回，3成功
     *
     * @mbggenerated
     */
    private Integer orderStatus;

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
     * 开户名
     *
     * @mbggenerated
     */
    private String accountName;

    /**
     * 充值记录银行卡转账图片凭证
     *
     * @mbggenerated
     */
    private String pictureAds;

    /**
     * 操作状态：1初始化，2系统放弃，3手动放弃，4锁定
     *
     * @mbggenerated
     */
    private Integer operateStatus;

    /**
     * 是否需要数据同步：1不需要同步，2需要同步
     *
     * @mbggenerated
     */
    private Integer isSynchro;

    /**
     * 审核状态：1初始化，2审核收款失败，3审核收款成功
     *
     * @mbggenerated
     */
    private Integer checkStatus;

    /**
     * 审核失败缘由，审核失败的原因
     *
     * @mbggenerated
     */
    private String checkInfo;

    /**
     * 系统运行自动放弃的时间：订单分配完毕之后，订单类型是：下发分配订单，如果卡商在超过这个时间没有进行放弃或者锁定这样的操作，则自动修改成放弃。
     *
     * @mbggenerated
     */
    private Date invalidTime;

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
     * 运行计算状态：：0初始化，1锁定，2计算失败，3计算成功
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
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

    public Integer getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(Integer operateStatus) {
        this.operateStatus = operateStatus;
    }

    public Integer getIsSynchro() {
        return isSynchro;
    }

    public void setIsSynchro(Integer isSynchro) {
        this.isSynchro = isSynchro;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public Date getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
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

    public Integer getCurday() {
        return curday;
    }

    public void setCurday(Integer curday) {
        this.curday = curday;
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

    public Integer getYn() {
        return yn;
    }

    public void setYn(Integer yn) {
        this.yn = yn;
    }
}